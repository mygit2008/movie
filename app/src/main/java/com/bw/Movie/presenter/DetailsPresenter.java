package com.bw.Movie.presenter;

import com.bw.Movie.bean.DetailsBean;
import com.bw.Movie.bean.GuanZhuBean;
import com.bw.Movie.bean.PingLunBean;
import com.bw.Movie.bean.YingBean;
import com.bw.Movie.interview.IDetailsView;
import com.bw.Movie.model.DetailsModel;

import example.com.mvplibrary.mvp.BasePresenter;

/**
 * Created by BoyLucky on 2018/8/6.
 */

public class DetailsPresenter extends BasePresenter<DetailsModel,IDetailsView> {
    public void getDetails(int id) {
        model.getDetails(id, new DetailsModel.GetModel() {
            @Override
            public void getmodel(DetailsBean recommendCinemaBean) {
                view.onSuccess(recommendCinemaBean);
            }
        });
    }

    public void getGuan(int id) {
        model.getGuan(id, new DetailsModel.GetGuan() {
            @Override
            public void getmodel(GuanZhuBean guanZhuBean) {
                view.onSuccess(guanZhuBean);
            }
        });
    }

    public void getQu(int id) {
        model.getQu(id, new DetailsModel.GetGuan() {
            @Override
            public void getmodel(GuanZhuBean guanZhuBean) {
                view.onSuccess(guanZhuBean);
            }
        });
    }

    public void getPing(int id, int page, int count) {
        model.getPing(id, page, count, new DetailsModel.GetPing() {
            @Override
            public void getmodel(PingLunBean pingLunBean) {
                view.onSuccess(pingLunBean);
            }
        });
    }

    public void getFaBiao(int id, Integer integer, String s) {
        model.getFaBiao(id, integer, s, new DetailsModel.GetGuan() {
            @Override
            public void getmodel(GuanZhuBean guanZhuBean) {
                view.onSuccess(guanZhuBean);
            }
        });
    }

    public void getYing(int id) {
        model.getYing(id, new DetailsModel.GetModel_Ying() {
            @Override
            public void getmodel(YingBean yingBean) {
                view.onSuccess(yingBean);
            }
        });
    }
}
