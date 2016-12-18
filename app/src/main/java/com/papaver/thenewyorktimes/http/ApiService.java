package com.papaver.thenewyorktimes.http;

import com.papaver.thenewyorktimes.http.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Office on 2016-12-16.
 */

public interface ApiService {

    @GET(HttpUrl.REQUEST)
    Call<ApiResponse> request(@Query("api-key") String apiKey);

}
