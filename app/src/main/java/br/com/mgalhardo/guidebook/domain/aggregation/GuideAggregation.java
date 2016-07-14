package br.com.mgalhardo.guidebook.domain.aggregation;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.mgalhardo.guidebook.domain.entity.Guide;

public class GuideAggregation implements Parcelable {

    public static final Parcelable.Creator<GuideAggregation> CREATOR = new Parcelable.Creator<GuideAggregation>() {
        @Override
        public GuideAggregation createFromParcel(Parcel source) {
            return new GuideAggregation(source);
        }

        @Override
        public GuideAggregation[] newArray(int size) {
            return new GuideAggregation[size];
        }
    };
    public int total;
    @SerializedName("data")
    public List<Guide> guides;

    public GuideAggregation() {
    }

    protected GuideAggregation(Parcel in) {
        this.total = in.readInt();
        this.guides = in.createTypedArrayList(Guide.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total);
        dest.writeTypedList(this.guides);
    }
}
