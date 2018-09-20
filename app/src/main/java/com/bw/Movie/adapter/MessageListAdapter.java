package com.bw.Movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.Movie.R;
import com.bw.Movie.bean.MassageBean;

import java.util.ArrayList;


/**
 * Created by WXY on 2018/8/24.
 */

public class MessageListAdapter extends RecyclerView.Adapter{
    private ArrayList<MassageBean> list;
    private Context context;

    public MessageListAdapter(ArrayList<MassageBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void setList(ArrayList<MassageBean> list){
        if(list!=null){
          this.list=list;
          notifyItemInserted(list.size()-1);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MassageBean massageBean = list.get(viewType);
        if(massageBean!=null){
            boolean isuser = massageBean.isIsuser();
            if(isuser){
                return new MessagHoder2(LayoutInflater.from(context).inflate(R.layout.customer_end_item,parent,false));
            }else {
                return new MessagHoder1(LayoutInflater.from(context).inflate(R.layout.service_end_item,parent,false));
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       if(holder instanceof MessagHoder1){
           ((MessagHoder1)holder).tv1.setText(""+list.get(position).getMassagebody());
       }else if(holder instanceof MessagHoder2){
           ((MessagHoder2)holder).tv2.setText(""+list.get(position).getMassagebody());
       }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * 样式1
     */
    public static class MessagHoder1 extends RecyclerView.ViewHolder{
        public TextView tv1;

        public MessagHoder1(View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.service_meassage_item);
        }
    }

    /**
     * 样式2
     */
    public static class MessagHoder2 extends RecyclerView.ViewHolder{
        public TextView tv2;
        public MessagHoder2(View itemView) {
            super(itemView);
            tv2=itemView.findViewById(R.id.custom_massage_item);
        }
    }

}
