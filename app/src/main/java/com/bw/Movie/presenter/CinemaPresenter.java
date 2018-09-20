package com.bw.Movie.presenter;

import com.bw.Movie.bean.RecommendCinemaBean;
import com.bw.Movie.interview.ICinemaView;
import com.bw.Movie.model.CinemaModel;

import example.com.mvplibrary.mvp.BasePresenter;

/**
 * Created by BoyLucky on 2018/8/2.
 */

public class CinemaPresenter extends BasePresenter<CinemaModel,ICinemaView>{
    public void getCinemas(int page, int count, String longitude, String latitude) {

        model.getCinemas(page,count,longitude,latitude,new CinemaModel.GetModel() {
            @Override
            public void getmodel(RecommendCinemaBean recommendCinemaBean) {
                view.onSuccess(recommendCinemaBean);
            }
        });
    }
}
