package com.example.lanlankuaidi;

import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private EditText Et_sendmg,Et_name,Et_sendpd,Et_mg;
    private Button Bt_test,Bt_register;
    private ImageButton imageButton;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Bmob.initialize(this,"6ecac23e20031114a23933b7925a671f");

        initView();
    }

    private void initView(){
        imageButton = (ImageButton) findViewById(R.id.Ib_photo1);
        Et_mg = (EditText) findViewById(R.id.Et_mg);
        Et_sendpd = (EditText) findViewById(R.id.Et_sendpd);
        Et_name = (EditText) findViewById(R.id.Et_name);
        Et_sendmg = (EditText) findViewById(R.id.Et_sendmg);
        Bt_register = (Button) findViewById(R.id.Bt_register);
        Bt_test = (Button) findViewById(R.id.Bt_test);
        imageButton.setOnClickListener(this);
        Bt_test.setOnClickListener(this);
        Bt_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String code = Et_mg.getText().toString();
        String password = Et_sendpd.getText().toString();
        String name = Et_name.getText().toString();
        String number = Et_sendmg.getText().toString();

        switch (v.getId()){
            case R.id.Bt_test:
                if (number.length() == 0) {
                    Toast.makeText(Register.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (number.length() != 11) {
                    Toast.makeText(Register.this, "请输入11位有效号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    BmobSMS.requestSMSCode(number, "", new QueryListener<Integer>() {
                        @Override
                        public void done(Integer smsId, BmobException e) {
                            if (e == null) {
                                Bt_test.setClickable(false);
                                Toast.makeText(Register.this, "验证码发送成功，请尽快使用", Toast.LENGTH_SHORT).show();
                                new CountDownTimer(60000, 1000) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        //Message_btn.setBackgroundResource(R.drawable.button_shape02);
                                        Bt_test.setText(millisUntilFinished / 1000 + "秒");
                                    }
                                    @Override
                                    public void onFinish() {
                                        Bt_test.setClickable(true);
                                        //Message_btn.setBackgroundResource(R.drawable.button_shape);
                                        Bt_test.setText("重新发送");
                                    }
                                }.start();
                            } else {
                                Toast.makeText(Register.this, "验证码发送失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
            case R.id.Bt_register:
                if (number.equals("") || password.equals("")) {
                    Toast.makeText(this, "手机号或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (code.length() == 0) {
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (number.length() != 11) {
                    Toast.makeText(this, "请输入11位有效号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = new User();
                //设置手机号码（必填）
                user.setMobilePhoneNumber(number);
                //设置用户名，如果没有传用户名，则默认为手机号码
                user.setUsername(name);
                //设置用户密码
                user.setPassword(password);
                //设置额外信息：此处为年龄
                user.setAge(18);
                user.signOrLogin(code, new SaveListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if (e == null) {
                            Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(Register.this, "验证码错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }
}
