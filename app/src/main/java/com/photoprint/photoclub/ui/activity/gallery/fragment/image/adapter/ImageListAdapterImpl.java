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

    private InteractionListener interactionListener;

    @Inject
    ImageListAdapterImpl() {
    }

    @NonNull
    @Override
    public LocalImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_image, parent, false);
        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) v.getLayoutParams();
        int side = parent.getMeasuredWidth() / 3 - (int) (context.getResources().getDimensionPixelOffset(R.dimen.offset_folder_item_4dp) * 2.5);
        params.width = side;
        params.height = side;
        v.setLayoutParams(params);
        return new LocalImageHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalImageHolder holder, int position) {
        holder.bind(items.get(position));
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

    public void setInteractionListener(InteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }

    class LocalImageHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView image;
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

        public void bind(LocalImage localImage) {
            imagePath = localImage.getFullPath();
            bindImage();
        }

        void bindImage() {
            if (image.getMeasuredWidth() == 0) {
                image.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
                image.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
            } else {
                onImageViewSizeDetermined();
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
