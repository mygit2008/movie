package com.bw.Movie.model;

import com.bw.Movie.MyApp;
import com.bw.Movie.api.APIFunction;
import com.bw.Movie.bean.GouPiaoJiLuBean;

import example.com.mvplibrary.mvp.BaseModel;
import example.com.utilslibrary.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GPJLModel extends BaseModel {

    public void getGPJL(final GetModel getModel){
        RetrofitUtil.getInstence(MyApp.context).create(APIFunction.class)
                .getGPJL()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GouPiaoJiLuBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GouPiaoJiLuBean gouPiaoJiLuBean) {
                        getModel.onSuccess(gouPiaoJiLuBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface GetModel{
        void onSuccess(GouPiaoJiLuBean gouPiaoJiLuBean);
    }
}
