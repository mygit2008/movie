package com.bw.Movie.model;

import android.util.Log;

import com.bw.Movie.MyApp;
import com.bw.Movie.api.APIFunction;
import com.bw.Movie.bean.RegisterBean;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterModel extends BaseModel {
    public void getRegister(String nickName, String phone, String pwd, String pwd2, int sex/*, String birthday, String imei, String ua, String screenSize, String os*/, String email, final GetModel getModel) {
        RetrofitUtil.getInstence(MyApp.context).create(APIFunction.class)
                .getRegister(nickName, phone, pwd, pwd2, sex, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        getModel.onSuccess(registerBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface GetModel {
        void onSuccess(RegisterBean registerBean);
    }
}
