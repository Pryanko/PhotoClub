package com.photoprint.photoclub.ui.dialog.loading;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import com.photoprint.photoclub.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Диалог загрузки
 *
 * @author Grigoriy Pryamov.
 */
public class LoadingDialog extends Dialog {

    //region views
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    //endregion

    public LoadingDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loading_dialog);
        ButterKnife.bind(this);
        bind();
    }

    private void bind() {
        progressBar.animate().alpha(1.0f).setDuration(900).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                progressBar.setVisibility(View.VISIBLE);
            }
        }).start();
    }
}
