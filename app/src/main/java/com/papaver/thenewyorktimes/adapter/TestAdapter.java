package com.papaver.thenewyorktimes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.papaver.thenewyorktimes.R;
import com.papaver.thenewyorktimes.adapter.holder.StoryViewHolder;
import com.papaver.thenewyorktimes.http.model.MultiMedia;
import com.papaver.thenewyorktimes.http.model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.papaver.thenewyorktimes.adapter.ItemType.CONTENT_LANDSCAPE;
import static com.papaver.thenewyorktimes.adapter.ItemType.CONTENT_ONLY_TEXT;
import static com.papaver.thenewyorktimes.adapter.ItemType.CONTENT_PORTRAIT;

/**
 * Created by Office on 2016-12-17.
 */

public class TestAdapter extends RecyclerView.Adapter {

    // ========================================================================================== //
    private final boolean DEBUG_FLAG = true;
    private final String DEBUG_TAG = this.getClass().getSimpleName();

    // ========================================================================================== //
    public interface OnResultItemClickListener {
        void onItemClick(Result result, int position);
    }

    // ========================================================================================== //
    private final List<Result> mResults = new ArrayList<Result> ();
    private OnResultItemClickListener mListener;

    public TestAdapter(List<Result> results, OnResultItemClickListener listener) {
        if ( results != null ) {
            mResults.addAll(results);
        }
        mListener = listener;
    }

    // ========================================================================================== //
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch ( viewType ) {
            case CONTENT_LANDSCAPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_landscape, parent, false);
                break;
            case CONTENT_PORTRAIT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_portrait, parent, false);
                break;
            case CONTENT_ONLY_TEXT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_text, parent, false);
                break;
        }

        if ( view == null ) {
            return null;
        }

        StoryViewHolder holder = new StoryViewHolder(view);
        holder.mTextView.setOnClickListener(mOnClickListener);
        holder.mTextView.setTag(holder);

        return holder;
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if ( view == null ) {
                return;
            }

            StoryViewHolder holder = (StoryViewHolder) view.getTag();
            if ( holder == null ) {
                return;
            }

            int position = holder.getAdapterPosition();
            Result result = mResults.get(position);

            if ( mListener != null ) {
                mListener.onItemClick(result, position);
            }

        }
    };

    // ========================================================================================== //
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        StoryViewHolder vh = (StoryViewHolder) holder;
        if ( vh == null ) {
            return;
        }

        Result result = mResults.get(position);
        if ( result == null ) {
            return;
        }

        vh.mTextView.setText( "" + result.getTitle() );
        if ( viewType == ItemType.CONTENT_ONLY_TEXT ) {
            return;
        }

        List<MultiMedia> medias = result.getMultiMedias();
        if ( medias == null || medias.size() == 0 ) {
            return;
        }

        //Logger.e(DEBUG_FLAG, DEBUG_TAG + ", media size = " + medias.size());
        String url = null;
        if ( viewType == ItemType.CONTENT_LANDSCAPE ) {
            url = medias.get(3).getUrl(); // format = "mediumThreeByTwo210";
        } else if ( viewType == ItemType.CONTENT_PORTRAIT ) {
            url = medias.get(2).getUrl(); // format = "Normal";
        }

        if ( url != null ) {
            Picasso.with(vh.mImageView.getContext())
                    .load(url)
                    .placeholder(android.R.color.white)
                    .error(android.R.color.white)
                    .into(vh.mImageView);
        } else {
            Picasso.with(vh.mImageView.getContext())
                    .load(R.color.colorPrimary)
                    .placeholder(android.R.color.white)
                    .error(android.R.color.white)
                    .into(vh.mImageView);
        }

    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    @Override
    public int getItemViewType(int position) {
        if ( position < 0 || position > mResults.size() - 1 ) {
            return 0;
        }

        Result result = mResults.get(position);
        if ( result == null ) {
            return 0;
        }

        List<MultiMedia> medias = result.getMultiMedias();
        if ( medias == null || medias.size() < 5 ) {
            return ItemType.CONTENT_ONLY_TEXT;
        }

        MultiMedia media = medias.get(2); // format = "Normal";
        return media.getWidth() < media.getHeight() ? ItemType.CONTENT_PORTRAIT : CONTENT_LANDSCAPE;
    }

    // ========================================================================================== //

}
