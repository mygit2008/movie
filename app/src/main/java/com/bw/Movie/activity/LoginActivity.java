package com.bw.Movie.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.Movie.MyApp;
import com.bw.Movie.R;
import com.bw.Movie.bean.LoginBean;
import com.bw.Movie.common.Constants;
import com.bw.Movie.interview.LoginView;
import com.bw.Movie.model.LoginModel;
import com.bw.Movie.presenter.LoginPresenter;
import com.bw.Movie.utils.EncryptUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import example.com.mvplibrary.BaseActivity;
import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.VerifyUtils;

public class LoginActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener,LoginView {
    private SimpleDraweeView mCircleImageView;
    /**
     * Username
     */
    private EditText mLoginUserAccount;
    /**
     * Password
     */
    private EditText mLoginUserPassword;
    private TextView mLoginUserBtn;
    /**
     * 去注册
     */
    private TextView mGoRegister;
    private String login_account;
    private String encrypt_pwd;
    private View mLogin_user_btn;
    private String login_pwd;
    private SharedPreferences user;
    private SharedPreferences.Editor edit;
    private ImageView pwd_eye;
    private boolean eye_b = true;
    private ImageView wechat_login;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        SharedPreferences user = this.getSharedPreferences("user", 0);
        if(user.getBoolean("checkout",false)){
            EMClient.getInstance().login( user.getString("phone","hehe"),"hehe", new EMCallBack() {
                @Override
                public void onSuccess() {
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }

                @Override
                public void onError(int i, String s) {
                    Log.e("tag","登录失败"+s);
                }

                @Override
                public void onProgress(int i, String s) {
                    Log.e("tag","登录进度"+i);
                }
            });
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        //创建微信api并注册到微信
        Constants.wx_api = WXAPIFactory.createWXAPI(MyApp.context, Constants.APP_ID, true);
        Constants.wx_api.registerApp(Constants.APP_ID);
        edit = user.edit();
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new LoginModel();
    }

    @Override
    public void initView() {
        mCircleImageView = (SimpleDraweeView) findViewById(R.id.circleImageView);
        mLoginUserAccount = (EditText) findViewById(R.id.login_user_account);
        mLoginUserPassword = (EditText) findViewById(R.id.login_user_password);
        mLoginUserBtn = (TextView) findViewById(R.id.login_user_btn);
        mLoginUserBtn.setOnClickListener(this);
        mGoRegister = (TextView) findViewById(R.id.go_register);
        mGoRegister.setOnClickListener(this);
        pwd_eye = (ImageView) findViewById(R.id.pwd_eye);
        pwd_eye.setOnClickListener(this);
        wechat_login = findViewById(R.id.wechat_login);
        wechat_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.wechat_login:
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                Constants.wx_api.sendReq(req);
                break;
            case R.id.login_user_btn:
                initLogin();
                break;
            case R.id.go_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.pwd_eye:
                if (eye_b == true){
                    mLoginUserPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pwd_eye.setImageResource(R.mipmap.eye_open);
                    eye_b = false;
                }else {
                    mLoginUserPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pwd_eye.setImageResource(R.mipmap.eye_close);
                    eye_b = true;
                }
                mLoginUserPassword.postInvalidate();
                break;
        }
    }

    private void initLogin() {
        String account = mLoginUserAccount.getText().toString();
        String pass = mLoginUserPassword.getText().toString();
        VerifyUtils.isMobile(MyApp.context,account);
        VerifyUtils.isPassword(MyApp.context,pass);
        String pwd = EncryptUtil.encrypt(pass);
        if(VerifyUtils.isMobile(MyApp.context,account) && VerifyUtils.isPassword(MyApp.context,pass)) {
            presenter.getLogin(account,pwd);
        }


        EMClient.getInstance().login( account,pass, new EMCallBack() {
            @Override
            public void onSuccess() {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }

            @Override
            public void onError(int i, String s) {
                Log.e("tag","登录失败"+s);
            }

            @Override
            public void onProgress(int i, String s) {
                Log.e("tag","登录进度"+i);
            }
        });
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        String message = loginBean.getMessage();
        if (message.equals("登陆成功") || message == "登陆成功"){
            edit.putString("nickName",loginBean.getResult().getUserInfo().getNickName());
            edit.putString("sessionId",loginBean.getResult().getSessionId());
            edit.putString("userId",loginBean.getResult().getUserId()+"");
            edit.putString("phone",loginBean.getResult().getUserInfo().getPhone());
            edit.putString("lastLoginTime", loginBean.getResult().getUserInfo().getLastLoginTime()+"");
            edit.putString("birthday",loginBean.getResult().getUserInfo().getBirthday()+"");
            edit.putInt("sex",loginBean.getResult().getUserInfo().getSex());
            edit.putString("headPic",loginBean.getResult().getUserInfo().getHeadPic());
            edit.putInt("qiandao",1);
            edit.commit();
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else {
            Toast.makeText(MyApp.context,loginBean.getMessage()+"",Toast.LENGTH_SHORT).show();
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void aaa(){

    }
}
