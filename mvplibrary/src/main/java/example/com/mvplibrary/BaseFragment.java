package example.com.mvplibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.com.mvplibrary.mvp.BaseModel;
import example.com.mvplibrary.mvp.BasePresenter;
import example.com.mvplibrary.mvp.IBaseView;

/**
 * @author zhangjunyou
 * @date 2018/7/17
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {
    public P presenter;
    private final String TAG = BaseFragment.class.getSimpleName();
    protected View rootView;
    protected Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = LayoutInflater.from(getContext()).inflate(getLayoutid(), container, false);
            unbinder = ButterKnife.bind(this, rootView);
        }
        presenter = initPresenter();
        if (presenter != null) {
            presenter.attch(initModel(), this);
        }
        ScreenAdapterTools.getInstance().loadView((ViewGroup) rootView);
//        NetWorkUtils.getCurrentNetworkState(getContext());
        return rootView;
    }

    /**
     * 初始化控件
     */
    protected abstract void initView(View view);

    /**
     * 初始化model
     *
     * @return
     */
    protected abstract BaseModel initModel();

    /**
     * 初始化presenter
     *
     * @return
     */
    protected abstract P initPresenter();

    /**
     * 获取布局ID
     *
     * @return int
     */
    protected abstract int getLayoutid();

    /**
     * 获取数据
     */
    protected abstract void initData();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.d("调用了-------> onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.e("调用了-------> onCreate()");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        Logger.v("调用了-------> onViewCreated()");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.i("调用了-------> onActivityCreated()");
        initData();
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.w("调用了-------> onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.e("调用了-------> onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.i("调用了-------> onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.d("调用了-------> onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.e("调用了-------> onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.v("调用了-------> onDestroy()");
        if (presenter != null) {
            presenter = null;
            unbinder.unbind();//解绑
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.w("调用了-------> onDetach()");
    }

    @Override
    public void serverFail(String msg) {

    }
}
