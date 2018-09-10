package com.photoprint.photoclub.ui.activity.base;

import com.photoprint.photoclub.ui.activity.ActivityNavigator;
import com.photoprint.photoclub.ui.mvp.view.MvpView;

/**
 * Базовый персональный навигатор
 *
 * @author Grigoriy Pryamov.
 */
public abstract class BaseNavigator<A extends MvpView> {

    protected A baseActivity;
    protected final ActivityNavigator activityNavigator;

    protected BaseNavigator(ActivityNavigator activityNavigator) {
        this.activityNavigator = activityNavigator;
    }

    //Методы onPause и onResume - делегируем наследнику - возможно дополнительное поведение
    public abstract void onResume(A activity);

    public abstract void onPause();

    /**
     * Выполняет операцию c проверкой активити на null
     *
     * @param action Операция для выполнения
     */
    protected void forSafeAction(Consumer action) {
        if (baseActivity != null) {
            action.apply();
        }
    }

    /**
     * Операция, выполняемая для перехода.
     *
     */
    public interface Consumer {

        /**
         * Выполняет операцию для локального навигатора.
         */
        void apply();
    }
}
