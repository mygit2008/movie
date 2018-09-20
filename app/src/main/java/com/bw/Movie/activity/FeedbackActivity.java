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
import com.bw.Movie.bean.FeedbackBean;
import com.bw.Movie.interview.FeedbackCallBack;
import com.bw.Movie.model.FeedbackModer;
import com.bw.Movie.presenter.FeedbackPresenter;

import example.com.mvplibrary.BaseActivity;

public class FeedbackActivity extends BaseActivity<FeedbackPresenter> implements View.OnClickListener,FeedbackCallBack.BaseFeedbackView {
    private ImageView return_icon;
    private TextView text_title;
    private Button btn_sub;
    private EditText text_context;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_feedback;
    }

    public void initView() {
        return_icon = findViewById(R.id.return_icon);
        text_title = findViewById(R.id.text_title);
        btn_sub = findViewById(R.id.btn_sub);
        text_context = findViewById(R.id.text_context);

    }

    public void initData() {
        hideActionBar();
        return_icon.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        text_title.setText("意见反馈");
    }

    @Override
    protected FeedbackPresenter initPresenter() {
        return new FeedbackPresenter();
    }

    @Override
    public FeedbackModer initModel() {
        return new FeedbackModer();
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.return_icon:
                finish();
                break;
            case R.id.btn_sub:
                presenter.upcontent(text_context.getText().toString());
                break;
        }
    }

    @Override
    public void Seecc(FeedbackBean data) {
        Toast.makeText(this,""+data.getStatus(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void feali(Throwable throwable) {
        Toast.makeText(this,""+throwable.toString(),Toast.LENGTH_LONG).show();
    }
}
