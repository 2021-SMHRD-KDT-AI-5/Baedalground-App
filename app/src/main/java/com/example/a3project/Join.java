package com.example.a3project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Join extends AppCompatActivity {



    EditText edt_joinid, edt_joinpw, edt_checkpw, edt_address, edt_age;
    CheckBox cb_man, cb_women;
    Button btn_check, btn_join;
    String gender = null;

//    public void checked(View v){
//        cb_man = findViewById(R.id.cb_man);
//        cb_women = findViewById(R.id.cb_women);
//
//
//        cb_man.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (cb_man.isChecked()) {
//                    cb_women.setChecked(false);
//                    gender = cb_man.getText().toString();
//                }
//            }
//        });
//
//
//        cb_women.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(cb_women.isChecked()){
//                    cb_man.setChecked(false);
//                    gender = cb_women.getText().toString();
//                }
//            }
//        });
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        edt_joinid = findViewById(R.id.edt_joinid);
        edt_joinpw = findViewById(R.id.edt_joinpw);
        edt_checkpw = findViewById(R.id.edt_checkpw);
        edt_address = findViewById(R.id.edt_address);
        edt_age = findViewById(R.id.edt_age);
        btn_check = findViewById(R.id.btn_check);
        btn_join = findViewById(R.id.btn_join);

        cb_man = findViewById(R.id.cb_man);
        cb_women = findViewById(R.id.cb_women);


        cb_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_man.isChecked()) {
                    cb_women.setChecked(false);
                    gender = cb_man.getText().toString();
                }
            }
        });


        cb_women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb_women.isChecked()){
                    cb_man.setChecked(false);
                    gender = cb_women.getText().toString();
                }
            }
        });

        String id = edt_joinid.getText().toString();
        String pw = edt_joinpw.getText().toString();
        String address = edt_address.getText().toString();
        String age = edt_age.getText().toString();
//        String gender = check;

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String pw = edt_joinpw.getText().toString();
                Log.d("test", pw+"//"+edt_checkpw.getText());
                if(edt_joinpw.getText().toString().equals(edt_checkpw.getText().toString())){
                    Toast.makeText(Join.this, "비밀번호가 일치합니다.", Toast.LENGTH_LONG).show();
                    btn_join.setClickable(true);
                }else{
                    // 다음으로 못넘어가게
                    Toast.makeText(Join.this, "비밀번호가 다릅니다!", Toast.LENGTH_LONG).show();
                    btn_join.setClickable(false);
                }
            }
        });





        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = edt_joinid.getText().toString();
                String pw = edt_joinpw.getText().toString();
                String address = edt_address.getText().toString();
                String age = edt_age.getText().toString();

                memberVO member = new memberVO();

                member.setId(id);
                member.setPw(pw);

                member.setAddress(address);
                member.setAge(age);
                member.setGender(gender);

                // vo에 값 셋팅 후 로그인 페이지 이동
                Intent it = new Intent();
                finish();
            }
        });


    }


}