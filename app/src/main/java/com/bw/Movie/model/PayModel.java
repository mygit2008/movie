package com.bw.Movie.model;

import com.bw.Movie.MyApp;
import com.bw.Movie.api.PaymentApi;
import com.bw.Movie.bean.WXzfBean;

import java.io.IOException;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * @author zhangjunyou
 * @date 2018/8/25
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */
public class PayModel extends BaseModel {
    public void getWXPay(int i, String orderId, final IPayModel iPayModel) {
        RetrofitUtil.getInstence(MyApp.context).create(PaymentApi.class).getWXPay(i, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WXzfBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WXzfBean WXzfBean) {
                        iPayModel.wxSuccess(WXzfBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getZFBPay(int i, String orderId, final IPayModel iPayModel) {
        RetrofitUtil.getInstence(MyApp.context).create(PaymentApi.class).getZFBPay(i, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            iPayModel.zfbSuccess(string);
                        } catch (IOException e) {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface IPayModel {
        void wxSuccess(WXzfBean WXzfBean);

        void zfbSuccess(String string);
    }
}
