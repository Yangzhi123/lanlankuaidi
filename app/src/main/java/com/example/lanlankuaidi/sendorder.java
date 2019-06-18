package com.example.lanlankuaidi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class sendorder extends AppCompatActivity {

    private ListView mylist;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendorder);

        Bmob.initialize(this,"6ecac23e20031114a23933b7925a671f");

        mylist = (ListView) findViewById(R.id.listv);
        loadDate();
    }

    private void loadDate(){
        User user = BmobUser.getCurrentUser(User.class);
        BmobQuery<Order> query = new BmobQuery<Order>();
        query.addWhereEqualTo("author", user.getObjectId());
        query.findObjects(new FindListener<Order>() {
            @Override
            public void done(final List<Order> list, BmobException e) {
                if (e == null){
                    adapter = new MyAdapter(list,getApplicationContext());
                    mylist.setAdapter(adapter);
                    mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            loadDate();
                        }
                    });
                }
            }
        });
    }
}
