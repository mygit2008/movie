package com.bw.Movie.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bw.Movie.MyApp;
import com.bw.Movie.R;
import com.bw.Movie.bean.IndentBean;
import com.bw.Movie.bean.MovieListBean;
import com.bw.Movie.bean.NewMessageBean;
import com.bw.Movie.bean.PayResult;
import com.bw.Movie.bean.SchedulBean;
import com.bw.Movie.bean.WXzfBean;
import com.bw.Movie.common.Constants;
import com.bw.Movie.interview.IndentView;
import com.bw.Movie.model.IndentModel;
import com.bw.Movie.presenter.IndentPresenter;
import com.bw.Movie.widget.SeatTable;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import example.com.mvplibrary.BaseActivity;
import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.MD5Util;
import example.com.utilslibrary.utils.SharedPreferencesUtil;

public class ScheduleActivity extends BaseActivity<IndentPresenter> implements IndentView, View.OnClickListener {
    private String mid;
    private String cid;
    private String orderId;
    private IWXAPI iwxapi;
    public SeatTable seatTableView;
    private LinearLayout mPiaoMess;
    private int count = 0;
    /**
     * 支付
     */
    private Button pay_btn;
    private List<SchedulBean.ResultBean> result;
    private TextView cinema_name;
    private String name;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_schedulelist;
    }

    @Override
    public void initView() {
        super.initView();
        EventBus.getDefault().register(this);
        seatTableView = (SeatTable) findViewById(R.id.seatView);
        mPiaoMess = (LinearLayout) findViewById(R.id.piaoMess);
        pay_btn = (Button) findViewById(R.id.pay_btn);
        cinema_name = (TextView) findViewById(R.id.cinema_name);
        pay_btn.setOnClickListener(this);
    }

    @Subscribe(sticky = true)
    public void onEvent(NewMessageBean event) {
        cid = event.getCid();
        mid = event.getMid();
        name = event.getName();
    }

    @Override
    protected void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("movieId", mid);
        map.put("cinemasId", cid);
        cinema_name.setText(name);
        presenter.ScheduleMoviesPresener(map);
        presenter.getMovieListCinema(mid);
    }

    @Override
    protected IndentPresenter initPresenter() {
        return new IndentPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new IndentModel();
    }

    @Override
    public void success(IndentBean indentBean) {
        if (indentBean.getOrderId() != null) {
            orderId = indentBean.getOrderId();
            EventBus.getDefault().postSticky(orderId);
            startActivity(new Intent(ScheduleActivity.this, PayActivity.class));
            Toast.makeText(MyApp.context, indentBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void ScheduleSuccess(SchedulBean schedulBean) {
        if (schedulBean != null) {
            result = schedulBean.getResult();
        }
        gitZuoWei();
    }

    @Override
    public void ScheduleCinema(MovieListBean movieListBean) {
        Log.e("movieListBean", movieListBean.getResult().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.pay_btn:
                if (result != null && !(result.equals(""))) {
                    String userId = SharedPreferencesUtil.getPreference(MyApp.context, "user").getString("userId");
                    String str = userId + result.get(0).getId() + count + "movie";
                    String sign = MD5Util.encrypt(str);
                    presenter.getOrders(result.get(0).getId(), count, sign);
                }
                break;
        }
    }

    private void gitZuoWei() {
        seatTableView.setScreenName(result.get(0).getScreeningHall());//设置屏幕名称
        seatTableView.setMaxSelected(3);//设置最多选中
        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if (column == 2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if (row == 6 && column == 6) {
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {
                ++count;
                TextView tv = new TextView(ScheduleActivity.this);
                tv.setText(count + "人" + (row + 1) + "排" + (column + 1) + "座");
                tv.setTextColor(Color.parseColor("#ffffff"));
                mPiaoMess.addView(tv);
            }

            @Override
            public void unCheck(int row, int column) {
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        seatTableView.setData(10, 15);
    }

}
