package com.bw.Movie.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bw.Movie.R;
import com.bw.Movie.adapter.GPJLAdapter;
import com.bw.Movie.adapter.MovieListAdapter;
import com.bw.Movie.bean.GouPiaoJiLuBean;
import com.bw.Movie.interview.GPJLView;
import com.bw.Movie.model.GPJLModel;
import com.bw.Movie.presenter.GPJLPresenter;

import java.util.List;

import example.com.mvplibrary.BaseActivity;
import example.com.mvplibrary.mvp.BaseModel;

public class GouPiaoJiLuActivity extends BaseActivity<GPJLPresenter> implements GPJLView, View.OnClickListener {

    private ImageView mGpjlBack;
    private RelativeLayout mGpjlHead;
    private ImageView mNoneGpjlImg;
    private RelativeLayout mNoneGpjlRelate;
    private RecyclerView mGPJLRv;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_gou_piao_ji_lu;
    }

    @Override
    protected void initData() {
        presenter.getGPJL();
    }

    @Override
    protected GPJLPresenter initPresenter() {
        return new GPJLPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new GPJLModel();
    }

    @Override
    public void onSuccess(GouPiaoJiLuBean gouPiaoJiLuBean) {
        List<GouPiaoJiLuBean.ResultBean> result = gouPiaoJiLuBean.getResult();
        if (gouPiaoJiLuBean.getMessage().equals("查询成功")){
            mNoneGpjlRelate.setVisibility(View.GONE);
            mGPJLRv.setVisibility(View.VISIBLE);
            mGPJLRv.setLayoutManager(new LinearLayoutManager(this));
            GPJLAdapter gpjlAdapter = new GPJLAdapter(this, result);
            mGPJLRv.setAdapter(gpjlAdapter);
            mGPJLRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            gpjlAdapter.setmItemClickListener(new MovieListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int postion) {

                }
            });
        }else if (gouPiaoJiLuBean.getMessage().equals("无数据")){
            mNoneGpjlRelate.setVisibility(View.VISIBLE);
            mGPJLRv.setVisibility(View.GONE);
        }
    }

    @Override
    public void initView() {
        mGpjlBack = (ImageView) findViewById(R.id.gpjl_back);
        mGpjlBack.setOnClickListener(this);
        mGpjlHead = (RelativeLayout) findViewById(R.id.gpjl_head);
        mGPJLRv = (RecyclerView) findViewById(R.id.gpjl_rv);
        mNoneGpjlImg = (ImageView) findViewById(R.id.none_gpjl_img);
        mNoneGpjlRelate = (RelativeLayout) findViewById(R.id.none_gpjl_relate);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.gpjl_back:
                finish();
                break;
        }
    }
}
