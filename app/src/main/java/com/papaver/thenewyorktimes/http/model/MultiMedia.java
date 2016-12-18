package com.papaver.thenewyorktimes.http.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Office on 2016-12-16.
 */

public class MultiMedia implements Parcelable {

    @SerializedName("url")
    private String mUrl;
    @SerializedName("format")
    private String mFormat;
    @SerializedName("height")
    private int mHeight;
    @SerializedName("width")
    private int mWidth;
    @SerializedName("image")
    private String mType;
    @SerializedName("photo")
    private String mSubType;
    @SerializedName("caption")
    private String mCaption;
    @SerializedName("copyright")
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

    // ========================================================================================== //
    public MultiMedia(Parcel in) {
        mUrl = in.readString();
        mFormat = in.readString();
        mHeight = in.readInt();
        mWidth = in.readInt();
        mType = in.readString();
        mSubType = in.readString();
        mCaption = in.readString();
        mCopyright = in.readString();
    }

    public static final Creator<MultiMedia> CREATOR = new Creator<MultiMedia>() {
        @Override
        public MultiMedia createFromParcel(Parcel in) {
            return new MultiMedia(in);
        }

        @Override
        public MultiMedia[] newArray(int size) {
            return new MultiMedia[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(mUrl);
        dest.writeString(mFormat);
        dest.writeInt(mHeight);
        dest.writeInt(mWidth);
        dest.writeString(mType);
        dest.writeString(mSubType);
        dest.writeString(mCaption);
        dest.writeString(mCopyright);
    }


    // ========================================================================================== //
}
