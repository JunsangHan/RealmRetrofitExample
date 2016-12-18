package com.papaver.thenewyorktimes.adapter;

/**
 * Created by Office on 2016-12-17.
 */

public interface Item {

    String getTitleOfItem();

    boolean isTextItem();
    boolean isLandScape();

    String getMediaUrl();
    String getArticleShortUrl();

}
