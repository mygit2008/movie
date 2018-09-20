package com.bw.Movie.presenter;

import com.bw.Movie.bean.CommentFindBean;
import com.bw.Movie.interview.ICommentFindView;
import com.bw.Movie.model.MyCommentFindModel;

import example.com.mvplibrary.mvp.BasePresenter;

/**
 * Created by BoyLucky on 2018/8/6.
 */

public class CommentFindPresenter extends BasePresenter<MyCommentFindModel,ICommentFindView> {
    public void getAll(int commentId,int page, int count) {
          model.getCommentFind(commentId, page, count, new MyCommentFindModel.GetModel() {
              @Override
              public void getmodel(CommentFindBean commentFindBean) {
                  view.onSuccess(commentFindBean);
              }
          });
    }
}
