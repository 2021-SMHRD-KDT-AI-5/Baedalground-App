package com.example.a3project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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

public class write_review extends AppCompatActivity {
    RatingBar score_taste, score_amount, score_speed;
    EditText edt_review;
    Button btn_review_ok;

    RequestQueue requestQueue;
    StringRequest stringRequest_reviewup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        score_taste = findViewById(R.id.score_taste);
        score_amount = findViewById(R.id.score_amount);
        score_speed = findViewById(R.id.score_speed);
        edt_review = findViewById(R.id.edt_review);
        btn_review_ok = findViewById(R.id.btn_review_ok);

        score_taste.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
               float taste = score_taste.getRating();

            }
        });




        requestQueue = Volley.newRequestQueue(getApplicationContext());


        stringRequest_reviewup = new StringRequest(Request.Method.POST,
                "http://172.30.1.54:8090/p3_server/CommunityServlet",
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
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //접근제한자 리턴타입(맵형식)<키, 벨류>
                Map<String, String> params = new HashMap<>();
//                params.put("taste", score_taste.getRating());
//                params.put("amount", edt_restaurant.getText().toString());
//                params.put("speed", edt_time.getText().toString());
//                params.put("review", edt_location.getText().toString());
//                params.put("nick", tv_nick.getText().toString());
                return params;
            }
        };

        btn_review_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestQueue.add(stringRequest_reviewup);

                Intent it = new Intent();
                setResult(RESULT_OK, it);
                finish();

            }
        });

    }
}
