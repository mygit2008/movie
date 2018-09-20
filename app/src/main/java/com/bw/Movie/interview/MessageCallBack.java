package com.bw.Movie.interview;

import com.bw.Movie.bean.MessageBean;
import com.bw.Movie.bean.UpdataBean;
import com.bw.Movie.model.MessageModer;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.mvplibrary.mvp.BasePresenter;
import example.com.mvplibrary.mvp.IBaseView;

/**
 * Created by WXY on 2018/8/9.
 */

public interface MessageCallBack {
    abstract class BaseRegisterPresenter<M extends BaseRegisterModer, B extends IBaseView> extends BasePresenter<MessageModer,BaseRegisterView> {

    }

    abstract class BaseRegisterModer extends BaseModel {

    }

    interface BaseRegisterView extends IBaseView {
        void Seecc(MessageBean data);
        void feali(Throwable throwable);
        void Seeccimage(UpdataBean updataBean);
    }
}
