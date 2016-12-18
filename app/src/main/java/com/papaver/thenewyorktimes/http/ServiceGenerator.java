package com.papaver.thenewyorktimes.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Office on 2016-12-16.
 */

public class ServiceGenerator {

    private static Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl(HttpUrl.baseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Retrofit retrofit() {
        return sRetrofit;
    }

    public static <S> S createService(Class<S> serviceClass) {
        return sRetrofit.create(serviceClass);
    }

}
