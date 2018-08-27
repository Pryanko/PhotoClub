package com.photoprint.photoclub.ui.activity.category;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.model.Category;
import com.photoprint.photoclub.ui.activity.category.adapter.CategoryListAdapter;
import com.photoprint.photoclub.ui.activity.category.interactor.CategoryLoader;
import com.photoprint.photoclub.ui.activity.service.model.ServiceParams;
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

    private final CategoryLoader categoryLoader;
    private final CategoryListAdapter categoryListAdapter;
    private final Navigator navigator;
    private Disposable loadDisposable = Disposables.disposed();

    @Inject
    CategoryPresenter(CategoryViewState viewState,
                      CategoryLoader categoryLoader,
                      CategoryListAdapter categoryListAdapter, Navigator navigator) {
        super(viewState);
        this.categoryLoader = categoryLoader;
        this.categoryListAdapter = categoryListAdapter;
        this.navigator = navigator;
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
        ServiceParams serviceParams = new ServiceParams();
        serviceParams.setCategoryId(category.getId());
        navigator.navigateToServiceActivity(serviceParams);
    }
}
