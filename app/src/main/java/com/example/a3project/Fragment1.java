package com.example.a3project;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment1 extends Fragment {

    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        editSearch = (EditText) view.findViewById(R.id.edt_select);
        listView = (ListView) view.findViewById(R.id.list_view);

        // 리스트를 생성한다.
        list = new ArrayList<String>();

        // 검색에 사용할 데이터을 미리 저장한다.
        settingList();

        arraylist = new ArrayList<String>();
        arraylist.addAll(list);



        RequestQueue requestQueue;

        StringRequest stringRequest_search;

        EditText edt_select;
        Button btn_select;

        edt_select = view.findViewById(R.id.edt_select);
        btn_select = view.findViewById(R.id.btn_select);

        requestQueue = Volley.newRequestQueue(getContext());


        stringRequest_search = new StringRequest(Request.Method.POST, "http://172.30.1.54:8090/p3_server/SearchServlet", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                byte[] byMsg = response.getBytes();
                Charset utf8Charset = Charset.forName("utf-8");
                CharBuffer charBuffer = utf8Charset.decode(ByteBuffer.wrap(byMsg));
                response = charBuffer.toString();

                if (response!=null) {
                    Toast.makeText(getContext(), "검색 성공!", Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject = null;

                    try {
                        jsonObject = new JSONObject(response);
                        jsonObject.getJSONArray("0").get(0);
                        for (int i = 0 ; i<jsonObject.length();i++){
                            list.add(String.valueOf(jsonObject.getJSONArray(String.valueOf(i)).get(3)));
                        }
                        adapter = new SearchAdapter(list,getContext());

                        // 리스트뷰에 아답터를 연결한다.
                        listView.setAdapter(adapter);


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



        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue.add(stringRequest_search);

            }
        });

        return view;

    }
    private void settingList() {
    }
}