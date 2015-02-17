package com.songmho.saylog;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by songmho on 2015-02-17.
 */
public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //ActionBar setting
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff3598db));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Saying");

        //뷰 선언 및 초기화
        Intent intent=getIntent();
        TextView saying=(TextView)findViewById(R.id.saying);
        TextView source=(TextView)findViewById(R.id.source);
        TextView date=(TextView)findViewById(R.id.date);

        saying.setText(intent.getStringExtra("saying"));
        source.setText(intent.getStringExtra("source"));
        date.setText(intent.getStringExtra("date"));

    }
}
