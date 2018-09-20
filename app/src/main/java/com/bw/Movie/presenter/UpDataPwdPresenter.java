package com.bw.Movie.presenter;
import com.bw.Movie.bean.MessageBean;
import com.bw.Movie.bean.UpdataBean;
import com.bw.Movie.interview.UpDataPwdCallback;
import com.bw.Movie.model.UpDataPwdModer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WXY on 2018/8/9.
 */

public class UpDataPwdPresenter extends UpDataPwdCallback.BaseUpDataPwdPresenter<UpDataPwdModer,UpDataPwdCallback.BaseUpDataPwdView>
{

    public void updatapwd(String s, String s1, String s2) {
        model.updatapwd(s,s1,s2).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpdataBean>() {
                    @Override
                    public void accept(UpdataBean updataBean) throws Exception {
                        view.Seecc(updataBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                       view.feali(throwable);
                    }
                });
    }
}
