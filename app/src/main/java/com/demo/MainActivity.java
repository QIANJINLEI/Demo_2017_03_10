package com.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.demo.home.GovermentFragment;
import com.demo.home.HomeFragment;
import com.demo.home.NewsFragment;
import com.demo.home.ServerFragment;
import com.demo.home.SettingFragment;

import static com.demo.R.drawable.server_selector;


public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private ListView mLeft_drawer;
    private FragmentTabHost tabHost;
    private String HOME = "home";
    private String NEWS = "news";
    ;
    private String SERVER = "server";
    ;
    private String GOVERNMENT = "government";
    private String SETTING = "setting";
    private View view_home = null;
    private View view_news = null;
    private View view_server = null;
    private View view_government = null;
    private View view_setting = null;
    private TabHost.OnTabChangeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLeft_drawer = (ListView) findViewById(R.id.left_drawer);
        tabHost = (FragmentTabHost) findViewById(R.id.ft_main);
        tabHost.setup(this, getSupportFragmentManager(), R.id.content_frame);
        Bundle bundle = new Bundle();

        tabHost.addTab(tabHost.newTabSpec(HOME).setIndicator(getview(this, "home")), HomeFragment.class, bundle);
        tabHost.addTab(tabHost.newTabSpec(NEWS).setIndicator(getview(this, "news")), NewsFragment.class, bundle);
        tabHost.addTab(tabHost.newTabSpec(SERVER).setIndicator(getview(this, "server")), ServerFragment.class, bundle);
        tabHost.addTab(tabHost.newTabSpec(GOVERNMENT).setIndicator(getview(this, "government")), GovermentFragment.class, bundle);
        tabHost.addTab(tabHost.newTabSpec(SETTING).setIndicator(getview(this, "setting")), SettingFragment.class, bundle);
        initChanged();
       // initSeleced();
        /**
         * 内存开始泄露了~
         */
        listener.onTabChanged(HOME);
    }

    private void initSeleced() {
        view_home.setSelected(false);
        view_news.setSelected(false);
        view_server.setSelected(false);
        view_government.setSelected(false);
        view_setting.setSelected(false);
    }

    private void initChanged() {
        listener = new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if (s.equals(HOME)) {
                    initSeleced();
                    view_home.setSelected(true);
                } else if (s.equals(NEWS)) {
                    initSeleced();
                    view_news.setSelected(true);
                } else if (s.equals(SERVER)) {
                    initSeleced();
                    view_server.setSelected(true);
                } else if (s.equals(GOVERNMENT)) {
                    initSeleced();
                    view_government.setSelected(true);
                } else{
                    initSeleced();
                    view_setting.setSelected(true);
                }

            }
        };
        tabHost.setOnTabChangedListener(listener);
    }

    public View getview(Context context, String name) {
        View view = View.inflate(context, R.layout.tabhost_main, null);
        ImageView iv_tabHost = (ImageView) view.findViewById(R.id.iv_tabHost);
        TextView tv_tabHost = (TextView) view.findViewById(R.id.tv_tabHost);
        if (TextUtils.equals(name, "home")) {
            iv_tabHost.setImageResource(R.drawable.home_selector);
            tv_tabHost.setText("首页");
            view_home = view;
        } else if (TextUtils.equals(name, "news")) {
            iv_tabHost.setImageResource(R.drawable.news_selector);
            tv_tabHost.setText("新闻中心");
            view_news = view;
        } else if (TextUtils.equals(name, "server")) {
            iv_tabHost.setImageResource(server_selector);
            tv_tabHost.setText("智慧服务");
            view_server = view;
        } else if (TextUtils.equals(name, "government")) {
            iv_tabHost.setImageResource(R.drawable.govaffairs_selector);
            tv_tabHost.setText("政务");
            view_government = view;
        } else {
            iv_tabHost.setImageResource(R.drawable.setting_selector);
            tv_tabHost.setText("设置");
            view_setting = view;
        }
        return view;
    }
}
