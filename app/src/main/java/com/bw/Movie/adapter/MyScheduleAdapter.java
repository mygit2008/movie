package com.bw.Movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.Movie.R;
import com.bw.Movie.bean.SchedulBean;

import java.util.List;

public class MyScheduleAdapter extends RecyclerView.Adapter<MyScheduleAdapter.MyViewHolder> {
    private Context context;
    private List<SchedulBean.ResultBean> list;
    private gouItemClick gouItemClick;

    public void setGouItemClick(MyScheduleAdapter.gouItemClick gouItemClick) {
        this.gouItemClick = gouItemClick;
    }

    public MyScheduleAdapter(Context context, List<SchedulBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.schedule_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.schedule_title.setText("放映大厅" + list.get(position).getScreeningHall());
        holder.schedule_time.setText("起始时间" + list.get(position).getBeginTime() + "---" + list.get(position).getEndTime());
        holder.schedule_datury_time.setText("放映时长" + list.get(position).getDuration());
        holder.schedule_total.setText("座位总数" + list.get(position).getSeatsTotal());
        holder.schedule_use_count.setText("座位已占用数量" + list.get(position).getSeatsUseCount());
        int status = list.get(position).getStatus();
        if (status == 1) {
            //holder.schedule_goupiao.setEnabled(true);
            holder.schedule_status.setText("正在上映");
        } else {
            //holder.schedule_goupiao.setEnabled(false);
            holder.schedule_status.setText("已过期");
        }
        holder.schedule_goupiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gouItemClick != null) {
                    gouItemClick.rlvOnItemClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView schedule_goupiao, schedule_status, schedule_use_count, schedule_title, schedule_time, schedule_datury_time, schedule_total;
        private Button cha;

        public MyViewHolder(View itemView) {
            super(itemView);
            schedule_title = itemView.findViewById(R.id.schedule_title);
            schedule_time = itemView.findViewById(R.id.schedule_time);
            schedule_datury_time = itemView.findViewById(R.id.schedule_datury_time);
            schedule_total = itemView.findViewById(R.id.schedule_total);
            schedule_use_count = itemView.findViewById(R.id.schedule_use_count);
            schedule_status = itemView.findViewById(R.id.schedule_status);
            schedule_goupiao = itemView.findViewById(R.id.schedule_goupiao);
        }
    }

    public interface gouItemClick {
        void rlvOnItemClick(View v, int p);
    }
}
