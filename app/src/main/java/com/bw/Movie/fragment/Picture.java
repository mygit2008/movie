package com.bw.Movie.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bw.Movie.R;
import com.bw.Movie.activity.DetailsMoviesActivity;
import com.bw.Movie.adapter.MyMoviesContentAdapter;
import com.bw.Movie.bean.MessageEvent;
import com.bw.Movie.bean.WtBean;
import com.bw.Movie.interview.IWtView;
import com.bw.Movie.interview.OnItemClickListener;
import com.bw.Movie.model.WtModel;
import com.bw.Movie.presenter.WtPresenter;
import com.bw.Movie.widget.CustPagerTransformer;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import example.com.mvplibrary.BaseFragment;
import example.com.mvplibrary.mvp.BaseModel;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * @author zhangjunyou
 * @date 2018/8/2
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */
public class Picture extends BaseFragment<WtPresenter> implements IWtView, View.OnClickListener {
    private TextView picture_change_1, picture_change_2, picture_change_3;
    private View view;
    private ImageView huan, img;
    private RelativeLayout rl;
    private RecyclerView rlv;
    private ViewPager vp;
    private List<WtBean.ResultBean> result;
    private int page = 1;
    private int count = 10;
    private TextView hot_title, hot_content;
    private boolean falg = false;

    @Override
    protected void initView(View view) {
        picture_change_1 = view.findViewById(R.id.picture_change_1);
        picture_change_2 = view.findViewById(R.id.picture_change_2);
        picture_change_3 = view.findViewById(R.id.picture_change_3);
        huan = view.findViewById(R.id.lei_zhuanhuan);
        huan.setOnClickListener(this);
        hot_title = view.findViewById(R.id.hot_title);
        hot_content = view.findViewById(R.id.hot_content);
        img = view.findViewById(R.id.img);
        rl = view.findViewById(R.id.rl);
        rlv = view.findViewById(R.id.rlv);
        vp = view.findViewById(R.id.vp);
        picture_change_1.setOnClickListener(this);
        picture_change_2.setOnClickListener(this);
        picture_change_3.setOnClickListener(this);
    }

    @Override
    protected BaseModel initModel() {
        return new WtModel();
    }

    @Override
    protected WtPresenter initPresenter() {
        return new WtPresenter();
    }

    protected int getLayoutid() {
        return R.layout.picture_layout;
    }


    @Override
    protected void initData() {
        presenter.getWt(page, count);
    }

    @Override
    public void onSuccess(WtBean wtBean) {
        result = wtBean.getResult();
        //背景渐变
        Glide.with(getContext()).load(result.get(0).getImageUrl())
                .bitmapTransform(new BlurTransformation(getContext(), 20), new CenterCrop(getContext()))
                .into(img);
        hot_title.setText(result.get(0).getName());
        hot_content.setText(result.get(0).getSummary());
        vp.setPageTransformer(true, new CustPagerTransformer(getContext()));
        vp.setOffscreenPageLimit(5);
        vp.setAdapter(new MyAdapter());
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //背景渐变
                Glide.with(getContext()).load(result.get(position % result.size()).getImageUrl())
                        .bitmapTransform(new BlurTransformation(getContext(), 20),
                                new CenterCrop(getContext()))
                        .into(img);
                hot_title.setText(result.get(position % result.size()).getName());
                hot_content.setText(result.get(position % result.size()).getSummary());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rlv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        MyMoviesContentAdapter adapter = new MyMoviesContentAdapter(getContext(), result);

        rlv.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MessageEvent me = new MessageEvent(result.get(position).getId() + "");
                EventBus.getDefault().postSticky(me);
                startActivity(new Intent(getContext(), DetailsMoviesActivity.class));

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.picture_change_1:
                picture_change_1.setBackgroundResource(R.drawable.shape);
                picture_change_2.setBackgroundResource(R.drawable.shape4);
                picture_change_3.setBackgroundResource(R.drawable.shape4);
                presenter.getWt(page, count);
                break;
            case R.id.picture_change_2:
                picture_change_2.setBackgroundResource(R.drawable.shape);
                picture_change_1.setBackgroundResource(R.drawable.shape4);
                picture_change_3.setBackgroundResource(R.drawable.shape4);
                presenter.getWt1(page, count);
                break;
            case R.id.picture_change_3:
                picture_change_3.setBackgroundResource(R.drawable.shape);
                picture_change_2.setBackgroundResource(R.drawable.shape4);
                picture_change_1.setBackgroundResource(R.drawable.shape4);
                presenter.getWt2(page, count);
                break;
            case R.id.lei_zhuanhuan:
                if (falg) {
                    rl.setVisibility(View.VISIBLE);
                    rlv.setVisibility(View.GONE);
                    falg = false;
                } else {
                    rl.setVisibility(View.GONE);
                    rlv.setVisibility(View.VISIBLE);
                    falg = true;
                }

                break;
        }
    }

    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        // 当只有3张图片或者2张图片的时候，滑动存在BUG问题(滑动时有白屏)的修改如下
        // destroyItem(View container, int position, Object
        // object)方法中不removeView
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        /**
         * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
         */
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(getContext()).load(result.get(position % result.size()).getImageUrl()).into(imageView);
            container.addView(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MessageEvent me = new MessageEvent(result.get(position).getId() + "");
                    EventBus.getDefault().postSticky(me);
                    startActivity(new Intent(getContext(), DetailsMoviesActivity.class));
                }
            });
            return imageView;
        }
    }

}
