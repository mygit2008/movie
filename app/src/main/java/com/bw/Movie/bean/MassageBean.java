package com.bw.Movie.bean;

/**
 * Created by WXY on 2018/8/24.
 */

public class MassageBean {
    private String massagebody;
    private boolean isuser;

    public MassageBean(String massagebody, boolean isuser) {
        this.massagebody = massagebody;
        this.isuser = isuser;
    }

    public MassageBean() {
    }

    public String getMassagebody() {
        return massagebody;
    }

    public void setMassagebody(String massagebody) {
        this.massagebody = massagebody;
    }

    public boolean isIsuser() {
        return isuser;
    }

    public void setIsuser(boolean isuser) {
        this.isuser = isuser;
    }
}
