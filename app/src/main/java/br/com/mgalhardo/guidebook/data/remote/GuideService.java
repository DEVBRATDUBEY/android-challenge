package br.com.mgalhardo.guidebook.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.mgalhardo.guidebook.core.aggregation.GuideAggregation;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

public interface GuideService {

    @GET("upcomingGuides")
    Observable<GuideAggregation> getGuideAggregation();

    class Builder {

        private static final String ENDPOINT = "http://private-c60ade-guidebook1.apiary-mock.com";

        public static GuideService build() {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(GuideService.class);
        }

    }
}