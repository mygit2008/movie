package com.bw.Movie.interview;

import com.bw.Movie.bean.IndentBean;
import com.bw.Movie.bean.MovieListBean;
import com.bw.Movie.bean.SchedulBean;
import com.bw.Movie.bean.WXzfBean;

import example.com.mvplibrary.mvp.IBaseView;

/**
 * @author zhangjunyou
 * @date 2018/8/16
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */
public interface IndentView extends IBaseView {
    void success(IndentBean indentBean);

    void ScheduleSuccess(SchedulBean schedulBean);

    void ScheduleCinema(MovieListBean movieListBean);

}
