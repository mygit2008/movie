package com.bw.Movie.interview;

import com.bw.Movie.bean.UserBean;

import example.com.mvplibrary.mvp.IBaseView;

public interface UserView extends IBaseView {
    void getUser(UserBean userBean);
}
