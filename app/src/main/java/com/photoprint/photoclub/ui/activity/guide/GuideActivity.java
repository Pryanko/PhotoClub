package com.photoprint.photoclub.ui.activity.guide;

import android.os.Bundle;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.ui.activity.base.ActivityModule;
import com.photoprint.photoclub.ui.activity.base.MvpActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author Grigoriy Pryamov.
 */
public class GuideActivity extends MvpActivity implements GuideView {

    private static final Logger logger = LoggerFactory.getLogger(GuideActivity.class);

    //region di
    GuideScreenComponent screenComponent;
    GuideComponent component;
    @Inject
    Navigator navigator;
    //endregion
    //region views
    //endregion
    private GuidePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        screenComponent = getScreenComponent();
        component = screenComponent.guideComponentBuilder()
                .activityModule(new ActivityModule(this))
                .build();
        component.inject(this);
        super.onCreate(savedInstanceState);
        presenter = getMvpDelegate().getPresenter(component::guidePresenter, GuidePresenter.class);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        presenter.initialize();
    }

    public GuideScreenComponent getScreenComponent() {
        Object saved = getLastCustomNonConfigurationInstance();
        if (saved == null) {
            return appComponent().guideScreenComponent();
        } else {
            return (GuideScreenComponent) saved;
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return screenComponent;
    }
}
