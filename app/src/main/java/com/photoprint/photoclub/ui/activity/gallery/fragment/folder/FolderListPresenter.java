package com.photoprint.photoclub.ui.activity.gallery.fragment.folder;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.ui.activity.gallery.fragment.folder.adapter.FolderListAdapter;
import com.photoprint.photoclub.ui.activity.gallery.fragment.folder.interactor.FolderImageLoader;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * @author Grigoriy Pryamov.
 */
public class FolderListPresenter extends BaseMvpViewStatePresenter<FolderListView, FolderListViewState> {

    private static final Logger logger = LoggerFactory.getLogger(FolderListPresenter.class);

    private final FolderImageLoader folderImageLoader;
    private final FolderListAdapter folderListAdapter;
    private Disposable loadDisposable = Disposables.disposed();

    @Inject
    FolderListPresenter(FolderListViewState viewState,
                        FolderImageLoader folderImageLoader,
                        FolderListAdapter folderListAdapter) {
        super(viewState);
        this.folderImageLoader = folderImageLoader;
        this.folderListAdapter = folderListAdapter;
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
        loadDisposable = folderImageLoader.getFolders()
                .observeOn(AppSchedulers.ui())
                .subscribeOn(AppSchedulers.db())
                .subscribe(result -> {
                    if (result.isSuccessful()) {
                        folderListAdapter.setFolders(result.getFolders());
                    }
                    //todo - доработать на случай отсутсвия данных
                });
    }

    @Override
    public void destroy() {
        loadDisposable.dispose();
        super.destroy();
    }
}
