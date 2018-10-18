package com.photoprint.photoclub.ui.activity.gallery.fragment.folder;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class FolderListPresenter extends BaseMvpViewStatePresenter<FolderListView, FolderListViewState> {

    private static final Logger logger = LoggerFactory.getLogger(FolderListPresenter.class);

    @Inject
    FolderListPresenter(FolderListViewState viewState) {
        super(viewState);
    }

    @Override
    protected void onInitialize() {

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
