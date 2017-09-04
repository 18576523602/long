package com.example.atest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentPagerAdapter adapter;
    private List<String> lists = Arrays.asList("短信1", "短信2", "短信3", "短信4", "短信5", "短信6", "短信7");
    private List<Fragment> fragments = new ArrayList<>();
    private ViewPagerIndicator indicator;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        indicator = (ViewPagerIndicator) findViewById(R.id.vi);
        viewPager = (ViewPager) findViewById(R.id.vp);
        initDatas();
        indicator.setTabItemTitles(lists);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager,0);
    }

    private void initDatas() {

        for (String list : lists) {
            VpSimpleFragment fragment = VpSimpleFragment.newInstance(list);
            fragments.add(fragment);
        }


        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
    }
}
