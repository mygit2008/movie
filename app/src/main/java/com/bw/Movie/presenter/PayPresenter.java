package com.bw.Movie.presenter;

import com.bw.Movie.bean.IndentBean;
import com.bw.Movie.bean.SchedulBean;
import com.bw.Movie.bean.WXzfBean;
import com.bw.Movie.interview.IPayView;
import com.bw.Movie.model.IndentModel;
import com.bw.Movie.model.PayModel;

import example.com.mvplibrary.mvp.BasePresenter;

/**
 * @author zhangjunyou
 * @date 2018/8/25
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */
public class PayPresenter extends BasePresenter<PayModel, IPayView> {
    public void getWXPay(int i, String orderId) {
        model.getWXPay(i, orderId, new PayModel.IPayModel() {

            @Override
            public void wxSuccess(WXzfBean WXzfBean) {
                view.wxSuccess(WXzfBean);
            }

            @Override
            public void zfbSuccess(String string) {

            }

        });
    }

    public void getZFBPay(int i, String orderId) {
        model.getZFBPay(i, orderId, new PayModel.IPayModel() {

            @Override
            public void wxSuccess(WXzfBean WXzfBean) {

            }

            @Override
            public void zfbSuccess(String string) {
                view.zfbSuccess(string);
            }

        });
    }

}
