package com.photoprint.photoclub.ui.activity.serviceinfo.fragment.serviceinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.ui.activity.serviceinfo.ServiceInfoActivity;
import com.photoprint.photoclub.ui.fragment.base.MvpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Grigoriy Pryamov.
 */
public class ServiceInfoFragment extends MvpFragment implements ServiceInfoFragmentView {

    private static final Logger logger = LoggerFactory.getLogger(ServiceInfoFragment.class);

    public static ServiceInfoFragment newInstance() {
        return new ServiceInfoFragment();
    }

    //region di
    ServiceInfoFragmentComponent component;
    //endregion

    //region Views
    @BindView(R.id.imageView)
    SimpleDraweeView imageView;
    @BindView(R.id.valueMaquette)
    TextView valueMaquette;
    @BindView(R.id.addMaquetteBtn)
    ImageButton addMaquetteBtn;
    //endregion

    private OnClickSelectMaquetteBtnListener onClickSelectMaquetteBtnListener;
    private ServiceInfoFragmentPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        component = ((ServiceInfoActivity) getActivity()).getComponent().serviceInfoFragmentComponent();
        component.inject(this);
        super.onCreate(savedInstanceState);
        presenter = getMvpDelegate().getPresenter(component::serviceInfoFragmentPresenter, ServiceInfoFragmentPresenter.class);
        presenter.initialize();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_info, container, false);
        ButterKnife.bind(this, view);
        addMaquetteBtn.setOnClickListener(v -> {
            if (onClickSelectMaquetteBtnListener != null) {
                onClickSelectMaquetteBtnListener.onClickSelectMaquetteBtn();
            }
        });
        return view;
    }

    public void setOnClickSelectMaquetteBtnListener(OnClickSelectMaquetteBtnListener onClickSelectMaquetteBtnListener) {
        this.onClickSelectMaquetteBtnListener = onClickSelectMaquetteBtnListener;
    }

    /**
     * Взаимодействие
     */
    public interface OnClickSelectMaquetteBtnListener {

        void onClickSelectMaquetteBtn();
    }
}

