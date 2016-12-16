package com.example.administrator.news.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.news.R;
import com.example.administrator.news.Util.CacheUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeadActivity extends AppCompatActivity {

    @BindView(R.id.llayout)
    LinearLayout LLayout;
    @BindView(R.id.pv_Lead)
    ViewPager pvlead;
    @BindView(R.id.btn_enter)
    Button btnEnter;
    private int[] imgs = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        ButterKnife.bind(this);
        //初始化数据
        initData();
        //初始化引导页面图片下面的球显示的所在的页面
        iniImageBall();
        // 新建一个适配器
        MyPagerAdapter adapter = new MyPagerAdapter();
        //给ViewPager 设置适配器
        pvlead.setAdapter(adapter);
        //给ViewPager设置监听
        pvlead.setOnPageChangeListener(new MyPagerListener());

    }

    private void iniImageBall() {
        for (int i = 0; i < imgs.length; i++) {
            //新建一个视图
            View v = new View(this);
            //把小球图片设置到视图里
            v.setBackgroundResource(R.drawable.indicator_pager_normal);
            //让小球在线性布局里的距离 为30 dp
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);
            if (i != 0) {
                //小球左边距为30
                params.leftMargin = 30;
            } else {
                //否则就给试图设置绿色的小绿球
                v.setBackgroundResource(R.drawable.indicator_pager_pressed);
            }
            //把布局参数放进去
            v.setLayoutParams(params);
            //给线性布局里添加试图。
            LLayout.addView(v);
        }
    }

    private void initData() {
        //初始化图片
        imgs[0] = R.mipmap.bd;
        imgs[1] = R.mipmap.small;
        imgs[2] = R.mipmap.welcome;
        imgs[3] = R.mipmap.wy;

    }

    @OnClick(R.id.btn_enter)
    public void onClick() {
        Intent intent = new Intent(LeadActivity.this, MainActivity.class);
        startActivity(intent);
        /*第一次进入软件的时候里面是没有数据的所以第一进入往里写入数据(也就是引导页面)所以是false(false的意思就是里面没有数据)
            第二次进入因为里面有数据了 所以就不执行引导页面了 直接跳过引导页面进行下一个activity
         */
        CacheUtil.putBooleanIntoSp(LeadActivity.this, "is_first", false);
        finish();
    }

    //ViewPager的适配器
    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            //图片长度
            return imgs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 实例化条目
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = new ImageView(LeadActivity.this);
            iv.setBackgroundResource(imgs[position]);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }
    }
    //ViewPager监听
    class MyPagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < imgs.length; i++) {
                LLayout.getChildAt(i).setBackgroundResource(R.drawable.indicator_pager_normal);
            }
            LLayout.getChildAt(position).setBackgroundResource(R.drawable.indicator_pager_pressed);
                //判断图片位置是否在第三位 如果大于第3张或者小于第3张 就显示跳过按钮
            if (position >= 3) {
                btnEnter.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
