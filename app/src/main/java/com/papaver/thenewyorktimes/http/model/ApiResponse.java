package com.papaver.thenewyorktimes.http.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Office on 2016-12-16.
 */

public class ApiResponse {

    @SerializedName("status")
    private String mStatus;
    @SerializedName("copyright")
    private String mCopyright;
    @SerializedName("section")
    private String mSection;
    @SerializedName("last_updated")
    private String mLastUpdated;
    @SerializedName("num_results")
    private int mNumResults;
    @SerializedName("results")
    private List<Result> mResults;

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getCopyright() {
        return mCopyright;
    }

    public void setCopyright(String copyright) {
        mCopyright = copyright;
    }

    public String getSection() {
        return mSection;
    }

    public void setSection(String section) {
        mSection = section;
    }

    public String getLastUpdated() {
        return mLastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        mLastUpdated = lastUpdated;
    }

    public int getNumResults() {
        return mNumResults;
    }

    public void setNumResults(int numResults) {
        mNumResults = numResults;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }

}
