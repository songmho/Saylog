package com.songmho.saylog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by songmho on 2015-02-15.
 */
public class SplashActivity extends Activity {
    int DELAYED=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler();
    }

    private void handler() {
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               overridePendingTransition(0,android.R.anim.fade_in);
               startActivity(new Intent(SplashActivity.this, MainActivity.class));
               finish();
           }
       },DELAYED);
    }
}
