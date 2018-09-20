package com.bw.Movie.presenter;

import com.bw.Movie.bean.ByIdMoviesBean;
import com.bw.Movie.bean.GuanZhuBean;
import com.bw.Movie.interview.IByIdMoviesView;
import com.bw.Movie.model.MyByIdMoviesModel;

import example.com.mvplibrary.mvp.BasePresenter;

public class ByIdMoviesPresenter extends BasePresenter<MyByIdMoviesModel,IByIdMoviesView> {
    private static final String TAG = "ByIdMoviesPresenter----";
         public void ByIdMoviesPresener(int movieId){
             model.onByIdMoviesData(movieId, new MyByIdMoviesModel.ByIdMoviesListener() {
                 @Override
                 public void ByIdMoviesSuccess(ByIdMoviesBean bean) {
                     view.ByIdMoviesSuccess(bean);
                 }
             });
         }
    public void getGuan(MyByIdMoviesModel myByIdMoviesModel,int p) {
        myByIdMoviesModel.getGuan(p, new MyByIdMoviesModel.GetModel() {
            @Override
            public void getmodel(GuanZhuBean recommendCinemaBean) {
                view.HotMoviesSuccess(recommendCinemaBean);
            }
        });
    }

    public void getQu(MyByIdMoviesModel myByIdMoviesModel,int id) {
        myByIdMoviesModel.getQu(id, new MyByIdMoviesModel.GetModel() {
            @Override
            public void getmodel(GuanZhuBean recommendCinemaBean) {
                view.HotMoviesSuccess(recommendCinemaBean);
            }
        });
    }
}
