package com.bw.Movie.interview;

import com.bw.Movie.bean.DetailsBean;
import com.bw.Movie.bean.GuanZhuBean;
import com.bw.Movie.bean.PingLunBean;
import com.bw.Movie.bean.YingBean;

import example.com.mvplibrary.mvp.IBaseView;

/**
 * Created by BoyLucky on 2018/8/6.
 */

public interface IDetailsView extends IBaseView {
    void onSuccess(DetailsBean detailsBean);

    void onSuccess(GuanZhuBean guanZhuBean);

    void onSuccess(PingLunBean pingLunBean);

    void onSuccess(YingBean yingBean);
}
