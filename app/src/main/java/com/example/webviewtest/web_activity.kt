package com.example.webviewtest

import android.graphics.Bitmap
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.web_layout.*


class web_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_layout)
        supportActionBar?.hide()
        val settings = webView.settings
        settings.javaScriptEnabled=true
        settings.javaScriptCanOpenWindowsAutomatically = true//js和android交互
        settings.allowFileAccess = true // 允许访问文件
        //settings.setAppCacheEnabled(true) //设置H5的缓存打开,默认关闭
        settings.useWideViewPort = true//设置webview自适应屏幕大小
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL//设置，可能的话使所有列的宽度不超过屏幕宽度
        settings.loadWithOverviewMode = true//设置webview自适应屏幕大小
        settings.domStorageEnabled = true//设置可以使用localStorage
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.domStorageEnabled = true
        val data1=intent.getStringExtra("data1")
        val data2=intent.getStringExtra("data2")
        //webView.webViewClient=WebViewClient()
        val mwebViewClient=object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val redirectUrl:String= request?.url.toString()
                if (redirectUrl.startsWith("http:")||redirectUrl.startsWith("https:")){
                    if (view != null) {
                        view.loadUrl(redirectUrl)

                    }
                    return true
                }
                //下面代码来自“https://www.jianshu.com/p/46c7e7a5bd20”用于拦截自定义协议。
                if(!redirectUrl.startsWith("http:")||!redirectUrl.startsWith("https:")){
                    return true
                }
                return super.shouldOverrideUrlLoading(view, request)
            }

        }
        webView.webViewClient=mwebViewClient
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
                if (data1!=null)
                {
                    if (data1.length==0)
                        webView.loadUrl("https://cn.bing.com/")
                    else {
                        webView.loadUrl("https://cn.bing.com/search?q=${data1}")

                    }
                }

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