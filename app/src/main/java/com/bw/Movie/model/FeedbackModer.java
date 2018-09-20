package com.bw.Movie.model;

import com.bw.Movie.MyApp;
import com.bw.Movie.api.SettingApi;
import com.bw.Movie.bean.FeedbackBean;
import com.bw.Movie.interview.FeedbackCallBack;

import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observable;

/**
 * Created by WXY on 2018/8/9.
 */

public class FeedbackModer extends FeedbackCallBack.BaseFeedbackModer{
    public Observable<FeedbackBean> setcontent(String s) {
       return RetrofitUtil.getInstence(MyApp.context).create(SettingApi.class).getRegister(s);
    }
}
