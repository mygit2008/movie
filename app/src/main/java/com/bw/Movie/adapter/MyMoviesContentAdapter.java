package com.bw.Movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.Movie.R;
import com.bw.Movie.activity.DetailsMoviesActivity;
import com.bw.Movie.bean.MessageEvent;
import com.bw.Movie.bean.WtBean;
import com.bw.Movie.interview.OnItemClickListener;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MyMoviesContentAdapter extends RecyclerView.Adapter<MyMoviesContentAdapter.MyViewHolder>{
    private Context context;
    private List<WtBean.ResultBean> list;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MyMoviesContentAdapter(Context context, List<WtBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movies_content2_layout, parent, false);
        ScreenAdapterTools.getInstance().loadView((ViewGroup) view);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        MyViewHolder myViewHolder = holder;
        Glide.with(context).load(list.get(position).getImageUrl()).into(myViewHolder.movies2_picture);
        myViewHolder.movies2_title.setText(list.get(position).getName());
        myViewHolder.movies2_content.setText(list.get(position).getSummary());
        myViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView movies2_picture;
        TextView movies2_title, movies2_content;
        LinearLayout ll;
        public MyViewHolder(View itemView) {
            super(itemView);
            movies2_picture = itemView.findViewById(R.id.movies2_picture);
            movies2_content = itemView.findViewById(R.id.movies2_content);
            movies2_title = itemView.findViewById(R.id.movies2_title);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
