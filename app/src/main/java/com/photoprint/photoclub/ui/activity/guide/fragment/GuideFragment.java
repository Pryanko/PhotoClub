package com.photoprint.photoclub.ui.activity.guide.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.photoprint.utils.Preconditions;
import com.facebook.drawee.view.SimpleDraweeView;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.model.Guide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Grigoriy Pryamov.
 */
public class GuideFragment extends Fragment {

    public static final String EXTRA_GUIDE = "GUIDE";

    @BindView(R.id.descriptionText)
    TextView descriptionText;
    @BindView(R.id.imageView)
    SimpleDraweeView imageView;

    private Guide guide;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Preconditions.checkNotNull(getArguments());
        this.guide = getArguments().getParcelable(EXTRA_GUIDE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        ButterKnife.bind(this, view);
        bindViews();
        return view;
    }

    private void bindViews() {
        descriptionText.setText(guide.getText());
        imageView.setImageURI(guide.getImage480());
    }
}
