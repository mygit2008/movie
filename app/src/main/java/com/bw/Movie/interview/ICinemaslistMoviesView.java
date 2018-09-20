package com.bw.Movie.interview;

import com.bw.Movie.bean.CinemasListBean;

import example.com.mvplibrary.mvp.IBaseView;

public interface ICinemaslistMoviesView extends IBaseView {
    //电影ID
    String getMovieId();
    //登录成功
    void HotMoviesSuccess(CinemasListBean bean);
}
