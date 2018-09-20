package com.bw.Movie.interview;

import com.bw.Movie.bean.RecommendCinemaBean;

import example.com.mvplibrary.mvp.IBaseView;

/**
 * Created by BoyLucky on 2018/8/2.
 */

public interface ICinemaView extends IBaseView {
    void onSuccess(RecommendCinemaBean recommendCinemaBean);
}
