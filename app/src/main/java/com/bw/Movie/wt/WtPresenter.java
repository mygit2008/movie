package com.bw.Movie.wt;

import example.com.mvplibrary.mvp.BasePresenter;

/**
 * Created by BoyLucky on 2018/8/23.
 */

public class WtPresenter extends BasePresenter<WtModel,IWtView> {
    public void getWt(int page, int count) {
        model.getWt(page, count, new WtModel.GetModel() {
            @Override
            public void getmodel(WtBean wtBean) {
                view.onSuccess(wtBean);
            }
        });
    }
    public void getWt1(int page, int count) {
        model.getWt1(page, count, new WtModel.GetModel() {
            @Override
            public void getmodel(WtBean wtBean) {
                view.onSuccess(wtBean);
            }
        });
    }
    public void getWt2(int page, int count) {
        model.getWt2(page, count, new WtModel.GetModel() {
            @Override
            public void getmodel(WtBean wtBean) {
                view.onSuccess(wtBean);
            }
        });
    }
}
