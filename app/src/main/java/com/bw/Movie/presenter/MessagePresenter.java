package com.bw.Movie.presenter;

import com.bw.Movie.bean.MessageBean;
import com.bw.Movie.bean.UpdataBean;
import com.bw.Movie.interview.MessageCallBack;
import com.bw.Movie.model.MessageModer;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WXY on 2018/8/9.
 */

public class MessagePresenter extends MessageCallBack.BaseRegisterPresenter<MessageModer,MessageCallBack.BaseRegisterView> {

    public void uploderdta(String s, int sex) {
        model.uploderdata(s,sex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MessageBean>() {
                    @Override
                    public void accept(MessageBean messageBean) throws Exception {
                        view.Seecc(messageBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void uplodimage(File file) {
        model.uploderiamge(file).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpdataBean>() {
                    @Override
                    public void accept(UpdataBean updataBean) throws Exception {
                        view.Seeccimage(updataBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
