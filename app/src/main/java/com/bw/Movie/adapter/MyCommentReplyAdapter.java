package com.bw.Movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.Movie.R;
import com.bw.Movie.bean.CommentFindBean;
import com.bw.Movie.utils.TimeUtil;

import java.util.List;

public class MyCommentReplyAdapter extends RecyclerView.Adapter<MyCommentReplyAdapter.MyViewHolder>{
    private Context context;
    private List<CommentFindBean.ResultBean> list;

    public MyCommentReplyAdapter(Context context, List<CommentFindBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_reply_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getReplyHeadPic()).into(holder.tu);
        holder.name.setText(list.get(position).getReplyUserName());
        holder.juti.setText(list.get(position).getReplyContent());
        long replyTime = list.get(position).getReplyTime();
        String s = TimeUtil.unixTimestamp2BeijingTime(replyTime, "yyyy-MM-dd HH:mm:ss");
        holder.time.setText(s);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView name,juti,time;
        private ImageView tu;

        public MyViewHolder(View itemView) {
            super(itemView);
            juti = itemView.findViewById(R.id.comment_find_co);
            name = itemView.findViewById(R.id.coment_find_name);
            time = itemView.findViewById(R.id.comment_find_time);
            tu = itemView.findViewById(R.id.comment_find_headpic);
        }
    }
}
