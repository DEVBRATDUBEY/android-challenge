package br.com.mgalhardo.guidebook.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Guide implements Parcelable {

    public static final Creator<Guide> CREATOR = new Creator<Guide>() {
        @Override
        public Guide createFromParcel(Parcel source) {
            return new Guide(source);
        }

        @Override
        public Guide[] newArray(int size) {
            return new Guide[size];
        }
    };
    public String startDate;
    public String endDate;
    public String name;
    public String url;
    public Venue venue;
    public String icon;

    public Guide() {
    }

    protected Guide(Parcel in) {
        this.startDate = in.readString();
        this.endDate = in.readString();
        this.name = in.readString();
        this.url = in.readString();
        this.venue = in.readParcelable(Venue.class.getClassLoader());
        this.icon = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.startDate);
        dest.writeString(this.endDate);
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeParcelable(this.venue, flags);
        dest.writeString(this.icon);
    }
}