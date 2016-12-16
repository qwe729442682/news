package com.example.administrator.news.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.administrator.news.R;
import com.example.administrator.news.Util.CacheUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogoActivity extends AppCompatActivity {

    @BindView(R.id.tv_left_time)
    TextView tvLeftTime;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    private int leftTime = 3;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){

                case 0 :
                    if(leftTime > -1){
                        String newText = "广告倒计时：" + leftTime-- + "秒";
                        tvLeftTime.setText(newText);
                        handler.sendEmptyMessageDelayed(0,1000);
                    }else{

                        boolean booleanFromSp = CacheUtil.getBooleanFromSp(LogoActivity.this,CacheUtil.IS_FIRST,true);

                        if(booleanFromSp){
                            Intent intent = new Intent(LogoActivity.this,LeadActivity.class);
                            startActivity(intent);
                            finish();
                        }else{

                        Intent intent = new Intent(LogoActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();

                        }
                    }

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initAnimation();


    }

    private void initAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0 , 1);
        alphaAnimation.setDuration(3200);
        activityMain.startAnimation(alphaAnimation);
        handler.sendEmptyMessageDelayed(0,1000);
    }
}
