package com.bw.Movie.model;

import com.bw.Movie.MyApp;
import com.bw.Movie.api.CinemaApi;
import com.bw.Movie.bean.AllBean;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by BoyLucky on 2018/8/6.
 */

public class AllModel extends BaseModel {

    public void getAll(int page, int count, final GetModel getModel) {
        RetrofitUtil.getInstence(MyApp.context).create(CinemaApi.class)
                .getAll(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AllBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AllBean recommendCinemaBean) {
                        getModel.getmodel(recommendCinemaBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface GetModel{
        void getmodel(AllBean recommendCinemaBean);
    }
}
