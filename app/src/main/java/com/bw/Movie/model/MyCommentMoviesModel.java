package com.bw.Movie.model;

import com.bw.Movie.MyApp;
import com.bw.Movie.api.GetDataInterface;
import com.bw.Movie.bean.CommentPingBean;

import java.util.Map;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyCommentMoviesModel extends BaseModel {

     public void onCommentMoviesData(Map<String, String> map, final CommentMoviesListener commentMoviesListener){
         RetrofitUtil.getInstence(MyApp.context).create(GetDataInterface.class)
                 .getCommentPing(map)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Observer<CommentPingBean>() {
                     @Override
                     public void onSubscribe(Disposable d) {

                     }

                     @Override
                     public void onNext(CommentPingBean commentPingBean) {
                          commentMoviesListener.CommentMoviesSuccess(commentPingBean);
                     }

                     @Override
                     public void onError(Throwable e) {

                     }

                     @Override
                     public void onComplete() {

                     }
                 });
     }
    public interface CommentMoviesListener {
        //成功时调用
        void CommentMoviesSuccess(CommentPingBean bean);
    }

}
