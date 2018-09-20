package com.bw.Movie.presenter;

import com.bw.Movie.bean.CommentReplyBean;
import com.bw.Movie.interview.ICommentReplyView;
import com.bw.Movie.model.MyCommentReplyModel;

import java.util.HashMap;
import java.util.Map;

import example.com.mvplibrary.mvp.BasePresenter;

public class CommentReplyPresenter extends BasePresenter<MyCommentReplyModel,ICommentReplyView> {
         public void CommentReplyPresener(MyCommentReplyModel myCommentReplyModel, final ICommentReplyView iCommentReplyView){
             Map<String, String> map = new HashMap<>();
             map.put("commentId",iCommentReplyView.getcommentId());
             map.put("replyContent",iCommentReplyView.getreplyContent());
             myCommentReplyModel.onCommentMoviesData(map, new MyCommentReplyModel.CommentReplyListener() {
                 @Override
                 public void CommentReplySuccess(CommentReplyBean bean) {
                     iCommentReplyView.onSuccess(bean);
                 }
             });
         }
}
