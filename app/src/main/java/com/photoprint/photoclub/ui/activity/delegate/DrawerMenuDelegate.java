package com.photoprint.photoclub.ui.activity.delegate;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.di.ActivityScope;
import com.photoprint.photoclub.ui.activity.ActivityNavigator;
import com.photoprint.photoclub.ui.mvp.MvpDelegate;
import com.photoprint.photoclub.ui.view.appmenu.DrawerMenuView;
import com.photoprint.photoclub.ui.view.appmenu.model.DrawerMenuItem;
import com.photoprint.photoclub.ui.view.appmenu.model.DrawerMenuParams;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Делегат для обработки бокового меню.
 *
 * @author Grigoriy Pryamov.
 */
@ActivityScope
public class DrawerMenuDelegate {

    private static final String MVP_CHILD_ID = "MainDrawer";

    private static final Logger logger = LoggerFactory.getLogger(DrawerMenuDelegate.class);

    /**
     * Activity
     */
    private final Activity activity;
    /**
     * Делегат для обработки тулбара.
     */
    private final ToolbarDelegate toolbarDelegate;
    /**
     * Навигатор между Activity
     */
    private final ActivityNavigator activityNavigator;
    /**
     * DrawerLayout
     */
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    /**
     * Боковое меню
     */
    @BindView(R.id.mainDrawer)
    DrawerMenuView drawerMenuView;
    /**
     * Текущий пункт меню
     */
    private DrawerMenuItem currentItem;
    /**
     * Слушатель изменения состояния {@link DrawerLayout}
     */
    private DrawerStateChangeListener drawerStateChangeListener;
    /**
     * Сторонний слушатель нажатия на кнопку навигации {@link ToolbarDelegate}
     */
    private NavigationOnClickListener navigationOnClickListener;
    /**
     * Отложенная операция перехода на другой экран
     */
    private Runnable navigateRunnable;
    /**
     * Флаг, что отображается иконка "Назад" вместо гамбургера
     */
    private boolean iconBack;

    @Inject
    DrawerMenuDelegate(Activity activity,
                       ToolbarDelegate toolbarDelegate,
                       ActivityNavigator activityNavigator) {
        this.activity = activity;
        this.toolbarDelegate = toolbarDelegate;
        this.activityNavigator = activityNavigator;
    }


    public interface DrawerStateChangeListener {
        void onDrawerStateChanged(int newState);
    }

    public interface NavigationOnClickListener {
        boolean onClick();
    }

    public void init(@NonNull MvpDelegate parent, @Nullable DrawerMenuItem currentItem) {
        init(parent, currentItem, false);
    }

    public void init(@NonNull MvpDelegate parent, @Nullable DrawerMenuItem currentItem, boolean iconBack) {
        this.currentItem = currentItem;
        setIconBack(iconBack);
        ButterKnife.bind(this, activity);
        toolbarDelegate.setNavigationOnClickListener(toolbarListener);
        drawerLayout.addDrawerListener(drawerListener);
        drawerMenuView.setInteractionListener(mainDrawerInteractionListener);
        drawerMenuView.init(parent, MVP_CHILD_ID);
        DrawerMenuParams drawerMenuParams = new DrawerMenuParams();
        drawerMenuParams.setSelectedItem(currentItem);
        drawerMenuView.setParams(drawerMenuParams);
    }

    private void setIconBack(boolean iconBack) {
        this.iconBack = iconBack;
        if (iconBack) {
            toolbarDelegate.setNavigationIcon(R.drawable.ic_arrow_left);
        } else {
            toolbarDelegate.setNavigationIcon(R.drawable.ic_menu);
        }
    }

    public void setDrawerStateChangeListener(DrawerStateChangeListener drawerStateChangeListener) {
        this.drawerStateChangeListener = drawerStateChangeListener;
    }

    public void setNavigationOnClickListener(NavigationOnClickListener navigationOnClickListener) {
        this.navigationOnClickListener = navigationOnClickListener;
    }

    public boolean onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    private final DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {
            if (navigateRunnable != null) {
                navigateRunnable.run();
                navigateRunnable = null;
            }
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            if (drawerStateChangeListener != null) {
                drawerStateChangeListener.onDrawerStateChanged(newState);
            }
        }
    };

    private final DrawerMenuView.InteractionListener mainDrawerInteractionListener = new DrawerMenuView.InteractionListener() {
        @Override
        public void onNewOrderClicked() {
            if (currentItem != DrawerMenuItem.NEW_ORDER) {
                navigateRunnable = () -> activityNavigator.navigateToCategoryActivity(activity);
                logger.trace("onNewOrderClicked");
            }
            drawerLayout.closeDrawers();
        }

        @Override
        public void onMyOrderClicked() {
            if (currentItem != DrawerMenuItem.MY_ORDER) {
                navigateRunnable = () -> activityNavigator.navigateToCategoryActivity(activity);
                logger.trace("onMyOrderClicked");
            }
            drawerLayout.closeDrawers();
        }
    };

    private final View.OnClickListener toolbarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (navigationOnClickListener != null) {
                if (navigationOnClickListener.onClick()) {
                    return;
                }
            }
            if (iconBack) {
                activityNavigator.navigateBack(activity);
            } else {
                drawerLayout.openDrawer(drawerMenuView);
            }
        }
    };
}
