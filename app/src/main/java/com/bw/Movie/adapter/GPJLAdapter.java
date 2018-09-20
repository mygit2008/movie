package com.bw.Movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.Movie.R;
import com.bw.Movie.bean.GouPiaoJiLuBean;

import java.util.List;

import example.com.utilslibrary.utils.TimeUtil;

public class GPJLAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<GouPiaoJiLuBean.ResultBean> list;
    private MovieListAdapter.OnItemClickListener mItemClickListener;

    public void setmItemClickListener(MovieListAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public GPJLAdapter(Context context, List<GouPiaoJiLuBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.gpjl_item_layout, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        GouPiaoJiLuBean.ResultBean resultBean = list.get(position);
        viewHolder.cinemas_name.setText(resultBean.getCinemaName());
        int beginTime = list.get(position).getBeginTime();
        String s = TimeUtil.unixTimestamp2BeijingTime(beginTime, "yyyy-MM-dd HH:mm:ss");
        viewHolder.changci.setText(s);
        viewHolder.movie_name.setText(resultBean.getMovieName());
        viewHolder.num.setText(resultBean.getAmount());
        int amount = resultBean.getAmount();
        double price = resultBean.getPrice();
        int p = (int) (price * amount);
        viewHolder.num_price.setText(p);
//        viewHolder.zhuangtai.setText();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int layoutPosition = viewHolder.getLayoutPosition();
                mItemClickListener.onItemClick(view,layoutPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView cinemas_name,movie_name,changci,num,num_price,zhuangtai;
        public ViewHolder(View itemView) {
            super(itemView);
            cinemas_name = itemView.findViewById(R.id.gpjl_cinemas_name);
            movie_name = itemView.findViewById(R.id.gpjl_movie_name);
            changci = itemView.findViewById(R.id.gpjl_changci);
            num = itemView.findViewById(R.id.gpjl_num);
            num_price = itemView.findViewById(R.id.gpjl_num_price);
            zhuangtai = itemView.findViewById(R.id.gpjl_zhuangtai);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int postion);
    }
}
