package com.bw.Movie.presenter;

import android.util.Log;

import com.bw.Movie.bean.UserInfoBean;
import com.bw.Movie.bean.UserSignInBean;
import com.bw.Movie.interview.UserSignInView;
import com.bw.Movie.model.UserSignInModel;

import example.com.mvplibrary.mvp.BasePresenter;

public class UserSignInPresenter extends BasePresenter<UserSignInModel,UserSignInView> {
    private static final String TAG = "UserSignInPresenter";
    public void getUserSignIn(){
        model.getUserSignIn(new UserSignInModel.GetModel() {
            @Override
            public void onSuccess(UserSignInBean userSignInBean) {
                Log.e(TAG, "onSuccess: "+userSignInBean.getMessage() );
                view.onSuccess(userSignInBean);
            }
        });
    }
    public void getUserInfo(){
        model.getUserInfo(new UserSignInModel.UserInfoGetModel() {
            @Override
            public void onSuccess(UserInfoBean userInfoBean) {
                view.onUserInfoSuccess(userInfoBean);
            }
        });
    }
}
