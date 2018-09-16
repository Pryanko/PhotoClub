package com.photoprint.photoclub.ui.activity.serviceinfo;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.ui.activity.base.ActivityModule;
import com.photoprint.photoclub.ui.activity.base.MvpActivity;
import com.photoprint.photoclub.ui.activity.delegate.DrawerMenuDelegate;
import com.photoprint.photoclub.ui.activity.delegate.ToolbarDelegate;
import com.photoprint.photoclub.ui.activity.serviceinfo.fragment.maquettelist.MaquetteListFragment;
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
    private static String F_TAG_MAQUETTE_LIST = "F_TAG_REMAINING_STOPS";
    //endregion

    //region extra
    private static final String EXTRA_SERVICE_INFO_PARAMS = "EXTRA_SERVICE_INFO_PARAMS";

    public static Intent getCallingIntent(Context context, ServiceInfoParams serviceInfoParams) {
        Intent intent = new Intent(context, ServiceInfoActivity.class);
        intent.putExtra(EXTRA_SERVICE_INFO_PARAMS, serviceInfoParams);
        return intent;
    }

    //region di
    ServiceInfoScreenComponent screenComponent;
    ServiceInfoComponent component;
    @Inject
    Navigator navigator;
    @Inject
    ToolbarDelegate toolbarDelegate;
    @Inject
    DrawerMenuDelegate drawerMenuDelegate;
    //endregion
    //region views
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
        drawerMenuDelegate.init(getMvpDelegate(), null, true);
        drawerMenuDelegate.setNavigationOnClickListener(() -> {
            onBackPressed();
            return true;
        });

        presenter.initialize();
        setupServiceInfoFragment();
        setupMaquetteListFragment();
    }

    @Override
    protected void onResume() {
        navigator.onResume(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        navigator.onPause();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        presenter.onBackBtnClicked();
    }

    private void setupServiceInfoFragment() {
        serviceInfoFragment = (ServiceInfoFragment) getFragmentManager().findFragmentByTag(F_TAG_SERVICE_INFO);
        if (serviceInfoFragment == null) {
            serviceInfoFragment = ServiceInfoFragment.newInstance();
            getFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, serviceInfoFragment, F_TAG_SERVICE_INFO)
                    .hide(serviceInfoFragment)
                    .commit();
        }
        serviceInfoFragment.setOnClickSelectMaquetteBtnListener(() -> presenter.onClickSelectMaquetteBtn());
    }

    private void setupMaquetteListFragment() {
        maquetteListFragment = (MaquetteListFragment) getFragmentManager().findFragmentByTag(F_TAG_MAQUETTE_LIST);
        if (maquetteListFragment == null) {
            maquetteListFragment = MaquetteListFragment.newInstance();
            getFragmentManager().beginTransaction()
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
            return appComponent().ServiceInfoScreenComponent();
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
        if (getFragmentManager() != null) {
            getFragmentManager().beginTransaction()
                    .hide(fragmentVisible ? serviceInfoFragment : maquetteListFragment)
                    .show(fragmentVisible ? maquetteListFragment : serviceInfoFragment)
                    .commit();
        }
    }

    @Override
    public void showMaquetteList() {
        getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .hide(serviceInfoFragment)
                .show(maquetteListFragment)
                .commitAllowingStateLoss();
    }

    @Override
    public void hideMaquetteList() {
        getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .hide(maquetteListFragment)
                .show(serviceInfoFragment)
                .commitAllowingStateLoss();
    }
}
