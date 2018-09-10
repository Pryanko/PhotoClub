package com.photoprint.photoclub.ui.activity.serviceinfo;

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
import com.photoprint.photoclub.ui.activity.serviceinfo.model.ServiceInfoParams;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Экран с информаций об услуге
 *
 * @author Grigoriy Pryamov.
 */
public class ServiceInfoActivity extends MvpActivity implements ServiceInfoView {

    private static final Logger logger = LoggerFactory.getLogger(ServiceInfoActivity.class);

    private static final String EXTRA_SERVICE_INFO_PARAMS = "EXTRA_SERVICE_INFO_PARAMS";

    //region extra
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
        ButterKnife.bind(this);

        toolbarDelegate.init();
        drawerMenuDelegate.init(getMvpDelegate(), null, true);
        drawerMenuDelegate.setNavigationOnClickListener(() -> {
            onBackPressed();
            return true;
        });
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
}
