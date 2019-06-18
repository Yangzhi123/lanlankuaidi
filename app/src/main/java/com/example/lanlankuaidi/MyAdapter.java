package com.example.lanlankuaidi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class MyAdapter extends BaseAdapter {

    private List<Order> list;
    private Context context;
    private LayoutInflater layout;

    public MyAdapter(List<Order> list, Context context) {
        Bmob.initialize(context,"6ecac23e20031114a23933b7925a671f");
        this.list = list;
        this.context = context;
        layout = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layout.inflate(R.layout.layout, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.Tv_na1);
            viewHolder.number = (TextView) convertView.findViewById(R.id.Tv_nu1);
            viewHolder.money = (TextView) convertView.findViewById(R.id.Tv_mo);
            viewHolder.origin = (TextView) convertView.findViewById(R.id.Tv_or);
            viewHolder.finish = (TextView) convertView.findViewById(R.id.Tv_fi);
            viewHolder.weight = (TextView) convertView.findViewById(R.id.Tv_we);
            viewHolder.remark = (TextView) convertView.findViewById(R.id.Tv_re);
            viewHolder.fangqi = (Button) convertView.findViewById(R.id.Bt_fangqi12);
            convertView.setTag(viewHolder);
        }
        //获取viewHolder实例
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.number.setText(list.get(position).getNumber());
        viewHolder.money.setText(list.get(position).getMoney());
        viewHolder.origin.setText(list.get(position).getOrigin());
        viewHolder.finish.setText(list.get(position).getZhongd());
        viewHolder.weight.setText(list.get(position).getWeight());
        viewHolder.remark.setText(list.get(position).getRemarks());
        viewHolder.fangqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = BmobUser.getCurrentUser(User.class);
                Order p = new Order();
                p.setObjectId(list.get(position).getObjectId());
                p.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Toast.makeText(context, "退单成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "退单失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        viewHolder.fangqi.getTag();
        return convertView;
    }


    static class ViewHolder {
        TextView name, number, money, origin, finish, weight, remark;
        Button fangqi;
    }
}
