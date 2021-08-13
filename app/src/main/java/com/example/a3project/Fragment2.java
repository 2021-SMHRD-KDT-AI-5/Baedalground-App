package com.example.a3project;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;



public class Fragment2 extends Fragment {
    CheckBox cb_1,cb_2,cb_3,cb_4;
    TextView tv_test;
    String resultText = "";
    Button btn_test2;
    RadioButton rb_1,rb_2,rb_3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //fragment2 주석추가
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        cb_1 = view.findViewById(R.id.cb_1);
        cb_2 = view.findViewById(R.id.cb_2);
        cb_3 = view.findViewById(R.id.cb_3);
        cb_4 = view.findViewById(R.id.cb_4);
        rb_1 = view.findViewById(R.id.rb_1);
        rb_2 = view.findViewById(R.id.rb_2);
        rb_3 = view.findViewById(R.id.rb_3);
        btn_test2 = view.findViewById(R.id.btn_test2);


        cb_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });
        cb_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });
        cb_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });
        cb_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });
        btn_test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_test.setText("체크항목: " + Checked(view));
            }
        });





        return view;
    }
    public String Checked(View v) { // 체크되었을 때 동작할 메소드 구현
        resultText = "";
        // 체크되었을 때 값을 저장할 스트링 값
        if (cb_1.isChecked()) {
            resultText += "10대";  // cb_1이 체크되있다면
        }
        if (cb_2.isChecked()) {
            resultText += "20대";
        }
        if (cb_3.isChecked()) {
            resultText += "30대";
        }
        if (cb_4.isChecked()) {
            resultText += "40대";
        }
        if (rb_1.isChecked()){
            resultText += "남자";  // rb_1이 체크되있다면
        }
        if (rb_2.isChecked()){
            resultText += "여자";
        }
        if (rb_3.isChecked()){
            resultText += "상관없음";
        }

        return resultText; // 체크된 값 리턴
    }

}
