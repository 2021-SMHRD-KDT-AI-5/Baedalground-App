package com.example.a3project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ToptenAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inf;
    private int layout;
    // 데이터 정보 서버에서 전송받아와서 변수 생성하기

    public ToptenAdapter(Context context, LayoutInflater inf, int layout) {
        this.context = context;
        this.inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
    }

    @Override
    public int getCount() {
        // return data.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        // return data.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){ // position == 0
            convertView = inf.inflate(layout, parent, false);
        }

        TextView tv_store = convertView.findViewById(R.id.tv_store);
        // tv_store.setText(data.get(position).getStore());

        TextView tv_menu = convertView.findViewById(R.id.tv_menu);
        // tv_menu.setText(data.get(position).getMenu());

        TextView tv_price = convertView.findViewById(R.id.tv_price);
        // tv_price.setText(data.get(position).getPrice());

        TextView tv_addr = convertView.findViewById(R.id.tv_addr);
        // tv_Addr.setText(data.get(position).getAddr());

        return convertView;
    }

}
