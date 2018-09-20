package com.bw.Movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bw.Movie.R;
import com.bw.Movie.adapter.MovieListAdapter;
import com.bw.Movie.bean.UserInfoBean;
import com.bw.Movie.bean.UserSignInBean;
import com.bw.Movie.common.MessageEvent;
import com.bw.Movie.interview.UserSignInView;
import com.bw.Movie.model.UserSignInModel;
import com.bw.Movie.presenter.UserSignInPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import example.com.mvplibrary.BaseActivity;
import example.com.mvplibrary.mvp.BaseModel;

public class AttentionMovieActivity extends BaseActivity<UserSignInPresenter> implements UserSignInView, View.OnClickListener {


    private RecyclerView mAttentionMovieRv;
    private ImageView mNoneGuanzhu;
    private RelativeLayout mNoneGuanzhuRelate;
    private ImageView mAttentionMovieBack;
    private ImageView mNoneGuanzhuMovie;
    private RelativeLayout mNoneGuanzhuMovieRelate;

    @Override
    public void onSuccess(UserSignInBean userSignInBean) {

    }

    @Override
    public void onUserInfoSuccess(UserInfoBean userInfoBean) {
        final UserInfoBean.ResultBean result = userInfoBean.getResult();
        if (result.getMovieList().size() != 0) {
            mNoneGuanzhuRelate.setVisibility(View.GONE);
            mAttentionMovieRv.setVisibility(View.VISIBLE);
        } else {
            mNoneGuanzhuRelate.setVisibility(View.VISIBLE);
            mAttentionMovieRv.setVisibility(View.GONE);
        }
        final List<UserInfoBean.ResultBean.MovieListBean> movieList = userInfoBean.getResult().getMovieList();
        MovieListAdapter listAdapter = new MovieListAdapter(this, movieList);
        mAttentionMovieRv.setAdapter(listAdapter);
        mAttentionMovieRv.setLayoutManager(new LinearLayoutManager(this));
        listAdapter.setmItemClickListener(new MovieListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                MessageEvent me = new MessageEvent(movieList.get(postion).getId() + "");
                EventBus.getDefault().postSticky(me);
                startActivity(new Intent(AttentionMovieActivity.this, DetailsMoviesActivity.class));
            }
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_attention_movie;
    }

    @Override
    protected void initData() {
        presenter.getUserInfo();
    }

    @Override
    protected UserSignInPresenter initPresenter() {
        return new UserSignInPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new UserSignInModel();
    }

    @Override
    public void initView() {
        mAttentionMovieRv = (RecyclerView) findViewById(R.id.attention_movie_rv);
        mNoneGuanzhu = (ImageView) findViewById(R.id.none_guanzhu_movie);
        mNoneGuanzhuRelate = (RelativeLayout) findViewById(R.id.none_guanzhu_movie_relate);
        mAttentionMovieBack = (ImageView) findViewById(R.id.attention_movie_back);
        mAttentionMovieBack.setOnClickListener(this);
        mNoneGuanzhuMovie = (ImageView) findViewById(R.id.none_guanzhu_movie);
        mNoneGuanzhuMovieRelate = (RelativeLayout) findViewById(R.id.none_guanzhu_movie_relate);
        mAttentionMovieRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.attention_movie_back:
                finish();
                break;
        }
    }
}
