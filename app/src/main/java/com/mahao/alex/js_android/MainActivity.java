package com.mahao.alex.js_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private EditText et;

    @SuppressLint("JavascriptInterface")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = ((EditText) findViewById(R.id.et));
        //加载页面
        webView = (WebView) findViewById(R.id.webView);
        //允许JavaScript执行
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("utf-8");

        // 添加一个对象, 让JS可以访问该对象的方法, 该对象中可以调用JS中的方法
        webView.addJavascriptInterface(new Object(), "obj");

        //找到Html文件，也可以用网络上的文件
        webView.loadUrl("file:///android_asset/index.html");
    }


    public void show2Html(View view){
        webView.loadUrl("javascript:show('"+et.getText().toString()+"')");
    }

    private  class Object {


        @JavascriptInterface
        public void intent2Activity(String activity){
            if(activity.equals("login")){
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        }




        @JavascriptInterface
        public void showDialog(String str){
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("消息")
                    .setMessage(str)
                    .setPositiveButton("确定",null)
                    .setNegativeButton("取消",null)
                    .create();

            dialog.show();
        }
    }
}

