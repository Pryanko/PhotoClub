package com.photoprint.photoclub.ui.activity.serviceinfo.fragment.maquettelist;

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
import com.photoprint.photoclub.ui.activity.serviceinfo.ServiceInfoActivity;
import com.photoprint.photoclub.ui.activity.serviceinfo.fragment.maquettelist.adapter.MaquetteListAdapterImpl;
import com.photoprint.photoclub.ui.adapter.ItemDecoration;
import com.photoprint.photoclub.ui.fragment.base.MvpFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Grigoriy Pryamov.
 */
public class MaquetteListFragment extends MvpFragment implements MaquetteListView {

    private static final Logger logger = LoggerFactory.getLogger(MaquetteListFragment.class);

    public static MaquetteListFragment newInstance() {
        return new MaquetteListFragment();
    }

    //region Di
    MaquetteListComponent component;
    @Inject
    MaquetteListAdapterImpl maquetteListAdapter;
    //endregion

    //region views
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private MaquetteListPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        component = ((ServiceInfoActivity) getActivity()).getComponent().maquetteListComponent();
        component.inject(this);
        super.onCreate(savedInstanceState);
        presenter = getMvpDelegate().getPresenter(component::maquetteListPresenter, MaquetteListPresenter.class);
        presenter.initialize();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maquette_list, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new ItemDecoration(
                getResources().getDimensionPixelOffset(R.dimen.offset_category_item_sides),
                getResources().getDimensionPixelOffset(R.dimen.offset_category_item_sides),
                getResources().getDimensionPixelOffset(R.dimen.offset_category_item_top),
                getResources().getDimensionPixelOffset(R.dimen.offset_category_item_bottom)));
        recyclerView.setAdapter(maquetteListAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView.setAdapter(null);
    }
}
