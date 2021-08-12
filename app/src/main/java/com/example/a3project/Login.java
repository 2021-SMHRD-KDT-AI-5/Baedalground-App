package com.example.a3project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText edt_id, edt_pw;
    Button btn_login;
    TextView tv_gojoin;

    RequestQueue requestQueue;
    StringRequest stringRequest_join;
    StringRequest stringRequest_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        btn_login = findViewById(R.id.btn_login);
        tv_gojoin = findViewById(R.id.tv_goJoin);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        stringRequest_login = new StringRequest(Request.Method.POST, "http://172.30.1.54:8090/p3_server/LoginServlet", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("1")) {
                    Intent it_login = new Intent(Login.this, MainActivity.class);
                    it_login.putExtra("id", edt_id.getText().toString());
                    Toast.makeText(getApplicationContext(), "로그인성공!", Toast.LENGTH_SHORT).show();
                    startActivity(it_login);
                }else{
                    Toast.makeText(getApplicationContext(), "실패...", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", edt_id.getText().toString());
                params.put("pw", edt_pw.getText().toString());

                return params;
            }
        };


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(edt_id.equals(member.getId()) && edt_pw.equals(member.getPw())){
////                    Intent it = new Intent(); // main 으로 이동할 Intent
////                    it.putExtra("id", R.id.edt_id);
//                }else{
//                    // 로그인 실패시 toast
//                    Toast.makeText(Login.this, "로그인을 다시 실행해주세요!", Toast.LENGTH_LONG).show();
//                }
                requestQueue.add(stringRequest_login);
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