package com.bw.Movie.model;

import com.bw.Movie.MyApp;
import com.bw.Movie.api.SettingApi;
import com.bw.Movie.bean.UpdataBean;
import com.bw.Movie.interview.UpDataPwdCallback;

import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observable;

/**
 * Created by WXY on 2018/8/9.
 */

public class UpDataPwdModer extends UpDataPwdCallback.BaseUpDataPwdModer {

    public Observable<UpdataBean> updatapwd(String s, String s1, String s2) {
        return RetrofitUtil.getInstence(MyApp.context).create(SettingApi.class).getRegister(s,s1,s2);
    }
}
