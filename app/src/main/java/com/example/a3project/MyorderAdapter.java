package com.example.a3project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MyorderAdapter extends RecyclerView.Adapter<MyorderAdapter.ViewHolder> {

    private ArrayList<JSONArray> moData;

    //  아이템뷰 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder{
        
        TextView tv_ordereddate, tv_orderedname, tv_orderedprice;

        ViewHolder(View itemView){
            super(itemView);

            tv_ordereddate = itemView.findViewById(R.id.tv_ordereddate);
            tv_orderedname = itemView.findViewById(R.id.tv_orderedname);
            tv_orderedprice = itemView.findViewById(R.id.tv_orderedprice);
        }
    }

    MyorderAdapter(ArrayList<JSONArray> list){
        moData = list;
    }

    //  아이템 뷰 위해 뷰홀더 객체 생성해서 리턴
    @Override
    public MyorderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.rv_myorder_item, parent, false);
        MyorderAdapter.ViewHolder vh = new MyorderAdapter.ViewHolder(view);

        return vh;
    }

    //  position 해당하는 데이터 아이템뷰에 표시
    @Override
    public void onBindViewHolder(MyorderAdapter.ViewHolder holder, int position) {
        String tv_ordereddate, tv_orderedname, tv_orderedprice;
        try {
            tv_ordereddate = moData.get(position).get(0).toString().substring(0, 10);
            String[] temp_name = moData.get(position).get(1).toString().split("\"");
            tv_orderedname = temp_name[1];
            tv_orderedprice = moData.get(position).get(2).toString();

            holder.tv_ordereddate.setText(tv_ordereddate);
            holder.tv_orderedname.setText(tv_orderedname);
            holder.tv_orderedprice.setText(tv_orderedprice);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return moData.size();
    }
}
