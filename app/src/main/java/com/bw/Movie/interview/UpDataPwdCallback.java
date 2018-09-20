package com.bw.Movie.interview;

import com.bw.Movie.bean.UpdataBean;
import com.bw.Movie.model.UpDataPwdModer;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.mvplibrary.mvp.BasePresenter;
import example.com.mvplibrary.mvp.IBaseView;

/**
 * Created by WXY on 2018/8/9.
 */

public interface UpDataPwdCallback {
    abstract class BaseUpDataPwdPresenter<M extends BaseUpDataPwdModer, B extends IBaseView> extends BasePresenter<UpDataPwdModer,BaseUpDataPwdView> {

    }

    abstract class  BaseUpDataPwdModer extends BaseModel {

    }

    interface BaseUpDataPwdView extends IBaseView {
        void Seecc(UpdataBean data);
        void feali(Throwable throwable);
    }
}
