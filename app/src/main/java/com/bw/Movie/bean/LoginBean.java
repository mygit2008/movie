package com.bw.Movie.bean;

public class LoginBean {

/**
 * @author zhangjunyou
 * @date 2018/6/20
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */


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

        private String sessionId;
        private int userId;
        private UserInfoBean userInfo;

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean {
            /**
<<<<<<< HEAD
             * birthday : 320256000000
             * headPic : http://172.17.8.100/images/movie/head_pic/2018-07-21/20180721120945.jpg
             * id : 5
             * lastLoginTime : 1533197707000
             * nickName : 你的益达
             * phone : 18600151568
=======
             * birthday : -2185430743000
             * headPic : http://172.17.8.100/images/movie/head_pic/bwjy.jpg
             * id : 29
             * lastLoginTime : 1533606644000
             * nickName : 已宕机……
             * phone : 13693214379
>>>>>>> feature_yin
             * sex : 1
             */

            private long birthday;
            private String headPic;
            private int id;
            private long lastLoginTime;
            private String nickName;
            private String phone;
            private int sex;

            public long getBirthday() {
                return birthday;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public String getHeadPic() {
                return headPic;
            }

            public void setHeadPic(String headPic) {
                this.headPic = headPic;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getLastLoginTime() {
                return lastLoginTime;
            }

            public void setLastLoginTime(long lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }
        }
    }
}
