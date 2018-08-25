package com.photoprint.photoclub.ui.activity.category;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class CategoryPresenter extends BaseMvpViewStatePresenter<CategoryView, CategoryViewState> {

    private static final Logger logger = LoggerFactory.getLogger(CategoryPresenter.class);

    @Inject
    CategoryPresenter(CategoryViewState viewState) {
        super(viewState);
    }


    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
