package com.bw.Movie.interview;

import com.bw.Movie.bean.LoginBean;

import example.com.mvplibrary.mvp.IBaseView;

public interface LoginView extends IBaseView {
    void onSuccess(LoginBean loginBean);
}
