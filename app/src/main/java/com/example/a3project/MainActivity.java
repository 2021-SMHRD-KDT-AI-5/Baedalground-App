package com.example.a3project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    //  상단바
    BottomNavigationView tnv;
    //  하단바
    BottomNavigationView bnv;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;
    Fragment5 fragment5;
    Fragment6 fragment6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tnv = findViewById(R.id.topview);
        bnv = findViewById(R.id.bottomview);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();
        fragment6 = new Fragment6();

        /// 밖에다 놔두면 처음 화면에 띄워짐
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment6).commit();
        // bottomNavigationBar 에서 어떤 메뉴가 선택되는지 감지한다.
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // 매개변수 item => 내가 선택한 메뉴
                switch (item.getItemId()) {
                    case R.id.menu_1:  // tab1이라면

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment1).commit();
                        // R.id.container 자리에 fragment1을 끼우겠다!
                        break;
                    case R.id.menu_2:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment2).commit();
                        // R.id.container 자리에 fragment2을 끼우겠다!

                        break;
                    case R.id.menu_3:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment3).commit();
                        // R.id.container 자리에 fragment3을 끼우겠다!

                        break;
                    case R.id.menu_4:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment4).commit();
                        // R.id.container 자리에 fragment4을 끼우겠다!

                        break;
                    case R.id.menu_5:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment5).commit();
                        break;
                }


                return true;
            }
        });
        tnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:  // tab1이라면
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment6).commit();
                        // R.id.container 자리에 fragment1을 끼우겠다!
//                        R.id.login.visible(true);
                        break;
                    case R.id.login:
                        Intent it_login = new Intent(MainActivity.this, Login.class);
                        startActivity(it_login);
                }
                return true;

            }


        });
    }
}
