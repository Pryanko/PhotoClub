package com.photoprint.photoclub.ui.activity.gallery.fragment.image;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.model.LocalImage;
import com.photoprint.photoclub.ui.activity.gallery.fragment.image.adapter.ImageListAdapter;
import com.photoprint.photoclub.ui.activity.gallery.fragment.image.interceptor.LocalImageLoader;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * @author Grigoriy Pryamov.
 */
public class ImageListPresenter extends BaseMvpViewStatePresenter<ImageListView, ImageListViewState> {

    private static final Logger logger = LoggerFactory.getLogger(ImageListPresenter.class);

    private final ImageListAdapter imageListAdapter;
    private final LocalImageLoader localImageLoader;
    private Disposable loadDisposable = Disposables.disposed();

    private List<LocalImage> localImages = new ArrayList<>();

    @Inject
    ImageListPresenter(ImageListViewState viewState,
                       ImageListAdapter imageListAdapter, LocalImageLoader localImageLoader) {
        super(viewState);
        this.imageListAdapter = imageListAdapter;
        this.localImageLoader = localImageLoader;
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
    }

    void applyFolder(String folder) {
        loadDisposable.dispose();
        loadDisposable = localImageLoader.getLocalImagesByFolder(folder)
                .observeOn(AppSchedulers.ui())
                .subscribeOn(AppSchedulers.db())
                .subscribe(result -> {
                    if (result.isSuccessful()) {
                        localImages = result.getLocalImages();
                        imageListAdapter.setImageList(localImages);
                    }
                    //todo - доработать на случай отсутсвия данных
                });
    }

    void hideImageList() {
        imageListAdapter.clearAdapter();
    }

    void onImageClicked(int position) {
        LocalImage localImage = localImages.get(position);
        localImage.setSelectedForPrint(!localImage.isSelectedForPrint());
        imageListAdapter.setImageSelected(position);
    }

    @Override
    public void destroy() {
        loadDisposable.dispose();
        super.destroy();
    }
}
