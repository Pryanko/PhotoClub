package com.photoprint.photoclub.model;

import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.photoprint.photoclub.model.base.ModelWithId;

/**
 * Основная модель для экрана обучения
 *
 * @author Grigoriy Pryamov.
 */
public class Guide implements ModelWithId<Long>,Parcelable {

    /**
     * Id мануала
     */
    @PrimaryKey
    private long id;
    /**
     * Заголовок (нименование)
     */
    private String header;
    /**
     * Картинка для большого разрешения
     */
    private String image1024;
    /**
     * Картинка для экранов с маленьким разрешением
     */
    private String image480;
    /**
     * Текст мануала
     */
    private String text;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getImage1024() {
        return image1024;
    }

    public void setImage1024(String image1024) {
        this.image1024 = image1024;
    }

    public String getImage480() {
        return image480;
    }

    public void setImage480(String image480) {
        this.image480 = image480;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Guide{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", image1024='" + image1024 + '\'' +
                ", image480='" + image480 + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Guide guide = (Guide) o;

        if (id != guide.id) return false;
        if (header != null ? !header.equals(guide.header) : guide.header != null) return false;
        if (image1024 != null ? !image1024.equals(guide.image1024) : guide.image1024 != null)
            return false;
        if (image480 != null ? !image480.equals(guide.image480) : guide.image480 != null)
            return false;
        return text != null ? text.equals(guide.text) : guide.text == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (image1024 != null ? image1024.hashCode() : 0);
        result = 31 * result + (image480 != null ? image480.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.header);
        dest.writeString(this.image1024);
        dest.writeString(this.image480);
        dest.writeString(this.text);
    }

    public Guide() {
    }

    protected Guide(Parcel in) {
        this.id = in.readLong();
        this.header = in.readString();
        this.image1024 = in.readString();
        this.image480 = in.readString();
        this.text = in.readString();
    }

    public static final Parcelable.Creator<Guide> CREATOR = new Parcelable.Creator<Guide>() {
        @Override
        public Guide createFromParcel(Parcel source) {
            return new Guide(source);
        }

        @Override
        public Guide[] newArray(int size) {
            return new Guide[size];
        }
    };
}
