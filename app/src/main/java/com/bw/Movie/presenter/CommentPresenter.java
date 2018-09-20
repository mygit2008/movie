package com.bw.Movie.presenter;

import com.bw.Movie.bean.CommentBean;
import com.bw.Movie.interview.ICommentView;
import com.bw.Movie.model.CommentModel;

import example.com.mvplibrary.mvp.BasePresenter;

/**
 * Created by BoyLucky on 2018/8/6.
 */

public class CommentPresenter extends BasePresenter<CommentModel,ICommentView> {
    public void getAll(int movieId,int page, int count) {
        model.getComment(movieId, page, count, new CommentModel.GetModel() {
            @Override
            public void getmodel(CommentBean commentBean) {
                view.onSuccess(commentBean);
            }
        });
    }
}
