package com.example.administrator.day15;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * java调用js
     */
    private Button mBtn1;
    /**
     * js调用java
     */
    private Button mBtn2;
    /**
     * 加载百度页面
     */
    private Button mBtn3;
    private WebView mWv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn1.setOnClickListener(this);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn2.setOnClickListener(this);
        mBtn3 = (Button) findViewById(R.id.btn3);
        mBtn3.setOnClickListener(this);
        mWv = (WebView) findViewById(R.id.wv);
        //获取html路径
        mWv.loadUrl("file:///android_asset/a.html");
        //获取管理器
        WebSettings settings = mWv.getSettings();
        //设置支持javascript
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWv.setWebChromeClient(new WebChromeClient());
       /* //调用了Android代码
        mWv.addJavascriptInterface(this,showAndroid());*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn1:
                mWv.loadUrl("javascript:show('我是Android传过来的代码')");
                break;
            case R.id.btn2:
                //调用了Android代码
                mWv.addJavascriptInterface(this,showAndroid());
                break;
            case R.id.btn3:
                mWv.loadUrl("https://www.baidu.com");
                break;
        }
    }
    @JavascriptInterface
    public String showAndroid(){
        Toast.makeText(this, "js调用了Android代码", Toast.LENGTH_SHORT).show();
        return null;
    }
}
