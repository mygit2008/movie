package com.bw.Movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.Movie.R;
import com.bw.Movie.activity.CommentReplyActivity;
import com.bw.Movie.bean.CommentBean;
import com.bw.Movie.bean.NewMessageBean;
import com.bw.Movie.utils.TimeUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MyCommentAdapter extends RecyclerView.Adapter<MyCommentAdapter.MyViewHolder>{
    private Context context;
    private List<CommentBean.ResultBean> list;

    public MyCommentAdapter(Context context, List<CommentBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getCommentHeadPic()).into(holder.tu);
        holder.name.setText(list.get(position).getCommentUserName());
        holder.juti.setText(list.get(position).getCommentContent());
        long commentTime = list.get(position).getCommentTime();
        String s = TimeUtil.unixTimestamp2BeijingTime(commentTime, "yyyy-MM-dd HH:mm:ss");
        holder.time.setText(s);

        int greatNum = list.get(position).getGreatNum();
        if (greatNum > 0) {
            holder.zan.setText(greatNum+"");
        }else {
            holder.zan.setText("赞");
        }
        holder.huifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewMessageBean me = new NewMessageBean(null,list.get(position).getCommentId()+"",list.get(position).getCommentTime()+"",list.get(position).getCommentContent(),list.get(position).getCommentUserName(),list.get(position).getCommentHeadPic());
                EventBus.getDefault().postSticky(me);
                context.startActivity(new Intent(context, CommentReplyActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView zan,huifu;
        private TextView name,juti,time;
        private ImageView tu;

        public MyViewHolder(View itemView) {
            super(itemView);
            juti = itemView.findViewById(R.id.comment_co);
            name = itemView.findViewById(R.id.comment_name);
            time = itemView.findViewById(R.id.comment_time);
            tu = itemView.findViewById(R.id.comment_headpic);
            zan = itemView.findViewById(R.id.comment_zan);
            huifu = itemView.findViewById(R.id.c_huifu);
        }
    }
}
