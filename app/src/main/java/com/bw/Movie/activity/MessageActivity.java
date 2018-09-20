package com.bw.Movie.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.bw.Movie.MyApp;
import com.bw.Movie.R;
import com.bw.Movie.bean.MessageBean;
import com.bw.Movie.bean.UpdataBean;
import com.bw.Movie.interview.MessageCallBack;
import com.bw.Movie.model.MessageModer;
import com.bw.Movie.presenter.MessagePresenter;
import com.bw.Movie.utils.ImageUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import example.com.mvplibrary.BaseActivity;
import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.DisplayMetricsUtil;
import io.reactivex.annotations.NonNull;

public class MessageActivity extends BaseActivity<MessagePresenter> implements View.OnClickListener, MessageCallBack.BaseRegisterView {

    private Button timerbtn;
    private ImageView return_icon;
    private TextView iconiamge;
    private Date date;
    private int t = 0;
    private AlertDialog dialog;
    private ArrayList<String> arrayList;
    private ArrayList<String> arrayList2;
    private ArrayList<String> arrayList3;
    private ArrayList<String> arrayList4;
    private ArrayList<String> arrayList5;
    private HashMap<String, ArrayList<String>> map;
    private ListView mListView;
    private ListView mListView2;
    private ListView mListView3;
    private int n;
    private int y;
    private int r;
    private Button queding;
    private Button quxiao;
    private String format;
    private Button switchover;
    private RadioGroup group;
    private EditText setloginpwd;
    private int sex = 1;
    private ImageView uploderimage;
    private AlertDialog dialog2;
    private Button btnm1;
    private Button btnm2;
    private Button btnm3;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private String imagePath;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_message;
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

