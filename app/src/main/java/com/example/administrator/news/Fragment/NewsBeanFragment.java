package com.example.administrator.news.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.administrator.news.Adapter.MyFragmentItemAdapter;
import com.example.administrator.news.R;
import com.example.administrator.news.Util.NoHttpInstance;
import com.example.administrator.news.enty.Constant;
import com.example.administrator.news.enty.NewBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class NewsBeanFragment extends Fragment {

    private PullToRefreshListView listView;
    private List<NewBean.ResultBean.DataBean> datas;
    public String url;
    public NewsBeanFragment(String url){
        this.url = url;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listView = (PullToRefreshListView) inflater.inflate(R.layout.fragment_listview,null);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                Toast.makeText(getContext(), "正在请求网络数据", Toast.LENGTH_SHORT).show();

                listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listView.onRefreshComplete();
                    }
                }, 1000);
            }
        });


        return listView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //请求队列
        Request<String> topNewStringRequest = NoHttp.createStringRequest(Constant.BASE_URL + url);
        //请求数据
        NoHttpInstance.getInstance().add(Constant.WHAT_NENS_REQUEST, topNewStringRequest, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String result = response.get();
                NewBean newBean = JSON.parseObject(result, NewBean.class);
                datas = newBean.getResult().getData();
                MyFragmentItemAdapter adapter = new MyFragmentItemAdapter(getContext(),datas);
                listView.setAdapter(adapter);

            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }
}
