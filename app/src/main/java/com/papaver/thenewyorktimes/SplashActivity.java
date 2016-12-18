package com.papaver.thenewyorktimes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.papaver.thenewyorktimes.activity.StoryListActivity;
import com.papaver.thenewyorktimes.resource.ResourceManager;

public class SplashActivity extends AppCompatActivity {

    // ========================================================================================== //
    private final boolean DEBUG_FLAG = true;
    private final String DEBUG_TAG = this.getClass().getSimpleName();

    // ========================================================================================== //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        goToNextActivity();
        ResourceManager.get(App.get()).request();
    }

    // ========================================================================================== //
    private void goToNextActivity() {
        Intent intent = new Intent(this, StoryListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        finish();
    }

    // ========================================================================================== //

}
