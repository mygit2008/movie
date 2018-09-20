package com.bw.Movie.presenter;

import com.bw.Movie.bean.RegisterBean;
import com.bw.Movie.interview.RegisterView;
import com.bw.Movie.model.RegisterModel;

import example.com.mvplibrary.mvp.BasePresenter;

public class RegisterPresenter extends BasePresenter<RegisterModel,RegisterView>{
    public void getRegister(String nickName, String phone, String pwd, String pwd2, int sex/*, String birthday, String imei, String ua, String screenSize, String os*/, String email){
        model.getRegister(nickName, phone, pwd, pwd2, sex, email, new RegisterModel.GetModel() {
            @Override
            public void onSuccess(RegisterBean registerBean) {
                view.onSuccess(registerBean);
            }
        });
    }
}
