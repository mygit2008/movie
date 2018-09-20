package com.bw.Movie.fragment;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.Movie.R;
import com.bw.Movie.activity.DetailsActivity;
import com.bw.Movie.adapter.RlvAdapter;
import com.bw.Movie.adapter.RlvAdapter_G;
import com.bw.Movie.bean.RecommendCinemaBean;
import com.bw.Movie.interview.ICinemaView;
import com.bw.Movie.interview.RlvItemClick;
import com.bw.Movie.model.CinemaModel;
import com.bw.Movie.presenter.CinemaPresenter;
import com.bw.Movie.utils.SpacesItemDecoration;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import example.com.mvplibrary.BaseFragment;
import example.com.mvplibrary.mvp.BaseModel;

/**
 * Created by BoyLucky on 2018/8/6.
 */

public class TuijianFragment extends BaseFragment <CinemaPresenter> implements ICinemaView {
    private RecyclerView rlv,rlv_g;
    private LinearLayout g_ll;
    private int page = 1;
    private int count = 10;
    private View view;
    private SmartRefreshLayout srl_out;
    private List<RecommendCinemaBean.ResultBean.NearbyCinemaListBean> list_all;
    private RlvAdapter_G rlvAdapter_g;
    private RlvAdapter adapter;

    private String latitude = "116.30551391385724";
    private String longitude = "40.04571807462411";

    @Override
    protected void initView(View view) {
        list_all = new ArrayList<RecommendCinemaBean.ResultBean.NearbyCinemaListBean>();
        rlv = view.findViewById(R.id.rlv);
//        rlv_g = view.findViewById(R.id.rlv_g);
        srl_out = view.findViewById(R.id.sf_out);
//        g_ll = view.findViewById(R.id.g_ll);
        //设置 Header 为 Material风格
        srl_out.setRefreshHeader(new WaveSwipeHeader(getContext()));
//设置 Footer 为 球脉冲
        srl_out.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        srl_out.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.getCinemas(1,count, longitude, latitude);
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                list_all.clear();
            }
        });
        srl_out.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page++;
                presenter.getCinemas(page,count, longitude, latitude);
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    @Override
    protected BaseModel initModel() {
        return new CinemaModel();
    }

    @Override
    protected CinemaPresenter initPresenter() {
        return new CinemaPresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.tuijian_frag;
    }

    @Override
    protected void initData() {
        presenter.getCinemas(page, count,longitude,latitude);
    }

    @Override
    public void onSuccess(RecommendCinemaBean recommendCinemaBean) {
        Log.e("tag", "-----" + recommendCinemaBean.getMessage());
//        List<RecommendCinemaBean.ResultBean.FollowCinemasBean> g_list = recommendCinemaBean.getResult().getFollowCinemas();
        List<RecommendCinemaBean.ResultBean.NearbyCinemaListBean> list = recommendCinemaBean.getResult().getNearbyCinemaList();
        if (list != null){
            for (int i = 0; i < list.size(); i++) {
                list_all.add(list.get(i));
            }
        }else{
            Toast.makeText(getContext(),"到底啦亲",Toast.LENGTH_SHORT).show();
        }
        rlv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        if (list_all!=null){
            adapter = new RlvAdapter(getContext(), list_all);
            rlv.setAdapter(adapter);
            int space = 8;
            rlv.addItemDecoration(new SpacesItemDecoration(space));
        }
//        adapter = new RlvAdapter(getContext(), list_all);
//        rlv.setAdapter(adapter);
        adapter.setRlvItemClick(new RlvItemClick() {
            @Override
            public void rlvOnItemClick(View v, int p) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("id",p);
                startActivity(intent);
            }
        });
//        添加Android自带的分割线
        rlv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
//        if (g_list.size()!=0 ){
//            Log.e("g_list","----"+g_list.size());
//            rlv_g.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
//            rlvAdapter_g = new RlvAdapter_G(getContext(), g_list);
//            rlv_g.setAdapter(rlvAdapter_g);
//            int space = 8;
//            rlv_g.addItemDecoration(new SpacesItemDecoration(space));
//            rlvAdapter_g.setRlvItemClick(new RlvItemClick() {
//                @Override
//                public void rlvOnItemClick(View v, int p) {
//                    Intent intent = new Intent(getContext(), DetailsActivity.class);
//                    intent.putExtra("id",p);
//                    startActivity(intent);
//                }
//            });
//            //添加Android自带的分割线
//            rlv_g.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
//        }else{
//            g_ll.setVisibility(View.GONE);
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getCinemas(page, count, longitude, latitude);
    }


}
