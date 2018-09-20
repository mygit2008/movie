package com.bw.Movie.interview;

import com.bw.Movie.bean.WtBean;

import example.com.mvplibrary.mvp.IBaseView;

/**
 * Created by BoyLucky on 2018/8/23.
 */

public interface IWtView extends IBaseView {
    void onSuccess(WtBean wtBean);
}
