package com.bw.Movie.interview;

import com.bw.Movie.model.HobbyModer;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.mvplibrary.mvp.BasePresenter;
import example.com.mvplibrary.mvp.IBaseView;

/**
 * Created by WXY on 2018/8/10.
 */

public interface HobbyCallback {
    abstract class BaseHobbyPresenter<M extends BaseModel, B extends IBaseView> extends BasePresenter<HobbyModer,BaseHobbyView> {

    }

    abstract class  BaseHobbyModer extends BaseModel {

    }

    interface BaseHobbyView extends IBaseView {
    }
}
