package com.papaver.thenewyorktimes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.papaver.thenewyorktimes.App;
import com.papaver.thenewyorktimes.R;
import com.papaver.thenewyorktimes.adapter.Item;
import com.papaver.thenewyorktimes.adapter.StoryAdapter;
import com.papaver.thenewyorktimes.event.DataLoadedEvent;
import com.papaver.thenewyorktimes.resource.ResourceManager;
import com.papaver.thenewyorktimes.util.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoryListActivity extends AppCompatActivity {

    // ========================================================================================== //
    private final boolean DEBUG_FLAG = true;
    private final String DEBUG_TAG = this.getClass().getSimpleName();

    // ========================================================================================== //
    private boolean mIsActivityInit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_list);
        ButterKnife.bind(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

        /**
         * onCreate()가 아닌 onStart()에서
         * init() 작업을 수행하므로 최초 실행인지 체크.
         */
        if ( mIsActivityInit ) {
            return;
        }

        init();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        mOnResultItemClickListener = null;
        mOnRefreshListener = null;
        mRefreshHandler.removeCallbacks(mRefreshRunnable);
        super.onDestroy();
    }

    // ========================================================================================== //
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataLoadedEvent(DataLoadedEvent event) {
        Logger.e(DEBUG_FLAG, DEBUG_TAG + ", onDataLoadedEvent(), event = " + event.isSuccess());
        mSwipeRefreshLayout.setRefreshing(false);
        if ( !event.isSuccess() ) {
            return;
        }
        mItems.clear();

        List<Item> items = event.getItems();
        if ( items != null ) {
            mItems.addAll(items);
        }
        mRefreshHandler.removeCallbacks(mRefreshRunnable);
        setAdapter();
    }

    // ========================================================================================== //
    private final List<Item> mItems = new ArrayList<>();

    // ========================================================================================== //
    private void init() {
        /**
         * 이벤트 버스 등록하기 전에 read() 하면 타이밍 상 못 받아오는 경우 발생.
         * SplashActivity에서 미리 request() 메소드를 호출하므로
         * 이벤트 버스 등록 전에 Http 작업이 완료되면 데이터 못 받아올 수 있음.
         *
         * onCreate()가 아닌 onStart()에서 init() 수행.
         */
        List<Item> items = ResourceManager.get(App.get()).read();
        if ( items != null ) {
            mItems.addAll(items);
        }

        initSwipeRefreshLayout();
        initAdapter();
        mIsActivityInit = true;
    }

    // ========================================================================================== //
    @Bind(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);
    }

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            ResourceManager.get(App.get()).request();
            /**
             * 극단적으로 오래 걸리는 상황인 경우,
             * Progress 종료.
             */
            mRefreshRunnable = new Runnable() {
                @Override
                public void run() {
                    mRefreshHandler.sendEmptyMessage(0);
                }
            };
            mRefreshHandler.postDelayed(mRefreshRunnable, 5000);
        }
    };

    private Runnable mRefreshRunnable;
    private Handler mRefreshHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Logger.e(DEBUG_FLAG, DEBUG_TAG + ", mRefreshHandler, handleMessage()");
            if ( mSwipeRefreshLayout != null ) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
    };

    // ========================================================================================== //
    private StoryAdapter mAdapter;
    //private TestAdapter mAdapter;
    @Bind(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    private void initAdapter() {
        //mAdapter = new TestAdapter(mResults, mOnResultItemClickListener);
        mAdapter = new StoryAdapter(mItems, mOnResultItemClickListener);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, 1);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void setAdapter() {
        mAdapter.setItems(mItems);
    }

    private StoryAdapter.OnResultItemClickListener mOnResultItemClickListener = new StoryAdapter.OnResultItemClickListener() {
        @Override
        public void onItemClick(Item item, int position) {
            Logger.e(DEBUG_FLAG, DEBUG_TAG + ", onItemClick(), item = " + item.getTitleOfItem());
            goToStoryActivity(item);
            //Logger.e(DEBUG_FLAG, DEBUG_TAG + ", onItemClick(), result = " + result.getTitle());
            //goToStoryActivity(result);
        }
    };

    // ========================================================================================== //
    private void goToStoryActivity(Item item) {
        Intent intent = new Intent(this, StoryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("url", item.getArticleShortUrl());
        startActivity(intent);
    }

    // ========================================================================================== //

}
