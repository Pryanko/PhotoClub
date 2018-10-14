package com.photoprint.photoclub.ui.activity.category;

import com.photoprint.utils.Preconditions;
import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.helper.transition.ScreenSeparator;
import com.photoprint.photoclub.helper.transition.TypeTransition;
import com.photoprint.photoclub.model.Category;
import com.photoprint.photoclub.ui.activity.category.adapter.CategoryListAdapter;
import com.photoprint.photoclub.ui.activity.category.interactor.CategoryLoader;
import com.photoprint.photoclub.ui.activity.service.model.ServiceParams;
import com.photoprint.photoclub.ui.activity.servicesettings.model.ServiceSettingsParams;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * @author Grigoriy Pryamov.
 */
public class CategoryPresenter extends BaseMvpViewStatePresenter<CategoryView, CategoryViewState> {

    private static final Logger logger = LoggerFactory.getLogger(CategoryPresenter.class);

    private List<Category> categories = Collections.emptyList();

    private final Navigator navigator;
    private final ScreenSeparator separator;
    private final CategoryLoader categoryLoader;
    private final CategoryListAdapter categoryListAdapter;
    private Disposable loadDisposable = Disposables.disposed();

    @Inject
    CategoryPresenter(CategoryViewState viewState,
                      CategoryLoader categoryLoader,
                      CategoryListAdapter categoryListAdapter,
                      Navigator navigator, ScreenSeparator separator) {
        super(viewState);
        this.categoryLoader = categoryLoader;
        this.categoryListAdapter = categoryListAdapter;
        this.navigator = navigator;
        this.separator = separator;
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
        loadDisposable = categoryLoader.getCategories()
                .subscribeOn(AppSchedulers.db())
                .observeOn(AppSchedulers.ui())
                .subscribe(result -> {
                    if (result.isSuccessful()) {
                        this.categories = result.getCategories();
                        categoryListAdapter.setCategories(this.categories);
                    } else {
                        //Обработка пустого списка
                    }
                }, logger::error);
    }

    @Override
    public void destroy() {
        loadDisposable.dispose();
        super.destroy();
    }

    public void onCategoryClicked(int position) {
        logger.trace("onCategoryClicked - position:" + position);
        Category category = categories.get(position);
        Preconditions.checkNotNull(category);
        navigateToNextScreen(separator.getTransitionForCategory(category.getType()), category);
    }

    private void navigateToNextScreen(TypeTransition typeTransition, Category category) {
        switch (typeTransition) {
            case SERVICE: {
                ServiceParams serviceParams = new ServiceParams();
                serviceParams.setCategoryId(category.getId());
                navigator.navigateToServiceActivity(serviceParams);
                break;
            }
            case SETTINGS: {
                ServiceSettingsParams serviceSettingsParams = new ServiceSettingsParams();
                serviceSettingsParams.setCategoryId(category.getId());
                navigator.navigateToServiceSettingsActivity(serviceSettingsParams);
                break;
            }
        }
    }
}
