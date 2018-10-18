package com.photoprint.photoclub.ui.activity.gallery.fragment.folder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.ui.activity.gallery.GalleryActivity;
import com.photoprint.photoclub.ui.adapter.ItemDecoration;
import com.photoprint.photoclub.ui.fragment.base.MvpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Grigoriy Pryamov.
 */
public class FolderListFragment extends MvpFragment implements FolderListView {

    private static final Logger logger = LoggerFactory.getLogger(FolderListFragment.class);

    public static FolderListFragment newIstance() {
        return new FolderListFragment();
    }

    //region DI
    FolderListComponent component;
    //endregion
    //region views
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    //endregion

    private FolderListPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        component = ((GalleryActivity) getActivity()).getComponent().folderComponent();
        component.inject(this);
        super.onCreate(savedInstanceState);
        presenter = getMvpDelegate().getPresenter(component::folderListPresenter, FolderListPresenter.class);
        presenter.initialize();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_folder_list, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new ItemDecoration(
                getResources().getDimensionPixelOffset(R.dimen.offset_category_item_sides_8dp),
                getResources().getDimensionPixelOffset(R.dimen.offset_category_item_sides_8dp),
                getResources().getDimensionPixelOffset(R.dimen.offset_category_item_top),
                getResources().getDimensionPixelOffset(R.dimen.offset_category_item_bottom)));
//        recyclerView.setAdapter();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView.setAdapter(null);
    }
}
