package com.example.a3project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Commu_list_Adapter extends RecyclerView.Adapter<Commu_list_Adapter.ViewHolder> {

    private ArrayList<JSONArray> clData;

    //  아이템뷰 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_list_title, tv_commu_res, tv_commu_time, tv_commu_min, tv_commu_host_id;

        ViewHolder(View itemView){
            super(itemView);

            tv_list_title = itemView.findViewById(R.id.tv_list_title);
            tv_commu_res = itemView.findViewById(R.id.tv_commu_res);
            tv_commu_time = itemView.findViewById(R.id.tv_commu_time);
            tv_commu_min = itemView.findViewById(R.id.tv_commu_min);
            tv_commu_host_id = itemView.findViewById(R.id.tv_commu_host_id);
        }
    }

    Commu_list_Adapter(ArrayList<JSONArray> list){
        clData = list;
    }

    //  아이템 뷰 위해 뷰홀더 객체 생성해서 리턴
    @Override
    public Commu_list_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.rv_commu_item, parent, false);
        Commu_list_Adapter.ViewHolder vh = new Commu_list_Adapter.ViewHolder(view);

        return vh;
    }

    //  position 해당하는 데이터 아이템뷰에 표시
    @Override
    public void onBindViewHolder(Commu_list_Adapter.ViewHolder holder, int position) {
        String tv_list_title, tv_commu_res, tv_commu_time, tv_commu_min, tv_commu_host_id;
        try {
            tv_list_title = clData.get(position).get(0).toString();
            Log.d("adapter test", tv_list_title);
            tv_commu_res = clData.get(position).get(1).toString();
            tv_commu_time = clData.get(position).get(2).toString();
            tv_commu_min = clData.get(position).get(3).toString();
            tv_commu_host_id = clData.get(position).get(4).toString();

            holder.tv_list_title.setText(tv_list_title);
            holder.tv_commu_res.setText(tv_commu_res);
            holder.tv_commu_time.setText(tv_commu_time);
            holder.tv_commu_min.setText(tv_commu_min);
            holder.tv_commu_host_id.setText(tv_commu_host_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return clData.size();
    }
    
}
