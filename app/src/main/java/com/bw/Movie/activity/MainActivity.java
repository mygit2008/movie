package com.bw.Movie.activity;

import android.graphics.Color;

import com.bw.Movie.R;
import com.bw.Movie.fragment.Cinema;
import com.bw.Movie.fragment.Member;
import com.bw.Movie.fragment.Picture;
import com.bw.Movie.fragment.Setting;
import com.hjm.bottomtabbar.BottomTabBar;

import example.com.mvplibrary.BaseActivity;
import example.com.mvplibrary.mvp.BaseModel;
import example.com.mvplibrary.mvp.BasePresenter;


public class MainActivity extends BaseActivity {

    private BottomTabBar mTabBar;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    public void initView() {
        mTabBar = (BottomTabBar) findViewById(R.id.tabBar);
    }

    @Override
    protected void initData() {
        getSupportActionBar().hide();
        mTabBar.init(getSupportFragmentManager())
                .setImgSize(60, 60)
                .setFontSize(14)
                .setTabPadding(15, 0, 5)
                .setChangeColor(Color.BLACK, Color.GRAY)
                .addTabItem("影片", R.mipmap.down_film_img, Picture.class)
                .addTabItem("影院", R.mipmap.down_cinema_img, Cinema.class)
                .addTabItem("会员", R.mipmap.down_vip_img, Member.class)
                .addTabItem("设置", R.mipmap.down_setting_img, Setting.class)
                .setTabBarBackgroundResource(R.color.white)
                .isShowDivider(true);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected BaseModel initModel() {
        return null;
    }

}
