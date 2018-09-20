package com.bw.Movie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bw.Movie.R;
import com.bw.Movie.bean.UserInfoBean;
import com.bw.Movie.bean.UserSignInBean;
import com.bw.Movie.interview.UserSignInView;
import com.bw.Movie.model.UserSignInModel;
import com.bw.Movie.presenter.UserSignInPresenter;
import com.bw.Movie.widget.SignCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import example.com.mvplibrary.BaseActivity;
import example.com.mvplibrary.mvp.BaseModel;

public class QianDaoActivity extends BaseActivity<UserSignInPresenter> implements UserSignInView{

    private String date;
    private SignCalendar calendar;
    private Button btn_sign;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_qian_dao;
    }

    @Override
    protected void initData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        date = formatter.format(curDate);
        calendar = (SignCalendar) findViewById(R.id.sc_main);
        btn_sign = (Button) findViewById(R.id.btn_sign);
        btn_sign.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                presenter.getUserSignIn();
                // TODO Auto-generated method stub
                List<String> list = new ArrayList<String>();
//                list.add("2016-06-30");
                list.add(date);
//                calendar.setCalendarDaysBgColor(list,R.mipmap.qiandao_true);
                calendar.addMarks(list, 0);

            }
        });
    }

    @Override
    protected UserSignInPresenter initPresenter() {
        return new UserSignInPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new UserSignInModel();
    }

    @Override
    public void onSuccess(UserSignInBean userSignInBean) {
        String message = userSignInBean.getMessage();
        if (message.equals("签到成功")){

        }
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserInfoSuccess(UserInfoBean userInfoBean) {

    }
}

