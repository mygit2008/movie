package com.bw.Movie.interview;

import com.bw.Movie.bean.RegisterBean;

import example.com.mvplibrary.mvp.IBaseView;

public interface RegisterView extends IBaseView {
    void onSuccess(RegisterBean registerBean);
}
