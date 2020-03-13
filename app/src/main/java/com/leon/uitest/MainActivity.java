package com.leon.uitest;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
//    LinearLayout[] linearLayouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    void initialize() {
        initializeLinearLayout();
        setTabLayoutOnclickListener();
    }

    void initializeLinearLayout() {
        MyPagerAdapter adapterViewPager = new MyPagerAdapter(getSupportFragmentManager(), getApplicationContext());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem(1);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    void setTabLayoutOnclickListener() {
        tabLayout = findViewById(R.id.tabLayout);
        TabLayout.Tab tab = tabLayout.getTabAt(2);
        tab.select();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                linearLayouts[tab.getPosition()].setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                linearLayouts[tab.getPosition()].setVisibility(View.GONE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
