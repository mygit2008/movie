package com.bw.Movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.Movie.R;
import com.bw.Movie.activity.ScheduleActivity;
import com.bw.Movie.bean.CinemasListBean;
import com.bw.Movie.bean.NewMessageBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MyCinemasListAdapter extends RecyclerView.Adapter<MyCinemasListAdapter.MyViewHolder>{
    private Context context;
    private List<CinemasListBean.ResultBean> list;
    private String mid;

    public MyCinemasListAdapter(Context context, List<CinemasListBean.ResultBean> list, String mid) {
        this.context = context;
        this.list = list;
        this.mid = mid;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cinemaslist_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getLogo()).into(holder.tu);

        holder.name.setText(list.get(position).getName());
        holder.juti.setText(list.get(position).getAddress());
        holder.cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewMessageBean be = new NewMessageBean(mid,list.get(position).getId()+"",null,null,list.get(position).getName(),null);
                EventBus.getDefault().postSticky(be);
                context.startActivity(new Intent(context, ScheduleActivity.class));

            }
        });
        }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView name,juti;
        private Button cha;
        private ImageView tu;

        public MyViewHolder(View itemView) {
            super(itemView);
            juti = itemView.findViewById(R.id.cinemaslist_juti);
            name = itemView.findViewById(R.id.cinemaslist_name);
            cha = itemView.findViewById(R.id.cinemaslist_cha);
            tu = itemView.findViewById(R.id.cinemaslist_tu);

        }
    }
}
