package com.photoprint.photoclub.ui.activity.gallery.fragment.image.imagesettingcard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.ui.activity.gallery.fragment.image.ImageListComponent;
import com.photoprint.photoclub.ui.mvp.MvpDelegate;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Карточка для настроек изображения (фотографии) для печати
 *
 * @author Grigoriy Pryamov.
 */
public class ImageSettingCard extends FrameLayout implements ImageSettingCardView {

    private static final Logger logger = LoggerFactory.getLogger(ImageSettingCard.class);

    //region DI
    private ImageSettingCardComponent component;
    //endregion
    //region MVP
    private MvpDelegate mvpDelegate;
    private ImageSettingCardPresenter presenter;
    //endregion
    //region VIEWS
    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.nextButton)
    Button nextButton;
    @BindView(R.id.addCount)
    ImageButton addCount;
    @BindView(R.id.cutCount)
    ImageButton cutCount;
    @BindView(R.id.crop)
    ImageButton crop;
    @BindViews({R.id.count, R.id.nextButton, R.id.addCount, R.id.cutCount, R.id.crop})
    List<View> allViews;
    //endregion

    private OnCropBtnClickListener onCropBtnClickListener;

    public ImageSettingCard(@NonNull Context context) {
        this(context, null);
    }

    public ImageSettingCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageSettingCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(getContext(), R.layout.view_image_setting_card, this);

        if (isInEditMode()) {
            return;
        }
        ButterKnife.bind(this);
        nextButton.setOnClickListener(v -> presenter.onNextBtnClicked());
        addCount.setOnClickListener(v -> presenter.onAddCountBtnClicked());
        cutCount.setOnClickListener(v -> presenter.onCutCountBtnClicked());
        crop.setOnClickListener(v -> {
            if (onCropBtnClickListener != null) {
                onCropBtnClickListener.onCropBtnClicked();
            }
        });
    }

    public void init(ImageListComponent imageListComponent, MvpDelegate parent, String id) {
        component = imageListComponent.imageSettingCardComponent();
        component.inject(this);
        mvpDelegate = new MvpDelegate(component.mvpProcessor(), this);
        mvpDelegate.init(parent, id);
        presenter = mvpDelegate.getPresenter(component::imageSettingCardPresenter, ImageSettingCardPresenter.class);
        presenter.initialize();
    }

    public void setOnCropBtnClickListener(OnCropBtnClickListener onCropBtnClickListener) {
        this.onCropBtnClickListener = onCropBtnClickListener;
    }

    public void setParams(@Nullable Long imageId) {
        logger.trace(String.valueOf(imageId));
        applyImage(imageId);
    }

    private void applyImage(@Nullable Long imageId) {
        presenter.applyImage(imageId);
    }

    @Override
    public void setCardEnabled(boolean enabled) {
        for (View view : allViews) {
            view.setEnabled(enabled);
        }
    }

    public interface OnCropBtnClickListener {
        void onCropBtnClicked();
    }
}
