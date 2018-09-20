package com.bw.Movie.model;

import android.util.Log;

import com.bw.Movie.MyApp;
import com.bw.Movie.api.APIFunction;
import com.bw.Movie.bean.UserInfoBean;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserInfoModel extends BaseModel{
    private static final String TAG = "UserInfoModel";
    public void getUserInfo(final GetModel getModel){
        RetrofitUtil.getInstence(MyApp.context).create(APIFunction.class)
            .getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        getModel.onSuccess(userInfoBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface GetModel {
        void onSuccess(UserInfoBean userInfoBean);
    }
}
