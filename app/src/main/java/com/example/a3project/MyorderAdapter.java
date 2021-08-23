//package com.example.a3project;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//
//import java.util.ArrayList;
//
//public class MyorderAdapter extends BaseAdapter {
//    private Context context; // inflater 추출하기 위해서 받아온 Activity 정보
//    private LayoutInflater inf; // xml을 눈에 보이게 하는 도구
//    private int layout; // 템플릿
//    private ArrayList<> data; // 데이터
//
//    public MyorderAdapter(Context context,int layout,ArrayList<> data){
//        this.context = context;
//        this.inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        this.layout = layout;
//        this.data = data;
//    }
//    @Override
//    public int getCount() {
//        return data.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return data.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//
//        return convertView;
//    }
//}
