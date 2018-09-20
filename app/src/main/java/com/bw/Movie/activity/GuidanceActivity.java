package com.bw.Movie.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bw.Movie.MyApp;
import com.bw.Movie.R;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.GenericDraweeView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import example.com.utilslibrary.utils.DisplayMetricsUtil;

public class GuidanceActivity extends AppCompatActivity {
    private ViewPager guidance_view_page;
    private ArrayList<Integer> arrayList;
    private LinearLayout.LayoutParams layoutParams;
    private int n;
    private int width;
    private int height;
    private ImageView imag;
    private SharedPreferences user;
    private BitmapFactory.Options options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidance);
        initData();
        initView();
    }
    /**
     * 沉浸式模式che
     */
    private void hideActionBar() {
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
    private void initData() {
        hideActionBar();
        arrayList = new ArrayList<>();
        arrayList.add(R.mipmap.yd_img1);
        arrayList.add(R.mipmap.yd_img2);
        arrayList.add(R.mipmap.yd_img4);
        arrayList.add(R.mipmap.yd_img3);
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        guidance_view_page=findViewById(R.id.guidance_view_page);
        width = DisplayMetricsUtil.getScreenWidth(MyApp.context);
        height = DisplayMetricsUtil.getScreenHeight(MyApp.context);
        user = GuidanceActivity.this.getSharedPreferences("user", 0);
        if(user.getBoolean("GuidanceCheckout",false)){
            tointent();
        }
        options = new BitmapFactory.Options();
    }


    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        guidance_view_page.setAdapter(new PagerAdapter() {
            private ImageView imag;

            @Override
            public int getCount() {
                return arrayList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                imag = new ImageView(GuidanceActivity.this);
                imag.setImageResource(arrayList.get(position));
                imag.setScaleType(ImageView.ScaleType.FIT_XY);
                options.inJustDecodeBounds=true;
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), arrayList.get(position));
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                layoutParams.width=width;
                layoutParams.height=height;
                imag.setLayoutParams(layoutParams);
                container.addView(imag);
                return imag;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View)object);
            }
        });
        guidance_view_page.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                n=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        guidance_view_page.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    if(n==(arrayList.size()-1)){
                        if(!(event.getX()>width/2||event.getX()<width/3)){
                            double v1 = height / event.getY();
                            if(v1<1.24&&v1>1.15){
                                user.edit().putBoolean("GuidanceCheckout",true).commit();
                                tointent();
                            }
                        }
                    }
                }
                return false;
            }
        });
    }

    public void  tointent(){
        startActivity( new Intent(GuidanceActivity.this,LoginActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        user=null;
    }
}
