package com.bw.Movie.interview;

import com.bw.Movie.bean.WXzfBean;

import example.com.mvplibrary.mvp.IBaseView;

/**
 * @author zhangjunyou
 * @date 2018/8/25
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */
public interface IPayView extends IBaseView {
    void wxSuccess(WXzfBean WXzfBean);

    void zfbSuccess(String string);
}
