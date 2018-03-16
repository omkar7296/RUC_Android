package com.upitt.ruc;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.upitt.ruc.Views.MainActivityViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static String url;
    public static String mname;

    @BindView(R.id.activity_main_viewPager)
    ViewPager mainViewPager;

    @BindView(R.id.activity_main_tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        url = getIntent().getStringExtra("imgURL");
        mname = getIntent().getStringExtra("mgivenName");
        MainActivityViewPagerAdapter adapter = new MainActivityViewPagerAdapter(getSupportFragmentManager());

        mainViewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(mainViewPager);
    }
}
