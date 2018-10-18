package com.photoprint.photoclub.ui.activity.gallery.fragment.folder.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.di.ScreenScope;
import com.photoprint.photoclub.ui.activity.gallery.fragment.folder.model.Folder;
import com.photoprint.photoclub.ui.adapter.base.BaseItemsRecyclerAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
public class FolderListAdapterImpl
        extends BaseItemsRecyclerAdapter<Folder, FolderListAdapterImpl.FolderHolder>
        implements FolderListAdapter {

    private InteractionListener interactionListener;

    @Inject
    FolderListAdapterImpl() {
    }

    @NonNull
    @Override
    public FolderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_folder, parent, false);
        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) v.getLayoutParams();
        params.width = parent.getMeasuredWidth() / 2;
        v.setLayoutParams(params);
        return new FolderHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public void setFolders(@NonNull List<Folder> folders) {
        DiffUtilCallback diffUtilCallback = new DiffUtilCallback(this.items, folders);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback, false);
        this.items = folders;
        diffResult.dispatchUpdatesTo(this);
    }

    public void setInteractionListener(InteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }

    class FolderHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageFolder)
        ImageView imageFolder;
        @BindView(R.id.title)
        TextView title;
        private String imagePath;

        FolderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (interactionListener != null) {
                    interactionListener.onFolderClicked(items.get(getAdapterPosition()).getName());
                }
            });
        }

        void bind(Folder folder) {
            title.setText(folder.getName());
            imagePath = folder.getFolderImage();
            bindImage();
        }

        void bindImage() {
            if (imageFolder.getMeasuredWidth() == 0) {
                imageFolder.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
                imageFolder.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
            } else {
                onImageViewSizeDetermined();
            }
        }

        private void onImageViewSizeDetermined() {
            Glide.with(context)
                    .load(imagePath)
                    .override(imageFolder.getMeasuredWidth(), imageFolder.getMeasuredHeight())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(imageFolder);
        }

        private final ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (imageFolder.getMeasuredWidth() > 0) {
                    onImageViewSizeDetermined();
                    imageFolder.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        };
    }

    public interface InteractionListener {
        void onFolderClicked(String folderName);
    }
}
