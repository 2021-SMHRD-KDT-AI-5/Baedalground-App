package com.example.a3project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class res_menu_Adapter extends FragmentPagerAdapter {

    private Fragment[] mFragmentList;

    public res_menu_Adapter(FragmentManager fm, Fragment[] mFragmentList) {
        super(fm);
        this.mFragmentList = mFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList[position];
    }

    @Override
    public int getCount() {
        return mFragmentList.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Menu";
            case 1:
                return "Info";
            case 2:
                return "Review";
            default:
                return "";
        }
//        return super.getPageTitle(position);
    }
}
