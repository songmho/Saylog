package com.songmho.saylog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by songmho on 2015-02-17.
 */
public class DetailActivity extends ActionBarActivity {

    String classname;
    String saying_str;
    String source_str;
    String date_str;
    Intent intent;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //ActionBar setting
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff3598db));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Saying");

        //뷰 선언 및 초기화
        intent=getIntent();
        final TextView saying=(TextView)findViewById(R.id.saying);
        final TextView source=(TextView)findViewById(R.id.source);
        final TextView date=(TextView)findViewById(R.id.date);
        progressBar=(ProgressBar)findViewById(R.id.progress);

        classname= getClassname();
        saying_str=intent.getStringExtra("saying");
        source_str=intent.getStringExtra("source");
        date_str=intent.getStringExtra("date");

        MakeView(saying, source, date);
    }

    private void MakeView(final TextView saying, final TextView source, final TextView date) {
        progressBar.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                ParseQuery<ParseObject> query=ParseQuery.getQuery(classname);
                query.whereContains("saying", saying_str);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> parseObjects, ParseException e) {
                        saying.setText(parseObjects.get(0).getString("saying"));
                        source.setText(parseObjects.get(0).getString("source"));
                        date.setText(parseObjects.get(0).getString("date"));
                    }
                });
            }
        }).start();

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_detail,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:            //삭제하는 경우
                ParseQuery<ParseObject> query=ParseQuery.getQuery(classname);
                query.whereContains("saying",saying_str);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> parseObjects, ParseException e) {
                        try {
                            parseObjects.get(0).delete();
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
                Toast.makeText(getApplicationContext(),"Delete it.",Toast.LENGTH_SHORT).show();
                finish();
                break;

            case R.id.action_fix:            //고치는 경우
                Intent add=new Intent(DetailActivity.this,AddnFixActivity.class);
                add.putExtra("state","fix");
                add.putExtra("saying",intent.getStringExtra("saying"));
                add.putExtra("source",intent.getStringExtra("source"));
                add.putExtra("date",intent.getStringExtra("date"));
                startActivity(add);
                break;

            case R.id.action_share:            //공유하는 경우
                Intent share_intent=new Intent();
                share_intent.setAction(Intent.ACTION_SEND);
                share_intent.putExtra(Intent.EXTRA_TEXT,saying_str+" -"+source_str);
                share_intent.setType("text/plain");
                startActivity(share_intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private String getClassname() {
        String class_name;
        SharedPreferences pref=getSharedPreferences("login_info", Context.MODE_PRIVATE);
        class_name=pref.getString("classname","");

        return class_name;
    }
}
