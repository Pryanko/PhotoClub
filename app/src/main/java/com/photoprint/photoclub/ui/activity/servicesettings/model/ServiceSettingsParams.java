package com.photoprint.photoclub.ui.activity.servicesettings.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Входные параметры для экрана настройки услуги
 *
 * @author Grigoriy Pryamov.
 */
public class ServiceSettingsParams implements Parcelable {

    /**
     * Id категории - для зпроса к бд за услугами)
     */
    private long categoryId;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.categoryId);
    }

    public ServiceSettingsParams() {
    }

    protected ServiceSettingsParams(Parcel in) {
        this.categoryId = in.readLong();
    }

    public static final Parcelable.Creator<ServiceSettingsParams> CREATOR = new Parcelable.Creator<ServiceSettingsParams>() {
        @Override
        public ServiceSettingsParams createFromParcel(Parcel source) {
            return new ServiceSettingsParams(source);
        }

        @Override
        public ServiceSettingsParams[] newArray(int size) {
            return new ServiceSettingsParams[size];
        }
    };
}
