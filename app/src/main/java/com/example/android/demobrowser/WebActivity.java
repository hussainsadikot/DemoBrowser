package com.example.android.demobrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {
   public String x="";
   public WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            x= bundle.getString("URL");

        }

        if(!(x.contains("https://"))){
            x = "https://"+x;
        }
        else if(!(x.contains(".com"))){
            x =x+".com";
        }
        webView = findViewById(R.id.webview);

        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(x);

    }
}
