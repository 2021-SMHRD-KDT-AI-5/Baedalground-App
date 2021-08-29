package com.example.a3project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class listclick extends AppCompatActivity {

    Button btn_join;
    EditText edt_title;
    TextView tv_restaurant, tv_time, tv_nick, tv_mynick,edt_min, edt_content, edt_location;

    RequestQueue requestQueue;
    StringRequest stringRequest_listclick; // 게시글 리스트업
    StringRequest stringRequest_join; // 게시글 리스트업

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listclick);
        btn_join = findViewById(R.id.btn_setup);
        edt_title = findViewById(R.id.edt_title);
        edt_min = findViewById(R.id.edt_min);
        edt_content = findViewById(R.id.edt_content);
        edt_location = findViewById(R.id.edt_location);
        tv_restaurant = findViewById(R.id.edt_restaurant);
        tv_time = findViewById(R.id.edt_time);
        tv_nick = findViewById(R.id.tv_nick);
        tv_mynick = findViewById(R.id.tv_mynick);

        Intent itsecond = getIntent();
        String title = itsecond.getStringExtra("title");
        String host_nick = itsecond.getStringExtra("host_nick");

//        Log.v("hhd", "listclick : "+title+","+host_nick);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        stringRequest_listclick = new StringRequest(Request.Method.POST, "http://172.30.1.54:8090/p3_server/ListClickServlet", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject_list_ck = new JSONObject(response);

                    jsonObject_list_ck.getJSONArray("0").get(0);

                    Log.d("리스트 obj", jsonObject_list_ck.getJSONArray("0").get(0).toString());

                    edt_title.setText(jsonObject_list_ck.getJSONArray("0").get(0).toString());
                    tv_restaurant.setText(jsonObject_list_ck.getJSONArray("0").get(1).toString());
                    tv_time.setText(jsonObject_list_ck.getJSONArray("0").get(2).toString());
                    edt_min.setText(jsonObject_list_ck.getJSONArray("0").get(3).toString());
                    edt_location.setText(jsonObject_list_ck.getJSONArray("0").get(4).toString());
                    edt_content.setText(jsonObject_list_ck.getJSONArray("0").get(5).toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                Intent it = getIntent();
                String list_id = it.getStringExtra("id");
                String list_title = it.getStringExtra("title");

                params.put("id", list_id);
                params.put("title", list_title);

//                Log.d()

                return params;
            }
        };

//
//        stringRequest_listclick = new StringRequest(Request.Method.GET, SERVER_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        Log.v("글목록", response);
//                        try {
//                            JSONArray array = new JSONArray(response);
//
//                            for (int i = 0; i < array.length(); i++) {
//
//
//                                JSONObject obj = array.getJSONObject(0);
//
//                                edt_title.setText(obj.getString("title"));
//                                edt_min.setText(obj.getString("min"));
//                                edt_content.setText(obj.getString("content"));
//                                edt_location.setText(obj.getString("min"));
//                                tv_restaurant.setText(obj.getString("restaurant"));
//                                tv_time.setText(obj.getString("time"));
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
////                        Log.v("글목록", error.getMessage());
//                        Toast.makeText(getApplicationContext(), "오류발생>>" + error, Toast.LENGTH_SHORT).show();
//                    }
//                });

        requestQueue.add(stringRequest_listclick);

// 여기부터 코드에요
        stringRequest_join = new StringRequest(Request.Method.POST,
                "http://172.30.1.11:20000/p3_server/JoinServelt",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.v("커뮤니티실행", response);

                        //응답을 처리하는 메소드
                        if (response.equals("1")) {
                            Toast.makeText(getApplicationContext(),
                                    "게시글등록 완료!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 에러를 처리하는 메소드
                Toast.makeText(getApplicationContext(),
                        "게시글등록 실패!", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //접근제한자 리턴타입(맵형식)<키, 벨류>
                Map<String, String> params = new HashMap<>();
                params.put("title", edt_title.getText().toString());
                params.put("restaurant", tv_restaurant.getText().toString());
                params.put("time", tv_time.getText().toString());
                params.put("location", edt_location.getText().toString());
                params.put("min", edt_min.getText().toString());
                params.put("nick", tv_nick.getText().toString());
                params.put("content", edt_content.getText().toString());
                params.put("my_nick", tv_mynick.getText().toString());
                return params;
            }
        };

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestQueue.add(stringRequest_join);

                Intent it = new Intent();
                setResult(RESULT_OK, it);

                finish();

            }
        });
    }
}