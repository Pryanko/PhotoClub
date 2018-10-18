package com.photoprint.photoclub.ui.activity.gallery;

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
        folderListFragment = (FolderListFragment) getFragmentManager().findFragmentByTag(F_TAG_FOLDER_LIST);
        if (folderListFragment == null) {
            folderListFragment = FolderListFragment.newIstance();
            getFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, folderListFragment, F_TAG_FOLDER_LIST)
//                    .hide(folderListFragment)
                    .commit();
        }
    }

}
