package com.example.administrator.news.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.news.R;
import com.example.administrator.news.enty.NewBean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyFragmentItemAdapter extends BaseAdapter {

    private Context context;
    private List<NewBean.ResultBean.DataBean> datas;


    public MyFragmentItemAdapter(Context context, List<NewBean.ResultBean.DataBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int postion) {
        return datas.get(postion);
    }

    @Override
    public long getItemId(int postion) {
        return postion;
    }

    @Override
    public View getView(int postion, View converView, ViewGroup viewGroup) {
        View view = null;
        ViewHodler hodler = null;
        if(converView == null){

            hodler = new ViewHodler();
            view = View.inflate(context, R.layout.fragment_listview_item,null);
            hodler.iv = (ImageView) view.findViewById(R.id.fragment_listview_imageview);
            hodler.tv_title = (TextView) view.findViewById(R.id.fragment_listview_title);
            hodler.tv_date = (TextView) view.findViewById(R.id.fragment_listview_date);

            view.setTag(hodler);

        }else{
            view = converView;
            hodler = (ViewHodler) view.getTag();
        }

        //初始化数据
        Glide.with(context).load(datas.get(postion).getThumbnail_pic_s()).crossFade().into(hodler.iv);
        hodler.tv_title.setText(datas.get(postion).getTitle());
        hodler.tv_date.setText(datas.get(postion).getDate());

        return view;
    }
    class ViewHodler{
        ImageView iv;
        TextView tv_title;
        TextView tv_date;
    }
}
