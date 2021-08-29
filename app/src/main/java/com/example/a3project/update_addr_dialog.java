package com.example.a3project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class update_addr_dialog extends Dialog {

    Context context;

    WebView wb_update_addr;
    WebSettings wbSettings;

    public update_addr_dialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams raWindow = new WindowManager.LayoutParams();
        raWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        raWindow.dimAmount = 0.8f;
        getWindow().setAttributes(raWindow);

        setContentView(R.layout.update_addr_dialog);
        setCanceledOnTouchOutside(true);

        wb_update_addr = findViewById(R.id.wb_update_addr);

        wbSettings = wb_update_addr.getSettings();
        wbSettings.setJavaScriptEnabled(true);
//        wbSettings.setLoadWithOverviewMode(true);
//        wbSettings.setUseWideViewPort(true);
        wbSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wbSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        wbSettings.setDomStorageEnabled(true);
        wb_update_addr.loadUrl("http://172.30.1.54:8090/p3_server/insert_xy.jsp");

    }
}