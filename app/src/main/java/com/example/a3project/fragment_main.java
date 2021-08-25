package com.example.a3project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
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
    ArrayList<commuVO> data = new ArrayList<>();
    ListView lv;
    Button btn_write;
    TextView tv_usernick, tv_host_nick;
    TextView tv_title;
    WebView wb_test;
    WebSettings wbSettings;

    RequestQueue requestQueue;
    StringRequest stringRequest_listup; // 게시글 리스트업


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containerC,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, containerC, false);
        lv = view.findViewById(R.id.lv);
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
        wb_test.loadUrl("http://172.30.1.54:8090/p3_server/insert_xy.jsp");




        tv_usernick = view.findViewById(R.id.tv_list_nick);

        SharedPreferences spf = getActivity().getApplicationContext().getSharedPreferences("basic", Context.MODE_PRIVATE);
        String nick = spf.getString("nick", "");
        Toast.makeText(getContext(), nick, Toast.LENGTH_SHORT).show();
        tv_usernick.setText(nick);


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

        String SERVER_URL = "http://172.30.1.54:8090/p3_server/CommunityListServlet?location=dddd";

        stringRequest_listup = new StringRequest(Request.Method.GET, SERVER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.v("글목록", response);

                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject obj = array.getJSONObject(i);

                                data.add(new commuVO(obj.getString("title"), obj.getString("restaurant"), obj.getString("time"), obj.getString("min"),
                                        null, obj.getString("host_nick"), obj.getString("location"), null));

                            }

                            commuAdapter adapter = new commuAdapter(getActivity(), R.layout.commulist, data);
                            adapter.notifyDataSetChanged();
                            //listView에 dapater 설정
                            lv.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Log.v("글목록", error.getMessage());
                        Toast.makeText(getActivity(), "오류발생>>" + error, Toast.LENGTH_SHORT).show();
                    }
                });
        data.clear();
        requestQueue.add(stringRequest_listup);




        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title = data.get(i).getTitle();
                String host_nick = data.get(i).getHost_nick();
//                Log.v("hhd", title+","+host_nick);


                Intent it = new Intent(getContext(), listclick.class);
                it.putExtra("host_nick", host_nick);
                it.putExtra("title", title);
                startActivity(it);
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
