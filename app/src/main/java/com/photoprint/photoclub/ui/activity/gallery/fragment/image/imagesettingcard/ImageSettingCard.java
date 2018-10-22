package com.photoprint.photoclub.ui.activity.gallery.fragment.image.imagesettingcard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.ui.activity.gallery.fragment.image.ImageListComponent;
import com.photoprint.photoclub.ui.mvp.MvpDelegate;

import butterknife.BindView;
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

    @BindView(R.id.nextButton)
    Button button;

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
    }

    public void init(ImageListComponent imageListComponent, MvpDelegate parent, String id) {
        component = imageListComponent.imageSettingCardComponent();
        component.inject(this);
        mvpDelegate = new MvpDelegate(component.mvpProcessor(), this);
        mvpDelegate.init(parent, id);
        presenter = mvpDelegate.getPresenter(component::imageSettingCardPresenter, ImageSettingCardPresenter.class);
        presenter.initialize();
    }
}
