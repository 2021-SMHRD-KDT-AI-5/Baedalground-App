package com.example.a3project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    //  상단바
    BottomNavigationView tnv;
    //  하단바
    BottomNavigationView bnv;
    TextView tv_name,tv_address;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;
    Fragment5 fragment5;
    Fragment6 fragment6;

    Button btn_logo, btn_login2, btn_logout2;

    MyOnClickListener click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tnv = findViewById(R.id.topview);
        bnv = findViewById(R.id.bottomview);
        tv_name = findViewById(R.id.tv_name);
        tv_address = findViewById(R.id.tv_address);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();
        fragment6 = new Fragment6();
        click = fragment6;

        btn_logo = findViewById(R.id.btn_logo);
        btn_login2 = findViewById(R.id.btn_login2);
        btn_logout2 = findViewById(R.id.btn_logout);

//        Intent it_addr = getIntent();
//        it_addr.getStringExtra("address");
//        Toast.makeText(getApplicationContext(), it_addr.getStringExtra("address"), Toast.LENGTH_SHORT).show();

//        Bundle bundle = new Bundle();
//        bundle.putString("address", it_addr.getStringExtra("address"));
//
//        fragment6.setArguments(bundle);

        Intent data = getIntent();
        if(data.getStringExtra("id")!=null){
            btn_login2.setVisibility(View.GONE);
            btn_logout2.setVisibility(View.VISIBLE);
        }

        if(data.getStringExtra("frag_name") != null && data.getStringExtra("frag_name").equals("frag4")) {
//            Log.d("프레그먼트", data.getStringExtra("frag_name"));
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment4).commit();


        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment6).commit();
            }

        // bottomNavigationBar 에서 어떤 메뉴가 선택되는지 감지한다.
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // 매개변수 item => 내가 선택한 메뉴
                switch (item.getItemId()) {
                    case R.id.menu_1:  // tab1이라면
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                        break;
                    case R.id.menu_2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                        break;
                    case R.id.menu_3:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                        Intent it_commu = new Intent(getApplicationContext(), Commu.class);
                        startActivity(it_commu);
                        break;
                    case R.id.menu_4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment4).commit();
                        break;
                    case R.id.menu_5:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment5).commit();
                        break;
                }
                return true;
            }
        });

        btn_login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_login = new Intent(MainActivity.this, Login.class);
                startActivity(it_login);
            }
        });

        btn_logout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.removeExtra("id");
                btn_login2.setVisibility(View.VISIBLE);
                btn_logout2.setVisibility(View.GONE);
                SharedPreferences spf = getSharedPreferences("basic", MODE_PRIVATE);
                SharedPreferences.Editor editor = spf.edit();
                editor.remove("id");
                editor.remove("address");
                editor.commit();
                click.myOnClick();
//                Intent it = new Intent(MainActivity.this, Login.class);
//                startActivity(it);
            }
        });

        btn_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment6).commit();
            }
        });
    }
}
