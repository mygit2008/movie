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
import com.bw.Movie.adapter.CinemasAdapter;
import com.bw.Movie.bean.UserInfoBean;
import com.bw.Movie.bean.UserSignInBean;
import com.bw.Movie.interview.UserSignInView;
import com.bw.Movie.model.UserSignInModel;
import com.bw.Movie.presenter.UserSignInPresenter;

import java.util.List;

import example.com.mvplibrary.BaseActivity;
import example.com.mvplibrary.mvp.BaseModel;

public class AttentionCinemasActivity extends BaseActivity<UserSignInPresenter> implements UserSignInView, View.OnClickListener {


    private RecyclerView mAttentionCinemasRv;
    private ImageView mNoneGuanzhuCinemas;
    private RelativeLayout mNoneGuanzhuCinemasRelate;
    private ImageView mAttentionCinemasBack;
    private RelativeLayout mAttentionCinemasHead;

    @Override
    public void onSuccess(UserSignInBean userSignInBean) {

    }

    @Override
    public void onUserInfoSuccess(UserInfoBean userInfoBean) {
        final UserInfoBean.ResultBean result = userInfoBean.getResult();
        if (result.getCinemasList().size() != 0) {
            mNoneGuanzhuCinemasRelate.setVisibility(View.GONE);
            mAttentionCinemasRv.setVisibility(View.VISIBLE);
        } else {
            mNoneGuanzhuCinemasRelate.setVisibility(View.VISIBLE);
            mAttentionCinemasRv.setVisibility(View.GONE);
        }
        final List<UserInfoBean.ResultBean.CinemasListBean> cinemasList = userInfoBean.getResult().getCinemasList();
        CinemasAdapter adapter = new CinemasAdapter(this, cinemasList);
        mAttentionCinemasRv.setAdapter(adapter);
        mAttentionCinemasRv.setLayoutManager(new LinearLayoutManager(this));
        mAttentionCinemasRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter.setOnItemClickListener(new CinemasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(AttentionCinemasActivity.this, DetailsActivity.class);
                intent.putExtra("id", cinemasList.get(postion).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_attention_cinemas;
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
        mAttentionCinemasRv = (RecyclerView) findViewById(R.id.attention_cinemas_rv);
        mNoneGuanzhuCinemas = (ImageView) findViewById(R.id.none_guanzhu_cinemas);
        mNoneGuanzhuCinemasRelate = (RelativeLayout) findViewById(R.id.none_guanzhu_cinemas_relate);
        mAttentionCinemasBack = (ImageView) findViewById(R.id.attention_cinemas_back);
        mAttentionCinemasBack.setOnClickListener(this);
        mAttentionCinemasHead = (RelativeLayout) findViewById(R.id.attention_cinemas_head);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.attention_cinemas_back:
                finish();
                break;
        }
    }
}
