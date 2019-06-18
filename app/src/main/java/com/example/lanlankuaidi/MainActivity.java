package com.example.lanlankuaidi;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private getFragment getFragment;
    private sendFragment sendFragment;
    private Button getm,sendm;
    private TextView Tv_name, textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bmob.initialize(this,"6ecac23e20031114a23933b7925a671f");

        User b = BmobUser.getCurrentUser(User.class);
        Tv_name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.Tv_name);
        textView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.Tv_number);
        Tv_name.setText(b.getUsername().toString());
        textView.setText(b.getMobilePhoneNumber().toString());



        initView();
    }

    private void initView(){
        getm = (Button) findViewById(R.id.get);
        sendm = (Button) findViewById(R.id.send);
        getm.setOnClickListener(this);
        sendm.setOnClickListener(this);
        getm.setBackgroundColor(0xfff06345);
        getm.setTextColor(0xffffffff);
        sendm.setTextColor(0xffffffff);
        sendm.setBackgroundColor(0xff343434);

        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        getm.setBackgroundColor(0xfff06345);
        sendm.setBackgroundColor(0xff343434);
        getFragment = new getFragment();
        transaction.replace(R.id.id_content, getFragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(MainActivity.this, sendorder.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(MainActivity.this, myorder.class);
            startActivity(intent);
        } else if (id == R.id.nav_home) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {
            Intent i1 = new Intent(MainActivity.this,AboutUs.class);
            startActivity(i1);
        } else if (id == R.id.nav_send) {
            BmobUser.logOut();   //清除缓存用户对象
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();

        switch (v.getId())
        {
            case R.id.get:
                getm.setBackgroundColor(0xfff06345);
                sendm.setBackgroundColor(0xff343434);
                getFragment = new getFragment();
                transaction.replace(R.id.id_content, getFragment);
//                transaction.show(getFragment).hide(sendFragment);
                break;
            case R.id.send:
                getm.setBackgroundColor(0xff343434);
                sendm.setBackgroundColor(0xfff06345);
                if (sendFragment == null)
                {
                    sendFragment = new sendFragment();
                }
                // 使用当前Fragment的布局替代id_content的控件
                transaction.replace(R.id.id_content, sendFragment);
//                transaction.show(sendFragment).hide(getFragment);
                break;
        }
        // transaction.addToBackStack();
        // 事务提交
        transaction.commit();
    }
}
