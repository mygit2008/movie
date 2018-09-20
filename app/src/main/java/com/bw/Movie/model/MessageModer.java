package com.bw.Movie.model;

import com.bw.Movie.MyApp;
import com.bw.Movie.api.SettingApi;
import com.bw.Movie.bean.MessageBean;
import com.bw.Movie.bean.UpdataBean;
import com.bw.Movie.interview.MessageCallBack;

import java.io.File;

import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by WXY on 2018/8/9.
 */

public class MessageModer extends MessageCallBack.BaseRegisterModer {
    public Observable<MessageBean> uploderdata(String s, int sex) {
        return RetrofitUtil.getInstence(MyApp.context).create(SettingApi.class).getRegister(s,sex);
    }

    public Observable<UpdataBean> uploderiamge(File file) {
        RequestBody filebody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), filebody);
        return RetrofitUtil.getInstence(MyApp.context).create(SettingApi.class).uploadFile(part);
    }
}
