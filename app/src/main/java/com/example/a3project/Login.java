package com.example.a3project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText edt_id, edt_pw;
    Button btn_login;
    TextView tv_gojoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        btn_login = findViewById(R.id.btn_login);
        tv_gojoin = findViewById(R.id.tv_goJoin);

        memberVO member = new memberVO();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_id.equals(member.getId()) && edt_pw.equals(member.getPw())){
//                    Intent it = new Intent(); // main 으로 이동할 Intent
//                    it.putExtra("id", R.id.edt_id);
                }else{
                    // 로그인 실패시 toast
                    Toast.makeText(Login.this, "로그인을 다시 실행해주세요!", Toast.LENGTH_LONG).show();
                }
            }
        });

        tv_gojoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_join = new Intent(Login.this, Join.class);
                startActivity(it_join);
            }
        });
    }
}