package com.bw.Movie.fragment;

import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bw.Movie.MyApp;
import com.bw.Movie.R;
import com.bw.Movie.activity.CustomerServiceActivity;
import com.bw.Movie.activity.FeedbackActivity;
import com.bw.Movie.activity.LoginActivity;
import com.bw.Movie.activity.MessageActivity;
import com.bw.Movie.activity.UpDataPwdActivity;
import com.bw.Movie.model.SettingModer;
import com.bw.Movie.presenter.SettingPresenter;

import example.com.mvplibrary.BaseFragment;
import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.CleanMessageUtil;
import example.com.utilslibrary.utils.SharedPreferencesUtil;

/**
 * @author zhangjunyou
 * @date 2018/8/2
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */
public class Setting extends BaseFragment<SettingPresenter> implements View.OnClickListener {

    private RelativeLayout iconmessage;
    private Intent intentmessage1;
    private RelativeLayout iconpwd;
    private RelativeLayout iconfeedback;
    private RelativeLayout iconsetphone;
    private ImageView return_icon;
    private TextView iconiamge;
    private Button btn1, cancel;
    private AlertDialog alertDialog;
    private AlertDialog.Builder ibuilder;
    private TextView chace;
    private RelativeLayout setchache;
    private Button unenlist;
    private CheckBox che;
    private SharedPreferences user;

    @Override
    protected void initView(View view) {
        iconmessage = view.findViewById(R.id.setmessage);
        iconpwd = view.findViewById(R.id.setpwd);
        iconfeedback = view.findViewById(R.id.setfeedback);
        iconsetphone = view.findViewById(R.id.setphone);
        iconiamge = view.findViewById(R.id.text_title);
        return_icon = view.findViewById(R.id.return_icon);
        chace = view.findViewById(R.id.chace);
        setchache = view.findViewById(R.id.setchache);
        che = view.findViewById(R.id.che);
        unenlist = view.findViewById(R.id.unenlist);
    }

    @Override
    protected BaseModel initModel() {
        return new SettingModer();
    }

    @Override
    protected SettingPresenter initPresenter() {
        return new SettingPresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.setting_fragment_layout;
    }

    @Override
    protected void initData() {
        iconiamge.setText("个人信息");
        user = getActivity().getSharedPreferences("user", 0);
        String totalCacheSize = CleanMessageUtil.getTotalCacheSize(MyApp.context);
        chace.setText("" + totalCacheSize);
        return_icon.setVisibility(View.GONE);
        iconmessage.setOnClickListener(this);
        iconpwd.setOnClickListener(this);
        iconfeedback.setOnClickListener(this);
        iconsetphone.setOnClickListener(this);
        chace.setOnClickListener(this);
        setchache.setOnClickListener(this);
        unenlist.setOnClickListener(this);
        boolean checkout = user.getBoolean("checkout", false);
        che.setChecked(checkout);
        String userId = user.getString("userId", "");
        String sessionId = user.getString("sessionId", "");
        Log.e("tag",userId+"----------"+sessionId);
        che.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setmessage:
                intentmessage1 = new Intent(getContext(), MessageActivity.class);
                startActivityForResult(intentmessage1, 10);
                break;
            case R.id.setpwd:
                startActivityForResult(new Intent(getContext(), UpDataPwdActivity.class), 20);
                break;
            case R.id.setfeedback:
                startActivityForResult(new Intent(getContext(), FeedbackActivity.class), 30);
                break;
            case R.id.setphone:
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.service_tel, new LinearLayout(getContext()), true);
                btn1 = inflate.findViewById(R.id.svbtn_phone);
                cancel = inflate.findViewById(R.id.svbtn_cancel);
                alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setView(inflate);
                alertDialog.setTitle("进入客服");
                alertDialog.show();
                btn1.setOnClickListener(this);
                cancel.setOnClickListener(this);
                break;
            case R.id.svbtn_phone:
                if (alertDialog != null) {
                    startActivity(new Intent(getContext(), CustomerServiceActivity.class));
                }
                break;
            case R.id.svbtn_cancel:
                if (alertDialog != null) {
                    alertDialog.dismiss();
                    alertDialog = null;
                }
                break;
            case R.id.setchache:
                show();
                break;
            case R.id.unenlist:
                startActivity(new Intent(getContext(), LoginActivity.class));
                new SharedPreferencesUtil(MyApp.context).clear();
                getActivity().finish();
                break;
            case R.id.che:
                user.edit().putBoolean("checkout", che.isChecked()).commit();
                break;
        }

    }

    private void show() {
        ibuilder = new AlertDialog.Builder(getContext());
        ibuilder.setTitle("清除缓存");
        ibuilder.setMessage("清除缓存会导致下载的内容删除，是否确定?");
        ibuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ibuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CleanMessageUtil.clearAllCache(getContext().getApplicationContext());
                chace.setText(CleanMessageUtil.getTotalCacheSize(MyApp.context));
                dialog.dismiss();
            }
        }).create().show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iconmessage = null;
        if(presenter!=null){
            presenter.detach();
        }

    }
}
