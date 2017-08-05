package com.altimetrik.altimetrikdemo.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.altimetrik.altimetrikdemo.R;
import com.altimetrik.altimetrikdemo.helpers.SPDSingleton;

/**
 * Splash screen to begin the app with launcher screen
 */

public class SplashScreenActivity extends AppCompatActivity{

    // Splash screen timer
    private static int SPLASH_SCREEN_TIME_OUT = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initScreenTimer();
    }

    /*Method to call Feeds activity after splash screen timer*/
    private void initScreenTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SPDSingleton.getInstance().presentFeedsPage(SplashScreenActivity.this);
                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}
