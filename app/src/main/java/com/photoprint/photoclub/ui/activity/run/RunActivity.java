package com.photoprint.photoclub.ui.activity.run;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.ui.activity.base.ActivityModule;
import com.photoprint.photoclub.ui.activity.base.MvpActivity;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Grigoriy Pryamov.
 */
public class RunActivity extends MvpActivity implements RunView {

    private static final Logger logger = LoggerFactory.getLogger(RunActivity.class);

    /**
     * Время анимации появления прогресс бара
     */
    private static final long ANIMATE_DURATION = TimeUnit.SECONDS.toMillis(1);
    /**
     * Константа прозрачности
     */
    private static final float ANIMATE_ALPHA = 1.0f;

    //region Di
    RunScreenComponent screenComponent;
    RunComponent component;
    @Inject
    Navigator navigator;
    //end region
    //region views
    @BindView(R.id.nextButton)
    Button nextButton;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    //endRegion
    private RunPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        screenComponent = getScreenComponent();
        component = screenComponent.runComponentBuilder()
                .activityModule(new ActivityModule(this))
                .build();
        component.inject(this);
        super.onCreate(savedInstanceState);
        presenter = getMvpDelegate().getPresenter(component::runPresenter, RunPresenter.class);
        setContentView(R.layout.activity_run);
        ButterKnife.bind(this);

        nextButton.setOnClickListener(v -> presenter.onNextBtnClicked());

        presenter.initialize();
    }

    @Override
    protected void onResume() {
        navigator.onResume(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        navigator.onPause();
        super.onPause();
    }

    @Override
    public void setLoading(boolean loading) {
        logger.trace("setLoading");
        progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setBtnVisible(boolean visible) {
        logger.trace("setBtnVisible");
        nextButton.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private RunScreenComponent getScreenComponent() {
        Object saved = getLastCustomNonConfigurationInstance();
        if (saved == null) {
            return appComponent().runScreenComponent();
        } else {
            return (RunScreenComponent) saved;
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return screenComponent;
    }
}
