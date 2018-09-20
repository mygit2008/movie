package com.bw.Movie.model;


import com.bw.Movie.MyApp;
import com.bw.Movie.api.GetDataInterface;
import com.bw.Movie.api.PaymentApi;
import com.bw.Movie.bean.IndentBean;
import com.bw.Movie.bean.MovieListBean;
import com.bw.Movie.bean.SchedulBean;
import com.bw.Movie.bean.WXzfBean;

import java.io.IOException;
import java.util.Map;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author zhangjunyou
 * @date 2018/8/13
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */
public class IndentModel extends BaseModel {

    public void getOrders(int i, int i1, String sign, final IIndentModel iIndentModel) {
        RetrofitUtil.getInstence(MyApp.context).create(PaymentApi.class).getOrders(i, i1, sign)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IndentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(IndentBean indentBean) {
                        iIndentModel.success(indentBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void onScheduleMoviesData(Map<String, String> map, final IIndentModel iIndentModel) {
        RetrofitUtil.getInstence(MyApp.context).create(GetDataInterface.class).getScheduleListMovies(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SchedulBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SchedulBean schedulBean) {
                        iIndentModel.ScheduleMoviesSuccess(schedulBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getMovieListCinema(String mid, final IIndentModel iIndentModel) {
        RetrofitUtil.getInstence(MyApp.context).create(GetDataInterface.class).getMovieListCinema(mid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieListBean movieListBean) {
                        iIndentModel.ScheduleCinema(movieListBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public interface IIndentModel {
        void success(IndentBean indentBean);

        void ScheduleMoviesSuccess(SchedulBean bean);

        void ScheduleCinema(MovieListBean movieListBean);
    }
}
