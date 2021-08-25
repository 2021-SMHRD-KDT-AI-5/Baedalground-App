package com.example.a3project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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


public class fragment_join extends Fragment {
    ArrayList<joincommuVO> join_data = new ArrayList<>();
    ListView join_list;
    TextView tv_nickname;

    RequestQueue requestQueue;
    StringRequest stringRequest_joinlistup; // 게시글 리스트업



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_join, container, false);
        tv_nickname= view.findViewById(R.id.tv_nickname);


        join_list = view.findViewById(R.id.join_list);


        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        SharedPreferences spf = getActivity().getApplicationContext().getSharedPreferences("basic", Context.MODE_PRIVATE);
        String nick = spf.getString("nick", "");
        tv_nickname.setText(nick);

        String SERVER_URL = "http://172.30.1.54:8090/p3_server/JoinListServlet?my_nick="+nick;

        stringRequest_joinlistup = new StringRequest(Request.Method.GET, SERVER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("글목록", response);
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i=0; i<array.length(); i++){

                                JSONObject obj = array.getJSONObject(i);

                                join_data.add(new joincommuVO(obj.getString("title"),obj.getString("restaurant"),obj.getString("time"),obj.getString("min"),
                                        null,null,obj.getString("time") , obj.getString("time"), null));


                            }
                            joinAdater adapter = new joinAdater(getActivity().getApplicationContext(), R.layout.joinlist, join_data);
                            //listView에 dapater 설정
                            join_list.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("글목록", error.getMessage());
                        Toast.makeText(getActivity(), "오류발생>>"+error, Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(stringRequest_joinlistup);


        join_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getContext(), joinlistSelect.class);
                startActivity(it);
            }
        });

        return view;
    }
}