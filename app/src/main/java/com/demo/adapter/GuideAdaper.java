package com.demo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by wb-qjl256634 on 2017/3/13.
 */

public class GuideAdaper extends PagerAdapter {
    ArrayList<ImageView> data;
    public void  setData(ArrayList data) {
            this.data=data;
    }
    public  GuideAdaper(ArrayList data) {
        this.data=data;
    }
    @Override
    public int getCount() {
        if (data!=null){
            return data.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view= data.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
