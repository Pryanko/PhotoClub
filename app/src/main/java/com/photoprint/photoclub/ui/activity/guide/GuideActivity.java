package com.photoprint.photoclub.ui.activity.guide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.example.utils.ListUtils;
import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.model.Guide;
import com.photoprint.photoclub.ui.activity.base.ActivityModule;
import com.photoprint.photoclub.ui.activity.base.MvpActivity;
import com.photoprint.photoclub.ui.activity.guide.adapter.GuideAdapterImpl;
import com.photoprint.photoclub.ui.activity.guide.model.GuideParams;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Grigoriy Pryamov.
 */
public class GuideActivity extends MvpActivity implements GuideView {

    private static final Logger logger = LoggerFactory.getLogger(GuideActivity.class);

    //region extras
    private static final String EXTRA_GUIDE_PARAMS = "EXTRA_GUIDE_PARAMS";

    public static Intent getCallingIntent(Context context, GuideParams guideParams) {
        Intent intent = new Intent(context, GuideActivity.class);
        intent.putExtra(EXTRA_GUIDE_PARAMS, guideParams);
        return intent;
    }

    //region di
    GuideScreenComponent screenComponent;
    GuideComponent component;
    @Inject
    Navigator navigator;
    //endregion
    //region views
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.stepperView)
    PageIndicatorView stepperView;
    @BindView(R.id.backPageButton)
    Button backPageButton;
    @BindView(R.id.nextPageButton)
    Button nextPageButton;
    @BindView(R.id.nextButton)
    Button nextButton;
    @BindView(R.id.closeButton)
    Button closeButton;
    //endregion
    private GuidePresenter presenter;
    private GuideAdapterImpl guideAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        screenComponent = getScreenComponent();
        component = screenComponent.guideComponentBuilder()
                .activityModule(new ActivityModule(this))
                .guideParams(getIntent().getParcelableExtra(EXTRA_GUIDE_PARAMS))
                .build();
        component.inject(this);
        super.onCreate(savedInstanceState);
        presenter = getMvpDelegate().getPresenter(component::guidePresenter, GuidePresenter.class);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        guideAdapter = new GuideAdapterImpl(getSupportFragmentManager());
        viewPager.setAdapter(guideAdapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        nextPageButton.setOnClickListener(v -> presenter.onNextPageBtnClicked());
        backPageButton.setOnClickListener(v -> presenter.onBackPageBtnClicked());
        nextButton.setOnClickListener(v -> presenter.onNextBtnClicked());
        closeButton.setOnClickListener(v -> presenter.onCloseBtnClicked());
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
    public void setGuides(@NonNull List<Guide> guides) {
        if (ListUtils.notEmpty(guides)) {
            guideAdapter.setGuides(guides);
            viewPager.setCurrentItem(0);
            stepperView.setViewPager(viewPager);
            stepperView.setCount(guides.size());
            stepperView.setAnimationType(AnimationType.SWAP);
        }
    }

    @Override
    public void setCurrentPage(int positionPage) {
        viewPager.setCurrentItem(positionPage);
    }

    @Override
    public void setNextButtonVisible(boolean visible) {
        actionButtonVisible(nextButton, visible);
    }

    @Override
    public void setBackPageButtonEnabled(boolean enabled) {
        backPageButton.setEnabled(enabled);
        backPageButton.setTextColor(enabled ? getResources().getColor(R.color.appYellow)
                : getResources().getColor(R.color.appLightGray));
    }

    @Override
    public void setCloseButtonVisible(boolean visible) {
        actionButtonVisible(closeButton, visible);
    }

    private void actionButtonVisible(Button actionButton, boolean visible) {
        nextPageButton.setVisibility(visible ? View.GONE : View.VISIBLE);
        actionButton.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            stepperView.setSelected(position);
            presenter.setCurrentPagePosition(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
