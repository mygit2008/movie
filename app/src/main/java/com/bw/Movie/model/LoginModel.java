package com.bw.Movie.model;

import android.util.Log;
import com.bw.Movie.MyApp;
import com.bw.Movie.api.APIFunction;
import com.bw.Movie.bean.LoginBean;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginModel extends BaseModel {
    private static String TAG = "TAG : LoginModel  :";
    public void getLogin(String phone, String pwd, final GetModel getModel){
        RetrofitUtil.getInstence(MyApp.context).create(APIFunction.class)
                .getLogin(phone,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        getModel.onSuccess(loginBean);
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
        void onSuccess(LoginBean loginBean);
    }
}
