package com.papaver.thenewyorktimes.http;

/**
 * Created by Office on 2016-12-16.
 */

public class HttpUrl {

    private static final String API_URL = "https://api.nytimes.com";
    private static final String API_KEY = "cf23f0334a174fff975fc2400ccbfdd9";

    public static final String REQUEST = "/svc/topstories/v2/home.json";

    public static String baseUrl() {
        return API_URL;
    }

    public static String apiKey() {
        return API_KEY;
    }

}
