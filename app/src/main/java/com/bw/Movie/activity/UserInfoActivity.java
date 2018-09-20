package com.bw.Movie.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.Movie.MyApp;
import com.bw.Movie.R;
import com.bw.Movie.bean.UserBean;
import com.bw.Movie.interview.UserView;
import com.bw.Movie.model.UserModel;
import com.bw.Movie.presenter.UserPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import example.com.mvplibrary.BaseActivity;
import example.com.mvplibrary.mvp.BaseModel;
import example.com.mvplibrary.mvp.BasePresenter;
import example.com.utilslibrary.utils.SharedPreferencesUtil;
import example.com.utilslibrary.utils.TimeUtil;

public class UserInfoActivity extends BaseActivity<UserPresenter> implements View.OnClickListener,UserView {


    private ImageView mUserinfoBack;
    private TextView mUpdateUserinfo;
    private SimpleDraweeView mUserinfoImg;
    private TextView mUserinfoNickname;
    private TextView mUserinfoSex;
    private TextView mUserinfoBirthday;
    private TextView mUserinfoEmail;
    private TextView mUserinfoPhone;
    private TextView mUserinfoWechat;
    private RelativeLayout mBangdingwx;
    private Button mTuiChuDengLu;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initData() {
        presenter.getUser();
    }

    @Override
    protected UserPresenter initPresenter() {
        return new UserPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new UserModel();
    }

    @Override
    public void initView() {
        mUserinfoBack = (ImageView) findViewById(R.id.userinfo_back);
        mUserinfoBack.setOnClickListener(this);
        mUpdateUserinfo = (TextView) findViewById(R.id.update_userinfo);
        mUpdateUserinfo.setOnClickListener(this);
        mUserinfoImg = (SimpleDraweeView) findViewById(R.id.userinfo_img);
        mUserinfoNickname = (TextView) findViewById(R.id.userinfo_nickname);
        mUserinfoSex = (TextView) findViewById(R.id.userinfo_sex);
        mUserinfoBirthday = (TextView) findViewById(R.id.userinfo_birthday);
        mUserinfoEmail = (TextView) findViewById(R.id.userinfo_email);
        mUserinfoPhone = (TextView) findViewById(R.id.userinfo_phone);
        mTuiChuDengLu = (Button) findViewById(R.id.tuichudenglu);
        mUserinfoWechat = (TextView) findViewById(R.id.userinfo_wechat);
        mBangdingwx = (RelativeLayout) findViewById(R.id.bangdingwx);
        mBangdingwx.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.userinfo_back:
                finish();
                break;
            case R.id.update_userinfo:
                startActivity(new Intent(UserInfoActivity.this,UpdateUserInfoActivity.class));
                break;
            case R.id.bangdingwx:
                break;
            case R.id.tuichudenglu:
                startActivity(new Intent(this, LoginActivity.class));
                new SharedPreferencesUtil(MyApp.context).clear();
                this.finish();
                break;
        }
    }

    @Override
    public void getUser(UserBean userBean) {
        UserBean.ResultBean result = userBean.getResult();
        String message = userBean.getMessage();
        if (message.equals("查询成功")){
            mUserinfoImg.setImageURI(Uri.parse(result.getHeadPic()));
            String s = TimeUtil.unixTimestamp2BeijingTime(result.getBirthday(), "yyyy/MM/dd");
            mUserinfoBirthday.setText(s);
            mUserinfoNickname.setText(result.getNickName());
            StringBuilder sb  =new StringBuilder();
            for (int i = 0; i < result.getPhone().length(); i++) {
                char c = result.getPhone().charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            mUserinfoPhone.setText(sb.toString());
            if (result.getSex() == 1){
                mUserinfoSex.setText("男");
            }else if (result.getSex() == 2){
                mUserinfoSex.setText("女");
            }
            String s1 = TimeUtil.unixTimestamp2BeijingTime(result.getLastLoginTime(), "yyyy/MM/dd  HH:mm:ss");
            mUserinfoEmail.setText(s1);
        }
    }
}
