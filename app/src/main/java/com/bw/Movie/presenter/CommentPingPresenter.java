package com.bw.Movie.presenter;

import com.bw.Movie.bean.CommentPingBean;
import com.bw.Movie.interview.ICommentPingView;
import com.bw.Movie.model.MyCommentMoviesModel;

import java.util.HashMap;
import java.util.Map;

import example.com.mvplibrary.mvp.BasePresenter;

public class CommentPingPresenter extends BasePresenter<MyCommentMoviesModel,ICommentPingView> {
         public void CommentMoviesPresener(MyCommentMoviesModel myCommentMoviesModel, final ICommentPingView iCommentPingView){
             Map<String, String> map = new HashMap<>();
             map.put("movieId",iCommentPingView.getMovield());
             map.put("commentContent",iCommentPingView.getCommentContent());
             myCommentMoviesModel.onCommentMoviesData(map, new MyCommentMoviesModel.CommentMoviesListener() {
                 @Override
                 public void CommentMoviesSuccess(CommentPingBean bean) {
                     if(iCommentPingView !=null){
                         iCommentPingView.onSuccess(bean);
                     }
                 }
             });
         }
}
