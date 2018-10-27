package com.photoprint.photoclub.ui.activity.serviceinfo;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.ui.activity.base.ActivityModule;
import com.photoprint.photoclub.ui.activity.base.MvpActivity;
import com.photoprint.photoclub.ui.activity.base.delegate.RtPermissionDelegate;
import com.photoprint.photoclub.ui.activity.delegate.DrawerMenuDelegate;
import com.photoprint.photoclub.ui.activity.delegate.ToolbarDelegate;
import com.photoprint.photoclub.ui.activity.serviceinfo.fragment.maquettelist.MaquetteListFragment;
import com.photoprint.photoclub.ui.activity.serviceinfo.fragment.maquettelist.adapter.MaquetteListAdapterImpl;
import com.photoprint.photoclub.ui.activity.serviceinfo.fragment.serviceinfo.ServiceInfoFragment;
import com.photoprint.photoclub.ui.activity.serviceinfo.model.ServiceInfoParams;

import javax.inject.Inject;

/**
 * Экран с информаций об услуге
 *
 * @author Grigoriy Pryamov.
 */
public class ServiceInfoActivity extends MvpActivity implements ServiceInfoView {

    private static final Logger logger = LoggerFactory.getLogger(ServiceInfoActivity.class);

    //region Fragment tags
    private static String F_TAG_SERVICE_INFO = "F_TAG_SERVICE_INFO";
    private static String F_TAG_MAQUETTE_LIST = "F_TAG_MAQUETTE_LIST";
    //endregion

    //region extra
    private static final String EXTRA_SERVICE_INFO_PARAMS = "EXTRA_SERVICE_INFO_PARAMS";

    public static Intent getCallingIntent(Context context, ServiceInfoParams serviceInfoParams) {
        Intent intent = new Intent(context, ServiceInfoActivity.class);
        intent.putExtra(EXTRA_SERVICE_INFO_PARAMS, serviceInfoParams);
        return intent;
    }

    //region DI
    ServiceInfoScreenComponent screenComponent;
    ServiceInfoComponent component;
    @Inject
    Navigator navigator;
    @Inject
    ToolbarDelegate toolbarDelegate;
    @Inject
    DrawerMenuDelegate drawerMenuDelegate;
    @Inject
    MaquetteListAdapterImpl maquetteListAdapter;
    @Inject
    RtPermissionDelegate rtPermissionDelegate;
    //endregion
    //region FRAGMENTS
    private ServiceInfoFragment serviceInfoFragment;
    private MaquetteListFragment maquetteListFragment;
    //endregion
    private ServiceInfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        screenComponent = getScreenComponent();
        component = screenComponent.serviceInfoComponentBuilder()
                .activityModule(new ActivityModule(this))
                .serviceInfoParams(getIntent().getParcelableExtra(EXTRA_SERVICE_INFO_PARAMS))
                .build();
        component.inject(this);
        super.onCreate(savedInstanceState);
        presenter = getMvpDelegate().getPresenter(component::serviceInfoPresenter, ServiceInfoPresenter.class);
        setContentView(R.layout.activity_service_info);

        toolbarDelegate.init();

        rtPermissionDelegate.setCallback(granted -> presenter.onPermissionRequestFinished(granted));
        rtPermissionDelegate.onCreate(savedInstanceState);
        drawerMenuDelegate.init(getMvpDelegate(), null, true);
        drawerMenuDelegate.setNavigationOnClickListener(() -> {
            onBackPressed();
            return true;
        });

        presenter.initialize();
        setupServiceInfoFragment();
        setupMaquetteListFragment();
        maquetteListAdapter.setInteractionListener(maquette -> presenter.onMaquetteItemClicked(maquette));
    }

    @Override
    protected void onResume() {
        navigator.onResume(this);
        rtPermissionDelegate.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        navigator.onPause();
        rtPermissionDelegate.onPause();
        super.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        rtPermissionDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        rtPermissionDelegate.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        presenter.onBackBtnClicked();
    }

    private void setupServiceInfoFragment() {
        serviceInfoFragment = (ServiceInfoFragment) getSupportFragmentManager().findFragmentByTag(F_TAG_SERVICE_INFO);
        if (serviceInfoFragment == null) {
            serviceInfoFragment = ServiceInfoFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, serviceInfoFragment, F_TAG_SERVICE_INFO)
                    .hide(serviceInfoFragment)
                    .commit();
        }
        serviceInfoFragment.setOnClickSelectMaquetteBtnListener(() -> presenter.onClickSelectMaquetteBtn());
        serviceInfoFragment.setOnClickNextButtonListener(() -> presenter.onNextBtnClicked());
    }

    private void setupMaquetteListFragment() {
        maquetteListFragment = (MaquetteListFragment) getSupportFragmentManager().findFragmentByTag(F_TAG_MAQUETTE_LIST);
        if (maquetteListFragment == null) {
            maquetteListFragment = MaquetteListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, maquetteListFragment, F_TAG_MAQUETTE_LIST)
                    .hide(maquetteListFragment)
                    .commit();
        }
    }

    public ServiceInfoComponent getComponent() {
        return component;
    }

    public ServiceInfoScreenComponent getScreenComponent() {
        Object saved = getLastCustomNonConfigurationInstance();
        if (saved == null) {
            return appComponent().serviceInfoScreenComponent();
        } else {
            return (ServiceInfoScreenComponent) saved;
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return screenComponent;
    }

    @Override
    public void setMaquetteFragmentVisible(boolean fragmentVisible) {
        if (getSupportFragmentManager() != null) {
            getSupportFragmentManager().beginTransaction()
                    .hide(fragmentVisible ? serviceInfoFragment : maquetteListFragment)
                    .show(fragmentVisible ? maquetteListFragment : serviceInfoFragment)
                    .commit();
        }
    }

    @Override
    public void showMaquetteList() {
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .hide(serviceInfoFragment)
                .show(maquetteListFragment)
                .commitAllowingStateLoss();
    }

    @Override
    public void hideMaquetteList() {
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .hide(maquetteListFragment)
                .show(serviceInfoFragment)
                .commitAllowingStateLoss();
    }

    @Override
    public void setMaquetteName(String name) {
        serviceInfoFragment.setMaquetteName(name);
    }

    @Override
    public void setLoading(boolean loading) {
        logger.trace("Loading " + String.valueOf(loading));
    }

    @Override
    public void showDialogForPermissions() {
        rtPermissionDelegate.checkStoragePermissions();
    }
}
