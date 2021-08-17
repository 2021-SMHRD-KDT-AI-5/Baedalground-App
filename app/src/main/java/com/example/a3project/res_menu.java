package com.example.a3project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class res_menu extends AppCompatActivity {

    Fragment_res_name fragment_res_name;

    TextView tv_resm_name;
    TabLayout tabLayout;
    RatingBar ratingBar;
    ViewPager viewPager;

    res_menu_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_menu);

        tv_resm_name = findViewById(R.id.tv_resm_name);
        ratingBar = findViewById(R.id.ratingBar);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.vp_cont);

        Fragment[] mFragmentList = new Fragment[3];

        mFragmentList[0] = new Fragment_res_name();
        mFragmentList[1] = new Fragment_res_info();
        mFragmentList[2] = new Fragment_res_review();

        adapter = new res_menu_Adapter(getSupportFragmentManager(), mFragmentList);

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        Intent it = getIntent();
        String menu = it.getStringExtra("menu");

        Bundle bundle = new Bundle();

        ArrayList<String> testarr = new ArrayList<String>();
        testarr.add(menu);
        testarr.add(menu);

        bundle.putSerializable("res", testarr);
        mFragmentList[0].setArguments(bundle);
    }

}