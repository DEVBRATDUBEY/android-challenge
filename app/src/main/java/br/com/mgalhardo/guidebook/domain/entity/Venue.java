package br.com.mgalhardo.guidebook.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Venue implements Parcelable {

    public static final Parcelable.Creator<Venue> CREATOR = new Parcelable.Creator<Venue>() {
        @Override
        public Venue createFromParcel(Parcel source) {
            return new Venue(source);
        }

        @Override
        public Venue[] newArray(int size) {
            return new Venue[size];
        }
    };
    public String city;
    public String state;

    public Venue() {
    }

    protected Venue(Parcel in) {
        this.city = in.readString();
        this.state = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.city);
        dest.writeString(this.state);
    }
}