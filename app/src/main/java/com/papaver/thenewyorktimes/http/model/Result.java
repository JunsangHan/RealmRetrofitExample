package com.papaver.thenewyorktimes.http.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Office on 2016-12-16.
 */

public class Result implements Parcelable {

    @SerializedName("section")
    private String mSection;
    @SerializedName("subsection")
    private String mSubSection;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("abstract")
    private String mAbstract;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("byline")
    private String mByLine;
    @SerializedName("item_type")
    private String mItemType;
    @SerializedName("updated_date")
    private String mUpdated_date;
    @SerializedName("created_date")
    private String mCreatedDate;
    @SerializedName("published_date")
    private String mPublishedDate;
    @SerializedName("material_type_facet")
    private String mMaterialTypeFacet;
    @SerializedName("kicker")
    private String mKicker;
    @SerializedName("des_facet")
    private List<String> mDesFacet;
    //private RealmList<RealmString> mDesFacet;
    @SerializedName("org_facet")
    private List<String> mOrgFacet;
    //private RealmList<RealmString> mOrgFacet;
    @SerializedName("per_facet")
    private List<String> mPerFacet;
    //private RealmList<RealmString> mPerFacet;
    @SerializedName("geo_facet")
    private List<String> mGeoFacet;
    //private RealmList<RealmString> mGeoFacet;
    @SerializedName("multimedia")
    private List<MultiMedia> mMultiMedias;
    //private RealmList<MultiMedia> mMultiMedias;
    @SerializedName("short_url")
    private String mShortUrl;

    public String getSection() {
        return mSection;
    }

    public void setSection(String section) {
        mSection = section;
    }

    public String getSubSection() {
        return mSubSection;
    }

    public void setSubSection(String subSection) {
        mSubSection = subSection;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getAbstract() {
        return mAbstract;
    }

    public void setAbstract(String anAbstract) {
        mAbstract = anAbstract;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getByLine() {
        return mByLine;
    }

    public void setByLine(String byLine) {
        mByLine = byLine;
    }

    public String getItemType() {
        return mItemType;
    }

    public void setItemType(String itemType) {
        mItemType = itemType;
    }

    public String getUpdated_date() {
        return mUpdated_date;
    }

    public void setUpdated_date(String updated_date) {
        mUpdated_date = updated_date;
    }

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        mCreatedDate = createdDate;
    }

    public String getPublishedDate() {
        return mPublishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        mPublishedDate = publishedDate;
    }

    public String getMaterialTypeFacet() {
        return mMaterialTypeFacet;
    }

    public void setMaterialTypeFacet(String materialTypeFacet) {
        mMaterialTypeFacet = materialTypeFacet;
    }

    public String getKicker() {
        return mKicker;
    }

    public void setKicker(String kicker) {
        mKicker = kicker;
    }

    public List<String> getDesFacet() {
        return mDesFacet;
    }

    public void setDesFacet(List<String> desFacet) {
        mDesFacet = desFacet;
    }

    public List<String> getOrgFacet() {
        return mOrgFacet;
    }

    public void setOrgFacet(List<String> orgFacet) {
        mOrgFacet = orgFacet;
    }

    public List<String> getPerFacet() {
        return mPerFacet;
    }

    public void setPerFacet(List<String> perFacet) {
        mPerFacet = perFacet;
    }

    public List<String> getGeoFacet() {
        return mGeoFacet;
    }

    public void setGeoFacet(List<String> geoFacet) {
        mGeoFacet = geoFacet;
    }

    public List<MultiMedia> getMultiMedias() {
        return mMultiMedias;
    }

    public void setMultiMedias(List<MultiMedia> multiMedias) {
        mMultiMedias = multiMedias;
    }

    public String getShortUrl() {
        return mShortUrl;
    }

    public void setShortUrl(String shortUrl) {
        mShortUrl = shortUrl;
    }

    // ========================================================================================== //
    public Result(Parcel in) {
        mSection = in.readString();
        mSubSection = in.readString();
        mTitle = in.readString();
        mAbstract = in.readString();
        mUrl = in.readString();
        mByLine = in.readString();
        mItemType = in.readString();
        mUpdated_date = in.readString();
        mCreatedDate = in.readString();
        mPublishedDate = in.readString();
        mMaterialTypeFacet = in.readString();
        mKicker = in.readString();

        mDesFacet = in.createStringArrayList();
        mOrgFacet = in.createStringArrayList();
        mPerFacet = in.createStringArrayList();
        mGeoFacet = in.createStringArrayList();

        mMultiMedias = new ArrayList<MultiMedia>();
        in.readTypedList(mMultiMedias, MultiMedia.CREATOR);

        mShortUrl = in.readString();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(mSection);
        dest.writeString(mSubSection);
        dest.writeString(mTitle);
        dest.writeString(mAbstract);
        dest.writeString(mUrl);
        dest.writeString(mByLine);
        dest.writeString(mItemType);
        dest.writeString(mUpdated_date);
        dest.writeString(mCreatedDate);
        dest.writeString(mPublishedDate);
        dest.writeString(mMaterialTypeFacet);
        dest.writeString(mKicker);

        dest.writeStringList(mDesFacet);
        dest.writeStringList(mOrgFacet);
        dest.writeStringList(mPerFacet);
        dest.writeStringList(mGeoFacet);

        dest.writeTypedList(mMultiMedias);
        dest.writeString(mShortUrl);
    }

    // ========================================================================================== //

}
