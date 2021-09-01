package com.example.a3project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Commu extends AppCompatActivity {
    BottomNavigationView bnb;
    fragment_main fragment_main;
    fragment_join fragment_join;
    fragment_myposter fragment_myposter;
//    fragment_review Fragment_review;
    Button btn_login_c,btn_logo_c,btn_logout_c,btn_login2,btn_logout2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commu);

//        btn_commu = findViewById(R.id.btn_commu);
//        btn_commu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it = new Intent(MainActivity.this, commu.class);
//                startActivity(it);
//            }
//        });

        //메뉴는 findViewById 한다
        bnb = findViewById(R.id.bnb);
        btn_login_c = findViewById(R.id.btn_login_c);
        btn_logo_c = findViewById(R.id.btn_logo_c);
        btn_login2 = findViewById(R.id.btn_login2);
        btn_logout2 = findViewById(R.id.btn_logout2);
        btn_logout_c = findViewById(R.id.btn_logout_c);

        // Fragment들은 객체 생성한다.
        fragment_main = new fragment_main();
        fragment_join = new fragment_join();
        fragment_myposter = new fragment_myposter();
//        Fragment_review = new fragment_review();

//        Intent data = getIntent();
//        if(data.getStringExtra("id")!=null){
//            btn_login_c.setVisibility(View.GONE);
//            btn_logout_c.setVisibility(View.VISIBLE);
//        }

        Intent data_c = getIntent();
        String id = data_c.getStringExtra("id");
        if(id != null){
            btn_login_c.setVisibility(View.GONE);
            btn_logout_c.setVisibility(View.VISIBLE);
        }

//        Intent it_test = getIntent();
//        String id = it_test.getStringExtra("id");
//            Toast.makeText(this, id, Toast.LENGTH_SHORT).show();


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerC, fragment_main).commit();

        // bottmoNavigationBar에서 어떤 메뉴가 선택됐는지 감지한다.
        bnb.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){ // 내가 선택한 메뉴이 Id가
                    case R.id.tab1:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.containerC, fragment_main).commit();
//                        Intent it_c = new Intent(Commu.this,fragment_main.getClass());
//                        it_c.putExtra("id",id);
//                        startActivity(it_c);
                        // R.id.containerC 자리에 fragment1을 끼우겠다!!
                        break;
                    case R.id.tab2:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.containerC, fragment_join).commit();
                        break;
                    case R.id.tab3:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.containerC, fragment_myposter).commit();
                        break;
//                    case R.id.tab4:
//                        getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.containerC, Fragment_review).commit();
//                        break;
                }

                return true;
            }
        });



        btn_login_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_login = new Intent(Commu.this, Login.class);
                startActivity(it_login);

            }
        });
        btn_logo_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.containerC,Fragment6).commit();
                Intent it_logo = new Intent(v.getContext(), MainActivity.class);
                startActivity(it_logo);
            }
        });
        SharedPreferences spf = getApplicationContext().getSharedPreferences("basic", Context.MODE_PRIVATE);
//        String id = spf.getString("id", "");
//        if(!id.equals("")){
//            btn_login_c.setVisibility(View.VISIBLE);
//            btn_logout_c.setVisibility(View.GONE);
//
//        }


        btn_logout_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_logout_c.setVisibility(View.GONE);
                SharedPreferences.Editor editor = spf.edit();
                editor.remove("id");
                editor.remove("address");
                editor.commit();
                Intent it_logout = new Intent(v.getContext(),MainActivity.class);
                startActivity(it_logout);
//
//
            }
        });

    }
}