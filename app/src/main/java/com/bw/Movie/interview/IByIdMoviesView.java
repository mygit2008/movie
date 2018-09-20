package com.bw.Movie.interview;

import com.bw.Movie.bean.ByIdMoviesBean;
import com.bw.Movie.bean.GuanZhuBean;

import example.com.mvplibrary.mvp.IBaseView;

public interface IByIdMoviesView extends IBaseView {
    //登录成功
    void ByIdMoviesSuccess(ByIdMoviesBean bean);

    void HotMoviesSuccess(GuanZhuBean recommendCinemaBean);
}
