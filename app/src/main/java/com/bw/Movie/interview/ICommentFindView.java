package com.bw.Movie.interview;

import com.bw.Movie.bean.CommentFindBean;

import example.com.mvplibrary.mvp.IBaseView;

/**
 * Created by BoyLucky on 2018/8/2.
 */

public interface ICommentFindView extends IBaseView {
    void onSuccess(CommentFindBean commentFindBean);
}
