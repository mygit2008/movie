package com.bw.Movie.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.Movie.R;
import com.bw.Movie.adapter.MyCommentAdapter;
import com.bw.Movie.bean.CommentBean;
import com.bw.Movie.bean.CommentPingBean;
import com.bw.Movie.bean.MessageEvent;
import com.bw.Movie.interview.ICommentPingView;
import com.bw.Movie.interview.ICommentView;
import com.bw.Movie.model.CommentModel;
import com.bw.Movie.model.MyCommentMoviesModel;
import com.bw.Movie.presenter.CommentPingPresenter;
import com.bw.Movie.presenter.CommentPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import example.com.mvplibrary.BaseActivity;
import example.com.mvplibrary.mvp.BaseModel;

public class CommentActivity extends BaseActivity<CommentPresenter> implements ICommentView,ICommentPingView{

    private String mid;
    private int page=1;
    private int count=10;
    private RecyclerView comment_recy;
    private EditText shuru;
    private Button pinglun;
    private MyCommentAdapter adapter;
    private static String TAG = "CommentActivity------------";
    private String s;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_comment;
    }

    @Override
    public void initView() {
        super.initView();
        EventBus.getDefault().register(this);
        comment_recy = findViewById(R.id.comment_recy);//comment_recy
        shuru = findViewById(R.id.comment_shuru);
        pinglun = findViewById(R.id.comment_pinglun);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        comment_recy.setLayoutManager(linearLayout);
        pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initss();
            }
        });
    }

    private void initss() {
        CommentPingPresenter presenter2 = new CommentPingPresenter();
        presenter2.CommentMoviesPresener(new MyCommentMoviesModel(),this);
    }

    @Subscribe(sticky = true)
    public void onEvent(MessageEvent event){
        mid = event.getMid();
    }


    @Override
    protected void initData() {
        presenter.getAll(Integer.parseInt(mid),page,count);
    }

    @Override
    protected CommentPresenter initPresenter() {
        return new CommentPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new CommentModel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSuccess(CommentBean commentBean) {
        List<CommentBean.ResultBean> result = commentBean.getResult();
        if(result!=null&&!(result.equals(""))){
            adapter = new MyCommentAdapter(this,result);
            comment_recy.setAdapter(adapter);
        }
    }

    @Override
    public String getCommentContent() {
         s = shuru.getText().toString();
        return s;
    }

    @Override
    public String getMovield() {
        return mid;
    }

    @Override
    public void onSuccess(CommentPingBean commentPingBean) {
        String status = commentPingBean.getStatus();
        if(status.equals("0000")){
            Toast.makeText(this,commentPingBean.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        initData();
        shuru.setText("");
        adapter.notifyDataSetChanged();
    }
}
