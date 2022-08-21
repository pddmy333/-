package com.example.webviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.web_layout.*

class web_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_layout)
        supportActionBar?.hide()
        webView.settings.javaScriptEnabled=true
        val data1=intent.getStringExtra("data1")
        val data2=intent.getStringExtra("data2")
        webView.webViewClient= WebViewClient()

        when(data2){
            "baidu"->{
                if (data1 != null) {
                    if(data1.length==0)
                        webView.loadUrl("https://www.baidu.com/")
                    else{
                        webView.loadUrl("https://www.baidu.com/s?wd=${data1}")
                    }
                }

            }
            "bing"->{
                webView.loadUrl("https://cn.bing.com/search?q=${data1}")
            }
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView!!.canGoBack()) {
                webView.goBack()
                return true
            } else {
                finish()
                return false
            }
        }
    return false
    }
}