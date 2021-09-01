package com.example.a3project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class update_addr_dialog extends Dialog {

    Context context;

    WebView wb_update_addr;
    WebSettings wbSettings;

    LinearLayout webpop;

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

        wb_update_addr.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {

                final WebView newWebView = new WebView(getContext());
                newWebView.getSettings().setJavaScriptEnabled(true);
                newWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                newWebView.getSettings().setSupportMultipleWindows(true);
                newWebView.getSettings().setAllowContentAccess(true);
                newWebView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                view.addView(newWebView);

                WebView.WebViewTransport transport = (WebView.WebViewTransport)resultMsg.obj;
                transport.setWebView(newWebView);
                resultMsg.sendToTarget();

                newWebView.setWebViewClient(new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        wb_update_addr.scrollTo(0, 0);
                        view.loadUrl(url);

                        return true;
                    }
                });

                newWebView.setWebChromeClient(new WebChromeClient(){
                    @Override
                    public void onCloseWindow(WebView window) {
                        super.onCloseWindow(window);

                        if(newWebView != null){
                            wb_update_addr.removeView((newWebView));
                        }
                    }
                });

                return true;
            }
        });



        wbSettings = wb_update_addr.getSettings();
        wbSettings.setJavaScriptEnabled(true);
        wbSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        wbSettings.setSupportMultipleWindows(true);
//        wbSettings.setLoadWithOverviewMode(true);
//        wbSettings.setUseWideViewPort(true);
        wbSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wbSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        wbSettings.setDomStorageEnabled(true);
        wb_update_addr.loadUrl("http://172.30.1.54:8090/p3_server/insert_xy.jsp");



    }
}