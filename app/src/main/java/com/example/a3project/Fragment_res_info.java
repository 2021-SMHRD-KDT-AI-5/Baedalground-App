package com.example.a3project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Fragment_res_info extends Fragment {

    TextView tv_res_info_name, tv_res_info_tel, tv_res_info_addr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_res_info, container, false);

        tv_res_info_name = view.findViewById(R.id.tv_res_info_name);
        tv_res_info_tel = view.findViewById(R.id.tv_res_info_tel);
        tv_res_info_addr = view.findViewById(R.id.tv_res_info_addr);

        Bundle bundle =  getArguments();
        try {
            JSONObject res_info_name = new JSONObject(bundle.getSerializable("list_res_info").toString());

            Log.d("res_info", res_info_name.getJSONArray("0").get(0).toString());

            tv_res_info_name.setText(res_info_name.getJSONArray("0").get(0).toString());
            tv_res_info_addr.setText(res_info_name.getJSONArray("0").get(1).toString());
            tv_res_info_tel.setText(res_info_name.getJSONArray("0").get(2).toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }



        return view;
    }
}