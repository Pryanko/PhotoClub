package com.photoprint.photoclub.ui.activity.servicesettings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.ui.activity.base.ActivityModule;
import com.photoprint.photoclub.ui.activity.base.MvpActivity;
import com.photoprint.photoclub.ui.activity.delegate.DrawerMenuDelegate;
import com.photoprint.photoclub.ui.activity.delegate.ToolbarDelegate;
import com.photoprint.photoclub.ui.activity.servicesettings.model.ServiceSettingsParams;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
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
    @BindView(R.id.imageView)
    SimpleDraweeView imageView;
    @BindView(R.id.serviceName)
    TextView serviceName;
    @BindView(R.id.servicePrice)
    TextView servicePrice;
    @BindView(R.id.formatSpinner)
    AppCompatSpinner formatSpinner;
    @BindView(R.id.typeSpinner)
    AppCompatSpinner typeSpinner;
    @BindView(R.id.optionSwitch)
    Switch optionSwitch;
    @BindView(R.id.nextButton)
    Button nextButton;
    //region views

    //endregion
    private ArrayAdapter<String> spinnerAdapter;
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

        formatSpinner.setOnItemSelectedListener(formatSelectedListener);
        typeSpinner.setOnItemSelectedListener(typeSelectedListener);
        optionSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> presenter.onOptionSwitchClicked(isChecked));
        nextButton.setOnClickListener(v -> presenter.onNextButtonClicked());

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

    @Override
    public void setImage(String image) {
        imageView.setImageURI(image);
    }

    @Override
    public void setServiceName(String serviceName) {
        this.serviceName.setText(serviceName);
    }

    @Override
    public void setServicePrice(String price) {
        this.servicePrice.setText(price);
    }

    @Override
    public void setServiceNameList(@NonNull List<String> serviceNameList) {
        createSpinnerAdapter(serviceNameList, formatSpinner);
    }

    @Override
    public void setServiceTypeList(@NonNull List<String> serviceTypeList) {
        createSpinnerAdapter(serviceTypeList, typeSpinner);
    }

    private void createSpinnerAdapter(List<String> itemsList, AppCompatSpinner spinner) {
        spinnerAdapter = new ArrayAdapter<>(this, R.layout.service_spinner_item);
        spinnerAdapter.addAll(itemsList);
        spinner.setAdapter(spinnerAdapter);
    }

    private final AdapterView.OnItemSelectedListener formatSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            presenter.onFormatItemClicked(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private final AdapterView.OnItemSelectedListener typeSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            presenter.onTypeItemClicked(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}
