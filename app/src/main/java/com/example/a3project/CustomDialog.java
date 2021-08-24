package com.example.a3project;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class CustomDialog extends Dialog {

    Context context;
    EditText edt_readdress;
    Button btn_submit;

    RequestQueue requestQueue;
    StringRequest stringRequest_re_addr;

    public CustomDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestQueue = Volley.newRequestQueue(context);

        WindowManager.LayoutParams raWindow = new WindowManager.LayoutParams();
        raWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        raWindow.dimAmount = 0.8f;
        getWindow().setAttributes(raWindow);

        setContentView(R.layout.custom_dialog);
        setCanceledOnTouchOutside(true);

        edt_readdress = findViewById(R.id.edt_re_address);
        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "서버로 데이터", Toast.LENGTH_SHORT).show();

            }
        });

//        stringRequest_re_addr = new StringRequest(Request.Method.POST, "http");


    }

}
