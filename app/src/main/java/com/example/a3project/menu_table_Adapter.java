package com.example.a3project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class menu_table_Adapter extends RecyclerView.Adapter<menu_table_Adapter.ViewHolder> {

    private JSONObject mData;
    private Context context;
    private RequestQueue queue;

    JSONObject order_add = new JSONObject();
    JSONObject odad = new JSONObject();

    int num = 0;

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_menu;
        TextView tv_menu_name, tv_menu_price;
        Button btn_menu_add;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_menu = itemView.findViewById(R.id.iv_menu);
            tv_menu_name = itemView.findViewById(R.id.tv_menu_name);
            tv_menu_price = itemView.findViewById(R.id.tv_menu_price);
            btn_menu_add = itemView.findViewById(R.id.btn_menu_add);
        }
    }

    menu_table_Adapter(JSONObject list, Context context){
        mData = list;
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public menu_table_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.rv_menu_item, parent, false);
        menu_table_Adapter.ViewHolder vh = new menu_table_Adapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(menu_table_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String menu_img, name, price;

        try {
            menu_img = mData.getJSONArray(String.valueOf(position)).get(1).toString();
            name = mData.getJSONArray(String.valueOf(position)).get(2).toString();
            price = mData.getJSONArray(String.valueOf(position)).get(3).toString();

            JSONObject temp = new JSONObject();
            JSONArray od_add = new JSONArray();
//            temp.put("temp", name);
            od_add.put("");
            od_add.put(menu_img);
            od_add.put(name);
            od_add.put(price);
//            temp.put(String.valueOf(position),od_add);
//            Log.d("temp", temp.toString());
//            od_add.put(temp);

            String imgUrl = "http://172.30.1.54:8090/p3_server/img/res_menu_img/"+menu_img+".png";
            ImageRequest imageRequest = new ImageRequest(imgUrl, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    holder.iv_menu.setImageBitmap(response);
                    holder.tv_menu_name.setText(name);
                    holder.tv_menu_price.setText(price);
                }
            }, 0, 0, Bitmap.Config.ARGB_8888,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context.getApplicationContext(), "menu list error", Toast.LENGTH_SHORT).show();
                        }
                    });

            queue.add(imageRequest);

            holder.btn_menu_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
//                        Log.d("od_add", temp.toString());
//                        odad.put(String.valueOf(num),od_add);
                        odad.put(String.valueOf(position),od_add);
                        Log.d("odad", odad.toString());
                        order_add.put(String.valueOf(position), odad);

                        SharedPreferences spf = context.getSharedPreferences("menu_item", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = spf.edit();

                        editor.putString("addItem", odad.toString());
                        editor.commit();
                        num++;

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
        return mData.length();
    }


}
