package com.bw.Movie.bean;

import java.util.List;

public class CommentFindBean {

    /**
     * result : [{"replyContent":"赞赞啊","replyHeadPic":"http://172.17.8.100/images/head_pic/bwjy.jpg","replyTime":1534216056000,"replyUserId":6,"replyUserName":"谁的益达"},{"replyContent":"赞赞啊","replyHeadPic":"http://172.17.8.100/images/head_pic/bwjy.jpg","replyTime":1533719015000,"replyUserId":6,"replyUserName":"谁的益达"},{"replyContent":"赞赞啊","replyHeadPic":"http://172.17.8.100/images/head_pic/bwjy.jpg","replyTime":1533715474000,"replyUserId":6,"replyUserName":"谁的益达"},{"replyContent":"赞赞啊","replyHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-08-14/20180814145117.jpg","replyTime":1533002791000,"replyUserId":5,"replyUserName":"哈哈哈"},{"replyContent":"我还没看","replyHeadPic":"http://172.17.8.100/images/head_pic/bwjy.jpg","replyTime":1532590303000,"replyUserId":6,"replyUserName":"谁的益达"}]
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
         * replyContent : 赞赞啊
         * replyHeadPic : http://172.17.8.100/images/head_pic/bwjy.jpg
         * replyTime : 1534216056000
         * replyUserId : 6
         * replyUserName : 谁的益达
         */

        private String replyContent;
        private String replyHeadPic;
        private long replyTime;
        private int replyUserId;
        private String replyUserName;

        public String getReplyContent() {
            return replyContent;
        }

        public void setReplyContent(String replyContent) {
            this.replyContent = replyContent;
        }

        public String getReplyHeadPic() {
            return replyHeadPic;
        }

        public void setReplyHeadPic(String replyHeadPic) {
            this.replyHeadPic = replyHeadPic;
        }

        public long getReplyTime() {
            return replyTime;
        }

        public void setReplyTime(long replyTime) {
            this.replyTime = replyTime;
        }

        public int getReplyUserId() {
            return replyUserId;
        }

        public void setReplyUserId(int replyUserId) {
            this.replyUserId = replyUserId;
        }

        public String getReplyUserName() {
            return replyUserName;
        }

        public void setReplyUserName(String replyUserName) {
            this.replyUserName = replyUserName;
        }
    }
}
