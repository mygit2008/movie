package com.bw.Movie.model;

import com.bw.Movie.MyApp;
import com.bw.Movie.api.GetDataInterface;
import com.bw.Movie.bean.CinemasListBean;

import java.util.Map;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyCinemasListMoviesModel extends BaseModel {

     public void onCinemasListMoviesData(Map<String, String> map, final CinemasListMoviesListener cinemasListMoviesListener){
         RetrofitUtil.getInstence(MyApp.context).create(GetDataInterface.class)
                 .getCinemasListMovies(map)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Observer<CinemasListBean>() {
                     @Override
                     public void onSubscribe(Disposable d) {

                     }

                     @Override
                     public void onNext(CinemasListBean cinemasListBean) {
                          cinemasListMoviesListener.CinemasListMoviesSuccess(cinemasListBean);
                     }

                     @Override
                     public void onError(Throwable e) {

                     }

                     @Override
                     public void onComplete() {

                     }
                 });
     }
    public interface CinemasListMoviesListener {
        //成功时调用
        void CinemasListMoviesSuccess(CinemasListBean bean);
    }

}
