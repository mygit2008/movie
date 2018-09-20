package com.bw.Movie.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.Movie.R;
import com.bw.Movie.adapter.MyCinemasListAdapter;
import com.bw.Movie.bean.CinemasListBean;
import com.bw.Movie.bean.MessageEvent;
import com.bw.Movie.interview.ICinemaslistMoviesView;
import com.bw.Movie.model.MyCinemasListMoviesModel;
import com.bw.Movie.presenter.CinemasMoviesPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import example.com.mvplibrary.BaseActivity;
import example.com.mvplibrary.mvp.BaseModel;

public class CinemasListActivity extends BaseActivity<CinemasMoviesPresenter> implements ICinemaslistMoviesView{

    private String mid;
    private RecyclerView recy;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_cinemaslist;
    }

    @Override
    public void initView() {
        super.initView();
        EventBus.getDefault().register(this);
        recy = findViewById(R.id.cinemaslist_recy);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recy.setLayoutManager(linearLayout);
    }

    @Override
    protected void initData() {
         presenter.CinemasMoviesPresener(new MyCinemasListMoviesModel(),this);
    }

    @Subscribe(sticky = true)
    public void onEvent(MessageEvent event){
        mid = event.getMid();
    }

    @Override
    protected CinemasMoviesPresenter initPresenter() {
        return new CinemasMoviesPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new MyCinemasListMoviesModel();
    }

    @Override
    public String getMovieId() {
        return mid;
    }

    @Override
    public void HotMoviesSuccess(CinemasListBean bean) {
        List<CinemasListBean.ResultBean> result = bean.getResult();
        if(result!=null&&!(result.equals(""))){
            MyCinemasListAdapter adapter = new MyCinemasListAdapter(this,result,mid);
            recy.setAdapter(adapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
