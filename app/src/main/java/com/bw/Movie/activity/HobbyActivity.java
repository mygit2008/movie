package com.bw.Movie.activity;

import com.bw.Movie.R;
import com.bw.Movie.interview.HobbyCallback;

import example.com.mvplibrary.BaseActivity;
import example.com.mvplibrary.mvp.BaseModel;

public class HobbyActivity extends BaseActivity<HobbyCallback.BaseHobbyPresenter> implements HobbyCallback.BaseHobbyView {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_hobby;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected HobbyCallback.BaseHobbyPresenter initPresenter() {
        return null;
    }

    @Override
    protected BaseModel initModel() {
        return null;
    }

    @Override
    public void serverFail(String msg) {

    }
}
