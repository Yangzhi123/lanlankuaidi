package com.example.lanlankuaidi;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class sendFragment extends Fragment {

    private EditText Et_na, Et_nu, Et_money, Et_remarks, Et_origin, Et_final, Et_weight;
    private Button Bt_se;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send, container, false);

        User b = BmobUser.getCurrentUser(User.class);

        initView(view);

        Bt_se.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = BmobUser.getCurrentUser(User.class);
                Order post = new Order();
                post.setAuthor(user);
                post.setMoney(Et_money.getText().toString() + "元");
                post.setName(Et_na.getText().toString());
                post.setNumber(Et_nu.getText().toString());
                post.setState(true);
                post.setRemarks(Et_remarks.getText().toString());
                post.setOrigin(Et_origin.getText().toString());
                post.setZhongd(Et_final.getText().toString());
                post.setWeight(Et_weight.getText().toString() + "千克");
                User user1 = BmobUser.getCurrentUser(User.class);
                user1.setObjectId("81d688fef9");
                post.setName1(user1);

                post.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Toast.makeText(getActivity(), "发布成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "发布失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        return view;
    }

    private void initView(View view) {
        Et_money = (EditText) view.findViewById(R.id.Et_money);
        Et_na = (EditText) view.findViewById(R.id.Et_na);
        Et_nu = (EditText) view.findViewById(R.id.Et_nu);
        Bt_se = (Button) view.findViewById(R.id.Bt_se);
        Et_remarks = (EditText) view.findViewById(R.id.Et_remarks);
        Et_origin = (EditText) view.findViewById(R.id.Et_origin);
        Et_final = (EditText) view.findViewById(R.id.Et_final);
        Et_weight = (EditText) view.findViewById(R.id.Et_weight);
    }
}
