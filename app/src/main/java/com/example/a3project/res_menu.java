package com.example.a3project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class res_menu extends AppCompatActivity implements Serializable {

    Fragment_res_name fragment_res_name;

    TextView tv_resm_name;
    TabLayout tabLayout;
    ViewPager viewPager;

    res_menu_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_menu);

        tv_resm_name = findViewById(R.id.tv_resm_name);
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
//        String menu = it.getStringExtra("menu");
//        Log.d("menu_test", menu);

        Bundle bundle = it.getExtras();
        bundle.getSerializable("list_menu");

        try {
            JSONObject res_info_name = new JSONObject(bundle.getSerializable("list_res_info").toString());

//            Log.d("res_info", res_info_name.getJSONArray("0").get(0).toString());

            tv_resm_name.setText(res_info_name.getJSONArray("0").get(0).toString());

        Log.d("bundle_test", bundle.getSerializable("list_menu").toString());

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(res_menu.this, MainActivity.class);
                intent.putExtra("frag_name", "frag4");
                intent.putExtra("resTitle", tv_resm_name.getText().toString());
                startActivity(intent);
            }
        });
        
        mFragmentList[0].setArguments(bundle);
        mFragmentList[1].setArguments(bundle);

    } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}