    public void initData() {
        hideActionBar();
        date = new Date();
        arrayList = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();
        arrayList4 = new ArrayList<>();
        arrayList5 = new ArrayList<>();
        map = new HashMap<>();
        t = (int) date.getTime();
        iconiamge.setText("个人信息");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        format = simpleDateFormat.format(date);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.sex1) {
                    sex = 1;
                } else {
                    sex = 2;
                }
            }
        });
        setdata();
    }

    @Override
    protected MessagePresenter initPresenter() {
        return new MessagePresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new MessageModer();
    }


    private void setdata() {
        for (int i = 1930; i < 2100; i++) {
            arrayList.add("" + i);
        }
        for (int i = 1; i <= 31; i++) {
            if (i < 10) {
                arrayList2.add("0" + i);
            } else {
                arrayList2.add("" + i);
            }
        }
        map.put("" + 1, arrayList2);
        map.put("" + 3, arrayList2);
        map.put("" + 5, arrayList2);
        map.put("" + 7, arrayList2);
        map.put("" + 8, arrayList2);
        map.put("" + 10, arrayList2);
        map.put("" + 12, arrayList2);
        arrayList3.addAll(arrayList2);
        arrayList3.remove(30);
        map.put("" + 4, arrayList3);
        map.put("" + 6, arrayList3);
        map.put("" + 9, arrayList3);
        map.put("" + 11, arrayList3);
        int year = date.getYear();
        boolean b = year / 4 * 10 % 10 == 0;
        arrayList4.addAll(arrayList3);
        if (b) {
            arrayList4.remove(29);
            arrayList4.remove(28);
            map.put("" + 2, arrayList4);
        } else {
            arrayList4.remove(29);
            map.put("" + 2, arrayList4);
        }
        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                arrayList5.add("0" + i);
            } else {
                arrayList5.add("" + i);
            }
        }
    }

    public void initView() {
        iconiamge = findViewById(R.id.text_title);
        return_icon = findViewById(R.id.return_icon);
        timerbtn = findViewById(R.id.timerbtn);
        switchover = findViewById(R.id.switchover);
        setloginpwd = findViewById(R.id.setloginpwd);
        group = findViewById(R.id.group);
        uploderimage = findViewById(R.id.uploderimage);
        uploderimage.setOnClickListener(this);
        switchover.setOnClickListener(this);
        timerbtn.setOnClickListener(this);
        return_icon.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.timerbtn:
                View inflate = LayoutInflater.from(this).inflate(R.layout.data_timer_layout, new LinearLayout(this), true);
                mListView = inflate.findViewById(R.id.list1);
                mListView2 = inflate.findViewById(R.id.list2);
                mListView3 = inflate.findViewById(R.id.list3);
                queding = inflate.findViewById(R.id.queding);
                quxiao = inflate.findViewById(R.id.quxiao);
                queding.setOnClickListener(this);
                quxiao.setOnClickListener(this);
                mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList));
                mListView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        r = Integer.parseInt(arrayList.get(position));
                    }
                });
                mListView2 .setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList5));
                int month = date.getMonth();
                mListView3.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, map.get("" + month)));

                mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mListView3.setAdapter(new ArrayAdapter<String>(MessageActivity.this, android.R.layout.simple_list_item_1, map.get("" + (position + 1))));
                        y = position + 1;
                    }
                });
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int i = Integer.parseInt(arrayList.get(position));
                        y = i;
                        boolean b = i / 4 * 10 % 10 == 0;
                        boolean b2 = !(i / 100 * 10 % 10 == 0);
                        boolean b3 = i / 400 * 10 % 10 == 0;
                        int size = arrayList4.size();
                        if ((b & b2) || (!b2 & b3)) {
                            if (size == 28) {
                            } else if (size == 29) {
                                arrayList4.remove(28);
                                if (y == 2) {
                                    mListView3.setAdapter(new ArrayAdapter<String>(MessageActivity.this, android.R.layout.simple_list_item_1, map.get("" + (position + 1))));
                                }
                            } else if (size == 27) {
                                arrayList4.add("" + 28);
                                if (y == 2) {
                                    mListView3.setAdapter(new ArrayAdapter<String>(MessageActivity.this, android.R.layout.simple_list_item_1, map.get("" + (position + 1))));
                                }
                            }
                        }
                    }
                });
                String[] split = format.split("-");
                int i1 = Integer.parseInt(split[0]);//年
                int i2 = Integer.parseInt(split[1]);//月
                int i3 = Integer.parseInt(split[2]);//日
                int i4 = arrayList.indexOf("" + i1);
                int i5 = 0;
                int i6 = 0;
                if (i2 < 9) {
                    i5 = arrayList2.indexOf("0" + (i2));
                    if (i3 < 9) {
                        i6 = map.get("0" + i2).indexOf("0" + (i3));
                    } else {
                        i6 = map.get("" + i2).indexOf("" + (i3));
                    }
                } else {
                    i5 = arrayList2.indexOf("" + i2);
                    if (i3 < 9) {
                        i6 = map.get("" + i2).indexOf("0" + (i3));
                    } else {
                        i6 = map.get("" + i2).indexOf("" + (i3));
                    }
                }
                mListView.setSelection(i4);
                mListView2.setSelection(i5);
                mListView3.setSelection(i3 - 1);
                int i = DisplayMetricsUtil.getScreenWidth(MyApp.context);
                dialog = new AlertDialog.Builder(this).create();
                dialog.setView(inflate);
                dialog.show();
                break;
            case R.id.return_icon:
                finish();
                break;
            case R.id.queding:
                dialog.dismiss();
                int checkedItemPosition = mListView.getFirstVisiblePosition();
                int checkedItemPosition2 = mListView2.getFirstVisiblePosition();
                int checkedItemPosition3 = mListView3.getFirstVisiblePosition();
                String s1 = arrayList.get(checkedItemPosition);
                String s2 = arrayList2.get(checkedItemPosition2);
                timerbtn.setText("" + s1 + "-" + s2 + "-" + checkedItemPosition3);
                break;
            case R.id.quxiao:
                dialog.dismiss();
                break;
            case R.id.switchover:
                presenter.uploderdta(setloginpwd.getText().toString(), sex);
                if (!TextUtils.isEmpty(imagePath)) {
                    File file = new File(imagePath);
                    presenter.uplodimage(file);
                }
                break;
            case R.id.uploderimage:
                View inflate2 = LayoutInflater.from(this).inflate(R.layout.uploderimage_layout, new LinearLayout(this), true);
                btnm1 = inflate2.findViewById(R.id.open_camera);
                btnm2 = inflate2.findViewById(R.id.open_photo_album);
                btnm3 = inflate2.findViewById(R.id.cancelselect);
                btnm1.setOnClickListener(this);
                btnm2.setOnClickListener(this);
                btnm3.setOnClickListener(this);
                dialog2 = new AlertDialog.Builder(this).create();
                dialog2.setView(inflate2);
                dialog2.show();
                break;
            case R.id.open_camera:
                Intent openAlbumIntent = new Intent(
                        Intent.ACTION_GET_CONTENT);
                openAlbumIntent.setType("image/*");
                startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                dialog2.dismiss();
                break;
            case R.id.open_photo_album:
                dialog2.dismiss();
                takePicture();
                break;
            case R.id.cancelselect:
                dialog2.dismiss();
                break;

        }
    }

    private void takePicture() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 23) {
            // 需要申请动态权限
            int check = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        Intent openCameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment
                .getExternalStorageDirectory(), "image.jpg");
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= 24) {
            openCameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            tempUri = FileProvider.getUriForFile(this, "com.lt.uploadpicdemo.fileProvider", file);
        } else {
            tempUri = Uri.fromFile(new File(Environment
                    .getExternalStorageDirectory(), "image.jpg"));
        }
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            photo = ImageUtils.toRoundBitmap(photo); // 这个时候的图片已经被处理成圆形的了
            uploderimage.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了
        imagePath = ImageUtils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.e("imagePath", imagePath + "");
        if (imagePath != null) {
            // 拿着imagePath上传了

            Log.d("TAG", "imagePath:" + imagePath);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
            // 没有获取 到权限，从新请求，或者关闭app
            Toast.makeText(this, "需要存储权限", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void Seecc(MessageBean data) {
        Toast.makeText(this, data.getStatus(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void feali(Throwable throwable) {
        Toast.makeText(this, throwable.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void Seeccimage(UpdataBean updataBean) {
        Toast.makeText(this, "是否上传" + updataBean.getStatus(), Toast.LENGTH_LONG).show();
    }
}
