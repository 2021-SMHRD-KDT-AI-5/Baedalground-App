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

//    public void addFragment(Fragment fragment){
//        mFragmentList.add(fragment);
//    }

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
                return "First";
            case 1:
                return "Second";
            case 2:
                return "Third";
            default:
                return "";
        }
//        return super.getPageTitle(position);
    }
}
