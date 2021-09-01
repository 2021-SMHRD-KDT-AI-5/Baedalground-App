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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class fragment_main extends Fragment {
    ListView lv;
    Button btn_write;
    TextView tv_usernick, tv_host_nick;
    TextView tv_title;
    WebView wb_test;
    WebSettings wbSettings;

    RequestQueue requestQueue;
    StringRequest stringRequest_listup, StringRequest_listclick; // 게시글 리스트업

    RecyclerView rv_commu;
    GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }
    });


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containerC,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, containerC, false);
        lv = view.findViewById(R.id.lv);

        rv_commu = view.findViewById(R.id.rv_commu);
        ArrayList<JSONArray> data = new ArrayList<>();

        btn_write = view.findViewById(R.id.btn_write);

        tv_title = view.findViewById(R.id.tv_title);

        wb_test = view.findViewById(R.id.wb_test);
        wbSettings = wb_test.getSettings();
        wbSettings.setJavaScriptEnabled(true);
//        wbSettings.setLoadWithOverviewMode(true);
//        wbSettings.setUseWideViewPort(true);
        wbSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wbSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        wbSettings.setDomStorageEnabled(true);
        wb_test.loadUrl("http://172.30.1.54:8090/p3_server/markdown_save.jsp");

        tv_usernick = view.findViewById(R.id.tv_list_nick);

        SharedPreferences spf = getActivity().getApplicationContext().getSharedPreferences("basic", Context.MODE_PRIVATE);
        String nick = spf.getString("nick", "");
//        String id = spf.getString("id","");


        Intent it_c = getActivity().getIntent();
        String id = it_c.getStringExtra("id");
        if(id != null){
            tv_usernick.setText(nick + " 님 환영합니다.");
        }else {
            tv_usernick.setText("로그인이 필요합니다.");
//            btn_login_c.setVisibility(View.VISIBLE);
        }






        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wb_test.getVisibility()==View.GONE){
                    wb_test.setVisibility(View.VISIBLE);
                }else{
                    wb_test.setVisibility(View.GONE);
                }
            }
        });

//        String SERVER_URL = "http://172.30.1.54:8090/p3_server/CommunityListServlet?location=dddd";
        String SERVER_URL = "http://172.30.1.54:8090/p3_server/CommunityListServlet";

        stringRequest_listup = new StringRequest(Request.Method.POST, SERVER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null){

                    Log.d("commu test", response.toString());

                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response);

                        for(int i=0; i<jsonObject.length(); i++){
                            data.add(jsonObject.getJSONArray(String.valueOf(i)));

                        }

//                        Log.d("commu test", data.get(0).get(1).toString());
//                        Log.d("commu test", data.get(0).toString());
                        rv_commu.setLayoutManager(new LinearLayoutManager(getContext()));
                        Commu_list_Adapter adapter = new Commu_list_Adapter(data);
                        rv_commu.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(getContext(), "리스트업 실패...", Toast.LENGTH_SHORT).show();
                }
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                        Log.v("글목록", error.getMessage());
                Toast.makeText(getActivity(), "오류발생 >>" + error, Toast.LENGTH_SHORT).show();
            }
        });



        requestQueue.add(stringRequest_listup);

        rv_commu.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View childView = rv.findChildViewUnder(e.getX(), e.getY());
                if(childView != null && gestureDetector.onTouchEvent(e)){
                    int currentPosition = rv.getChildAdapterPosition(childView);

                    JSONArray currentItem = data.get(currentPosition);

                    Intent it_list_detail = new Intent(getContext(), listclick.class);
                    try {
                        it_list_detail.putExtra("id", data.get(currentPosition).get(4).toString());
                        it_list_detail.putExtra("title", data.get(currentPosition).get(0).toString());
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                    startActivity(it_list_detail);

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

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), write.class);
                startActivity(it);
            }
        });

        return view;




    }

}
