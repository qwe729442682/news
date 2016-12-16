package com.example.administrator.news.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.news.Adapter.MyNewsPageAdapter;
import com.example.administrator.news.R;
import com.example.administrator.news.enty.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class NewFragement extends Fragment {

    private List<Fragment> fragment;
    private ViewPager viewPager;
    private TabLayout tb;
    private LinearLayout ll;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ll = (LinearLayout) inflater.inflate(R.layout.layout_news_fragment_activity_main, null);
        viewPager = (ViewPager) ll.findViewById(R.id.vp_news_fragment);
        tb = (TabLayout) ll.findViewById(R.id.tab_title_fragment);
        tb.setupWithViewPager(viewPager);
        tb.setTabMode(TabLayout.MODE_SCROLLABLE);
        tb.setTabTextColors(Color.BLACK,Color.BLUE);

        return ll;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragment = new ArrayList<>();
        fragment.add(new NewsBeanFragment(Constant.TOP_QUERY_STRING));
        fragment.add(new NewsBeanFragment(Constant.SHEHUI_NEWS_QUERY_STRING));
        fragment.add(new NewsBeanFragment(Constant.GUONEI_NEWS_QUERY_STRING));
        fragment.add(new NewsBeanFragment(Constant.GUOJI_NEWS_QUERY_STRING));
        fragment.add(new NewsBeanFragment(Constant.TIYU_NEWS_QUERY_STRING));
        fragment.add(new NewsBeanFragment(Constant.YULE_NEWS_QUERY_STRING));
        fragment.add(new NewsBeanFragment(Constant.JUNSHI_NEWS_QUERY_STRING));
        fragment.add(new NewsBeanFragment(Constant.KEJI_NEWS_QUERY_STRING));
        fragment.add(new NewsBeanFragment(Constant.CAIJING_NEWS_QUERY_STRING));
        fragment.add(new NewsBeanFragment(Constant.SHISHANG_NEWS_QUERY_STRING));

        MyNewsPageAdapter adapter = new MyNewsPageAdapter(getFragmentManager(), fragment);
        viewPager.setAdapter(adapter);


    }
}
