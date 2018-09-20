package com.bw.Movie.interview;

import com.bw.Movie.bean.CommentBean;

import example.com.mvplibrary.mvp.IBaseView;

/**
 * Created by BoyLucky on 2018/8/2.
 */

public interface ICommentView extends IBaseView {
    void onSuccess(CommentBean commentBean);
}
