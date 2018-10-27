package com.photoprint.photoclub.ui.activity.gallery;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.ui.activity.base.ActivityModule;
import com.photoprint.photoclub.ui.activity.base.MvpActivity;
import com.photoprint.photoclub.ui.activity.delegate.ToolbarDelegate;
import com.photoprint.photoclub.ui.activity.gallery.fragment.folder.FolderListFragment;
import com.photoprint.photoclub.ui.activity.gallery.fragment.folder.adapter.FolderListAdapterImpl;
import com.photoprint.photoclub.ui.activity.gallery.fragment.image.ImageListFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Галлерея
 *
 * @author Grigoriy Pryamov.
 */
public class GalleryActivity extends MvpActivity implements GalleryView {

    private static final Logger logger = LoggerFactory.getLogger(GalleryActivity.class);

    //region Fragment tags
    private static String F_TAG_FOLDER_LIST = "F_TAG_FOLDER_LIST";
    private static String F_TAG_IMAGE_LIST = "F_TAG_IMAGE_LIST";
    //endregion

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, GalleryActivity.class);
    }

    //region DI
    GalleryScreenComponent screenComponent;
    GalleryComponent component;
    @Inject
    Navigator navigator;
    @Inject
    ToolbarDelegate toolbarDelegate;
    @Inject
    FolderListAdapterImpl folderListAdapter;
    //endregion
    //region FRAGMENTS
    private FolderListFragment folderListFragment;
    private ImageListFragment imageListFragment;
    //endregion

    private GalleryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        screenComponent = getScreenComponent();
        component = screenComponent.galleryComponentBuilder()
                .activityModule(new ActivityModule(this))
                .build();
        component.inject(this);
        super.onCreate(savedInstanceState);
        presenter = getMvpDelegate().getPresenter(component::galleryPresenter, GalleryPresenter.class);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);

        toolbarDelegate.init();
        toolbarDelegate.setNavigationIcon(R.drawable.ic_arrow_left);
        toolbarDelegate.setTitle(R.string.gallery_title);
        toolbarDelegate.setNavigationOnClickListener(v -> onBackPressed());

        folderListAdapter.setInteractionListener(folderName -> presenter.onFolderClicked(folderName));

        presenter.initialize();
        setupFolderListFragment();
        setupImageListFragment();
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

    public GalleryComponent getComponent() {
        return component;
    }

    private GalleryScreenComponent getScreenComponent() {
        Object saved = getLastCustomNonConfigurationInstance();
        if (saved == null) {
            return appComponent().galleryScreenComponent();
        } else {
            return (GalleryScreenComponent) saved;
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return screenComponent;
    }

    private void setupFolderListFragment() {
        folderListFragment = (FolderListFragment) getSupportFragmentManager().findFragmentByTag(F_TAG_FOLDER_LIST);
        if (folderListFragment == null) {
            folderListFragment = FolderListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, folderListFragment, F_TAG_FOLDER_LIST)
                    .commit();
        }
    }

    private void setupImageListFragment() {
        imageListFragment = (ImageListFragment) getSupportFragmentManager().findFragmentByTag(F_TAG_IMAGE_LIST);
        if (imageListFragment == null) {
            imageListFragment = ImageListFragment.newInstance();
            imageListFragment.setOnCropBtnClickListener(this::onCropBtnClicked);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, imageListFragment, F_TAG_IMAGE_LIST)
                    .hide(imageListFragment)
                    .commit();
        }
    }

    @Override
    public void showImageList(String nameFolder) {
        if (getSupportFragmentManager() != null) {
            getSupportFragmentManager().beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .hide(folderListFragment)
                    .show(imageListFragment)
                    .commitAllowingStateLoss();
            imageListFragment.setFolder(nameFolder);
        }
    }

    @Override
    public void hideImageList() {
        if (getSupportFragmentManager() != null) {
            getSupportFragmentManager().beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    .hide(imageListFragment)
                    .show(folderListFragment)
                    .commitAllowingStateLoss();
            imageListFragment.hideImageList();
        }
    }

    private void onCropBtnClicked() {
    }
}
