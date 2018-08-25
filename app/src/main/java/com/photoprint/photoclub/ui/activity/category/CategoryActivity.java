package com.photoprint.photoclub.ui.activity.category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.ui.activity.base.ActivityModule;
import com.photoprint.photoclub.ui.activity.base.MvpActivity;
import com.photoprint.photoclub.ui.activity.run.RunActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Экрна для выбора категории
 *
 * @author Grigoriy Pryamov.
 */
public class CategoryActivity extends MvpActivity implements CategoryView {

    private static final Logger logger = LoggerFactory.getLogger(RunActivity.class);

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, CategoryActivity.class);
    }

    //region di
    CategoryScreenComponent screenComponent;
    CategoryComponent component;
    @Inject
    Navigator navigator;
    //endregion
    //region view
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

        presenter.initialize();
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
}
