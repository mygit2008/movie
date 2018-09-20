package com.bw.Movie.api;

import com.bw.Movie.bean.AllBean;
import com.bw.Movie.bean.DetailsBean;
import com.bw.Movie.bean.GuanZhuBean;
import com.bw.Movie.bean.PingLunBean;
import com.bw.Movie.bean.RecommendCinemaBean;
import com.bw.Movie.bean.YingBean;
import com.bw.Movie.wt.WtBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by BoyLucky on 2018/8/3.
 */

public interface CinemaApi {
    @GET("cinema/v1/findRecommendCinemas")
    Observable<RecommendCinemaBean> getRecommendCinema(@Query("page") int page, @Query("count") int count,@Query("longitude") String longitude, @Query("latitude") String latitude);

    @GET("cinema/v1/findAllCinemas")
    Observable<AllBean> getAll(@Query("page") int page, @Query("count") int count);

    @GET("cinema/v1/findCinemaInfo")
    Observable<DetailsBean> getDetails(@Query("cinemaId") int cinemaId);

    @GET("cinema/v1/verify/followCinema")
    Observable<GuanZhuBean> getGuan(@Query("cinemaId") int cinemaId);

    @GET("cinema/v1/verify/cancelFollowCinema")
    Observable<GuanZhuBean> getQu(@Query("cinemaId") int cinemaId);

    @GET("cinema/v1/findAllCinemaComment")
    Observable<PingLunBean> getPing(@Query("cinemaId") int cinemaId, @Query("page") int page, @Query("count") int count);

    @POST("cinema1erify/cinemaComment")
    @FormUrlEncoded
    Observable<GuanZhuBean> getFaBiao(@Field("cinemaId") int cinemaId, @Field("commentUserId") int commentUserId, @Field("commentContent") String commentContent);

    @GET("movie/v1/findMovieListByCinemaId")
    Observable<YingBean> getying(@Query("cinemaId") int cinemaId);

    @GET("movie/v1/findHotMovieList")
    Observable<WtBean> getWt(@Query("page") int page,@Query("count") int count);
    @GET("movie/v1/findReleaseMovieList")
    Observable<WtBean> getWt1(@Query("page") int page,@Query("count") int count);
    @GET("movie/v1/findComingSoonMovieList")
    Observable<WtBean> getWt2(@Query("page") int page,@Query("count") int count);
}
