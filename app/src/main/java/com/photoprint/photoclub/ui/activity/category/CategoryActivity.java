package com.photoprint.photoclub.ui.activity.category;

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
import com.photoprint.photoclub.ui.activity.category.adapter.CategoryListAdapterImpl;
import com.photoprint.photoclub.ui.adapter.ItemDecoration;
import com.photoprint.photoclub.ui.activity.delegate.ToolbarDelegate;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Экрна для выбора категории
 *
 * @author Grigoriy Pryamov.
 */
public class CategoryActivity extends MvpActivity implements CategoryView {

    private static final Logger logger = LoggerFactory.getLogger(CategoryActivity.class);

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, CategoryActivity.class);
    }

    //region di
    CategoryScreenComponent screenComponent;
    CategoryComponent component;
    @Inject
    Navigator navigator;
    @Inject
    ToolbarDelegate toolbarDelegate;
    @Inject
    CategoryListAdapterImpl categoryListAdapter;
    //endregion
    //region view
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    //endregion
    private CategoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        screenComponent = getScreenComponent();
        component = screenComponent.categoryComponentBuilder()
                .activityModule(new ActivityModule(this))
                .build();
        component.inject(this);
        super.onCreate(savedInstanceState);
        presenter = getMvpDelegate().getPresenter(component::categoryPresenter, CategoryPresenter.class);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        toolbarDelegate.init();
        toolbarDelegate.setNavigationIcon(R.drawable.ic_menu);
        toolbarDelegate.setTitle(R.string.category_title);
        toolbarDelegate.setNavigationOnClickListener(v -> logger.trace("Menu item Clicked"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new ItemDecoration(
                getResources().getDimensionPixelOffset(R.dimen.offset_category_item_sides),
                getResources().getDimensionPixelOffset(R.dimen.offset_category_item_sides),
                getResources().getDimensionPixelOffset(R.dimen.offset_category_item_top),
                getResources().getDimensionPixelOffset(R.dimen.offset_category_item_bottom)));
        recyclerView.setAdapter(categoryListAdapter);

        categoryListAdapter.setInteractionListener(position -> presenter.onCategoryClicked(position));

        presenter.initialize();
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

    private CategoryScreenComponent getScreenComponent() {
        Object saved = getLastCustomNonConfigurationInstance();
        if (saved == null) {
            return appComponent().categoryScreenComponent();
        } else {
            return (CategoryScreenComponent) saved;
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return screenComponent;
    }

    @Override
    public void onDestroy() {
        recyclerView.setAdapter(null);
        super.onDestroy();
    }
}
