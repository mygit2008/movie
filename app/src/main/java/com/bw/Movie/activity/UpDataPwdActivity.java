package com.bw.Movie.activity;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.Movie.R;
import com.bw.Movie.bean.UpdataBean;
import com.bw.Movie.interview.UpDataPwdCallback;
import com.bw.Movie.model.UpDataPwdModer;
import com.bw.Movie.presenter.UpDataPwdPresenter;
import com.bw.Movie.utils.EncryptUtil;

import example.com.mvplibrary.BaseActivity;
import example.com.mvplibrary.mvp.BaseModel;

public class UpDataPwdActivity extends BaseActivity<UpDataPwdPresenter> implements UpDataPwdCallback.BaseUpDataPwdView, View.OnClickListener {

    private Button uploderbtn;
    private EditText setloginpasswors;
    private EditText setloginrelest;
    private EditText setlogincode;
    private ImageView return_icon;
    private TextView text_title;
    private Button btn_sub;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_up_data_pwd;
    }

    @Override
    public void initView() {
        super.initView();
        uploderbtn = findViewById(R.id.uploderbtn);
        setloginpasswors = findViewById(R.id.setloginpasswors);
        setloginrelest = findViewById(R.id.setloginrelest);
        setlogincode = findViewById(R.id.setlogincode);
        return_icon = findViewById(R.id.return_icon);
        text_title = findViewById(R.id.text_title);
        btn_sub = findViewById(R.id.btn_sub);
    }

    @Override
    protected void initData() {
        hideActionBar();
        uploderbtn.setOnClickListener(this);
        return_icon.setOnClickListener(this);
        text_title.setText("修改密码");

    }

    @Override
    protected UpDataPwdPresenter initPresenter() {
        return new UpDataPwdPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new UpDataPwdModer();
    }

    /**
     * 沉浸式模式
     */
    private void hideActionBar() {
        //沉浸式隐藏状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);//透明色
        }
        //隐藏标题栏
        getSupportActionBar().hide();
    }

    @Override
    public void Seecc(UpdataBean data) {
        Toast.makeText(this,data.getStatus()+""+data.getMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void feali(Throwable throwable) {
        Toast.makeText(this,throwable.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uploderbtn:
                String pwd= EncryptUtil.encrypt(setloginpasswors.getText().toString());
                String encrypt = EncryptUtil.encrypt(setloginrelest.getText().toString());
                String encrypt1 = EncryptUtil.encrypt(setlogincode.getText().toString());
                presenter.updatapwd(pwd,encrypt,encrypt1);
                break;
            case R.id.return_icon:
                finish();
                break;

        }
    }
}
