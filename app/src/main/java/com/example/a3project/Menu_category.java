package com.example.a3project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Menu_category extends AppCompatActivity {

    RecyclerView rv_cate;
    RequestQueue requestQueue;
    StringRequest stringRequest_menu;
    String search_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_category);

        rv_cate = findViewById(R.id.rv_cate);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        rv_cate.setLayoutManager(new LinearLayoutManager(this));

        Intent it_cate_search = getIntent();

        it_cate_search.getStringExtra("cate_menu");
        Log.d("parcel test", it_cate_search.getStringExtra("cate_menu"));

        ArrayList<JSONArray> json_cate = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(it_cate_search.getStringExtra("cate_menu"));

            for(int i=0; i<jsonObject.length(); i++){
                json_cate.add((JSONArray) jsonObject.getJSONArray(String.valueOf(i)));
            }

            Bundle bundle = new Bundle();
            bundle.putSerializable("menu", json_cate.toString());


            SearchAdapter adapter = new SearchAdapter(json_cate);
            rv_cate.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        rv_cate.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View childView = rv.findChildViewUnder(e.getX(), e.getY());

                if(childView != null && gestureDetector.onTouchEvent(e)){
                    int currentPosition = rv.getChildAdapterPosition(childView);

                    JSONArray currentItem = json_cate.get(currentPosition);

//                    검색 결과 클릭하면 메뉴정보 서버에서 알아오기
                    try {
                        search_menu = currentItem.get(2).toString();

                        requestQueue.add(stringRequest_menu);

                        return true;

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        stringRequest_menu = new StringRequest(Request.Method.POST, "http://172.30.1.54:8090/p3_server/MenuServlet", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response!=null) {
                    JSONObject jsonObject_menu = null;
                    try {
                        jsonObject_menu = new JSONObject(response);
                        jsonObject_menu.getJSONObject("menu_DTO");

                        Intent it_menu = new Intent(Menu_category.this, res_menu.class);

                        it_menu.putExtra("list_menu", jsonObject_menu.getJSONObject("menu_DTO").toString());
                        it_menu.putExtra("list_res_info", jsonObject_menu.getJSONObject("menu_info_DTO").toString());
                        Log.d("res_info_test", jsonObject_menu.getJSONObject("menu_info_DTO").toString());
                        startActivity(it_menu);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "검색 실패...", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("search_menu", search_menu);

                return params;
            }
        };

    }
}