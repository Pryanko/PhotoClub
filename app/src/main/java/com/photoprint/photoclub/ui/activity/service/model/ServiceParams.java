package com.photoprint.photoclub.ui.activity.service.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Входные параметры для экрана выбора услуг
 *
 * @author Grigoriy Pryamov.
 */
public class ServiceParams implements Parcelable {
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

    public ServiceParams() {
    }

    protected ServiceParams(Parcel in) {
        this.categoryId = in.readLong();
    }

    public static final Parcelable.Creator<ServiceParams> CREATOR = new Parcelable.Creator<ServiceParams>() {
        @Override
        public ServiceParams createFromParcel(Parcel source) {
            return new ServiceParams(source);
        }

        @Override
        public ServiceParams[] newArray(int size) {
            return new ServiceParams[size];
        }
    };
}
