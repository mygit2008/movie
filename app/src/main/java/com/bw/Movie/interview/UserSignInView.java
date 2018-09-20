package com.bw.Movie.interview;

import com.bw.Movie.bean.UserInfoBean;
import com.bw.Movie.bean.UserSignInBean;

import example.com.mvplibrary.mvp.IBaseView;

public interface UserSignInView extends IBaseView {
    void onSuccess(UserSignInBean userSignInBean);
    void onUserInfoSuccess(UserInfoBean userInfoBean);
}
