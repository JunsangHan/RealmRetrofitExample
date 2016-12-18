package com.papaver.thenewyorktimes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.papaver.thenewyorktimes.R;
import com.papaver.thenewyorktimes.adapter.holder.StoryViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.papaver.thenewyorktimes.adapter.ItemType.CONTENT_LANDSCAPE;
import static com.papaver.thenewyorktimes.adapter.ItemType.CONTENT_ONLY_TEXT;
import static com.papaver.thenewyorktimes.adapter.ItemType.CONTENT_PORTRAIT;

/**
 * Created by Office on 2016-12-17.
 */

public class StoryAdapter extends RecyclerView.Adapter {

    // ========================================================================================== //
    private final boolean DEBUG_FLAG = true;
    private final String DEBUG_TAG = this.getClass().getSimpleName();

    // ========================================================================================== //
    public interface OnResultItemClickListener {
        void onItemClick(Item item, int position);
    }

    // ========================================================================================== //
    private final List<Item> mItems = new ArrayList<>();
    private OnResultItemClickListener mListener;

    public StoryAdapter(List<Item> items, OnResultItemClickListener listener) {
        if ( items != null ) {
            mItems.addAll(items);
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
            Item item = mItems.get(position);

            if ( mListener != null ) {
                mListener.onItemClick(item, position);
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

        //Result result = mResults.get(position);
        Item item = mItems.get(position);
        if ( item == null ) {
            return;
        }

        vh.mTextView.setText( "" + item.getTitleOfItem() );
        if ( viewType == ItemType.CONTENT_ONLY_TEXT ) {
            return;
        }

        if ( item.isTextItem() ) {
            return;
        }

        //Logger.e(DEBUG_FLAG, DEBUG_TAG + ", media size = " + medias.size());
        String url = item.getMediaUrl();
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

    // ========================================================================================== //
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if ( position < 0 || position > mItems.size() - 1 ) {
            return 0;
        }

        Item item = mItems.get(position);
        if ( item == null ) {
            return 0;
        }

        if ( item.isTextItem() ) {
            return ItemType.CONTENT_ONLY_TEXT;
        }

        return item.isLandScape() ? ItemType.CONTENT_LANDSCAPE : ItemType.CONTENT_PORTRAIT;
    }

    // ========================================================================================== //
    public void setItems(List<Item> items) {
        mItems.clear();
        if ( items != null ) {
            mItems.addAll(items);
        }
        notifyDataSetChanged();
    }

    // ========================================================================================== //

}
