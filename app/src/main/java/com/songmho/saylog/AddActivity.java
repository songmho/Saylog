package com.songmho.saylog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.Calendar;

/**
 * Created by songmho on 2015-02-17.
 */
public class AddActivity extends ActionBarActivity implements View.OnClickListener {
    TextView text_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //ActionBar setting
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff3598db));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Add");

        //뷰 선언 및 초기화
        EditText et_saying=(EditText)findViewById(R.id.et_saying);
        EditText et_source=(EditText)findViewById(R.id.et_source);
        text_date=(TextView)findViewById(R.id.text_date);
        Button bt_date=(Button)findViewById(R.id.bt_date);
        Button bt_add=(Button)findViewById(R.id.bt_add);

        //리스너 및 어뎁터 설정
        bt_date.setOnClickListener(this);
        bt_add.setOnClickListener(this);
        text_date.setText(""+Calendar.getInstance().get(Calendar.YEAR)+"."+
                Calendar.getInstance().get(Calendar.MONTH)+"."+
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_date:
                final int cur_year= Calendar.getInstance().get(Calendar.YEAR);
                final int cur_mon=Calendar.getInstance().get(Calendar.MONTH);
                final int cur_day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

                Dialog date_picker=new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        text_date.setText(""+cur_year+"."+cur_mon+"."+ cur_day);
                    }
                },cur_year,cur_mon,cur_day);
                date_picker.show();
                break;
            case R.id.bt_add:
                ParseObject user_obj=new ParseObject("songmho");
                break;
            default:
                break;
        }
    }
}
