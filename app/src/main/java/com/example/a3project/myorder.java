package com.example.a3project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class myorder extends AppCompatActivity {

    RecyclerView rv_myordered;

    RequestQueue requestQueue;

    StringRequest stringRequest_myordered;

    ArrayList<JSONArray> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        rv_myordered = findViewById(R.id.rv_myordered);

        stringRequest_myordered = new StringRequest(Request.Method.POST, "http://172.30.1.54:8090/p3_server/MyorderedServlet", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    JSONObject jsonObject = null;

                    Log.d("My ordered", response);

                    try {
                        jsonObject = new JSONObject(response);
                        for(int i=0; i<jsonObject.length(); i++){
                            list.add(jsonObject.getJSONArray(String.valueOf(i)));
                        }

                        rv_myordered.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        MyorderAdapter adapter = new MyorderAdapter(list);
                        rv_myordered.setAdapter(adapter);
//                        list.clear();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }




                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                SharedPreferences spf = getSharedPreferences("basic", MODE_PRIVATE);
                String id = spf.getString("id", null);

                params.put("id", id);

                return params;
            }
        };

        list.clear();
        requestQueue.add(stringRequest_myordered);
    }
}