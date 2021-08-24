package com.example.a3project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Fragment4 extends Fragment {

    RecyclerView rv_ordered;
    Button btn_cancel, btn_bill;
    JSONObject arr;

    RequestQueue requestQueue;
    StringRequest stringRequest_ordered;

    ArrayList<JSONArray> order_list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_4, container, false);

        rv_ordered = view.findViewById(R.id.rv_ordered);
        btn_cancel = view.findViewById(R.id.btn_cancel);
        btn_bill = view.findViewById(R.id.btn_bill);

        requestQueue = Volley.newRequestQueue(getContext());

        SharedPreferences spf = getActivity().getApplicationContext().getSharedPreferences("menu_item", Context.MODE_PRIVATE);

//        Log.d("주문내역", spf.getString("addItem","없음"));
        try {
            arr = new JSONObject(spf.getString("addItem", "없음"));
//            Log.d("arr test", arr.toString());
            Iterator<String> it = arr.keys();

            while(it.hasNext()) {
                String key = it.next();
//                Log.d("arr list keys", key);
                order_list.add(arr.getJSONArray(key));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        rv_ordered.setLayoutManager(new LinearLayoutManager(getContext()));
        ordering_Adapter adapter = new ordering_Adapter(order_list,getContext());
//        Log.d("arr list test", order_list.toString());
        rv_ordered.setAdapter(adapter);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor= spf.edit();
                editor.remove("addItem");
                editor.commit();

                Intent it = new Intent(getContext(), MainActivity.class);
                startActivity(it);

                Log.d("주문취소", spf.getString("addItem","없음"));
            }
        });

        btn_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "결제 성공", Toast.LENGTH_SHORT).show();

                Log.d("ordered list", order_list.toString());

                requestQueue.add(stringRequest_ordered);

            }
        });

        stringRequest_ordered = new StringRequest(Request.Method.POST, "http://172.30.1.54:8090/p3_server/OrderedServlet", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response!=null) {
                    Intent it = new Intent(getContext(), MainActivity.class);
                    startActivity(it);
                }else{
                    Toast.makeText(getContext(), "검색 실패...", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
//                error.printStackTrace();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

//                params.put("id", );
//                params.put("res_name", );
//                params.put("menu_name", );
//                params.put("price", );

                return params;
            }
        };



        return view;
    }
}