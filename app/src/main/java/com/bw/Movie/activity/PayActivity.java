package com.bw.Movie.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bw.Movie.R;
import com.bw.Movie.bean.PayResult;
import com.bw.Movie.bean.WXzfBean;
import com.bw.Movie.common.Constants;
import com.bw.Movie.interview.IPayView;
import com.bw.Movie.model.PayModel;
import com.bw.Movie.presenter.PayPresenter;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import example.com.mvplibrary.BaseActivity;
import example.com.mvplibrary.mvp.BaseModel;

public class PayActivity extends BaseActivity<PayPresenter> implements View.OnClickListener, IPayView {

    /**
     * 微信支付
     */
    private RadioButton mWxzhifu;
    /**
     * 支付宝支付
     */
    private RadioButton mZfbzhifu;
    /**
     * 确认支付
     */
    private Button mConfirmPay;
    private IWXAPI iwxapi;
    private int type;
    /**
     * 订单号
     */
    private String orderId;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_pay;
    }

    public void initView() {
        mWxzhifu = (RadioButton) findViewById(R.id.wxzhifu);
        mWxzhifu.setOnClickListener(this);
        mZfbzhifu = (RadioButton) findViewById(R.id.zfbzhifu);
        mZfbzhifu.setOnClickListener(this);
        mConfirmPay = (Button) findViewById(R.id.confirmPay);
        mConfirmPay.setOnClickListener(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        iwxapi = WXAPIFactory.createWXAPI(this, Constants.appId, true);
        iwxapi.registerApp(Constants.appId);
    }

    @Override
    protected PayPresenter initPresenter() {
        return new PayPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new PayModel();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.wxzhifu:
                type = 1;
                break;
            case R.id.zfbzhifu:
                type = 2;
                break;
            case R.id.confirmPay:
                if (type == 1) {
                    presenter.getWXPay(1, orderId);
                }
                if (type == 2) {
                    presenter.getZFBPay(2, orderId);
                }
                break;
        }
    }

    @Subscribe(sticky = true)
    public void onEvent(String event) {
        orderId = event;
    }

    @Override
    public void wxSuccess(final WXzfBean WXzfBean) {
        Runnable payRunnable1 = new Runnable() {  //这里注意要放在子线程
            @Override
            public void run() {
                PayReq request = new PayReq(); //调起微信APP的对象
                //下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
                request.appId = WXzfBean.getAppId();
                request.partnerId = WXzfBean.getPartnerId();
                request.prepayId = WXzfBean.getPrepayId();
                request.packageValue = "Sign=WXPay";
                request.nonceStr = WXzfBean.getNonceStr();
                request.timeStamp = WXzfBean.getTimeStamp();
                request.sign = WXzfBean.getSign();
                iwxapi.sendReq(request);//发送调起微信的请求
            }
        };
        Thread payThread = new Thread(payRunnable1);
        payThread.start();
    }

    @Override
    public void zfbSuccess(final String string) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                String result = alipay.pay(string, true);
                Message msg = new Message();
                msg.what = 0;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //使用返回的支付结果字符串构建一个支付结果对象
                    PayResult payResult = new PayResult((String) msg.obj);
                    //同步返回需要验证的信息
                    String resultInfo = payResult.getResult();
                    //----------app端拿到支付宝同步返回的结果,需要发送给服务器端,
                    // 服务器端经过验证签名和解析支付结果,然后给app端返回最终支付的结果
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
