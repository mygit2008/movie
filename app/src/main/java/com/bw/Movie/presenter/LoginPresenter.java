package com.bw.Movie.presenter;

import com.bw.Movie.bean.LoginBean;
import com.bw.Movie.interview.LoginView;
import com.bw.Movie.model.LoginModel;

import example.com.mvplibrary.mvp.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginModel,LoginView> {
    public void getLogin(String phone,String pwd){
        model.getLogin(phone, pwd, new LoginModel.GetModel() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                view.onSuccess(loginBean);
            }
        });
    }
}
