package com.photoprint.photoclub.ui.activity.gallery;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class GalleryPresenter extends BaseMvpViewStatePresenter<GalleryView, GalleryViewState> {

    private static final Logger logger = LoggerFactory.getLogger(GalleryPresenter.class);

    private final Navigator navigator;

    /**
     * Флажок открыт ли фрагмент с выбором фотографий
     */
    private boolean imageListVisible;

    @Inject
    public GalleryPresenter(GalleryViewState viewState, Navigator navigator) {
        super(viewState);
        this.navigator = navigator;
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
        view.setImageListVisible(imageListVisible);
    }

    void onBackBtnClicked() {
        if (imageListVisible) {
            imageListVisible = false;
            view.setImageListVisible(imageListVisible);
        } else {
            navigator.navigateBack();
        }
    }

    void onFolderClicked(String nameFolder) {
        logger.trace(nameFolder);
        imageListVisible = true;
        view.setImageListVisible(imageListVisible);
    }
}
