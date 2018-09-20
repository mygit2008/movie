package com.bw.Movie.interview;

import com.bw.Movie.bean.AllBean;

import example.com.mvplibrary.mvp.IBaseView;

/**
 * Created by BoyLucky on 2018/8/2.
 */

public interface IAllView extends IBaseView {
    void onSuccess(AllBean recommendCinemaBean);
}
