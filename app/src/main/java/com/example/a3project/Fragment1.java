package com.example.a3project;

import android.content.Intent;
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
import android.widget.EditText;
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
import java.util.Map;

public class Fragment1 extends Fragment {

    RequestQueue requestQueue;

    StringRequest stringRequest_search;
    StringRequest stringRequest_menu;

    String search_menu;

    EditText edt_select;
    Button btn_select;

    RecyclerView rv_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        edt_select = view.findViewById(R.id.edt_select);
        btn_select = view.findViewById(R.id.btn_select);

        rv_search = view.findViewById(R.id.rv_search);

        ArrayList<JSONArray> list = new ArrayList<>();
        ArrayList<JSONArray> list_menu = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(getContext());

        GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        stringRequest_search = new StringRequest(Request.Method.POST, "http://172.30.1.54:8090/p3_server/SearchServlet", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response!=null) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response);

                        for(int i=0; i<jsonObject.length(); i++){
                            list.add(jsonObject.getJSONArray(String.valueOf(i)));
                        }

                        rv_search.setLayoutManager(new LinearLayoutManager(getContext()));

                        SearchAdapter adapter = new SearchAdapter(list);
                        rv_search.setAdapter(adapter);

                        Log.d("test", jsonObject.getJSONArray("0").get(2).toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getContext(), "검색 실패...", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("search", edt_select.getText().toString());

                return params;
            }
        };

        stringRequest_menu = new StringRequest(Request.Method.POST, "http://172.30.1.54:8090/p3_server/MenuServlet", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response!=null) {
                    JSONObject jsonObject_menu = null;
                    try {
                        jsonObject_menu = new JSONObject(response);
                        jsonObject_menu.getJSONObject("menu_DTO");

                        Intent it_menu = new Intent(getActivity(), res_menu.class);

                        it_menu.putExtra("list_menu", jsonObject_menu.getJSONObject("menu_DTO").toString());
                        it_menu.putExtra("list_res_info", jsonObject_menu.getJSONObject("menu_info_DTO").toString());
//                        Log.d("res_info_test", jsonObject_menu.getJSONObject("menu_info_DTO").toString());
                        startActivity(it_menu);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getContext(), "검색 실패...", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
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

        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                requestQueue.add(stringRequest_search);
            }
        });

        rv_search.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View childView = rv.findChildViewUnder(e.getX(), e.getY());
                if(childView != null && gestureDetector.onTouchEvent(e)){
                    int currentPosition = rv.getChildAdapterPosition(childView);

                    JSONArray currentItem = list.get(currentPosition);

//                    검색 결과 클릭하면 메뉴정보 서버에서 알아오기

                    try {
                        search_menu = currentItem.get(2).toString();
                        Toast.makeText(getContext(), search_menu, Toast.LENGTH_SHORT).show();

                        requestQueue.add(stringRequest_menu);

                        return true;

                    } catch (JSONException jsonException) {
                        Toast.makeText(getContext(), "에러", Toast.LENGTH_SHORT).show();
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

        return view;

    }
}