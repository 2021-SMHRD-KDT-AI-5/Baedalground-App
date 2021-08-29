package com.example.a3project;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.MotionEffect;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Fragment6 extends Fragment implements View.OnClickListener,MyOnClickListener {

    ImageView img_1,img_2,img_3,img_4,img_5,img_6;
    TextView edt_addr;
    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6;
    String cate;

    RequestQueue requestQueue;

    StringRequest stringRequest_category, stringRequest_readdress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_6, container, false);

        img_1 = view.findViewById(R.id.img_1);
        img_2 = view.findViewById(R.id.img_2);
        img_3 = view.findViewById(R.id.img_3);
        img_4 = view.findViewById(R.id.img_4);
        img_5 = view.findViewById(R.id.img_5);
        img_6 = view.findViewById(R.id.img_6);

        edt_addr = view.findViewById(R.id.edt_addr);

        btn_1 = view.findViewById(R.id.btn_cate_1);
        btn_2 = view.findViewById(R.id.btn_cate_2);
        btn_3 = view.findViewById(R.id.btn_cate_3);
        btn_4 = view.findViewById(R.id.btn_cate_4);
        btn_5 = view.findViewById(R.id.btn_cate_5);
        btn_6 = view.findViewById(R.id.btn_cate_6);

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);

        String addr = "";


        SharedPreferences spf = getActivity().getApplicationContext().getSharedPreferences("basic", Context.MODE_PRIVATE);
        addr = spf.getString("address", "");


        Intent it_f = getActivity().getIntent();
        String id = it_f.getStringExtra("id");

        if(id != null){
            edt_addr.setText(addr);
        } else {
            edt_addr.setText("로그인이 필요합니다");
        }
        requestQueue = Volley.newRequestQueue(getContext());

        edt_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!spf.getString("address", "").isEmpty()){
//                    CustomDialog customDialog = new CustomDialog(getContext());
//                    customDialog.show();
                    update_addr_dialog update_address_dialog = new update_addr_dialog(getContext());
                    update_address_dialog.show();
                }
            }
        });



        stringRequest_category = new StringRequest(Request.Method.POST, "http://172.30.1.54:8090/p3_server/CateServlet", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response!=null && !response.equals("")) {
                    JSONObject jsonObject_cate_list = null;
                    try {
                        jsonObject_cate_list = new JSONObject(response);

                        Intent it_cate_search = new Intent(getContext(), Menu_category.class);
                        it_cate_search.putExtra("cate_menu", jsonObject_cate_list.toString());
                        startActivity(it_cate_search);


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

                params.put("category", cate);

                return params;
            }
        };

        stringRequest_readdress = new StringRequest(Request.Method.POST, "http://172.30.1.54:8090/p3_server/ReaddressServlet", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    spf.edit().putString("address", response);
                    spf.edit().commit();
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
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

//                params.put("reAddr", readdress);

                return params;
            }
        };

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_cate_1){
            cate = "한식";
        }
        else if(v.getId()==R.id.btn_cate_2){
            cate = "일식";
        }
        else if(v.getId()==R.id.btn_cate_3){
            cate = "중식";
        }
        else if(v.getId()==R.id.btn_cate_4){
            cate = "양식";
        }
        else if(v.getId()==R.id.btn_cate_5){
            cate = "치킨";
        }
        else if(v.getId()==R.id.btn_cate_6) {
            cate = "디저트";
        }

        requestQueue.add(stringRequest_category);

    }


    @Override
    public void myOnClick() {
        edt_addr.setText("로그인이 필요합니다.");
    }

}