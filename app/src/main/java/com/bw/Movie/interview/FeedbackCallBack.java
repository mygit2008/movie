package com.bw.Movie.interview;

import com.bw.Movie.bean.FeedbackBean;
import com.bw.Movie.model.FeedbackModer;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.mvplibrary.mvp.BasePresenter;
import example.com.mvplibrary.mvp.IBaseView;

/**
 * Created by WXY on 2018/8/9.
 */

public interface FeedbackCallBack {
    abstract class BaseFeedbackPresenter<M extends BaseModel, B extends IBaseView> extends BasePresenter<FeedbackModer,BaseFeedbackView> {

    }

    abstract class  BaseFeedbackModer extends BaseModel {

    }

    interface BaseFeedbackView extends IBaseView {
        void Seecc(FeedbackBean data);
        void feali(Throwable throwable);
    }

}
