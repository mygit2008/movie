package com.bw.Movie.model;

import android.util.Log;

import com.bw.Movie.MyApp;
import com.bw.Movie.api.CinemaApi;
import com.bw.Movie.bean.DetailsBean;
import com.bw.Movie.bean.GuanZhuBean;
import com.bw.Movie.bean.PingLunBean;
import com.bw.Movie.bean.YingBean;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by BoyLucky on 2018/8/6.
 */

public class DetailsModel extends BaseModel {
    public void getDetails(int id, final GetModel getModel) {
        RetrofitUtil.getInstence(MyApp.context).create(CinemaApi.class)
                .getDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailsBean detailsBean) {
                        getModel.getmodel(detailsBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getGuan(int id, final GetGuan getGuan) {
        RetrofitUtil.getInstence(MyApp.context).create(CinemaApi.class)
                .getGuan(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GuanZhuBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GuanZhuBean guanZhuBean) {
                        getGuan.getmodel(guanZhuBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getQu(int id, final GetGuan getGuan) {
        RetrofitUtil.getInstence(MyApp.context).create(CinemaApi.class)
                .getQu(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GuanZhuBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GuanZhuBean guanZhuBean) {
                        getGuan.getmodel(guanZhuBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPing(int id, int page, int count, final GetPing getPing) {

        RetrofitUtil.getInstence(MyApp.context).create(CinemaApi.class)
                .getPing(id,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PingLunBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PingLunBean pingLunBean) {
                        getPing.getmodel(pingLunBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getFaBiao(int id, Integer integer, String s, final GetGuan getGuan) {

        RetrofitUtil.getInstence(MyApp.context).create(CinemaApi.class)
                .getFaBiao(id,integer,s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GuanZhuBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GuanZhuBean guanZhuBean) {
                        Log.e("tag","-----"+guanZhuBean.getMessage()+"----model");
                        getGuan.getmodel(guanZhuBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("tag","-----"+e+"----model");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getYing(int id, final GetModel_Ying getModel_ying) {
        RetrofitUtil.getInstence(MyApp.context).create(CinemaApi.class)
                .getying(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<YingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(YingBean yingBean) {
                        getModel_ying.getmodel(yingBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("uilwdchkqw","--------"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface GetModel{
        void getmodel(DetailsBean recommendCinemaBean);
    }
    public interface GetModel_Ying{
        void getmodel(YingBean yingBean);
    }
    public interface GetGuan{
        void getmodel(GuanZhuBean guanZhuBean);
    }
    public interface GetPing{
        void getmodel(PingLunBean pingLunBean);
    }
}
