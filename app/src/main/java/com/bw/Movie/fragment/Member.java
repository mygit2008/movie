package com.bw.Movie.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.Movie.R;
import com.bw.Movie.activity.AttentionCinemasActivity;
import com.bw.Movie.activity.AttentionMovieActivity;
import com.bw.Movie.activity.FeedbackActivity;
import com.bw.Movie.activity.GouPiaoJiLuActivity;
import com.bw.Movie.activity.QianDaoActivity;
import com.bw.Movie.activity.UserInfoActivity;
import com.bw.Movie.bean.GouPiaoJiLuBean;
import com.bw.Movie.bean.UserInfoBean;
import com.bw.Movie.bean.UserSignInBean;
import com.bw.Movie.interview.UserSignInView;
import com.bw.Movie.model.UserSignInModel;
import com.bw.Movie.presenter.UserSignInPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import example.com.mvplibrary.BaseFragment;
import example.com.mvplibrary.mvp.BaseModel;

/**
 * @author zhangjunyou
 * @date 2018/8/2
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */
public class Member extends BaseFragment<UserSignInPresenter> implements View.OnClickListener, UserSignInView {
    private View view;
    private SimpleDraweeView mUserImg;
    private TextView mUserPhone;
    private TextView mUserNickName;
    private RelativeLayout mUserRelative;
    private LinearLayout mGoupiao;
    private RelativeLayout mAttentionMovie;
    private RelativeLayout mAttentionCinemas;
    private LinearLayout mQiandao;
    private RelativeLayout mHuiYuanZhongXin;
    private ImageView mSexImg;

    @Override
    protected void initView(View view) {
        mUserImg = (SimpleDraweeView) view.findViewById(R.id.user_img);
        mUserPhone = (TextView) view.findViewById(R.id.user_phone);
        mUserPhone.setOnClickListener(this);
        mUserNickName = (TextView) view.findViewById(R.id.user_nickname);
        mUserRelative = (RelativeLayout) view.findViewById(R.id.user_relative);
        mUserRelative.setOnClickListener(this);
        mGoupiao = (LinearLayout) view.findViewById(R.id.goupiao);
        mGoupiao.setOnClickListener(this);
        mHuiYuanZhongXin = (RelativeLayout) view.findViewById(R.id.huiyuanzhongxin);
        mHuiYuanZhongXin.setOnClickListener(this);
        mSexImg = (ImageView) view.findViewById(R.id.sex_img);
        mAttentionMovie = (RelativeLayout) view.findViewById(R.id.attention_movie);
        mAttentionMovie.setOnClickListener(this);
        mAttentionCinemas = (RelativeLayout) view.findViewById(R.id.attention_cinemas);
        mAttentionCinemas.setOnClickListener(this);
        mQiandao = view.findViewById(R.id.qiandao);
        mQiandao.setOnClickListener(this);
    }

    @Override
    protected BaseModel initModel() {
        return new UserSignInModel();
    }

    @Override
    protected UserSignInPresenter initPresenter() {
        return new UserSignInPresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_member;
    }

    @Override
    protected void initData() {
        presenter.getUserInfo();
        SharedPreferences user = getContext().getSharedPreferences("user", 0);
        if (user != null) {
            String nickname = user.getString("nickName", "请登录");
            String phone = user.getString("phone", "");
            String headPic = user.getString("headPic", "");
            int sex = user.getInt("sex", 1);
            if (sex == 1){
                mSexImg.setImageResource(R.mipmap.sex_man);
            }else if (sex == 2){
                mSexImg.setImageResource(R.mipmap.sex_women);
            }
            StringBuilder sb  =new StringBuilder();
            for (int i = 0; i < phone.length(); i++) {
                char c = phone.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            mUserPhone.setText(sb.toString());
            mUserNickName.setText(nickname);
            mUserImg.setImageURI(Uri.parse(headPic));
        } else {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_relative:
                break;
            case R.id.goupiao:
                startActivity(new Intent(getContext(),GouPiaoJiLuActivity.class));
                break;
            case R.id.qiandao:
                startActivity(new Intent(getContext(),QianDaoActivity.class));
                break;
            case R.id.huiyuanzhongxin:
                startActivity(new Intent(getContext(),UserInfoActivity.class));
                break;
            case R.id.attention_movie:
                startActivity(new Intent(getContext(),AttentionMovieActivity.class));
                break;
            case R.id.attention_cinemas:
                startActivity(new Intent(getContext(),AttentionCinemasActivity.class));
                break;

        }
    }

    @Override
    public void onSuccess(UserSignInBean userSignInBean) {
        /*String message = userSignInBean.getMessage();
        if (message == "签到成功" || message.equals("签到成功")) {
            new Handler(new Handler.Callback() {
                //处理接收到的消息的方法
                @Override
                public boolean handleMessage(Message arg0) {
                    mMemberQiandao.setImageResource(R.mipmap.qiandao_true);
                    return false;
                }
            }).sendEmptyMessageDelayed(0, 500); //表示延时三秒进行任务的执行
            float curTranslationX = mMemberQiandao.getTranslationX();
            ObjectAnimator translation = ObjectAnimator.ofFloat(mMemberQiandao, "translationX", curTranslationX, 150,curTranslationX);
            ObjectAnimator rotate = ObjectAnimator.ofFloat(mMemberQiandao, "rotation", 0f, 360f,0f);
            AnimatorSet animSet = new AnimatorSet();
            animSet.play(translation).with(rotate);
            animSet.setDuration(1000);
            animSet.start();
            Toast.makeText(getContext(), "签到完成", Toast.LENGTH_SHORT).show();
        } else {
            mMemberQiandao.setImageResource(R.mipmap.qiandao_true);
            Toast.makeText(getContext(), userSignInBean.getMessage() + "", Toast.LENGTH_SHORT).show();
        }*/


    }

    @Override
    public void onUserInfoSuccess(UserInfoBean userInfoBean) {

    }

}
