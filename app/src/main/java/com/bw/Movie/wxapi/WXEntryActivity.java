package com.bw.Movie.wxapi;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bw.Movie.common.Constants;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

public class WXEntryActivity extends WXCallbackActivity implements IWXAPIEventHandler {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Constants.wx_api.handleIntent(getIntent(), this);
    }

    //微信请求相应
    @Override
    public void onReq(BaseReq baseReq) {

    }

    //发送到微信请求的响应结果
    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                Log.e("WXTest","onResp OK");

                if(resp instanceof SendAuth.Resp){
                    SendAuth.Resp newResp = (SendAuth.Resp) resp;
                    //获取微信传回的code
                    String code = newResp.code;
                    Log.i("WXTest","onResp code = "+code);
                }

                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Toast.makeText(this,"onResp ERR_USER_CANCEL",Toast.LENGTH_SHORT).show();
                Log.e("WXTest","onResp ERR_USER_CANCEL ");
                //发送取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                Toast.makeText(this,"onResp ERR_AUTH_DENIED",Toast.LENGTH_SHORT).show();
                Log.e("WXTest","onResp ERR_AUTH_DENIED");
                //发送被拒绝
                break;
            default:
                Toast.makeText(this,"onResp default errCode " + resp.errCode,Toast.LENGTH_SHORT).show();
                Log.e("WXTest","onResp default errCode " + resp.errCode);
                //发送返回
                break;
        }
        finish();
    }
}
