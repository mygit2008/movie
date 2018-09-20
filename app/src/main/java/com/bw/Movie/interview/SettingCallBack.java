package com.bw.Movie.interview;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.mvplibrary.mvp.BasePresenter;
import example.com.mvplibrary.mvp.IBaseView;

/**
 * Created by WXY on 2018/8/3.
 */

public interface SettingCallBack {

    abstract class SettingPresenter extends BasePresenter<SettingModer,SettingView> {

    }

    abstract class SettingModer extends BaseModel {

    }

    interface SettingView extends IBaseView {

    }
}
