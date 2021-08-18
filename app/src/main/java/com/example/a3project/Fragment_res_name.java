package com.example.a3project;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_res_name extends Fragment {

    TextView tv_resmenu_name;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_res_name, container, false);

        tv_resmenu_name = view.findViewById(R.id.tv_resname_name);

        recyclerView = view.findViewById(R.id.menu_container);

        Bundle bundle = getArguments();

        try {
            JSONObject menu = new JSONObject(bundle.getSerializable("list_menu").toString());

            Log.d("test Log", menu.getJSONArray("0").get(0).toString());

            tv_resmenu_name.setText(menu.getJSONArray("0").get(0).toString());

            Toast.makeText(getContext(), tv_resmenu_name.getText().toString(), Toast.LENGTH_SHORT).show();

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            menu_table_Adapter adapter= new menu_table_Adapter(menu);
            recyclerView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}