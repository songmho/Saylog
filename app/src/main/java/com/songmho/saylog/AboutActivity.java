package com.songmho.saylog;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by songmho on 2015-02-20.
 */
public class AboutActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //ActionBar setting
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff3598db));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("About");

        TextView version=(TextView)findViewById(R.id.version);
        PackageInfo packageInfo = null;
        try {
            packageInfo=getPackageManager().getPackageInfo(getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String versionname=packageInfo.versionName;
        version.setText(versionname);
    }
}
