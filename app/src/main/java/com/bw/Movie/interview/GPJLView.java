package com.bw.Movie.interview;

import com.bw.Movie.bean.GouPiaoJiLuBean;

import example.com.mvplibrary.mvp.IBaseView;

public interface GPJLView extends IBaseView {
    void onSuccess(GouPiaoJiLuBean gouPiaoJiLuBean);
}
