package com.example.a3project;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ToptenAdapter extends RecyclerView.Adapter<ToptenAdapter.ViewHolder> {

    private ArrayList<JSONArray> ttData;
    private Context context;
    private RequestQueue queue;

    //  아이템뷰 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView storeimg;
        TextView tv_num, tv_store, tv_menu, tv_price, tv_addr;

        ViewHolder(View itemView){
            super(itemView);

            storeimg = itemView.findViewById(R.id.storeimg);

            tv_num = itemView.findViewById(R.id.tv_num);
            tv_store = itemView.findViewById(R.id.tv_store);
            tv_menu = itemView.findViewById(R.id.tv_menu);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_addr = itemView.findViewById(R.id.tv_addr);
            
        }
    }

    ToptenAdapter(ArrayList<JSONArray> list, Context context){
        ttData = list;
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    //  아이템 뷰 위해 뷰홀더 객체 생성해서 리턴
    @Override
    public ToptenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.rv_top10_item, parent, false);
        ToptenAdapter.ViewHolder vh = new ToptenAdapter.ViewHolder(view);

        return vh;
    }

    //  position 해당하는 데이터 아이템뷰에 표시
    @Override
    public void onBindViewHolder(ToptenAdapter.ViewHolder holder, int position) {
        String tv_num, tv_store, tv_menu, tv_price, tv_addr, menu_img;
        try {

            tv_num = ttData.get(position).get(0).toString();
            tv_store = ttData.get(position).get(1).toString();
            menu_img = ttData.get(position).get(2).toString();
            tv_menu = ttData.get(position).get(3).toString();
            tv_price = ttData.get(position).get(4).toString();
            tv_addr = ttData.get(position).get(5).toString();

            String imgUrl = "http://172.30.1.54:8090/p3_server/img/res_menu_img/"+menu_img+".png";

            ImageRequest imageRequest = new ImageRequest(imgUrl, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    holder.tv_num.setText(tv_num);
                    holder.storeimg.setImageBitmap(response);
                    holder.tv_store.setText(tv_store);
                    holder.tv_menu.setText(tv_menu);
                    holder.tv_price.setText(tv_price);
                    holder.tv_addr.setText(tv_addr);
                }
            }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            queue.add(imageRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return ttData.size();
    }

}
