package com.example.a3project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class joinlistSelect extends AppCompatActivity {
    Button btn_ok, btn_change_order, btn_cancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinlist_select);
        btn_cancle = findViewById(R.id.btn_cancle);
        btn_change_order = findViewById(R.id.btn_change_order);
        btn_ok = findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent();
                setResult(RESULT_OK, it);

                finish();
            }
        });
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent();
                setResult(RESULT_OK, it);

                finish();
            }
        });


    }
}