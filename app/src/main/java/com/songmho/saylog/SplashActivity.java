package com.songmho.saylog;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by songmho on 2015-02-15.
 */
public class SplashActivity extends Activity {
    int DELAYED=2000;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar=(ProgressBar)findViewById(R.id.progress);

        progressBar.setVisibility(View.VISIBLE);

        handler();
    }

    private void handler() {
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               login();
           }
       },DELAYED);
    }

    private void login() {
        SharedPreferences pref_login=getSharedPreferences("login_info", MODE_PRIVATE);
        String email = pref_login.getString("email", "");
        String password = pref_login.getString("password", "");
        ParseUser.logInInBackground(email, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                 Class[] finalC = new Class[1];
                finalC[0] =MainActivity.class;
                finishSplash(finalC[0]);
            }
        });
    }

    private void finishSplash(Class finalC) {
        overridePendingTransition(0, android.R.anim.fade_in);
        startActivity(new Intent(SplashActivity.this, finalC));
        finish();
        progressBar.setVisibility(View.GONE);
    }
}
