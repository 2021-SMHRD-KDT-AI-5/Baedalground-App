package com.example.a3project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class Fragment3 extends Fragment {
    ArrayList<commuVO> data = new ArrayList<>();
    ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_3, container, false);

        lv = view.findViewById(R.id.lv);


//        commuAdater adapter = new commuAdater(getApplicationContext(), R.layout.commulist, data);
        //listView에 dapater 설정
//        lv.setAdapter(adapter);

        return view;
    }
}