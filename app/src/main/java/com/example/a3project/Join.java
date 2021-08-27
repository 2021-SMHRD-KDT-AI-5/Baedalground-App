package com.example.a3project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Join extends AppCompatActivity {

    EditText edt_joinid, edt_nick, edt_joinpw, edt_checkpw, edt_address, edt_age;
    CheckBox cb_man, cb_women;
    Button btn_check, btn_join;
    String gender = null;

    RequestQueue requestQueue;
    StringRequest stringRequest_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        edt_joinid = findViewById(R.id.edt_joinid);
        edt_nick = findViewById(R.id.edt_nick);
        edt_joinpw = findViewById(R.id.edt_joinpw);
        edt_checkpw = findViewById(R.id.edt_checkpw);
        edt_address = findViewById(R.id.edt_address);
        edt_age = findViewById(R.id.edt_age);
        btn_check = findViewById(R.id.btn_check);
        btn_join = findViewById(R.id.btn_setup);
        cb_man = findViewById(R.id.cb_man);
        cb_women = findViewById(R.id.cb_women);


        cb_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_man.isChecked()) {
                    cb_women.setChecked(false);
                    gender = "M";
                }
            }
        });

        cb_women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb_women.isChecked()){
                    cb_man.setChecked(false);
                    gender = "F";
                }
            }
        });


        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", edt_joinpw.getText()+"//"+edt_checkpw.getText());
                if(edt_joinpw.getText().toString().equals(edt_checkpw.getText().toString())){
                    Toast.makeText(Join.this, "비밀번호가 일치합니다.", Toast.LENGTH_LONG).show();
                    btn_join.setClickable(true);
                }else{
                    // 다음으로 못넘어가게
                    Toast.makeText(Join.this, "비밀번호가 다릅니다!", Toast.LENGTH_LONG).show();
                    btn_join.setClickable(false);
                }
            }
        });

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        stringRequest_join = new StringRequest(Request.Method.POST, "http://172.30.1.54:8090/p3_server/JoinServlet", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("1")) {
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "실패...", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                String id = edt_joinid.getText().toString();
                String nick = edt_nick.getText().toString();
                String pw = edt_joinpw.getText().toString();
                String address = edt_address.getText().toString();
                String age = edt_age.getText().toString();
                String cash = "0";

                params.put("id", id);
                params.put("nick", nick);
                params.put("pw", pw);
                params.put("address", address);
                params.put("age", age);
                params.put("gender", gender);
                params.put("cash", cash);

                return params;
            }
        };




        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue.add(stringRequest_join);
            }



        });


    }


}