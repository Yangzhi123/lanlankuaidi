package com.example.lanlankuaidi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText Et_email,Et_password;
    private Button Bt_login,Bt_new,Bt_forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Bmob.initialize(this,"6ecac23e20031114a23933b7925a671f");

        initView();
    }

    private void initView(){
        Et_email = (EditText) findViewById(R.id.Et_email);
        Et_password = (EditText) findViewById(R.id.Et_password);
        Bt_login = (Button) findViewById(R.id.Bt_login);
        Bt_new = (Button) findViewById(R.id.Bt_new);
        Bt_forget = (Button) findViewById(R.id.Bt_forget);
        Bt_forget.setOnClickListener(this);
        Bt_new.setOnClickListener(this);
        Bt_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String email,password;
        password = Et_password.getText().toString();
        email = Et_email.getText().toString();

        switch (v.getId()){
            case R.id.Bt_login:
                BmobUser.loginByAccount(email, password, new LogInListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if(user!=null){
                            if (e == null) {
                                Toast.makeText(getApplicationContext(),"登陆成功",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(),"账号或密码错误", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                break;
            case R.id.Bt_new:
                Intent k = new Intent(LoginActivity.this,Register.class);
                startActivity(k);
                break;
            case R.id.Bt_forget:
                Intent j = new Intent(LoginActivity.this,Newpassword.class);
                startActivity(j);
                break;
        }
    }
}
