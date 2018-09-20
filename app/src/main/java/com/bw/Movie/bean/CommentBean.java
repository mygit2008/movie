package com.bw.Movie.bean;

import java.util.List;

public class CommentBean {

    /**
     * result : [{"commentContent":"穷逼只有我i个","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":73,"commentTime":1534468709000,"commentUserId":13,"commentUserName":"王彭坤","greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"fdfgsdfgs是股市大幅改善","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":72,"commentTime":1534468691000,"commentUserId":13,"commentUserName":"王彭坤","greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"哈哈哈","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":71,"commentTime":1534468472000,"commentUserId":13,"commentUserName":"王彭坤","greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"哈哈","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-08-16/20180816153845.unknown","commentId":70,"commentTime":1534408245000,"commentUserId":131,"commentUserName":"刘晓栋","greatNum":2,"hotComment":0,"isGreat":0},{"commentContent":"如果是艺术请不要遮遮掩掩，如果是色情请不要发出来。","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-08-10/20180810190800.jpg","commentId":68,"commentTime":1534404218000,"commentUserId":10,"commentUserName":"再BB头给你捶烂哟","greatNum":3,"hotComment":0,"isGreat":0}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commentContent : 穷逼只有我i个
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/bwjy.jpg
         * commentId : 73
         * commentTime : 1534468709000
         * commentUserId : 13
         * commentUserName : 王彭坤
         * greatNum : 0
         * hotComment : 0
         * isGreat : 0
         */

        private String commentContent;
        private String commentHeadPic;
        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String commentUserName;
        private int greatNum;
        private int hotComment;
        private int isGreat;

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public int getHotComment() {
            return hotComment;
        }

        public void setHotComment(int hotComment) {
            this.hotComment = hotComment;
        }

        public int getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(int isGreat) {
            this.isGreat = isGreat;
        }
    }
}
