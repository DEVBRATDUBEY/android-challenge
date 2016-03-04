package br.com.mgalhardo.guidebook.core.aggregation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import br.com.mgalhardo.guidebook.core.entity.Guide;

public class GuideAggregation implements Serializable {

    public static final String KEY = "GuideAggregation";

    @SerializedName("data")
    public List<Guide> guides;

}