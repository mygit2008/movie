package com.bw.Movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.Movie.R;
import com.bw.Movie.bean.PingLunBean;
import com.bw.Movie.bean.YingBean;
import com.bw.Movie.utils.MyData;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.List;

/**
 * Created by BoyLucky on 2018/8/6.
 */

public class YingListAdapter extends BaseAdapter {
    private Context context;
    private  List<YingBean.ResultBean> list;

    public YingListAdapter(Context context, List<YingBean.ResultBean> list) {
        this.context = context;
        this.list = list;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView = null;
        if (convertView == null){
            holderView = new HolderView();
            convertView = View.inflate(context, R.layout.ying_lv_item,null);
            ScreenAdapterTools.getInstance().loadView((ViewGroup) convertView);
            holderView.img = convertView.findViewById(R.id.img);
            holderView.nick = convertView.findViewById(R.id.tv_name);
            holderView.time = convertView.findViewById(R.id.time);
            holderView.tv_jie = convertView.findViewById(R.id.tv_jie);
            holderView.t_price = convertView.findViewById(R.id.t_price);
            convertView.setTag(holderView);
        }else{
            holderView = (HolderView)convertView.getTag();
        }
        holderView.nick.setText(list.get(position).getName());
        holderView.tv_jie.setText(list.get(position).getSummary());
        String duration = list.get(position).getDuration();
        holderView.time.setText(duration);
        holderView.t_price.setText(list.get(position).getFare()+"元");


        if (list.get(position).getImageUrl()!= null){
            Glide.with(context).load(list.get(position).getImageUrl()).into(holderView.img);
        }
        return convertView;
    }
    class HolderView{
        TextView nick,tv_jie,time,t_price;
        ImageView img;
    }
}
