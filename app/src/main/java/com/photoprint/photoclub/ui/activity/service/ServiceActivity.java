package com.photoprint.photoclub.ui.activity.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.ui.activity.base.ActivityModule;
import com.photoprint.photoclub.ui.activity.base.MvpActivity;
import com.photoprint.photoclub.ui.activity.category.CategoryActivity;
import com.photoprint.photoclub.ui.activity.delegate.ToolbarDelegate;
import com.photoprint.photoclub.ui.activity.service.adapter.ServiceListAdapterImpl;
import com.photoprint.photoclub.ui.activity.service.model.ServiceParams;
import com.photoprint.photoclub.ui.adapter.ItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Grigoriy Pryamov.
 */
public class ServiceActivity extends MvpActivity implements ServiceView {

    private static final Logger logger = LoggerFactory.getLogger(CategoryActivity.class);

    //region extras
    private static final String EXTRA_SERVICE_PARAMS = "EXTRA_SERVICE_PARAMS";

    public static Intent getCallingIntent(Context context, ServiceParams serviceParams) {
        Intent intent = new Intent(context, ServiceActivity.class);
        intent.putExtra(EXTRA_SERVICE_PARAMS, serviceParams);
        return intent;
    }

    //region di
    ServiceScreenComponent screenComponent;
    ServiceComponent component;
    @Inject
    Navigator navigator;
    @Inject
    ToolbarDelegate toolbarDelegate;
    @Inject
    ServiceListAdapterImpl serviceListAdapter;
    //endregion
    //region views
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    //endregion
    private ServicePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        screenComponent = getScreenComponent();
        component = screenComponent.serviceComponentBuilder()
                .activityModule(new ActivityModule(this))
                .serviceParams(getIntent().getParcelableExtra(EXTRA_SERVICE_PARAMS))
                .build();
        component.inject(this);
        super.onCreate(savedInstanceState);
        presenter = getMvpDelegate().getPresenter(component::servicePresenter, ServicePresenter.class);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);

        toolbarDelegate.init();
        toolbarDelegate.setNavigationIcon(R.drawable.ic_arrow_left);
        toolbarDelegate.setNavigationOnClickListener(v -> onBackPressed());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new ItemDecoration(
                getResources().getDimensionPixelOffset(R.dimen.offset_category_item_sides),
                getResources().getDimensionPixelOffset(R.dimen.offset_category_item_sides),
                getResources().getDimensionPixelOffset(R.dimen.offset_category_item_top),
                getResources().getDimensionPixelOffset(R.dimen.offset_category_item_bottom)));
        recyclerView.setAdapter(serviceListAdapter);
        serviceListAdapter.setInteractionListener(position -> presenter.onServiceClicked(position));

        presenter.initialize();
    }

    @Override
    public void onBackPressed() {
        presenter.onBackBtnClicked();
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

    public ServiceScreenComponent getScreenComponent() {
        Object saved = getLastCustomNonConfigurationInstance();
        if (saved == null) {
            return appComponent().serviceScreenComponent();
        } else {
            return (ServiceScreenComponent) saved;
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return screenComponent;
    }
}
