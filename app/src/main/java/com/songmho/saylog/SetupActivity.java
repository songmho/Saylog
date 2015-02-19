package com.songmho.saylog;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by songmho on 2015-02-19.
 */
public class SetupActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Setup");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff3598db));

    }
}
