package com.bw.Movie.presenter;

import com.bw.Movie.bean.GouPiaoJiLuBean;
import com.bw.Movie.interview.GPJLView;
import com.bw.Movie.model.GPJLModel;

import example.com.mvplibrary.mvp.BasePresenter;

public class GPJLPresenter extends BasePresenter<GPJLModel,GPJLView> {
    public void getGPJL(){
        model.getGPJL(new GPJLModel.GetModel() {
            @Override
            public void onSuccess(GouPiaoJiLuBean gouPiaoJiLuBean) {
                view.onSuccess(gouPiaoJiLuBean);
            }
        });
    }
}
