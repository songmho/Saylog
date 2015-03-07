package com.songmho.saylog;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by songmho on 2015-02-21.
 */
public class MypageActivity extends ActionBarActivity {

    SharedPreferences pref;
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
        Button logout=(Button)findViewById(R.id.logout);
        pref=getSharedPreferences("login_info", Context.MODE_PRIVATE);

        username.setText(getUsername());
        ParseQuery<ParseObject> query=ParseQuery.getQuery(getClassname());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                number.setText("" + (parseObjects.size()-1));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                prefe_delete();
                finish();
            }
        });

    }

    private void prefe_delete() {
        SharedPreferences.Editor info_pref=pref.edit();
        info_pref.clear();
        info_pref.commit();
    }

    private String getUsername() {
        String username;
        username=pref.getString("name","");                       //이름 가져옴.

        return username;
    }
    private String getClassname() {
        String class_name;
        class_name=pref.getString("classname","");

        return class_name;
    }
}
