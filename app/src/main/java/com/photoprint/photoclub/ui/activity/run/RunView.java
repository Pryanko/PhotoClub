package com.photoprint.photoclub.ui.activity.run;

import com.photoprint.photoclub.ui.mvp.view.MvpView;

/**
 * @author Grigoriy Pryamov.
 */
interface RunView extends MvpView{

    void setLoading(boolean loading);

    void setBtnVisible(boolean visible);
}
