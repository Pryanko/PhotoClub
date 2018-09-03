package com.photoprint.photoclub.ui.activity.guide.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Параметры для экрана руководства
 *
 * @author Grigoriy Pryamov.
 */
public class GuideParams implements Parcelable {
    /**
     * Boolean определяющая был ли переход на экран руководства воиспроизведен с меню
     */
    private boolean navigateFromMenu;

    public boolean isNavigateFromMenu() {
        return navigateFromMenu;
    }

    public void setNavigateFromMenu(boolean navigateFromMenu) {
        this.navigateFromMenu = navigateFromMenu;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.navigateFromMenu ? (byte) 1 : (byte) 0);
    }

    public GuideParams() {
    }

    protected GuideParams(Parcel in) {
        this.navigateFromMenu = in.readByte() != 0;
    }

    public static final Parcelable.Creator<GuideParams> CREATOR = new Parcelable.Creator<GuideParams>() {
        @Override
        public GuideParams createFromParcel(Parcel source) {
            return new GuideParams(source);
        }

        @Override
        public GuideParams[] newArray(int size) {
            return new GuideParams[size];
        }
    };
}
