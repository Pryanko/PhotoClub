package com.photoprint.photoclub.ui.activity.category.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.di.ScreenScope;
import com.photoprint.photoclub.model.Category;
import com.photoprint.photoclub.ui.adapter.base.BaseItemsRecyclerAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
public class CategoryListAdapterImpl
        extends BaseItemsRecyclerAdapter<Category, CategoryListAdapterImpl.CategoryHolder>
        implements CategoryListAdapter {

    private InteractionListener interactionListener;

    @Inject
    CategoryListAdapterImpl() {
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_category, parent, false);
        return new CategoryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public void setCategories(@NonNull List<Category> categories) {
        DiffUtilCallback diffUtilCallback = new DiffUtilCallback(this.items, categories);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback, false);
        this.items = categories;
        diffResult.dispatchUpdatesTo(this);
    }

    class CategoryHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        SimpleDraweeView imageView;
        @BindView(R.id.title)
        TextView title;

        CategoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (interactionListener != null) {
                    interactionListener.onCategoryClicked(getAdapterPosition());
                }
            });
        }

        void bind(Category category) {
            imageView.setImageURI(category.getImage480());
            title.setText(category.getName());
        }
    }

    public void setInteractionListener(InteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }

    public interface InteractionListener {
        void onCategoryClicked(int position);
    }
}
