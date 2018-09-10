package com.photoprint.photoclub.ui.activity.serviceinfo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Входные параметры для экрана информации для услуги
 *
 * @author Grigoriy Pryamov.
 */
public class ServiceInfoParams implements Parcelable {
    /**
     * Id категории - для зпроса к бд за услугами)
     */
    private long serviceId;

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.serviceId);
    }

    public ServiceInfoParams() {
    }

    protected ServiceInfoParams(Parcel in) {
        this.serviceId = in.readLong();
    }

    public static final Creator<ServiceInfoParams> CREATOR = new Creator<ServiceInfoParams>() {
        @Override
        public ServiceInfoParams createFromParcel(Parcel source) {
            return new ServiceInfoParams(source);
        }

        @Override
        public ServiceInfoParams[] newArray(int size) {
            return new ServiceInfoParams[size];
        }
    };
}
