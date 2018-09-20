package com.bw.Movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import com.bw.Movie.bean.RecommendCinemaBean;

import java.util.List;

/**
 * Created by BoyLucky on 2018/8/24.
 */

public class Elv_Adapter extends BaseExpandableListAdapter {
    private Context context;
    List<RecommendCinemaBean.ResultBean.FollowCinemasBean> g_list ;
    List<RecommendCinemaBean.ResultBean.NearbyCinemaListBean> list;

    public Elv_Adapter(Context context, List<RecommendCinemaBean.ResultBean.FollowCinemasBean> g_list, List<RecommendCinemaBean.ResultBean.NearbyCinemaListBean> list) {
        this.context = context;
        this.g_list = g_list;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return 2;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
