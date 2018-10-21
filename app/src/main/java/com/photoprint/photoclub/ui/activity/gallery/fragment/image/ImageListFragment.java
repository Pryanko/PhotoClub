package com.photoprint.photoclub.ui.activity.gallery.fragment.image;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.ui.activity.gallery.GalleryActivity;
import com.photoprint.photoclub.ui.activity.gallery.adapter.GridSpaceDecoration;
import com.photoprint.photoclub.ui.activity.gallery.fragment.image.adapter.ImageListAdapterImpl;
import com.photoprint.photoclub.ui.fragment.base.MvpFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Список фотографий - галерея
 *
 * @author Grigoriy Pryamov.
 */
public class ImageListFragment extends MvpFragment implements ImageListView {

    private static final Logger logger = LoggerFactory.getLogger(ImageListFragment.class);

    public static ImageListFragment newIstance() {
        return new ImageListFragment();
    }

    //region DI
    ImageListComponent component;
    @Inject
    ImageListAdapterImpl imageListAdapter;
    //endregion
    //region views
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    //endregion

    private ImageListPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        component = ((GalleryActivity) getActivity()).getComponent().imageComponent();
        component.inject(this);
        super.onCreate(savedInstanceState);
        presenter = getMvpDelegate().getPresenter(component::imageListPresenter, ImageListPresenter.class);
        presenter.initialize();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_list, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new GridSpaceDecoration(3, getResources().getDimensionPixelOffset(R.dimen.offset_folder_item_4dp), true));
        imageListAdapter.setInteractionListener(position -> presenter.onImageClicked(position));
        recyclerView.setAdapter(imageListAdapter);
        return view;
    }

    public void setFolder(String folder) {
        presenter.applyFolder(folder);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView.setAdapter(null);
    }

    public void hideImageList() {
        presenter.hideImageList();
    }
}
