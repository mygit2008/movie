package com.bw.Movie.bean;

/**
 * @author zhangjunyou
 * @date 2018/8/25
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */
public class NoteBean {
    private String cinema_name;
    private String price;
    private String seatNum;
    private String screeningHall;

    public NoteBean(String cinema_name, String price, String seatNum, String screeningHall) {
        this.cinema_name = cinema_name;
        this.price = price;
        this.seatNum = seatNum;
        this.screeningHall = screeningHall;
    }

    public String getCinema_name() {
        return cinema_name;
    }

    public void setCinema_name(String cinema_name) {
        this.cinema_name = cinema_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public String getScreeningHall() {
        return screeningHall;
    }

    public void setScreeningHall(String screeningHall) {
        this.screeningHall = screeningHall;
    }
}
