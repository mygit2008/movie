package com.bw.Movie.api;

import com.bw.Movie.bean.ByIdMoviesBean;
import com.bw.Movie.bean.CinemasListBean;
import com.bw.Movie.bean.CommentBean;
import com.bw.Movie.bean.CommentFindBean;
import com.bw.Movie.bean.CommentPingBean;
import com.bw.Movie.bean.CommentReplyBean;
import com.bw.Movie.bean.GuanZhuBean;
import com.bw.Movie.bean.MovieListBean;
import com.bw.Movie.bean.SchedulBean;
import com.bw.Movie.bean.WtBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface GetDataInterface {

    //热门电影
    @GET("movie/v1/findHotMovieList")
    Observable<WtBean> getHotMovies(@Query("page") int page, @Query("count") int count);

    //正在上映
    @GET("movie/v1/findReleaseMovieList")
    Observable<WtBean> getReReleaseMovies(@Query("page") int page, @Query("count") int count);

    //即将上映
    @GET("movie/v1/findComingSoonMovieList")
    Observable<WtBean> getSoonMovies(@Query("page") int page, @Query("count") int count);

    //根据电影ID查询电影信息
    @GET("movie/v1/findMoviesDetail")
    Observable<ByIdMoviesBean> getByIdMovies(@Query("movieId") int movieId);

    //查询当前排片该电影的影院列表
    @GET("movie/v1/findCinemasListByMovieId")
    Observable<CinemasListBean> getCinemasListMovies(@QueryMap Map<String, String> map);

    //根据影院ID查询该影院当前排期的电影列表
    @GET("movie/v1/findMovieListByCinemaId")
    Observable<MovieListBean> getMovieListCinema(@Query("cinemaId") String cinemaId);

    //查询电影排期列表
    @GET("movie/v1/findMovieScheduleList")
    Observable<SchedulBean> getScheduleListMovies(@QueryMap Map<String, String> map);

    //查询用户评论
    @GET("movie/v1/findAllMovieComment")
    Observable<CommentBean> getComment(@Query("movieId") int movieId, @Query("page") int page, @Query("count") int count);

    //添加用户对影片的评论
    @POST("movie/v1/verify/movieComment")
    @FormUrlEncoded
    Observable<CommentPingBean> getCommentPing(@FieldMap Map<String, String> map);

    //关注
    @GET("movie/v1/verify/followMovie")
    Observable<GuanZhuBean> getGaun(@Query("movieId") int movieId);


    //取关注
    @GET("movie/v1/verify/cancelFollowMovie")
    Observable<GuanZhuBean> getQu(@Query("movieId") int movieId);

    //影片评论回复
    @GET("movie/v1/findCommentReply")
    Observable<CommentFindBean> getCommentFind(@Query("commentId") int commentId, @Query("page") int page, @Query("count") int count);

    //用户对评论的回复
    @POST("movie/v1/verify/commentReply")
    @FormUrlEncoded
    Observable<CommentReplyBean> postReply(@FieldMap Map<String, String> map);
}
