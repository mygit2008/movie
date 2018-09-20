package com.bw.Movie.activity;

//import android.content.Intent;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.bw.Movie.MyApp;
import com.bw.Movie.R;
import com.bw.Movie.bean.RegisterBean;
import com.bw.Movie.utils.EncryptUtil;
import com.bw.Movie.interview.RegisterView;
import com.bw.Movie.model.RegisterModel;
import com.bw.Movie.presenter.RegisterPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import example.com.mvplibrary.BaseActivity;
import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.VerifyUtils;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements View.OnClickListener, RegisterView {
    private static final String TAG = "RegisterActivity :";
    private EditText mRegUserName;
    private EditText mRegUserPhone;
    private EditText mRegUserPwd;
    private EditText mRegUserPwd2;
    private EditText mRegUserEmail;
    private TextView mRegisterUserBtn;
    private int sex;

    /**
     * 男
     */
    private RadioButton mRegisterSex1;
    /**
     * 女
     */
    private RadioButton mRegisterSex2;
    private SimpleDraweeView mCircleImageView;
    private String phone;
    private String pwd;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected RegisterPresenter initPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new RegisterModel();
    }

    @Override
    public void initView() {
        mCircleImageView = (SimpleDraweeView) findViewById(R.id.circleImageView);
        mRegUserName = (EditText) findViewById(R.id.reg_user_name);
        mRegUserPhone = (EditText) findViewById(R.id.reg_user_phone);
        mRegUserPwd = (EditText) findViewById(R.id.reg_user_pwd);
        mRegUserPwd2 = (EditText) findViewById(R.id.reg_user_pwd2);
        mRegisterSex1 = (RadioButton) findViewById(R.id.register_sex_1);
        mRegisterSex2 = (RadioButton) findViewById(R.id.register_sex_2);
        mRegUserEmail = (EditText) findViewById(R.id.reg_user_email);
        mRegisterUserBtn = (TextView) findViewById(R.id.register_user_btn);
        mRegisterUserBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.register_user_btn:
                initRegister();
                break;
        }
    }

    private void initRegister() {
        String nickName = mRegUserName.getText().toString();
        phone = mRegUserPhone.getText().toString();
        pwd = mRegUserPwd.getText().toString();
        String pwd2 = mRegUserPwd2.getText().toString();
        String email = mRegUserEmail.getText().toString();
        if (mRegisterSex1.isChecked()) {
            sex = 1;
            Log.e(TAG, "initRegister: 1"+mRegisterSex1.isChecked() );
        } else if (mRegisterSex2.isChecked()) {
            sex = 2;
            Log.e(TAG, "initRegister: 2"+mRegisterSex1.isChecked() );
        }
        if (nickName == null || nickName == "" || nickName.equals("")){
            Toast.makeText(MyApp.context,"请输入昵称",Toast.LENGTH_SHORT).show();
        }else {
            VerifyUtils.isMobile(MyApp.context, phone);
            if (VerifyUtils.isPassword(MyApp.context, pwd)){
                if (pwd == pwd2 || pwd.equals(pwd2)){
                    if(VerifyUtils.isEmail(MyApp.context,email)){
                        String encryptpwd = EncryptUtil.encrypt(pwd);
                        String encryptpwd2 = EncryptUtil.encrypt(pwd2);
                        presenter.getRegister(nickName, phone, encryptpwd, encryptpwd2, sex, email);
                    }
                }else {
                    Toast.makeText(MyApp.context,"请确认密码",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onSuccess(RegisterBean registerBean) {
        Log.e("TAG", "onSuccess: " + registerBean.getMessage() + "     " + registerBean.getStatus());
        String message = registerBean.getMessage();
        if (message == "注册成功" || message.equals("注册成功")) {
            Toast.makeText(this, "☺ 注册成功 ☺", Toast.LENGTH_SHORT).show();
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        EMClient.getInstance().createAccount(phone,pwd);
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        Log.e("tag","注册成功");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this,"注册",Toast.LENGTH_LONG).show();
                            }
                        });
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                        Log.e("tag","注册失败");
                    }
                }
            }.start();
        } else {
            Toast.makeText(this, "注册失败了o(╯□╰)o", Toast.LENGTH_SHORT).show();
        }
    }
}

