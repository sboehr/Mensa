package com.rac.simoneunddaniel.mensa.StartActivity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 04.07.2016.
 */
public class CustomViewPager extends ViewPager {

    private Context context;
    private AppCompatActivity activity;
    private boolean enabled;

    public CustomViewPager(Context context, AttributeSet attrs){
        super(context, attrs);
        this.enabled = true;
    }

    public CustomViewPager(Context context, AppCompatActivity activity) {
        super(context);
        this.context = context;
        this.activity = activity;
        this.enabled = true;
    }

    public CustomViewPager(Context context, AttributeSet attrs, AppCompatActivity activity) {
        super(context, attrs);
        this.context = context;
        this.activity = activity;
        this.enabled = true;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.enabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.enabled && super.onInterceptTouchEvent(event);

    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}

