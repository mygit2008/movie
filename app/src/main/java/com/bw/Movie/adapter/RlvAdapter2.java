package com.bw.Movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.Movie.R;
import com.bw.Movie.bean.AllBean;
import com.bw.Movie.interview.RlvItemClick;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.List;

/**
 * Created by BoyLucky on 2018/8/4.
 */

public class RlvAdapter2 extends RecyclerView.Adapter {
    private Context context;
    private List<AllBean.ResultBean> list;
    private RlvItemClick rlvItemClick;

    public void setRlvItemClick(RlvItemClick rlvItemClick) {
        this.rlvItemClick = rlvItemClick;
    }

    public RlvAdapter2(Context context, List<AllBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //找到布局
        View view = View.inflate(parent.getContext(), R.layout.child_item, null);
        ScreenAdapterTools.getInstance().loadView((ViewGroup) view);
        Myviewholder myviewholder = new Myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Myviewholder myviewholder = (Myviewholder) holder;
        myviewholder.tv_name.setText(list.get(position).getName());
        myviewholder.adress.setText(list.get(position).getAddress());
//        String logo = list.get(position).getLogo();
//        if (logo!=null){
//            Glide.with(context).load(logo).into(myviewholder.logo);
//        }
//        int distance = list.get(position).getDistance();
//        int i = distance / 1000;
        myviewholder.adress_tv.setVisibility(View.GONE);
        myviewholder.rl_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlvItemClick.rlvOnItemClick(v,list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class Myviewholder extends RecyclerView.ViewHolder {
        TextView tv_name,adress,adress_tv;
        RelativeLayout rl_out;
        ImageView logo;
        public Myviewholder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            adress = itemView.findViewById(R.id.adress);
            rl_out = itemView.findViewById(R.id.rl_out);
//            logo = itemView.findViewById(R.id.logo);
            adress_tv = itemView.findViewById(R.id.adress_tv);
        }
    }
}
