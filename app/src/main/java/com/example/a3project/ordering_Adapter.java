package com.example.a3project;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ordering_Adapter extends RecyclerView.Adapter<com.example.a3project.ordering_Adapter.ViewHolder>{

    private ArrayList<JSONArray> oData;
    private ordering_Adapter adapter;
    private Context context;

    //  아이템뷰 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_od_name, tv_od_price;
        Button btn_remove;

        ViewHolder(View itemView){
            super(itemView);

            tv_od_name = itemView.findViewById(R.id.tv_od_name);
//            Log.v("hhd","const : "+(tv_od_name==null)+"");
            tv_od_price = itemView.findViewById(R.id.tv_od_price);
            btn_remove = itemView.findViewById(R.id.btn_remove);
        }
    }

    ordering_Adapter(ArrayList<JSONArray> list,Context context){
        adapter = this;
        oData = list;
        this.context = context;
        Log.d("data_test", oData.toString());
    }

    //  아이템 뷰 위해 뷰홀더 객체 생성해서 리턴
//    @Override
//    public com.example.a3project.ordering_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Context context = parent.getContext();
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        View view = inflater.inflate(R.layout.rv_search_item, parent, false);
//        com.example.a3project.ordering_Adapter.ViewHolder vh = new com.example.a3project.ordering_Adapter.ViewHolder(view);
//        Log.v("hhd",(vh.tv_od_name==null)+"");
//
//        return vh;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.rv_order_ing_item, parent, false);
        com.example.a3project.ordering_Adapter.ViewHolder vh = new com.example.a3project.ordering_Adapter.ViewHolder(view);
//        Log.v("hhd",(vh.tv_od_name==null)+"");

        return vh;
    }

    //  position 해당하는 데이터 아이템뷰에 표시
    @Override
    public void onBindViewHolder(com.example.a3project.ordering_Adapter.ViewHolder holder, int position) {
        String op_name, op_price;
        ArrayList<String> ordered_add = new ArrayList<>();
        final  int position2 = position;
        try {
            op_name = oData.get(position).get(2).toString();
            op_price = oData.get(position).get(3).toString();

            holder.tv_od_name.setText(op_name);
            holder.tv_od_price.setText(op_price);
            holder.btn_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ordered_add.add(op_name);
//                    Log.d("add_list", ordered_add.toString());
                    Toast.makeText(v.getContext(), "remove test", Toast.LENGTH_SHORT).show();
                    Log.v("hhd",""+oData.size());
                    oData.remove(position2);
                    Log.v("hhd",""+oData.size());
                    adapter.notifyDataSetChanged();

                    // shared data remove
                    SharedPreferences spf = context.getSharedPreferences("menu_item", Context.MODE_PRIVATE);
                    String rs = spf.getString("addItem",null);
                    try {
                        JSONObject od = new JSONObject(rs);
                        od.remove(String.valueOf(position2));
                        Log.v("hhd", od.toString());

                        // shared data put again
                        SharedPreferences.Editor editor = spf.edit();
                        editor.putString("addItem", od.toString());
                        editor.commit();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return oData.size();
    }


}
