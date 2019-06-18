package com.example.lanlankuaidi;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class FirstActivity extends AppCompatActivity {

    private int i =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Bmob.initialize(this,"6ecac23e20031114a23933b7925a671f");

        if (BmobUser.isLogin()) {
            i = 1;
        } else {
            i = 0;
        }

        new Handler().postDelayed(new Runnable() {
            public void run() {
                /* Create an Intent that will start the Main WordPress Activity. */
                if (i == 1){
                    Intent mainIntent = new Intent(FirstActivity.this, MainActivity.class);
                    FirstActivity.this.startActivity(mainIntent);
                    FirstActivity.this.finish();
                }
                else{
                    Intent i = new Intent(FirstActivity.this,LoginActivity.class);
                    startActivity(i);
                    FirstActivity.this.finish();
                }
            }
        }, 1000);
    }
}
