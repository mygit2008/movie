package com.bw.Movie.model;

import com.bw.Movie.MyApp;
import com.bw.Movie.api.CinemaApi;
import com.bw.Movie.bean.RecommendCinemaBean;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by BoyLucky on 2018/8/2.
 */

public class CinemaModel extends BaseModel {

    public void getCinemas(int page, int count, String longitude, String latitude, final GetModel getModel) {
        RetrofitUtil.getInstence(MyApp.context).create(CinemaApi.class)
                .getRecommendCinema(page, count,longitude,latitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecommendCinemaBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RecommendCinemaBean recommendCinemaBean) {
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

    public interface GetModel {
        void getmodel(RecommendCinemaBean recommendCinemaBean);
    }
}
