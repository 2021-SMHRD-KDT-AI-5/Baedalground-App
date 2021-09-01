package com.example.a3project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class write extends AppCompatActivity {
    EditText edt_title, edt_restaurants, edt_time, edt_min, edt_location, edt_content;
    Button btn_search, btn_location, btn_setup;
    TextView tv_nick;

    RequestQueue requestQueue;
    StringRequest stringRequest_setup; // 게시글 등록

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        edt_title = findViewById(R.id.edt_title);
        edt_restaurants = findViewById(R.id.edt_restaurants);
        edt_time = findViewById(R.id.edt_time);
        edt_min = findViewById(R.id.edt_min);
        edt_location = findViewById(R.id.edt_location);
        edt_content = findViewById(R.id.edt_content);
        btn_search = findViewById(R.id.btn_search);
        btn_location = findViewById(R.id.btn_location);
        btn_setup = findViewById(R.id.btn_setup);
        tv_nick = findViewById(R.id.tv_nick);

        SharedPreferences spf = getApplicationContext().getSharedPreferences("basic", MODE_PRIVATE);
        String nick = spf.getString("nick", "");

        tv_nick.setText(nick);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        stringRequest_setup = new StringRequest(Request.Method.POST,
                "http://172.30.1.54:8090/p3_server/CommunityServlet",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //응답을 처리하는 메소드
                        if (response.equals("1")) {
                            Toast.makeText(getApplicationContext(),
                                    "게시글등록 완료!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),
                                    "게시글등록 실패!", Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 에러를 처리하는 메소드
                Toast.makeText(getApplicationContext(),
                        "게시글등록 에러!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //접근제한자 리턴타입(맵형식)<키, 벨류>
                Map<String, String> params = new HashMap<>();
                params.put("title", edt_title.getText().toString());
                params.put("restaurant", edt_restaurants.getText().toString());
                params.put("time", edt_time.getText().toString());
                params.put("location", edt_location.getText().toString());
                params.put("min", edt_min.getText().toString());
                params.put("nick", tv_nick.getText().toString());
                params.put("content", edt_content.getText().toString());
                return params;
            }
        };

        btn_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestQueue.add(stringRequest_setup);

                Intent it = new Intent(write.this, Commu.class);
                startActivity(it);
            }
        });
    }
}