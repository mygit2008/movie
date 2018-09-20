package com.bw.Movie.api;

import com.bw.Movie.bean.GouPiaoJiLuBean;
import com.bw.Movie.bean.LoginBean;
import com.bw.Movie.bean.RegisterBean;
import com.bw.Movie.bean.UserBean;
import com.bw.Movie.bean.UserInfoBean;
import com.bw.Movie.bean.UserSignInBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author zhangjunyou
 * @date 2018/7/21
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public interface APIFunction {
    /**
     * 用户登录
     *
     * @param phone
     * @param password
     * @return
     */
    @POST("user/v1/login")
    @FormUrlEncoded
    Observable<LoginBean> getLogin(
            @Field("phone") String phone,
            @Field("pwd") String password
    );

    /**
     * 用户注册
     *
     * @param nickName
     * @param phone
     * @param pwd
     * @param pwd2
     * @param sex
     * @param email
     * @return
     */
    @POST("user/v1/registerUser")
    @FormUrlEncoded
    Observable<RegisterBean> getRegister(
            @Field("nickName") String nickName,
            @Field("phone") String phone,
            @Field("pwd") String pwd,
            @Field("pwd2") String pwd2,
            @Field("sex") int sex,
            /*@Field("birthday") String birthday,
            @Field("imei") String imei,
            @Field("ua") String ua,
            @Field("screenSize") String screenSize,
            @Field("os") String os,*/
            @Field("email") String email
    );

    /**
     * 用户签到
     *
     * @return
     */
    @GET("user/v1/verify/userSignIn")
    Observable<UserSignInBean> getUserSignIn();

    @GET("user/v1/verify/findUserHomeInfo")
    Observable<UserInfoBean> getUserInfo();

    @GET("user/v1/verify/getUserInfoByUserId")
    Observable<UserBean> getUser();

    @GET("user/v1/verify/findUserBuyTicketRecordList")
    Observable<GouPiaoJiLuBean> getGPJL();
}
