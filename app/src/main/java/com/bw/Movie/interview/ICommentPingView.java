package com.bw.Movie.interview;

import com.bw.Movie.bean.CommentPingBean;

import example.com.mvplibrary.mvp.IBaseView;

/**
 * Created by BoyLucky on 2018/8/2.
 */

public interface ICommentPingView extends IBaseView {
    String getCommentContent();
    String getMovield();
    void onSuccess(CommentPingBean commentPingBean);
}
