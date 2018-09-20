package com.bw.Movie.model;

import com.bw.Movie.MyApp;
import com.bw.Movie.api.APIFunction;
import com.bw.Movie.bean.UserBean;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserModel extends BaseModel {
    public void getUser(final GetModel getModel){
        RetrofitUtil.getInstence(MyApp.context).create(APIFunction.class)
                .getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        getModel.getUser(userBean);
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
        void getUser(UserBean userBean);
    }
}
