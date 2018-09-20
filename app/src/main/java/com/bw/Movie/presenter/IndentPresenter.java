package com.bw.Movie.presenter;

import com.bw.Movie.bean.IndentBean;
import com.bw.Movie.bean.MovieListBean;
import com.bw.Movie.bean.SchedulBean;
import com.bw.Movie.bean.WXzfBean;
import com.bw.Movie.interview.IndentView;
import com.bw.Movie.model.IndentModel;

import java.util.Map;

import example.com.mvplibrary.mvp.BasePresenter;

/**
 * @author zhangjunyou
 * @date 2018/8/13
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */
public class IndentPresenter extends BasePresenter<IndentModel, IndentView> {

    public void getOrders(final int i, int i1, String sign) {
        model.getOrders(i, i1, sign, new IndentModel.IIndentModel() {
            @Override
            public void success(IndentBean indentBean) {
                view.success(indentBean);
            }

            @Override
            public void ScheduleMoviesSuccess(SchedulBean bean) {

            }

            @Override
            public void ScheduleCinema(MovieListBean movieListBean) {

            }
        });
    }


    public void ScheduleMoviesPresener(Map<String, String> map) {
        model.onScheduleMoviesData(map, new IndentModel.IIndentModel() {
            @Override
            public void success(IndentBean indentBean) {

            }

            @Override
            public void ScheduleMoviesSuccess(SchedulBean bean) {
                view.ScheduleSuccess(bean);
            }

            @Override
            public void ScheduleCinema(MovieListBean movieListBean) {

            }
        });
    }

    public void getMovieListCinema(String mid) {
        model.getMovieListCinema(mid, new IndentModel.IIndentModel() {
            @Override
            public void success(IndentBean indentBean) {

            }

            @Override
            public void ScheduleMoviesSuccess(SchedulBean bean) {

            }

            @Override
            public void ScheduleCinema(MovieListBean movieListBean) {
                view.ScheduleCinema(movieListBean);
            }
        });
    }
}
