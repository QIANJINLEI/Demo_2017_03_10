package com.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.demo.Utils.PreUtils;
import com.demo.Utils.Util;
import com.demo.adapter.GuideAdaper;

import java.util.ArrayList;

public class Guide extends AppCompatActivity {

    private ViewPager vp_guide;
    private int[] src = {
            R.drawable.guide_1,
            R.drawable.guide_2,
            R.drawable.guide_3
    };
    private ArrayList<ImageView> data;
    private LinearLayout ll_guide;
    private ImageView iv_guide;
    private Button btn_guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        ll_guide = (LinearLayout) findViewById(R.id.ll_guide);
        iv_guide = (ImageView) findViewById(R.id.iv_guide);
        btn_guide = (Button) findViewById(R.id.btn_guide);
        btn_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Guide.this, MainActivity.class);
                startActivity(intent);
                finish();
                PreUtils.setBoolean(Guide.this, Util.Config,false);
            }
        });
        initData();
        GuideAdaper guideAdaper = new GuideAdaper(data);
        vp_guide.setAdapter(guideAdaper);
        vp_guide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, int positionOffsetPixels) {
                iv_guide.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) iv_guide.getLayoutParams();
                        layoutParams.leftMargin = (int) ((ll_guide.getChildAt(1).getLeft() - ll_guide.getChildAt(0).getLeft()) * (position + positionOffset));
                        iv_guide.setLayoutParams(layoutParams);
                    }
                });
            }

            @Override
            public void onPageSelected(int position) {

                if (position==2){
                    btn_guide.setVisibility(View.VISIBLE);
                }else{
                    btn_guide.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        if (data == null)
            data = new ArrayList();
        for (int i = 0; i < src.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(src[i]);
            data.add(imageView);
            ImageView dog = new ImageView(this);
            dog.setImageResource(R.drawable.dot_guide_1);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

           if (i!=0)layoutParams.leftMargin = 10;
            dog.setLayoutParams(layoutParams);
            ll_guide.addView(dog);


        }
    }
}
