package com.bw.Movie.presenter;

import com.bw.Movie.bean.AllBean;
import com.bw.Movie.interview.IAllView;
import com.bw.Movie.model.AllModel;

import example.com.mvplibrary.mvp.BasePresenter;

/**
 * Created by BoyLucky on 2018/8/6.
 */

public class AllPresenter extends BasePresenter<AllModel,IAllView> {
    public void getAll(int page, int count) {
        model.getAll(page, count, new AllModel.GetModel() {
            @Override
            public void getmodel(AllBean recommendCinemaBean) {
                view.onSuccess(recommendCinemaBean);
            }
        });
    }
}
