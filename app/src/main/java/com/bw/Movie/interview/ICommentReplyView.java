package com.bw.Movie.interview;

import com.bw.Movie.bean.CommentReplyBean;

import example.com.mvplibrary.mvp.IBaseView;

/**
 * Created by BoyLucky on 2018/8/2.
 */

public interface ICommentReplyView extends IBaseView {
    //评论id
    String getcommentId();
    //回复内容
    String getreplyContent();
    void onSuccess(CommentReplyBean commentReplyBean);
}
