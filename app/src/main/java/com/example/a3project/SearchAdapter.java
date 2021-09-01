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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private ArrayList<JSONArray> sData;

    //  아이템뷰 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView simg;
        TextView stv1, stv2;

        ViewHolder(View itemView){
            super(itemView);

            simg = itemView.findViewById(R.id.simg);
            stv1 = itemView.findViewById(R.id.stv1);
            stv2 = itemView.findViewById(R.id.stv2);
        }
    }

    SearchAdapter(ArrayList<JSONArray> list){
        sData = list;
    }

    //  아이템 뷰 위해 뷰홀더 객체 생성해서 리턴
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.rv_search_item, parent, false);
        SearchAdapter.ViewHolder vh = new SearchAdapter.ViewHolder(view);

        return vh;
    }

    //  position 해당하는 데이터 아이템뷰에 표시
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String sp_name, sp_addr;
        try {
            sp_name = sData.get(position).get(1).toString();
            sp_addr = sData.get(position).get(2).toString();

            holder.simg.setImageResource(R.drawable.chicken);
            holder.stv1.setText(sp_name);
            holder.stv2.setText(sp_addr);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return sData.size();
    }


}
