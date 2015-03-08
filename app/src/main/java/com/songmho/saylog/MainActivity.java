package com.songmho.saylog;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.parse.ParseUser;

public class MainActivity extends ActionBarActivity {

    ActionBarDrawerToggle drawerToggle;
    FragmentTransaction fragmentTransaction;
    Fragment cur_fragment=new ListFragment();
    RecyclerView.LayoutManager layoutManager;
    String[] drawer_list_list= new String[]{"mypage","setup","about"};

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

        TextView username=(TextView)findViewById(R.id.username);
        username.setText(getUsername());

        RecyclerView drawer_list=(RecyclerView)findViewById(R.id.drawer_list);
        layoutManager=new LinearLayoutManager(this);
        drawer_list.setLayoutManager(layoutManager);
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close){         //drawerToggle 선언
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(ParseUser.getCurrentUser()==null)           //로그인이 되어있지 않을 때
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };


        //어뎁터 및 리스너 설정
        drawerLayout.setDrawerListener(drawerToggle);
        fragmentTransaction.replace(R.id.container, cur_fragment);
        fragmentTransaction.commit();
        drawer_list.setAdapter(new RecyclerAdapter(getApplicationContext(),drawer_list_list,R.layout.item_drawerlist));
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                   //추가 버튼 누르는 경우
                if(ParseUser.getCurrentUser()!=null) {      //로그인이 되어있을 경우
                    Intent add = new Intent(MainActivity.this, AddnFixActivity.class);
                    add.putExtra("state", "add");
                    startActivity(add);
                }
                else                                           //로그인 되어있지 않을 경우
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
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
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private String getUsername() {
        String username;
        SharedPreferences pref=getSharedPreferences("login_info", Context.MODE_PRIVATE);
        username=pref.getString("name","");                       //이름 가져옴.

        return username;
    }
}
