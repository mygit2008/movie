package com.bw.Movie.presenter;

import com.bw.Movie.bean.UserBean;
import com.bw.Movie.interview.UserView;
import com.bw.Movie.model.UserModel;

import example.com.mvplibrary.mvp.BasePresenter;

public class UserPresenter extends BasePresenter<UserModel,UserView> {
    public void getUser(){
        model.getUser(new UserModel.GetModel() {
            @Override
            public void getUser(UserBean userBean) {
                view.getUser(userBean);
            }
        });
    }
}
