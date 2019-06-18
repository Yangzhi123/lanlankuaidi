package com.example.lanlankuaidi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class getFragment extends Fragment implements View.OnClickListener{

    private TextView money1,origin1,remarks1,weight1,fin,c,d,e,f,g;
    private Button sure,abandon;
    private String id;
    private int i = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_get, container, false);
        initView(view);
        loadDate();

        return view;
    }

    private void initView(View view){
        money1 = (TextView) view.findViewById(R.id.Tv_mo);
        origin1 = (TextView) view.findViewById(R.id.Tv_or);
        remarks1 = (TextView) view.findViewById(R.id.Tv_re);
        weight1 = (TextView) view.findViewById(R.id.Tv_we);
        fin = (TextView) view.findViewById(R.id.Tv_fi);
        sure = (Button) view.findViewById(R.id.Bt_se);
        abandon = (Button) view.findViewById(R.id.Bt_abandon);
        c = (TextView) view.findViewById(R.id.c);
        d = (TextView) view.findViewById(R.id.d);
        e = (TextView) view.findViewById(R.id.e);
        f = (TextView) view.findViewById(R.id.f);
        g = (TextView) view.findViewById(R.id.g);
        sure.setOnClickListener(this);
        abandon.setOnClickListener(this);
    }

    private void loadDate(){
        BmobQuery<Order> bmobQuery = new BmobQuery<Order>();
        bmobQuery.addWhereEqualTo("state",true);
        bmobQuery.addWhereEqualTo("name1","81d688fef9");
        bmobQuery.setLimit(50);
        bmobQuery.findObjects(new FindListener<Order>() {
            @Override
            public void done(final List<Order> list, BmobException e) {
                if (e == null){
                    User myUser = BmobUser.getCurrentUser(User.class);
                    if (myUser.getObjectId() != list.get(i).getAuthor().getObjectId()){
                        money1.setText(list.get(i).getMoney());
                        origin1.setText(list.get(i).getOrigin());
                        remarks1.setText(list.get(i).getRemarks());
                        weight1.setText(list.get(i).getWeight());
                        fin.setText(list.get(i).getZhongd());
                        id = list.get(i).getObjectId();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Bt_se:
                Order p = new Order();
                User user = BmobUser.getCurrentUser(User.class);
                p.setState(false);
                p.setName1(user);
                p.update(id, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null){
                            Toast.makeText(getActivity().getApplication(),"接单成功",Toast.LENGTH_SHORT).show();
                            i++;
                            loadDate();
                        }
                    }
                });
                break;
            case R.id.Bt_abandon:
                i++;
                loadDate();
                break;
        }
    }
}
