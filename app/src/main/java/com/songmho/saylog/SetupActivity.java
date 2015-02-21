package com.songmho.saylog;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by songmho on 2015-02-19.
 */
public class SetupActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        //actionbar 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Setup");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff3598db));

        //view 초기화
        final Spinner spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.list,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setSelection(2);
        Log.d("df",""+getSort());

        //Adapter 설정
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        setSort(0);
                        spinner.setSelection(0);
                        break;
                    case 1:
                        spinner.setSelection(1);
                        setSort(1);
                        break;
                    case 2:
                        spinner.setSelection(2);
                        setSort(2);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setSort(int i) {
        SharedPreferences pref_login=getSharedPreferences("login_info",MODE_PRIVATE);
        final SharedPreferences.Editor editor=pref_login.edit();
        switch (i){
            case 0:
                editor.putString("sorting","time");
                break;
            case 1:
                editor.putString("sorting","saying");
                break;
            case 2:
                editor.putString("sorting","source");
                break;
        }
        editor.commit();
    }

    private int getSort() {
        String sort;
        SharedPreferences pref=getSharedPreferences("login_info", Context.MODE_PRIVATE);
        sort=pref.getString("sorting","");
        switch (sort) {
            case "time":
                return 0;
            case "saying":
                return 1;
            case "source":
                return 2;
            default:
                return -1;
        }
    }

}
