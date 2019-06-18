package com.example.lanlankuaidi;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class Newpassword extends AppCompatActivity implements View.OnClickListener {

    private EditText Et_number,Et_sms,Et_newpd;
    private Button Bt_sms,Bt_sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpassword);

        Bmob.initialize(this,"6ecac23e20031114a23933b7925a671f");
        initView();
    }

    private void initView(){
        Et_number = (EditText) findViewById(R.id.Et_number);
        Et_newpd = (EditText) findViewById(R.id.Et_newpd);
        Et_sms = (EditText) findViewById(R.id.Et_sms);
        Bt_sms = (Button) findViewById(R.id.Bt_sms);
        Bt_sure = (Button) findViewById(R.id.Bt_sure);
        Bt_sure.setOnClickListener(this);
        Bt_sms.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String number = Et_number.getText().toString();
        String code = Et_sms.getText().toString();
        String newpd = Et_newpd.getText().toString();

        switch (v.getId()){
            case R.id.Bt_sms:

                if (number.length() == 0) {
                    Toast.makeText(Newpassword.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (number.length() != 11) {
                    Toast.makeText(Newpassword.this, "请输入11位有效号码", Toast.LENGTH_SHORT).show();
                    return;
                }

                BmobSMS.requestSMSCode(number, "", new QueryListener<Integer>() {
                    @Override
                    public void done(Integer smsId, BmobException e) {
                        if (e == null) {
                            Bt_sms.setClickable(false);
                            Toast.makeText(Newpassword.this, "验证码发送成功，请尽快使用", Toast.LENGTH_SHORT).show();
                            new CountDownTimer(60000, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    //Message_btn.setBackgroundResource(R.drawable.button_shape02);
                                    Bt_sms.setText(millisUntilFinished / 1000 + "秒");
                                }
                                @Override
                                public void onFinish() {
                                    Bt_sms.setClickable(true);
                                    //Message_btn.setBackgroundResource(R.drawable.button_shape);
                                    Bt_sms.setText("重新发送");
                                }
                            }.start();
                        } else {
                            Toast.makeText(Newpassword.this, "验证码发送失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.Bt_sure:
                if (number.equals("") || newpd.equals("")) {
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

                BmobUser.resetPasswordBySMSCode(code, newpd, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null){
                            Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"重置失败：code ="+e.getErrorCode()+",msg = "+e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }
}
