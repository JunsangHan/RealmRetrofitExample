package com.papaver.thenewyorktimes.resource.model;

import io.realm.RealmObject;

/**
 * Created by Office on 2016-12-17.
 */

public class RealmMultiMedia extends RealmObject {

    private String mUrl;
    private String mFormat;
    private int mHeight;
    private int mWidth;
    private String mType;
    private String mSubType;
    private String mCaption;
    private String mCopyright;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getFormat() {
        return mFormat;
    }

    public void setFormat(String format) {
        mFormat = format;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getSubType() {
        return mSubType;
    }

    public void setSubType(String subType) {
        mSubType = subType;
    }

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }

    public String getCopyright() {
        return mCopyright;
    }

    public void setCopyright(String copyright) {
        mCopyright = copyright;
    }

}
