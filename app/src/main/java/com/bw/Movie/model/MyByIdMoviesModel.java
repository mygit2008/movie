package com.bw.Movie.model;

import com.bw.Movie.MyApp;
import com.bw.Movie.api.CinemaApi;
import com.bw.Movie.api.GetDataInterface;
import com.bw.Movie.bean.ByIdMoviesBean;
import com.bw.Movie.bean.GuanZhuBean;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyByIdMoviesModel extends BaseModel {
    private static final String TAG = "MyByIdMoviesModel-----";

     public void onByIdMoviesData(int movieId, final ByIdMoviesListener byIdMoviesListener){
         RetrofitUtil.getInstence(MyApp.context).create(GetDataInterface.class)
                 .getByIdMovies(movieId)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Observer<ByIdMoviesBean>() {
                     @Override
                     public void onSubscribe(Disposable d) {

                     }

                     @Override
                     public void onNext(ByIdMoviesBean byIdMoviesBean) {
                          byIdMoviesListener.ByIdMoviesSuccess(byIdMoviesBean);
                     }

                     @Override
                     public void onError(Throwable e) {

                     }

                     @Override
                     public void onComplete() {

                     }
                 });

     }

    public void getQu(int id, final GetModel getModel) {
        RetrofitUtil.getInstence(MyApp.context).create(GetDataInterface.class)
                .getQu(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GuanZhuBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GuanZhuBean guanZhuBean) {
                        getModel.getmodel(guanZhuBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface ByIdMoviesListener {
        //成功时调用
        void ByIdMoviesSuccess(ByIdMoviesBean bean);
    }
    public void getGuan(int p, final GetModel getModel) {
        RetrofitUtil.getInstence(MyApp.context).create(GetDataInterface.class)
                .getGaun(p)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GuanZhuBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GuanZhuBean guanZhuBean) {
                        getModel.getmodel(guanZhuBean);
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
        void getmodel(GuanZhuBean recommendCinemaBean);
    }

}
