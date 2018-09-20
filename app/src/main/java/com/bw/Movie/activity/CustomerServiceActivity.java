package com.bw.Movie.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.Movie.R;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.Movie.adapter.MessageListAdapter;
import com.bw.Movie.bean.MassageBean;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceActivity extends AppCompatActivity implements View.OnClickListener, EMMessageListener {
    private ImageView return_icon;
    private TextView text_title;

    private EditText massge;
    private Button send;
    private EditText text;
    private EMMessage message;
    private RecyclerView custommassge;
    private ArrayList<MassageBean> list;
    private MessageListAdapter listAdapter;
    private int height;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service);
        initView();
        initData();
    }
    /**
     * 沉浸式模式
     */
    private void hideActionBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(getResources().getColor(R.color.statacolorvalue));//透明色

            //获取status_bar_height资源的ID

            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");

            if (resourceId > 0) {

                //根据资源ID获取响应的尺寸值

                height = getResources().getDimensionPixelSize(resourceId);
            }
        }
        linearLayout.setPadding(0,height,0,0);
        //隐藏标题栏
        getSupportActionBar().hide();
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new MassageBean("请问有什么可以帮助您的吗？",false));
        listAdapter = new MessageListAdapter(list,this);
        custommassge.setAdapter(listAdapter);
        hideActionBar();
        text_title.setText("客服聊天室");
    }

    private void initView() {
        return_icon = findViewById(R.id.return_icon);
        text_title = findViewById(R.id.text_title);
        massge = findViewById(R.id.input_message);
        send = findViewById(R.id.sendmassge);
        text = findViewById(R.id.userid);
        return_icon.setOnClickListener(this);
        custommassge = findViewById(R.id.custommassge);
        custommassge.setLayoutManager(new LinearLayoutManager(this));
        send.setOnClickListener(this);
        linearLayout = findViewById(R.id.customerserviceActivity_layout);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_icon:
                finish();
                break;
            case R.id.sendmassge:
                sendmassagemethod();
                break;
        }

    }

    private void sendmassagemethod() {
        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
        if (TextUtils.isEmpty(text.getText().toString())) {
            message = EMMessage.createTxtSendMessage(massge.getText().toString(), "llc");
        } else {
            message = EMMessage.createTxtSendMessage(massge.getText().toString(), text.getText().toString());
        }
        //如果是群聊，设置chattype，默认是单聊
        message.setChatType(EMMessage.ChatType.Chat);
        //发送消息
        EMClient.getInstance().chatManager().sendMessage(message);
        //消息监听
        message.setMessageStatusCallback(new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.e("tag", "成功");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.add(new MassageBean(massge.getText().toString(),true));
                        listAdapter.setList(list);
                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                Log.e("tag", "----发送失败异常" + s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }


    @Override
    public void onMessageReceived(final List<EMMessage> messages) {
        //收到消息
        for (final EMMessage msg : messages) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String valus = ((EMTextMessageBody) msg.getBody()).getMessage();
                    list.add(new MassageBean(valus,false));
                    listAdapter.setList(list);
                }
            });
        }
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {
        //收到透传消息
        Log.e("tag", "收到透传消息");

    }

    @Override
    public void onMessageRead(List<EMMessage> messages) {
        //收到已读回执
        Log.e("tag", "收到已读回执");
    }

    @Override
    public void onMessageDelivered(List<EMMessage> message) {
        //收到已送达回执
        Log.e("tag", "收到透传消息");
    }

    @Override
    public void onMessageRecalled(List<EMMessage> messages) {
        //消息被撤回
        Log.e("tag", "消息被撤回");
    }

    @Override
    public void onMessageChanged(EMMessage message, Object change) {
        //消息状态变动
        Log.e("tag", "消息状态变动");
    }

    @Override
    protected void onResume() {
        super.onResume();
        EMClient.getInstance().chatManager().addMessageListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(this);
    }
}