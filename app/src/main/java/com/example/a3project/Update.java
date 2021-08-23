package com.example.a3project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Update extends AppCompatActivity {

    EditText edt_pw2,update_pw,update_checkpw,update_address,update_nick;
    Button update_check,btn_finish;
    String id,temp_pw,temp_nick,temp_address,up_pw;

    RequestQueue requestQueue;
    StringRequest stringRequest_update;
    private StringRequest StringRequest_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edt_pw2 = findViewById(R.id.edt_pw2);
        update_pw = findViewById(R.id.update_pw);
        update_checkpw = findViewById(R.id.update_checkpw);
        update_address = findViewById(R.id.update_address);
        update_nick = findViewById(R.id.update_nick);
        update_check = findViewById(R.id.update_check);
        btn_finish = findViewById(R.id.btn_finish);


        update_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", update_pw.getText()+"//"+update_checkpw.getText());
                if(update_pw.getText().toString().equals(update_checkpw.getText().toString())){
                    Toast.makeText(Update.this, "비밀번호가 일치합니다.", Toast.LENGTH_LONG).show();
                    btn_finish.setClickable(true);
                }else{
                    // 다음으로 못넘어가게
                    Toast.makeText(Update.this, "비밀번호가 다릅니다!", Toast.LENGTH_LONG).show();
                    btn_finish.setClickable(false);
                }
            }
        });
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest_update = new StringRequest(Request.Method.POST, "http://172.30.1.3:8090/WebToon/UpdateServlet", new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                if (response!=null) {
                    JSONObject jsonObject_update = null;

                    try {
                        jsonObject_update = new JSONObject(response);

//                        for(int i=0; i<jsonObject_menu.length(); i++){
//                            list_menu.add(jsonObject_menu.getJSONArray(String.valueOf(i)));
//                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(Update.this, "검색 실패...", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                Intent it_update = getIntent();
                params.put("id", it_update.getStringExtra("id"));
                params.put("temp_pw", temp_pw);
                params.put("up_pw",up_pw);
                params.put("temp_nick",temp_nick);
                params.put("temp_address",temp_address);
                return params;
            }
        };
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue.add(stringRequest_update);
                temp_pw = edt_pw2.getText().toString();
                up_pw = update_pw.getText().toString();
                temp_nick = update_nick.getText().toString();
                temp_address = update_address.getText().toString();

//                Toast.makeText(getApplicationContext(), "실패...", Toast.LENGTH_SHORT).show();

            }
        });




    }
}
