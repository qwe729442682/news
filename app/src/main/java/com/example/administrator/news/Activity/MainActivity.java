package com.example.administrator.news.Activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.administrator.news.Fragment.ChatFragement;
import com.example.administrator.news.Fragment.MusicFragement;
import com.example.administrator.news.Fragment.NewFragement;
import com.example.administrator.news.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.fl_content_activity_main)
    FrameLayout flContentActivityMain;

    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;
    private Fragment currFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        ButterKnife.bind(this);
        toolbar.setTitle("新闻");
        toolbar.setSubtitle("新闻新信息");
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        fragmentList = new ArrayList<>();

        //添加片段
        fragmentList.add(new NewFragement());
        fragmentList.add(new ChatFragement());
        fragmentList.add(new MusicFragement());
        //默认选择新闻
        navigation.setCheckedItem(R.id.submenu_1);
        //默认切换第一个fragment
        currFragment = fragmentList.get(0);
        fragmentManager.beginTransaction().add(R.id.fl_content_activity_main, currFragment).commit();
        //切换toggle,使toolbar和侧拉菜单关联起来并设置开关    抽屉菜单栏
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        //状态同步
        toggle.syncState();
        //侧拉菜单的点击事件
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int index = 0;
                switch (item.getItemId()) {
                    case R.id.submenu_1:
                        index = 0;
                        toolbar.setTitle("新闻");
                        break;
                    case R.id.submenu_2:
                        index = 1;
                        toolbar.setTitle("聊天");
                        break;
                    case R.id.submenu_3:
                        index = 2;
                        toolbar.setTitle("音乐");
                        break;
                }
                //打开管理事务
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                //切换fragment
                Fragment nextFragment = fragmentList.get(index);
                //如果下一个片段不等于当前的这个片段
                if (nextFragment != currFragment) {

                    if (!nextFragment.isAdded()) {

                        if (currFragment != null) {

                            transaction.hide(currFragment);
                        }
                        transaction.add(R.id.fl_content_activity_main, nextFragment);
                    } else {

                        if (currFragment != null) {

                            transaction.hide(currFragment);
                        }
                        transaction.show(nextFragment);

                    }
                    currFragment = nextFragment;
                }

                transaction.commit();
                drawerLayout.closeDrawers();
                return true;
            }
        });

    }


}

