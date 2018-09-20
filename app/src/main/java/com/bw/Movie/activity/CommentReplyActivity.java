package com.bw.Movie.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.Movie.R;
import com.bw.Movie.adapter.MyCommentReplyAdapter;
import com.bw.Movie.bean.CommentFindBean;
import com.bw.Movie.bean.CommentReplyBean;
import com.bw.Movie.bean.NewMessageBean;
import com.bw.Movie.interview.ICommentFindView;
import com.bw.Movie.interview.ICommentReplyView;
import com.bw.Movie.model.MyCommentFindModel;
import com.bw.Movie.model.MyCommentReplyModel;
import com.bw.Movie.presenter.CommentFindPresenter;
import com.bw.Movie.presenter.CommentReplyPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import example.com.mvplibrary.BaseActivity;
import example.com.mvplibrary.mvp.BaseModel;

public class CommentReplyActivity extends BaseActivity<CommentFindPresenter> implements ICommentFindView,ICommentReplyView {

    private String cid,name,time,coment,pic;
    private Button huifu;
    private TextView reply_name,reply_time,reply_co;
    private ImageView reply_pic;
    private EditText reply_shuru;
    private RecyclerView reply_recy;
    private int page = 1;
    private int count=10;
    private MyCommentReplyAdapter adapter;
    private static String TAG = "CommentReplyActivity-----";

    @Override
    protected int getLayoutID() {
        return R.layout.activity_comment_reply;
    }

    @Override
    public void initView() {
        super.initView();
        EventBus.getDefault().register(this);
        reply_name = findViewById(R.id.comment_reply_name);
        reply_co = findViewById(R.id.comment_reply_co);
        reply_pic = findViewById(R.id.comment_reply_headpic);
        reply_time = findViewById(R.id.comment_reply_time);
        reply_recy = findViewById(R.id.comment_reply_recy);
        reply_shuru = findViewById(R.id.comment_reply_shuru);
        huifu = findViewById(R.id.comment_reply_huifu);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        reply_recy.setLayoutManager(linearLayout);
        reply_name.setText(name);
        Glide.with(this).load(pic).into(reply_pic);
        reply_time.setText(time);
        reply_co.setText(coment);
        huifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initss();
            }
        });
    }

    @Subscribe(sticky = true)
    public void onEvent(NewMessageBean event){
        cid = event.getCid();
        name = event.getName();
        time = event.getCommentTime();
        coment = event.getComent();
        pic = event.getPic();
    }

    private void initss() {
        CommentReplyPresenter presenter2 = new CommentReplyPresenter();
        presenter2.CommentReplyPresener(new MyCommentReplyModel(),this);
    }

    @Override
    protected void initData() {
        presenter.getAll(Integer.parseInt(cid),page,count);
    }

    @Override
    protected CommentFindPresenter initPresenter() {
        return new CommentFindPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new MyCommentFindModel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSuccess(CommentFindBean commentFindBean) {
        List<CommentFindBean.ResultBean> result = commentFindBean.getResult();
        if(result!=null&&!(result.equals(""))){
            adapter = new MyCommentReplyAdapter(this,result);
            reply_recy.setAdapter(adapter);
        }
    }

    @Override
    public String getcommentId() {
        return cid;
    }

    @Override
    public String getreplyContent() {
        return reply_shuru.getText().toString();
    }

    @Override
    public void onSuccess(CommentReplyBean commentReplyBean) {
        String status = commentReplyBean.getStatus();
        if(status.equals("0000")){
            Toast.makeText(this,commentReplyBean.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
        initData();
        reply_shuru.setText("");
        adapter.notifyDataSetChanged();
    }
}
