package com.bw.Movie.api;

import com.bw.Movie.bean.FeedbackBean;
import com.bw.Movie.bean.MessageBean;
import com.bw.Movie.bean.UpdataBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by WXY on 2018/8/9.
 */

public interface SettingApi {
//    http://172.17.8.100/movieApi/tool/v1/verify/recordFeedBack
    @POST("tool/v1/verify/recordFeedBack")
    @FormUrlEncoded
    Observable<FeedbackBean> getRegister(
            @Field("content") String context
    );
    @POST("user/v1/verify/modifyUserInfo")
    @FormUrlEncoded
    Observable<MessageBean> getRegister(
            @Field("nickName") String nickName,
            @Field("sex") int sex
    );
    @POST("tool/v1/verify/recordFeedBack")
    @FormUrlEncoded
    Observable<UpdataBean> getRegister(
            @Field("content") String oldPwd,
            @Field("newPwd") String newPwd,
            @Field("newPwd2") String newPwd2
    );
    //信息上传
    @POST("user/v1/verify/uploadHeadPic")
    @Multipart
    Observable<UpdataBean> uploadFile(@Part MultipartBody.Part file);

}
