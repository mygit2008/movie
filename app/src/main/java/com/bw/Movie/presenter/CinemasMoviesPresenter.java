package com.bw.Movie.presenter;

import com.bw.Movie.bean.CinemasListBean;
import com.bw.Movie.interview.ICinemaslistMoviesView;
import com.bw.Movie.model.MyCinemasListMoviesModel;

import java.util.HashMap;
import java.util.Map;

import example.com.mvplibrary.mvp.BasePresenter;

public class CinemasMoviesPresenter extends BasePresenter<MyCinemasListMoviesModel,ICinemaslistMoviesView> {
         public void CinemasMoviesPresener(MyCinemasListMoviesModel myCinemasListMoviesModel, final ICinemaslistMoviesView iCinemaslistMoviesView){
             Map<String, String> map = new HashMap<>();
             map.put("movieId",iCinemaslistMoviesView.getMovieId());
             myCinemasListMoviesModel.onCinemasListMoviesData(map, new MyCinemasListMoviesModel.CinemasListMoviesListener() {
                 @Override
                 public void CinemasListMoviesSuccess(CinemasListBean bean) {
                     if(iCinemaslistMoviesView !=null){
                         iCinemaslistMoviesView.HotMoviesSuccess(bean);
                     }
                 }
             });
         }
}
