package com.bw.Movie.presenter;

import com.bw.Movie.bean.UserInfoBean;
import com.bw.Movie.interview.UserInfoView;
import com.bw.Movie.model.UserInfoModel;

import example.com.mvplibrary.mvp.BasePresenter;

public class UserInfoPresenter extends BasePresenter<UserInfoModel,UserInfoView> {
    public void getUserInfo(){
        model.getUserInfo(new UserInfoModel.GetModel() {
            @Override
            public void onSuccess(UserInfoBean userInfoBean) {
                view.onSuccess(userInfoBean);
            }
        });
    }
}
