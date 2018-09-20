package com.bw.Movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.Movie.R;
import com.bw.Movie.bean.UserInfoBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.List;

import example.com.utilslibrary.utils.TimeUtil;

public class MovieListAdapter extends RecyclerView.Adapter  {

    private Context context;
    private List<UserInfoBean.ResultBean.MovieListBean> list;
    private OnItemClickListener mItemClickListener;

    public void setmItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public MovieListAdapter(Context context, List<UserInfoBean.ResultBean.MovieListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.movie_item_layout, null);
        ScreenAdapterTools.getInstance().loadView((ViewGroup) view);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.movie_content.setText(list.get(position).getSummary());
        viewHolder.movie_name.setText(list.get(position).getName());
        String s = TimeUtil.unixTimestamp2BeijingTime(list.get(position).getReleaseTime(), "yyyy 年 MM 月 dd 日");
        viewHolder.movie_time.setText("上映时间 : "+s);
        viewHolder.movie_img.setImageURI(Uri.parse(list.get(position).getImageUrl()));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int layoutPosition = ((ViewHolder) holder).getLayoutPosition();
                mItemClickListener.onItemClick(view,layoutPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnClickListener(OnItemClickListener mItemClickListener){
        this.mItemClickListener=mItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView movie_img;
        TextView movie_name,movie_content,movie_time;
        RelativeLayout rl;
        public ViewHolder(View itemView) {
            super(itemView);
            movie_img = itemView.findViewById(R.id.movie_img);
            movie_content = itemView.findViewById(R.id.movie_content);
            movie_name = itemView.findViewById(R.id.movie_name);
            movie_time = itemView.findViewById(R.id.movie_time);
            rl = itemView.findViewById(R.id.rl);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int postion);
    }
}
