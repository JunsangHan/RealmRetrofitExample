package com.papaver.thenewyorktimes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.papaver.thenewyorktimes.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoryActivity extends AppCompatActivity {

    // ========================================================================================== //
    private final boolean DEBUG_FLAG = true;
    private final String DEBUG_TAG = this.getClass().getSimpleName();

    // ========================================================================================== //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        ButterKnife.bind(this);

        getIntentData();
        initWebView();
    }

    // ========================================================================================== //
    //private Result mResult;
    private String mUrl;

    private void getIntentData() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");

        //mResult = intent.getParcelableExtra("result");
        /*
        if ( mResult == null ) {
            Logger.e(DEBUG_FLAG, DEBUG_TAG + ", getIntentData(), result is null;");
            return;
        }
        Logger.e(DEBUG_FLAG, DEBUG_TAG + ", getIntentData(), result = " + mResult.getTitle());
        */
    }

    // ========================================================================================== //
    @Bind(R.id.webview)
    protected WebView mWebView;

    private void initWebView() {
        mWebView.setWebViewClient( new WebViewClient() );

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        mWebView.loadUrl( mUrl );
    }

    // ========================================================================================== //
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ( (keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack() ) {
            mWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    // ========================================================================================== //

}
