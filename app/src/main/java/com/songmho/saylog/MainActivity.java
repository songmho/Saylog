package com.songmho.saylog;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    ActionBarDrawerToggle drawerToggle;
    FragmentTransaction fragmentTransaction;
    Fragment cur_fragment=new ListFragment();
    Boolean isList=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //액션바 설정
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff3598db));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //뷰 선언 및 초기화
        DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        ImageButton bt_add=(ImageButton)findViewById(R.id.bt_add);
        fragmentTransaction=getFragmentManager().beginTransaction();
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        //어뎁터 및 리스너 설정
        drawerLayout.setDrawerListener(drawerToggle);
        fragmentTransaction.replace(R.id.container,cur_fragment);
        fragmentTransaction.commit();
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"add",Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))        //드로어 토글이 선택되었을 때
            return true;

        else if(item.getItemId()==R.id.action_view_frg){     //캘린더 버튼 클릭되었을 때
            changeFragment(item);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }

    private void changeFragment(MenuItem item) {
        fragmentTransaction=getFragmentManager().beginTransaction();

        if(isList) {
            cur_fragment = new CalendarFragment();
            item.setIcon(R.drawable.list);
            isList=false;
        }
        else if(!isList){
            cur_fragment = new ListFragment();
            item.setIcon(R.drawable.calendar);
            isList=true;
        }

        fragmentTransaction.replace(R.id.container, cur_fragment);
        fragmentTransaction.commit();
    }
}
