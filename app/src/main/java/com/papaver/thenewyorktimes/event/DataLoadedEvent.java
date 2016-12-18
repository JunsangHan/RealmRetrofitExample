package com.papaver.thenewyorktimes.event;

import com.papaver.thenewyorktimes.adapter.Item;

import java.util.List;

/**
 * Created by Office on 2016-12-17.
 */

public class DataLoadedEvent {

    private boolean mIsSuccess;
    private List<Item> mItems;

    public DataLoadedEvent(boolean isSuccess, List<Item> items) {
        mIsSuccess = isSuccess;
        mItems = items;
    }

    public boolean isSuccess() {
        return mIsSuccess;
    }

    public void setSuccess(boolean success) {
        mIsSuccess = success;
    }

    public List<Item> getItems() {
        return mItems;
    }

    public void setItems(List<Item> items) {
        mItems = items;
    }

}
