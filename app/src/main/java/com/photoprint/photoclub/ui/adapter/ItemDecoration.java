package com.photoprint.photoclub.ui.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Grigoriy Pryamov.
 */
public class ItemDecoration extends RecyclerView.ItemDecoration {

    private final int leftOffset, rightOffset, topOffset, bottomOffset;

    public ItemDecoration(int leftOffset,
                          int rightOffset,
                          int topOffset,
                          int bottomOffset) {
        this.leftOffset = leftOffset;
        this.rightOffset = rightOffset;
        this.topOffset = topOffset;
        this.bottomOffset = bottomOffset;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = leftOffset;
        outRect.right = rightOffset;
        if (shouldHaveTopOffset(view, parent)) {
            outRect.top = topOffset;
        }
        outRect.bottom = bottomOffset;

    }

    private boolean shouldHaveTopOffset(View view, RecyclerView parent) {
        int position = parent.getChildAdapterPosition(view);
        return position != -1 && parent.getChildAdapterPosition(view) == 0;
    }

    private boolean shouldHaveBottomOffset(View view, RecyclerView parent) {
        int position = parent.getChildAdapterPosition(view);
        return position != -1 && position == parent.getAdapter().getItemCount() - 1;
    }
}
