package com.bw.Movie.api;

import com.bw.Movie.bean.IndentBean;
import com.bw.Movie.bean.WXzfBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author zhangjunyou
 * @date 2018/8/13
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */
public interface PaymentApi {
    @POST("movie/v1/verify/buyMovieTicket")
    @FormUrlEncoded
    Observable<IndentBean> getOrders(@Field("scheduleId") int scheduleId, @Field("amount") int amount, @Field("sign") String sign);

    @POST("movie/v1/verify/pay")
    @FormUrlEncoded
    Observable<WXzfBean> getWXPay(@Field("payType") int payType, @Field("orderId") String orderId);

    @POST("movie/v1/verify/pay")
    @FormUrlEncoded
    Observable<ResponseBody> getZFBPay(@Field("payType") int payType, @Field("orderId") String orderId);
}
