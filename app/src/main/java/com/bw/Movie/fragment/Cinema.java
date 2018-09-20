package com.bw.Movie.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.Movie.R;
import com.bw.Movie.activity.AdressActivity;
import com.bw.Movie.activity.MyAdressActivity;
import com.bw.Movie.presenter.CinemaPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import example.com.mvplibrary.BaseFragment;
import example.com.mvplibrary.mvp.BaseModel;

/**
 * @author zhangjunyou
 * @date 2018/8/2
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */
public class Cinema extends BaseFragment {
    private TextView tuijian;
    private TextView shangying;
    private FrameLayout frag;
    private FragmentManager fragmentManager;
    private TextView address, tv_title;
    private LinearLayout ll;
    private ImageView dw_img;
    private int i;

    @Override
    protected void initView(View view) {
        EventBus.getDefault().register(this);
        fragmentManager = getFragmentManager();
        tuijian = view.findViewById(R.id.tuijian);
        address = view.findViewById(R.id.address);
        frag = view.findViewById(R.id.frag);
        shangying = view.findViewById(R.id.shangying);
        tv_title = view.findViewById(R.id.tv_title);
        dw_img = view.findViewById(R.id.dw_img);
        ll = view.findViewById(R.id.ll);
        fragmentManager.beginTransaction().replace(R.id.frag, new TuijianFragment()).commit();
        tuijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 1;
                tuijian.setBackgroundResource(R.drawable.shape);
                shangying.setBackgroundResource(R.drawable.shape4);
                fragmentManager.beginTransaction().replace(R.id.frag, new TuijianFragment()).commit();
            }
        });
        shangying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 2;
                tuijian.setBackgroundResource(R.drawable.shape4);
                shangying.setBackgroundResource(R.drawable.shape);
                fragmentManager.beginTransaction().replace(R.id.frag, new ShangyingFragment()).commit();
            }
        });
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MyAdressActivity.class));
            }
        });
        dw_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AdressActivity.class));
            }
        });
    }

    @Subscribe
    public void onMessageEvent(String str) {
        address.setText(str);
        tv_title.setVisibility(View.GONE);
        ll.setVisibility(View.VISIBLE);
        dw_img.setVisibility(View.VISIBLE);
        if (i == 1) {
            fragmentManager.beginTransaction().replace(R.id.frag, new TuijianFragment()).commit();
        } else {
            fragmentManager.beginTransaction().replace(R.id.frag, new ShangyingFragment()).commit();
        }
    }


    @Override
    protected BaseModel initModel() {
        return null;
    }

    @Override
    protected CinemaPresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutid() {
        return R.layout.cinema;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}