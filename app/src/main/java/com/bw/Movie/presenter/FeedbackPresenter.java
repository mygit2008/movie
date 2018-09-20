package com.bw.Movie.presenter;

import com.bw.Movie.bean.FeedbackBean;
import com.bw.Movie.bean.MessageBean;
import com.bw.Movie.interview.FeedbackCallBack;
import com.bw.Movie.model.FeedbackModer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WXY on 2018/8/9.
 */

public class FeedbackPresenter extends FeedbackCallBack.BaseFeedbackPresenter<FeedbackModer,FeedbackCallBack.BaseFeedbackView>{
    public void upcontent(String s) {
      model.setcontent(s)  .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Consumer<FeedbackBean>() {
                  @Override
                  public void accept(FeedbackBean feedbackBean) throws Exception {
                      view.Seecc(feedbackBean);
                  }
              }, new Consumer<Throwable>() {
                  @Override
                  public void accept(Throwable throwable) throws Exception {
                      view.feali(throwable);
                  }
              });
    }
}
