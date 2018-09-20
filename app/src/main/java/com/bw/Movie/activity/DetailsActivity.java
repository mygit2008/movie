package com.bw.Movie.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.Movie.R;
import com.bw.Movie.adapter.PingListAdapter;
import com.bw.Movie.adapter.YingListAdapter;
import com.bw.Movie.bean.DataBean;
import com.bw.Movie.bean.DetailsBean;
import com.bw.Movie.bean.GuanZhuBean;
import com.bw.Movie.bean.MessageEvent;
import com.bw.Movie.bean.PingLunBean;
import com.bw.Movie.bean.YingBean;
import com.bw.Movie.interview.IDetailsView;
import com.bw.Movie.model.DetailsModel;
import com.bw.Movie.presenter.DetailsPresenter;
import com.bw.Movie.utils.MyData;


import org.greenrobot.eventbus.EventBus;

import java.util.List;

import example.com.mvplibrary.BaseActivity;
import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.SharedPreferencesUtil;

public class DetailsActivity extends BaseActivity<DetailsPresenter> implements IDetailsView, View.OnClickListener {

    private int id;
    private TextView title,address,tel,g_tv;
    private DetailsBean.ResultBean result;
    private ListView lv;
    private int page = 1;
    private int count = 1;
    private YingListAdapter adapter;
    private ImageView y_img,back_img;
    private TextView but1,but2;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_details;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        title = findViewById(R.id.title);
        address = findViewById(R.id.address);
        but1 = findViewById(R.id.but1);
        but2 = findViewById(R.id.but2);
        but1.setOnClickListener(this);
        but2.setOnClickListener(this);
        y_img = findViewById(R.id.y_img);
        back_img = findViewById(R.id.back_img);
        g_tv = findViewById(R.id.g_tv);
        g_tv.setOnClickListener(this);
        tel = findViewById(R.id.tel);
        lv = findViewById(R.id.lv);
        getTime();
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //获取当前日期
   public void getTime(){
       MyData myData = new MyData();
       DataBean myData1 = myData.getMyData();
       int month = myData1.month;
       int day = myData1.day;
       but1.setText(month+"月"+day+"日");
       if (month == 4 || month == 6 ||month == 9 || month == 11){
           if (day == 30){
               month++;
               day=1;
               but2.setText(month+"月"+day+"日");
           }else{
               day++;
               but2.setText(month+"月"+day+"日");
           }
       }else if (month == 2){
           if (day == 28){
               month++;
               day=1;
               but2.setText(month+"月"+day+"日");
           }else{
               day++;
               but2.setText(month+"月"+day+"日");
           }
       }else{
           if (day == 31){
               month++;
               day=1;
               but2.setText(month+"月"+day+"日");
           }else{
               day++;
               but2.setText(month+"月"+day+"日");
           }
       }
    }
    @Override
    protected void initData() {
        presenter.getDetails(id);
        presenter.getYing(id);
    }

    @Override
    protected DetailsPresenter initPresenter() {
        return new DetailsPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new DetailsModel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSuccess(DetailsBean detailsBean) {
        result = detailsBean.getResult();
        boolean followCinema = result.isFollowCinema();
        Glide.with(this).load(result.getLogo()).into(y_img);
        title.setText(result.getName());
        address.setText(result.getAddress());
        tel.setText(result.getPhone());
        if (!followCinema){
            g_tv.setText("已关注");
        }else{
            g_tv.setText("关注影院");
        }
    }

    @Override
    public void onSuccess(GuanZhuBean guanZhuBean) {
        Log.e("tag222222","-----"+guanZhuBean.getMessage());
        if(guanZhuBean.getStatus().equals("0000")&&guanZhuBean.getMessage().equals("关注成功")){
            Toast.makeText(this,guanZhuBean.getMessage(),Toast.LENGTH_SHORT).show();
            g_tv.setText("已关注");
        }else if(guanZhuBean.getStatus().equals("0000")&&guanZhuBean.getMessage().equals("取消关注成功")){
            Toast.makeText(this,guanZhuBean.getMessage(),Toast.LENGTH_SHORT).show();
            g_tv.setText("关注影院");
        }
    }

    @Override
    public void onSuccess(PingLunBean pingLunBean) {
    }

    @Override
    public void onSuccess(YingBean yingBean) {
        final List<YingBean.ResultBean> result = yingBean.getResult();
        if (result!= null){
            adapter = new YingListAdapter(this, result);
            lv.setAdapter(adapter);
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessageEvent me = new MessageEvent(result.get(position).getId()+"");
                EventBus.getDefault().postSticky(me);
                startActivity(new Intent(DetailsActivity.this, DetailsMoviesActivity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.g_tv:
                if (g_tv.getText().equals("关注影院")){
                    presenter.getGuan(result.getId());
                }else if (g_tv.getText().equals("已关注")){
                    presenter.getQu(result.getId());
                }
                break;
            case R.id.but1:
                but1.setBackgroundResource(R.drawable.shape);
                but2.setBackgroundResource(R.drawable.shape4);
                break;
            case R.id.but2:
                but1.setBackgroundResource(R.drawable.shape4);
                but2.setBackgroundResource(R.drawable.shape);
                break;
        }
    }
}
