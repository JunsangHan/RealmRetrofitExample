package com.papaver.thenewyorktimes.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.papaver.thenewyorktimes.R;

/**
 * Created by Office on 2016-12-17.
 */

public class StoryViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageView;
    public TextView mTextView;

    public StoryViewHolder(View itemView) {
        super(itemView);
        mImageView = (ImageView) itemView.findViewById(R.id.imageview);
        mTextView = (TextView) itemView.findViewById(R.id.textview);
    }

}
