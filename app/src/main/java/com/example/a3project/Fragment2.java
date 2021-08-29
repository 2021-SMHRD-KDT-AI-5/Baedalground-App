package com.example.a3project;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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


public class Fragment2 extends Fragment {
//    CheckBox cb_1, cb_2, cb_3, cb_4;
    RadioGroup radiogroup_gender, radiogroup_age;
    RadioButton rb_1, rb_2, rb_3, cb_1, cb_2, cb_3, cb_4;
    EditText edt_myaddr;
    Button btn_top10, btn_condition;
    RecyclerView recyclerView;
    String age, gender, myaddr;
    TextView textView;

    Group gp_condition;

    RequestQueue requestQueue;
    StringRequest stringRequest_top10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //fragment2 주석추가
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        textView = view.findViewById(R.id.textView);

        radiogroup_age = (RadioGroup)view.findViewById(R.id.radioGroup_age);

        cb_1 = view.findViewById(R.id.cb_1);
        cb_2 = view.findViewById(R.id.cb_2);
        cb_3 = view.findViewById(R.id.cb_3);
        cb_4 = view.findViewById(R.id.cb_4);

//        CheckBox[] cb_gp = new CheckBox[4];
//        cb_gp[0] = cb_1;
//        cb_gp[1] = cb_2;
//        cb_gp[2] = cb_3;
//        cb_gp[3] = cb_4;

        radiogroup_age.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.cb_1){
                    age = cb_1.getText().toString();
                } else if (checkedId == R.id.cb_2) {
                    age = cb_2.getText().toString();
                } else if (checkedId == R.id.cb_3) {
                    age = cb_3.getText().toString();
                } else{
                    age = "40대";
                }
            }
        });



        radiogroup_gender = (RadioGroup)view.findViewById(R.id.radioGroup_gender);

        rb_1 = view.findViewById(R.id.rb_1);
        rb_2 = view.findViewById(R.id.rb_2);
        rb_3 = view.findViewById(R.id.rb_3);

        radiogroup_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_1){
                    gender = rb_1.getText().toString();
                } else if (checkedId == R.id.rb_2) {
                    gender = rb_2.getText().toString();
                } else{
                    gender = "";
                }
            }
        });

        ArrayList<JSONArray> list = new ArrayList<>();

        edt_myaddr = view.findViewById(R.id.edt_myaddr);

        recyclerView = view.findViewById(R.id.recyclerView);

        btn_top10 = view.findViewById(R.id.btn_top10);
        btn_condition = view.findViewById(R.id.btn_condition);

        gp_condition = view.findViewById(R.id.gp_condition);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        stringRequest_top10 = new StringRequest(Request.Method.POST, "http://172.30.1.54:8090/p3_server/ToptenServlet", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null){
                    JSONObject jsonObject = null;
                    try {
                        Log.d("탑텐 리스폰스", response.toString());
                        jsonObject = new JSONObject(response);

                        Log.d("탑텐 // ", jsonObject.get("0").toString());

                        for(int i=0; i<jsonObject.length(); i++){
                            list.add(jsonObject.getJSONArray(String.valueOf(i)));
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        ToptenAdapter adapter = new ToptenAdapter(list, getContext());
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                if(!age.equals("")){
                    params.put("age", age);
                }
                if(!gender.equals("")){
                    params.put("gender", gender);
                }
                if(!myaddr.equals("")){
                    params.put("myaddr", myaddr);
                }

                return params;
            }
        };

        btn_top10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edt_myaddr.getText().toString().equals("")) {
                    myaddr = "동";
                }else{
                    myaddr = edt_myaddr.getText().toString();
                }

                list.clear();
                requestQueue.add(stringRequest_top10);

                gp_condition.setVisibility(View.GONE);

            }
        });

        btn_condition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gp_condition.getVisibility()==View.GONE){
                    gp_condition.setVisibility(View.VISIBLE);
                }else{
                    gp_condition.setVisibility(View.GONE);
                }
            }
        });

    return view;
    }
}
