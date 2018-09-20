package com.bw.Movie.model;

import com.bw.Movie.MyApp;
import com.bw.Movie.api.GetDataInterface;
import com.bw.Movie.bean.CommentFindBean;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by BoyLucky on 2018/8/6.
 */

public class MyCommentFindModel extends BaseModel {

    public void getCommentFind(int commentId,int page, int count, final GetModel getModel) {
        RetrofitUtil.getInstence(MyApp.context).create(GetDataInterface.class)
                .getCommentFind(commentId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentFindBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommentFindBean commentFindBean) {
                          getModel.getmodel(commentFindBean);
                     }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface GetModel{
        void getmodel(CommentFindBean commentFindBean);
    }
}
