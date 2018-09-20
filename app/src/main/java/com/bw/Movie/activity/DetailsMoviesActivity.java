package com.bw.Movie.activity;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.Movie.MyApp;
import com.bw.Movie.R;
import com.bw.Movie.bean.ByIdMoviesBean;
import com.bw.Movie.bean.GuanZhuBean;
import com.bw.Movie.bean.MessageEvent;
import com.bw.Movie.interview.IByIdMoviesView;
import com.bw.Movie.model.MyByIdMoviesModel;
import com.bw.Movie.presenter.ByIdMoviesPresenter;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import example.com.mvplibrary.BaseActivity;
import example.com.mvplibrary.mvp.BaseModel;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;


public class DetailsMoviesActivity extends BaseActivity<ByIdMoviesPresenter> implements View.OnClickListener, IByIdMoviesView {

    private TextView tv;
    private String mid;
    private Button paiqi;
    private TextView details_dianping;
    private TextView title, content, type, origin, time;
    private ImageView picture;
    private Button movies_byid_guan, movies_byid_fen;
    private ByIdMoviesBean.ResultBean result;
    private TextView starring, director;
    private LinearLayout haibao;
    private JCVideoPlayer playerView;

    @Override
    protected int getLayoutID() {

        return R.layout.activity_details_movies;
    }

    @Override
    protected void initData() {
        presenter.ByIdMoviesPresener(Integer.parseInt(mid));
    }

    @Override
    protected ByIdMoviesPresenter initPresenter() {
        return new ByIdMoviesPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new MyByIdMoviesModel();
    }

    @Override
    public void initView() {
        super.initView();
        EventBus.getDefault().register(this);
        paiqi = findViewById(R.id.details_paiqi);
        paiqi.setOnClickListener(this);
        title = findViewById(R.id.movies_byid_title);
        content = findViewById(R.id.movies_byid_content);
        type = findViewById(R.id.movies_byid_type);
        origin = findViewById(R.id.movies_byid_origin);
        time = findViewById(R.id.movies_byid_time);
        starring = findViewById(R.id.starring);
        director = findViewById(R.id.director);
        picture = findViewById(R.id.movies_byid_picture);
        movies_byid_guan = findViewById(R.id.movies_byid_guan);
        movies_byid_guan.setOnClickListener(this);
        movies_byid_fen = findViewById(R.id.movies_byid_fen);
        movies_byid_fen.setOnClickListener(this);
        details_dianping = findViewById(R.id.details_dianping);
        haibao = findViewById(R.id.haibao);
        playerView = findViewById(R.id.playerView);
        details_dianping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageEvent me = new MessageEvent(mid);
                EventBus.getDefault().postSticky(me);
                startActivity(new Intent(DetailsMoviesActivity.this, CommentActivity.class));
            }
        });
    }

    //分享的监听
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(DetailsMoviesActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(DetailsMoviesActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(DetailsMoviesActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Subscribe(sticky = true)
    public void onEvent(MessageEvent event) {
        mid = event.getMid();
    }

    @Override
    public void ByIdMoviesSuccess(ByIdMoviesBean bean) {
        result = bean.getResult();
        Glide.with(MyApp.context).load(result.getImageUrl()).into(picture);
        title.setText(result.getName());
        content.setText(result.getSummary());
        director.setText(result.getDirector());
        starring.setText(result.getStarring());
        boolean followMovie = result.isFollowMovie();
        if (followMovie) {
            movies_byid_guan.setText("关注");
        } else {
            movies_byid_guan.setText("已关注");
        }

        origin.setText("国家:" + result.getPlaceOrigin());
        String replace = result.getDuration().replace("分钟", "");
        Integer in = Integer.parseInt(replace);
        if (in > 60) {
            int s = in / 60;
            int ss = in % 60;
            time.setText("片长:" + s + "小时" + ss + "分钟");
        } else {
            time.setText("片长:" + in + "分钟");
        }
        type.setText("类型:" + result.getMovieTypes());
        for (int i = 1; i < result.getPosterList().size(); i++) {
            ImageView imageView = new ImageView(MyApp.context);
            imageView.setPadding(10, 0, 5, 0);
            Glide.with(MyApp.context).load(result.getPosterList().get(i)).into(imageView);
            haibao.addView(imageView);
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.height = 200;
            params.width = 300;
            imageView.setLayoutParams(params);
        }
        //调用播放器
        playerView.setUp(result.getShortFilmList().get(0).getVideoUrl(), "视频");
        //封面
        playerView.ivThumb.setImageURI(Uri.parse(result.getShortFilmList().get(0).getImageUrl()));
    }

    @Override
    public void HotMoviesSuccess(GuanZhuBean guanZhuBean) {
        if (guanZhuBean.getMessage().equals("关注成功")) {
            movies_byid_guan.setText("已关注");
        } else if (guanZhuBean.getMessage().equals("取消关注成功")) {
            movies_byid_guan.setText("关注");
        }
    }

    @Override
    public void serverFail(String msg) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;
            case R.id.movies_byid_guan:
                String s = movies_byid_guan.getText().toString();
                if (s.equals("关注")) {
                    presenter.getGuan(new MyByIdMoviesModel(), result.getId());
                } else {
                    presenter.getQu(new MyByIdMoviesModel(), result.getId());
                }
                break;
            case R.id.details_paiqi:
                MessageEvent me = new MessageEvent(mid);
                EventBus.getDefault().postSticky(me);
                startActivity(new Intent(DetailsMoviesActivity.this, CinemasListActivity.class));
                break;
            case R.id.movies_byid_fen:
                UMImage image = new UMImage(DetailsMoviesActivity.this, result.getImageUrl());//网络图片
                new ShareAction(DetailsMoviesActivity.this).withMedia(image).withText(result.getName())
                        .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA
                        ).setCallback(shareListener).open();
                break;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
