package com.bw.Movie.model;

import com.bw.Movie.MyApp;
import com.bw.Movie.api.GetDataInterface;
import com.bw.Movie.bean.CommentReplyBean;

import java.util.Map;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyCommentReplyModel extends BaseModel {

     public void onCommentMoviesData(Map<String, String> map, final CommentReplyListener commentReplyListener){
         RetrofitUtil.getInstence(MyApp.context).create(GetDataInterface.class)
                 .postReply(map)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Observer<CommentReplyBean>() {
                     @Override
                     public void onSubscribe(Disposable d) {

                     }

                     @Override
                     public void onNext(CommentReplyBean commentReplyBean) {
                         commentReplyListener.CommentReplySuccess(commentReplyBean);
                     }

                     @Override
                     public void onError(Throwable e) {

                     }

                     @Override
                     public void onComplete() {

                     }
                 });
     }
    public interface CommentReplyListener {
        //成功时调用
        void CommentReplySuccess(CommentReplyBean bean);
    }

}
