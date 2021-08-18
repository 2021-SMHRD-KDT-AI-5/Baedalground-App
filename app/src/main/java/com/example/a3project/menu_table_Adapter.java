package com.example.a3project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class menu_table_Adapter extends RecyclerView.Adapter<menu_table_Adapter.ViewHolder> {

    private JSONObject mData;

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_menu;
        TextView tv_menu_name, tv_menu_price;
        Button btn_menu_add;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_menu = itemView.findViewById(R.id.iv_menu);
            tv_menu_name = itemView.findViewById(R.id.tv_menu_name);
            tv_menu_price = itemView.findViewById(R.id.tv_menu_price);
        }
    }

    menu_table_Adapter(JSONObject list){ mData = list; }

    @Override
    public menu_table_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.rv_menu_item, parent, false);
        menu_table_Adapter.ViewHolder vh = new menu_table_Adapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(menu_table_Adapter.ViewHolder holder, int position) {
        String menu_img, name, price;

        try {
            menu_img = mData.getJSONArray(String.valueOf(position)).get(1).toString();
            name = mData.getJSONArray(String.valueOf(position)).get(2).toString();
            price = mData.getJSONArray(String.valueOf(position)).get(3).toString();

            holder.iv_menu.setImageResource(R.drawable.chicken);
            holder.tv_menu_name.setText(name);
            holder.tv_menu_price.setText(price);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return mData.length();
    }


}
