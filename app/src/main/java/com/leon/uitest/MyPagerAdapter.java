package com.leon.uitest;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private Context context;

    MyPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public int getCount() {
        int NUM_ITEMS = 4;
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return AboutFragment.newInstance("4", "Page # 4");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return Menu2Fragment.newInstance(2);
            case 2: // Fragment # 1 - This will show SecondFragment
                return CommentsFragment.newInstance("2", "Page # 2");
            case 3: // Fragment # 1 - This will show SecondFragment
                return FactorsFragment.newInstance(2);
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.about);
            case 1:
                return context.getString(R.string.menu);
            case 2:
                return context.getString(R.string.comment);
            case 3:
                return context.getString(R.string.factor);
            default:
                return null;
        }
//        return "Page " + position;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

}
