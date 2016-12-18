package com.papaver.thenewyorktimes.http;

import android.content.Context;

import com.papaver.thenewyorktimes.http.model.ApiResponse;
import com.papaver.thenewyorktimes.http.model.Result;
import com.papaver.thenewyorktimes.resource.ResourceManager;
import com.papaver.thenewyorktimes.util.Logger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Office on 2016-12-17.
 */

public class HttpManager {

    // ========================================================================================== //
    private final boolean DEBUG_FLAG = true;
    private final String DEBUG_TAG = this.getClass().getSimpleName();

    // ========================================================================================== //
    public interface OnHttpTaskListener {
        void onTaskSuccess(ApiResponse apiResponse);
        void onTaskFailure();
    }

    // ========================================================================================== //
    private static HttpManager sManager = null;

    public static HttpManager get(Context c) {
        if ( sManager == null ) {
            synchronized ( ResourceManager.class ) {
                if ( sManager == null ) {
                    sManager = new HttpManager(c);
                }
            }
        }

        return sManager;
    }

    public void clear() {
        mListener = null;
        sManager = null;
    }

    // ========================================================================================== //
    private Context mContext;
    private final List<Result> mResults = new ArrayList<Result>();
    private OnHttpTaskListener mListener;

    private HttpManager(Context c) {
        init(c);
    }

    private void init(Context c) {
        mContext = c;
        mResults.clear();
    }

    // ========================================================================================== //
    public void getContents(OnHttpTaskListener listener) {
        mListener = listener;
        request();
    }

    private void request() {
        ApiService service = ServiceGenerator.createService(ApiService.class);
        Call<ApiResponse> call = service.request( HttpUrl.apiKey() );
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if ( response == null ) {
                    Logger.e(DEBUG_FLAG, DEBUG_TAG + ", request(), response is null;");
                    onTaskFailure();
                    return;
                }
                if ( !response.isSuccessful() ) {
                    Logger.e(DEBUG_FLAG, DEBUG_TAG + ", request(), error = " + response.message());
                    return;
                }

                Logger.e(DEBUG_FLAG, DEBUG_TAG + ", success; ");
                ApiResponse apiResponse = response.body();
                onTaskSuccess(apiResponse);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Logger.e(DEBUG_FLAG, DEBUG_TAG + ", onFailure(), t = " + t.toString());
                onTaskFailure();
            }
        });
    }

    // ========================================================================================== //
    private void onTaskFailure() {
        if ( mListener == null ) {
            return;
        }
        mListener.onTaskFailure();
    }

    private void onTaskSuccess(ApiResponse apiResponse) {
        if ( mListener == null ) {
            return;
        }
        mListener.onTaskSuccess(apiResponse);
    }

    // ========================================================================================== //

}
