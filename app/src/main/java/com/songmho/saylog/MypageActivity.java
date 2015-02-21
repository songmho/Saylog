package com.songmho.saylog;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by songmho on 2015-02-21.
 */
public class MypageActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        //ActionBar setting
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff3598db));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Mypage");

        TextView username=(TextView)findViewById(R.id.username);
        final TextView number=(TextView)findViewById(R.id.number);

        username.setText(getUsername());
        ParseQuery<ParseObject> query=ParseQuery.getQuery(getClassname());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                number.setText("" + (parseObjects.size()-1));
            }
        });

    }

    private String getUsername() {
        String username;
        SharedPreferences pref=getSharedPreferences("login_info", Context.MODE_PRIVATE);
        username=pref.getString("name","");                       //이름 가져옴.

        return username;
    }
    private String getClassname() {
        String class_name;
        SharedPreferences pref=getSharedPreferences("login_info", Context.MODE_PRIVATE);
        class_name=pref.getString("classname","");

        return class_name;
    }
}
