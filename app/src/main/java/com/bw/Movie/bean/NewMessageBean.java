package com.bw.Movie.bean;

public class NewMessageBean {
    String mid;
    String cid;
    String commentTime;
    String coment;
    String name;
    String pic;

    public NewMessageBean(String mid, String cid, String commentTime, String coment, String name, String pic) {
        this.mid = mid;
        this.cid = cid;
        this.commentTime = commentTime;
        this.coment = coment;
        this.name = name;
        this.pic = pic;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
