package com.photoprint.photoclub.ui.activity.servicesettings;

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
import com.photoprint.photoclub.ui.activity.servicesettings.model.ServiceSettingsParams;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Экран настройки для услуг
 *
 * @author Grigoriy Pryamov.
 */
public class ServiceSettingsActivity extends MvpActivity implements ServiceSettingsView {

    private static final Logger logger = LoggerFactory.getLogger(ServiceSettingsActivity.class);

    //region extras
    private static final String EXTRA_SERVICE_SETTINGS_PARAMS = "EXTRA_SERVICE_SETTINGS_PARAMS";

    public static Intent getCallingIntent(Context context, ServiceSettingsParams serviceSettingsParams) {
        Intent intent = new Intent(context, ServiceSettingsActivity.class);
        intent.putExtra(EXTRA_SERVICE_SETTINGS_PARAMS, serviceSettingsParams);
        return intent;
    }

    //region di
    ServiceSettingsScreenComponent screenComponent;
    ServiceSettingsComponent component;
    @Inject
    Navigator navigator;
    @Inject
    ToolbarDelegate toolbarDelegate;
    @Inject
    DrawerMenuDelegate drawerMenuDelegate;
    //endregion
    //region views

    //endregion
    private ServiceSettingsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        screenComponent = getScreenComponent();
        component = screenComponent.serviceSettingsComponentBuilder()
                .activityModule(new ActivityModule(this))
                .serviceSettingsParams(getIntent().getParcelableExtra(EXTRA_SERVICE_SETTINGS_PARAMS))
                .build();
        component.inject(this);
        super.onCreate(savedInstanceState);
        presenter = getMvpDelegate().getPresenter(component::serviceSettingsPresenter, ServiceSettingsPresenter.class);
        setContentView(R.layout.activity_service_settings);
        ButterKnife.bind(this);

        toolbarDelegate.init();
        drawerMenuDelegate.init(getMvpDelegate(), null, true);
        drawerMenuDelegate.setNavigationOnClickListener(() -> {
            onBackPressed();
            return true;
        });

        presenter.initialize();
    }

    public ServiceSettingsScreenComponent getScreenComponent() {
        Object saved = getLastCustomNonConfigurationInstance();
        if (saved == null) {
            return appComponent().ServiceSettingsScreenComponent();
        } else {
            return (ServiceSettingsScreenComponent) saved;
        }
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

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return screenComponent;
    }
}
