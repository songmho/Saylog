package com.songmho.saylog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Calendar;
import java.util.List;

/**
 * Created by songmho on 2015-02-17.
 */
public class AddnFixActivity extends ActionBarActivity implements View.OnClickListener {
    TextView text_date;
    EditText et_saying;
    EditText et_source;
    int cur_year;
    int cur_mon;
    int cur_day;
    String preAct_state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnfix);

        preAct_state=getIntent().getStringExtra("state");

        //ActionBar setting
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff3598db));
        getSupportActionBar().setHomeButtonEnabled(true);
        if(preAct_state.equals("add"))
            getSupportActionBar().setTitle("Add");
        else if(preAct_state.equals("fix"))
            getSupportActionBar().setTitle("Fix");

        //뷰 선언 및 초기화
        et_saying=(EditText)findViewById(R.id.et_saying);
        et_source=(EditText)findViewById(R.id.et_source);
        text_date=(TextView)findViewById(R.id.text_date);
        Button bt_date=(Button)findViewById(R.id.bt_date);
        Button bt_add=(Button)findViewById(R.id.bt_add);

        //리스너 및 어뎁터 설정
        bt_date.setOnClickListener(this);
        bt_add.setOnClickListener(this);
        if(preAct_state.equals("add")) {
            text_date.setText("" + Calendar.getInstance().get(Calendar.YEAR) + "." +
                    Calendar.getInstance().get(Calendar.MONTH) + "." +
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        }
        else if(preAct_state.equals("fix")){
            et_saying.setText(getIntent().getStringExtra("saying"));
            et_source.setText(getIntent().getStringExtra("source"));
            text_date.setText(getIntent().getStringExtra("date"));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_date:
                cur_year= Calendar.getInstance().get(Calendar.YEAR);
                cur_mon=Calendar.getInstance().get(Calendar.MONTH);
                cur_day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

                Dialog date_picker=new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        text_date.setText(""+cur_year+"."+cur_mon+"."+ cur_day);
                    }
                },cur_year,cur_mon,cur_day);
                date_picker.show();
                break;
            case R.id.bt_add:
                if(preAct_state.equals("add"))
                    add();
                else if(preAct_state.equals("fix"))
                    fix();

                finish();
                break;
            default:
                break;
        }
    }

    private void fix() {        //class에서 찾아서 fix시키는 메소드
        ParseQuery<ParseObject> query=ParseQuery.getQuery(getpref());
        query.whereContains("saying",getIntent().getStringExtra("saying"));
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                parseObjects.get(0).put("saying",String.valueOf(et_saying.getText()));
                parseObjects.get(0).put("source",String.valueOf(et_source.getText()));
                parseObjects.get(0).put("year",cur_year);
                parseObjects.get(0).put("mon",cur_mon);
                parseObjects.get(0).put("day",cur_day);
                parseObjects.get(0).put("date",text_date.getText());
                parseObjects.get(0).put("islist","true");
                parseObjects.get(0).saveInBackground();
            }
        });
        Toast.makeText(getApplicationContext(), "fix it", Toast.LENGTH_SHORT).show();
    }

    private void add() {        //class에 add하는 메소드
        ParseObject user_obj=new ParseObject(getpref());
        user_obj.put("saying",String.valueOf(et_saying.getText()));
        user_obj.put("source",String.valueOf(et_source.getText()));
        user_obj.put("year",cur_year);
        user_obj.put("mon",cur_mon);
        user_obj.put("day",cur_day);
        user_obj.put("date",text_date.getText());
        user_obj.put("islist","true");
        user_obj.saveInBackground();
        Toast.makeText(getApplicationContext(), "add it", Toast.LENGTH_SHORT).show();
    }

    private String getpref() {
        String class_name;
        SharedPreferences pref=getSharedPreferences("login_info", Context.MODE_PRIVATE);
        class_name=pref.getString("classname","");                       //클래스 이름 가져옴.

        return class_name;
    }
}
