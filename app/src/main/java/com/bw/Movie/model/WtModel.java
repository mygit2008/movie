package com.bw.Movie.model;

import com.bw.Movie.MyApp;
import com.bw.Movie.api.CinemaApi;
import com.bw.Movie.api.GetDataInterface;
import com.bw.Movie.bean.WtBean;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by BoyLucky on 2018/8/23.
 */

public class WtModel extends BaseModel {
    public void getWt(int page, int count, final GetModel getModel) {
        RetrofitUtil.getInstence(MyApp.context).create(GetDataInterface.class)
                .getHotMovies(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WtBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WtBean wtBean) {
                        getModel.getmodel(wtBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getWt1(int page, int count, final GetModel getModel) {
        RetrofitUtil.getInstence(MyApp.context).create(GetDataInterface.class)
                .getReReleaseMovies(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WtBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WtBean wtBean) {
                        getModel.getmodel(wtBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getWt2(int page, int count, final GetModel getModel) {
        RetrofitUtil.getInstence(MyApp.context).create(GetDataInterface.class)
                .getSoonMovies(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WtBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WtBean wtBean) {
                        getModel.getmodel(wtBean);
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
        void getmodel(WtBean wtBean);
    }
}
