package com.bw.Movie.interview;

import com.bw.Movie.bean.UserInfoBean;

import example.com.mvplibrary.mvp.IBaseView;

public interface UserInfoView extends IBaseView {
    void onSuccess(UserInfoBean userInfoBean);
}
