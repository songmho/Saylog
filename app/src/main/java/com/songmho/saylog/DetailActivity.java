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

        classname=getpref();
        saying_str=intent.getStringExtra("saying");
        source_str=intent.getStringExtra("source");
        date_str=intent.getStringExtra("date");

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
            case R.id.action_delete:
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

            case R.id.action_fix:
                Toast.makeText(getApplicationContext(),"fix",Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_share:
                Intent share_intent=new Intent();
                share_intent.setAction(Intent.ACTION_SEND);
                share_intent.putExtra(Intent.EXTRA_TEXT,saying_str+" -"+source_str);
                share_intent.setType("text/plain");
                startActivity(share_intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private String getpref() {
        String class_name;
        SharedPreferences pref=getSharedPreferences("login_info", Context.MODE_PRIVATE);
        class_name=pref.getString("classname","");

        return class_name;
    }
}
