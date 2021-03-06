package com.bw.Movie.bean;

import java.util.List;

/**
 * Created by BoyLucky on 2018/8/2.
 */

public class RecommendCinemaBean {

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        private List<FollowCinemasBean> followCinemas;
        private List<NearbyCinemaListBean> nearbyCinemaList;

        public List<FollowCinemasBean> getFollowCinemas() {
            return followCinemas;
        }

        public void setFollowCinemas(List<FollowCinemasBean> followCinemas) {
            this.followCinemas = followCinemas;
        }

        public List<NearbyCinemaListBean> getNearbyCinemaList() {
            return nearbyCinemaList;
        }

        public void setNearbyCinemaList(List<NearbyCinemaListBean> nearbyCinemaList) {
            this.nearbyCinemaList = nearbyCinemaList;
        }

        public static class FollowCinemasBean {
            /**
             * address : 西城区前门大街大栅栏街36号
<<<<<<< HEAD
             * commentTotal : 3
=======
             * commentTotal : 4
>>>>>>> deb7d9ff750ae2f806a5706ce25ac429ae60ceca
             * distance : 17774
             * followCinema : true
             * id : 2
             * logo : http://172.17.8.100/images/movie/logo/dgl.jpg
             * name : 大观楼电影院
             */

            private String address;
            private int commentTotal;
            private int distance;
            private boolean followCinema;
            private int id;
            private String logo;
            private String name;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getCommentTotal() {
                return commentTotal;
            }

            public void setCommentTotal(int commentTotal) {
                this.commentTotal = commentTotal;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public boolean isFollowCinema() {
                return followCinema;
            }

            public void setFollowCinema(boolean followCinema) {
                this.followCinema = followCinema;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class NearbyCinemaListBean {
            /**
             * address : 北京市海淀区上地南口华联商厦4F
             * commentTotal : 0
             * distance : 1647
             * followCinema : false
             * id : 18
             * logo : http://172.17.8.100/images/movie/logo/ctjh.jpg
             * name : 橙天嘉禾影城北京上地店
             */

            private String address;
            private int commentTotal;
            private int distance;
            private boolean followCinema;
            private int id;
            private String logo;
            private String name;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getCommentTotal() {
                return commentTotal;
            }

            public void setCommentTotal(int commentTotal) {
                this.commentTotal = commentTotal;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public boolean isFollowCinema() {
                return followCinema;
            }

            public void setFollowCinema(boolean followCinema) {
                this.followCinema = followCinema;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
