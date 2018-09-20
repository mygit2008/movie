package com.bw.Movie.fragment;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.Movie.R;
import com.bw.Movie.activity.DetailsActivity;
import com.bw.Movie.adapter.RlvAdapter2;
import com.bw.Movie.bean.AllBean;
import com.bw.Movie.interview.IAllView;
import com.bw.Movie.interview.RlvItemClick;
import com.bw.Movie.model.AllModel;
import com.bw.Movie.presenter.AllPresenter;
import com.bw.Movie.utils.SpacesItemDecoration;

import java.util.List;

import example.com.mvplibrary.BaseFragment;
import example.com.mvplibrary.mvp.BaseModel;

/**
 * Created by BoyLucky on 2018/8/6.
 */

public class ShangyingFragment extends BaseFragment<AllPresenter> implements IAllView {
    private RecyclerView rlv;
    private int page = 1;
    private int count = 10;
    @Override
    protected void initView(View view) {
        rlv = view.findViewById(R.id.rlv_shangying);
    }

    @Override
    protected BaseModel initModel() {
        return new AllModel();
    }

    @Override
    protected AllPresenter initPresenter() {
        return new AllPresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.shangying_frag;
    }

    @Override
    protected void initData() {
        presenter.getAll(page,count);
    }

    @Override
    public void onSuccess(AllBean recommendCinemaBean) {
        List<AllBean.ResultBean> result = recommendCinemaBean.getResult();
        rlv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        RlvAdapter2 adapter = new RlvAdapter2(getContext(), result);
        rlv.setAdapter(adapter);
        int space = 8;
        rlv.addItemDecoration(new SpacesItemDecoration(space));
        //添加Android自带的分割线
//        rlv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        adapter.setRlvItemClick(new RlvItemClick() {
            @Override
            public void rlvOnItemClick(View v, int p) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("id",p);
                startActivity(intent);
            }
        });
    }
}
