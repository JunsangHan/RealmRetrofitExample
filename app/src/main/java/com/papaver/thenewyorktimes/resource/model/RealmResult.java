package com.papaver.thenewyorktimes.resource.model;

import com.papaver.thenewyorktimes.adapter.Item;
import com.papaver.thenewyorktimes.http.model.MediaType;
import com.papaver.thenewyorktimes.util.DateUtils;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Office on 2016-12-17.
 */

public class RealmResult extends RealmObject implements Item {

    private String mSection;
    private String mSubSection;
    @PrimaryKey
    private String mTitle;
    private String mAbstract;
    private String mUrl;
    private String mByLine;
    private String mItemType;
    private String mUpdated_date;
    private String mCreatedDate;
    private String mPublishedDate;
    private String mMaterialTypeFacet;
    private String mKicker;
    private RealmList<RealmString> mDesFacet;
    private RealmList<RealmString> mOrgFacet;
    private RealmList<RealmString> mPerFacet;
    private RealmList<RealmString> mGeoFacet;
    private RealmList<RealmMultiMedia> mMultiMedias;
    private String mShortUrl;

    private long mUpdatedTimeStamp;

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

    public RealmList<RealmString> getDesFacet() {
        return mDesFacet;
    }

    public void setDesFacet(RealmList<RealmString> desFacet) {
        mDesFacet = desFacet;
    }

    public RealmList<RealmString> getOrgFacet() {
        return mOrgFacet;
    }

    public void setOrgFacet(RealmList<RealmString> orgFacet) {
        mOrgFacet = orgFacet;
    }

    public RealmList<RealmString> getPerFacet() {
        return mPerFacet;
    }

    public void setPerFacet(RealmList<RealmString> perFacet) {
        mPerFacet = perFacet;
    }

    public RealmList<RealmString> getGeoFacet() {
        return mGeoFacet;
    }

    public void setGeoFacet(RealmList<RealmString> geoFacet) {
        mGeoFacet = geoFacet;
    }

    public RealmList<RealmMultiMedia> getMultiMedias() {
        return mMultiMedias;
    }

    public void setMultiMedias(RealmList<RealmMultiMedia> multiMedias) {
        mMultiMedias = multiMedias;
    }

    public String getShortUrl() {
        return mShortUrl;
    }

    public void setShortUrl(String shortUrl) {
        mShortUrl = shortUrl;
    }

    public long getUpdatedTimeStamp() {
        return mUpdatedTimeStamp;
    }

    public void setUpdatedTimeStamp(long updatedTimeStamp) {
        mUpdatedTimeStamp = updatedTimeStamp;
    }

    public void setUpdatedTimeStamp(String date) {
        mUpdatedTimeStamp = DateUtils.getTimeStamp(date);
    }

    // ========================================================================================== //
    @Override
    public String getTitleOfItem() {
        return mTitle;
    }

    @Override
    public boolean isTextItem() {
        if ( mMultiMedias == null || mMultiMedias.size() < MediaType.TOTAL_TYPES ) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isLandScape() {
        if ( isTextItem() ) {
            return false;
        }

        // format = "Normal";
        RealmMultiMedia media = mMultiMedias.get(MediaType.NORMAL);
        return media.getWidth() < media.getHeight() ? false : true;
    }

    @Override
    public String getMediaUrl() {
        if ( isTextItem() ) {
            return null;
        }

        if ( isLandScape() ) {
            return mMultiMedias.get( MediaType.MEDIUM_THREE_BY_TWO_210 ).getUrl();
        }

        // ItemType.CONTENT_PORTRAIT;
        return mMultiMedias.get( MediaType.NORMAL ).getUrl();
    }

    @Override
    public String getArticleShortUrl() {
        return mShortUrl;
    }

    // ========================================================================================== //

}
