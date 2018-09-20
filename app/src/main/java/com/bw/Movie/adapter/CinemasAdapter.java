package com.bw.Movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.Movie.R;
import com.bw.Movie.bean.UserInfoBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.List;

public class CinemasAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<UserInfoBean.ResultBean.CinemasListBean> list;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CinemasAdapter(Context context, List<UserInfoBean.ResultBean.CinemasListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.cinemas_item_layout, null);
        ScreenAdapterTools.getInstance().loadView((ViewGroup) view);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.cinemas_name.setText(list.get(position).getName());
        viewHolder.cinemas_address.setText("影院地址 : "+list.get(position).getAddress());
        viewHolder.cinemas_img.setImageURI(Uri.parse(list.get(position).getLogo()));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView cinemas_img;
        TextView cinemas_name,cinemas_address;
        public ViewHolder(View itemView) {
            super(itemView);
            cinemas_address = itemView.findViewById(R.id.cinemas_address);
            cinemas_img = itemView.findViewById(R.id.cinemas_img);
            cinemas_name = itemView.findViewById(R.id.cinemas_name);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int postion);
    }
}
