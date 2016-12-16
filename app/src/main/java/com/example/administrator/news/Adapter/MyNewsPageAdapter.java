package com.example.administrator.news.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.news.enty.Constant;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyNewsPageAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragment;
    public MyNewsPageAdapter(FragmentManager fm,List<Fragment> fragment) {
        super(fm);
        this.fragment = fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return fragment.get(position);
    }

    @Override
    public int getCount() {
        return fragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constant.TITLES[position];
    }
}
