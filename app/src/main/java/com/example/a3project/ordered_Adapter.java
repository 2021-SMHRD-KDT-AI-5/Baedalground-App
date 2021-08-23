package com.example.a3project;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ordered_Adapter extends RecyclerView.Adapter<com.example.a3project.ordered_Adapter.ViewHolder>{

        private ArrayList<JSONArray> oData;

        //  아이템뷰 저장하는 뷰홀더 클래스
        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView tv_ordered_name, tv_ordered_price;
            Button btn_remove;
            
            ViewHolder(View itemView){
                super(itemView);

                tv_ordered_name = itemView.findViewById(R.id.tv_ordered_name);
                tv_ordered_price = itemView.findViewById(R.id.tv_ordered_price);
                btn_remove = itemView.findViewById(R.id.btn_remove);
            }
        }

        ordered_Adapter(ArrayList<JSONArray> list){
            oData = list;
        }

        //  아이템 뷰 위해 뷰홀더 객체 생성해서 리턴
        @Override
        public com.example.a3project.ordered_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.rv_search_item, parent, false);
            com.example.a3project.ordered_Adapter.ViewHolder vh = new com.example.a3project.ordered_Adapter.ViewHolder(view);

            return vh;
        }

        //  position 해당하는 데이터 아이템뷰에 표시
        @Override
        public void onBindViewHolder(com.example.a3project.ordered_Adapter.ViewHolder holder, int position) {
            String op_name, op_price;
            ArrayList<String> ordered_add = new ArrayList<>();

//            Bundle bundle = new Bundle();

            try {
                op_name = oData.get(position).get(2).toString();
                op_price = oData.get(position).get(3).toString();

                holder.tv_ordered_name.setText(op_name);
                holder.tv_ordered_price.setText(op_price);
                holder.btn_remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), op_name + " : " +  op_price, Toast.LENGTH_SHORT).show();
                        ordered_add.add(op_name);
                        Log.d("add_list", ordered_add.toString());
//                        Intent it = new Intent(v.getContext().getApplicationContext(), MainActivity.class);

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



