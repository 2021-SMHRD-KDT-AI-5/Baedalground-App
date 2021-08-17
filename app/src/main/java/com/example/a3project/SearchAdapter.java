package com.example.a3project;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentViewHolder;

import java.util.List;

public class SearchAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private LayoutInflater inflate;
    private ViewHolder viewHolder;




    public SearchAdapter(List<String> list, Context context){
        this.list = list;
        this.context = context;
        this.inflate = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
            View view = convertView;
        // 아이템 내 View들을 저장할 Holder 생성


        // convertView는 생성된적이 없으면 Null을 반환, 최초생성시 Null이므로 최초생성인지 판단
        if (view == null) {
            // 최초생성 View인 경우, inflation -> ViewHolder 생성 -> 해당 View에 setTag 저장
            // 데이터 넣기
            convertView = inflate.inflate(R.layout.select_listview,null);

            viewHolder = new ViewHolder();
//            viewHolder.label = (TextView) convertView.findViewById(R.id.label);

            convertView.setTag(viewHolder);
        } else {
            // ConvertView가 이미 생성된적 있다면, 저장되어있는 Holder 가져오기
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // Holder 객체 내의 뷰(TextView,ImageView)를 세팅
//        viewHolder.label.setText(list.get(position));

        return convertView;
    }
    class ViewHolder{

    }

}