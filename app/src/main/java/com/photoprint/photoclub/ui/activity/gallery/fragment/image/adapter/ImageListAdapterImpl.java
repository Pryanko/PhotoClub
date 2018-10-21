package com.photoprint.photoclub.ui.activity.gallery.fragment.image.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.di.ScreenScope;
import com.photoprint.photoclub.model.LocalImage;
import com.photoprint.photoclub.ui.adapter.base.BaseItemsRecyclerAdapter;
import com.photoprint.photoclub.ui.widget.ImageCheckBox;
import com.photoprint.utils.ListUtils;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
public class ImageListAdapterImpl
        extends BaseItemsRecyclerAdapter<LocalImage, ImageListAdapterImpl.LocalImageHolder>
        implements ImageListAdapter {
    /**
     * Процентная часть для ресайза изображения при тапе по итему
     */
    private final static float IMAGE_RESIZE = 0.9f;
    /**
     * Оригинальная процентная часть для ресайза при тапе по итему
     */
    private final static float ORIG_SIZE = 1.0f;
    /**
     * Скорость анимации ресайза итема
     */
    private final static int RESIZE_DURATION = 180;

    private InteractionListener interactionListener;
    /**
     * Полезная нагрузка для метода notify по умолчанию.
     * Иначе отработает стандартная анимация и все элементы "моргнут".
     */
    static final Object DEFAULT_PAYLOAD = new Object();
    /**
     * Полезная нагрузка для метода notify.
     * Обрабатывает изменение состояния элемента "Выбрано/Не выбрано"
     */
    static final Object SELECTED_CHANGE_PAYLOAD = new Object();

    @Inject
    ImageListAdapterImpl() {
    }

    @NonNull
    @Override
    public LocalImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_image, parent, false);
        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) v.getLayoutParams();
        int side = parent.getMeasuredWidth() / 3 - (int) (context.getResources().getDimensionPixelOffset(R.dimen.offset_folder_item_4dp) * 1.5);
        params.width = side;
        params.height = side;
        v.setLayoutParams(params);
        return new LocalImageHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalImageHolder holder, int position) {
        holder.bindAll(items.get(position));
    }

    @Override
    public void onBindViewHolder(@NonNull LocalImageHolder holder, int position,
                                 @NonNull List<Object> payloads) {
        if (ListUtils.notEmpty(payloads)) {
            LocalImage localImage = items.get(position);
            if (payloads.get(0) == DEFAULT_PAYLOAD) {
                holder.bindAll(localImage);
            } else if (payloads.get(0) == SELECTED_CHANGE_PAYLOAD) {
                holder.bindSelected(true, localImage.isSelectedForPrint());
            }
        } else {
            onBindViewHolder(holder, position);
        }
    }


    @Override
    public void clearAdapter() {
        if (ListUtils.notEmpty(items)) {
            items = Collections.emptyList();
            notifyDataSetChanged();
        }
    }

    @Override
    public void setImageList(@NonNull List<LocalImage> localImages) {
        DiffUtilCallback diffUtilCallback = new DiffUtilCallback(this.items, localImages);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback, false);
        this.items = localImages;
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public void setImageSelected(int position) {
        notifyItemChanged(position, SELECTED_CHANGE_PAYLOAD);
    }

    public void setInteractionListener(InteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }

    class LocalImageHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.imageCheckBox)
        ImageCheckBox imageCheckBox;
        private String imagePath;

        LocalImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (interactionListener != null) {
                    interactionListener.onImageClicked(getAdapterPosition());
                }
            });
        }

        void bindAll(LocalImage localImage) {
            imagePath = localImage.getFullPath();
            bindImage();
            bindSelected(false, localImage.isSelectedForPrint());
        }

        void bindImage() {
            if (image.getMeasuredWidth() == 0) {
                image.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
                image.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
            } else {
                onImageViewSizeDetermined();
            }
        }

        void bindSelected(boolean animation, boolean selected) {
            if (animation) {
                if (selected) {
                    image.animate().scaleX(IMAGE_RESIZE).scaleY(IMAGE_RESIZE).setDuration(RESIZE_DURATION);
                    imageCheckBox.setChecked(true, true);
                } else {
                    image.animate().scaleX(ORIG_SIZE).scaleY(ORIG_SIZE).setDuration(RESIZE_DURATION);
                    imageCheckBox.setChecked(false, true);
                }
            } else {
                if (selected) {
                    image.setScaleX(IMAGE_RESIZE);
                    image.setScaleY(IMAGE_RESIZE);
                    imageCheckBox.setChecked(true, false);
                } else {
                    image.setScaleX(ORIG_SIZE);
                    image.setScaleY(ORIG_SIZE);
                    imageCheckBox.setChecked(false, false);
                }
            }
        }

        private void onImageViewSizeDetermined() {
            Glide.with(context)
                    .load(imagePath)
                    .override(image.getMeasuredWidth(), image.getMeasuredHeight())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(image);
        }

        private final ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (image.getMeasuredWidth() > 0) {
                    onImageViewSizeDetermined();
                    image.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        };
    }

    public interface InteractionListener {
        void onImageClicked(int position);
    }
}
