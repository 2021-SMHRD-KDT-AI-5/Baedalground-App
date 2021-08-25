package com.example.a3project;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class fragment_review extends Fragment {
    ArrayList<reviewVO> review_data = new ArrayList<>();
    ListView lv_review;
    ImageView img_pp, img_star;
    Button btn_review;

    RequestQueue requestQueue;
    StringRequest stringRequest_reviewlist; // 게시글 리스트업


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containerC,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_4, containerC, false);
        lv_review = view.findViewById(R.id.lv_review);
        img_pp = view.findViewById(R.id.img_pp);
        img_star = view.findViewById(R.id.img_star);
        btn_review=view.findViewById(R.id.btn_review);


        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());


        String SERVER_URL = "http://172.30.1.54:8090/p3_server/ReviewServlet";

        stringRequest_reviewlist = new StringRequest(Request.Method.GET, SERVER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.v("글목록", response);

                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i=0; i<array.length(); i++){

                                JSONObject obj = array.getJSONObject(i);

                                review_data.add(new reviewVO(obj.getInt("taste"),obj.getInt("amount"),obj.getInt("speed"),
                                        obj.getString("review"), obj.getString("review_nick")));

                                reviewAdapter adapter = new reviewAdapter(getActivity().getApplicationContext(), R.layout.reviewlist, review_data);
                                //listView에 dapater 설정
                                lv_review.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Log.v("글목록", error.getMessage());
                        Toast.makeText(getActivity(), "오류발생>>"+error, Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(stringRequest_reviewlist);

        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), write_review.class);
                startActivity(it);
            }
        });

        return view;

    }

}