package com.example.a3project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Join extends AppCompatActivity {

    EditText edt_joinid, edt_joinpw, edt_checkpw, edt_address, edt_age;
    CheckBox cb_man, cb_women;
    Button btn_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        edt_joinid = findViewById(R.id.edt_joinid);
        edt_joinpw = findViewById(R.id.edt_joinpw);
        edt_checkpw = findViewById(R.id.edt_checkpw);
        edt_address = findViewById(R.id.edt_address);
        edt_age = findViewById(R.id.edt_age);
        cb_man = findViewById(R.id.cb_man);
        cb_women = findViewById(R.id.cb_woman);
        btn_join = findViewById(R.id.btn_join);

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberVO member = new memberVO();
                member.setId(edt_joinid.getText().toString());

                // 비밀번호 확인
                if(edt_joinpw.equals(edt_checkpw)){
                    member.setPw(edt_joinpw.getText().toString());
                }else{
                    Toast.makeText(Join.this, "비밀번호가 다릅니다!", Toast.LENGTH_LONG).show();
                }

                member.setAddress(edt_address.getText().toString());
                member.setAge(edt_age.getText().toString());

                // 체크박스 구분
                if(cb_man.isChecked()){
                    member.setGender(cb_man.getText().toString());
                }
                if(cb_women.isChecked()){
                    member.setGender(cb_women.getText().toString());
                }

                // vo에 값 셋팅 후 로그인 페이지 이동
                Intent it = new Intent();
                finish();
            }
        });
    }
}