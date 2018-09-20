package com.bw.Movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bw.Movie.R;
import com.bw.Movie.bean.PingLunBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.List;

/**
 * Created by BoyLucky on 2018/8/6.
 */

public class PingListAdapter extends BaseAdapter {
    private Context context;
    private  List<PingLunBean.ResultBean> list;

    public PingListAdapter(Context context, List<PingLunBean.ResultBean> list) {
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
            convertView = View.inflate(context, R.layout.lv_item_ping,null);
            ScreenAdapterTools.getInstance().loadView((ViewGroup) convertView);
            holderView.img = convertView.findViewById(R.id.s_img);
            holderView.nick = convertView.findViewById(R.id.nick);
            holderView.ping = convertView.findViewById(R.id.ping);
            convertView.setTag(holderView);
        }else{
            holderView = (HolderView)convertView.getTag();
        }
        holderView.nick.setText(list.get(position).getCommentUserName());
        holderView.ping.setText(list.get(position).getCommentContent());
        if (list.get(position).getCommentHeadPic() != null){
            holderView.img.setImageURI(Uri.parse(list.get(position).getCommentHeadPic()));

        }
        return convertView;
    }
    class HolderView{
        TextView nick,ping;
        SimpleDraweeView img;
    }
}
