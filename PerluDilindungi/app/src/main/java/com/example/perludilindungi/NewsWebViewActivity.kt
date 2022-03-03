package com.example.perludilindungi

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi

class NewsWebViewActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_web_view)

        var webView = findViewById<WebView>(R.id.newsWebView)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true
            }
        }

        val bundle:Bundle? = intent.extras
        val newsUrl = bundle?.getString("url")
//        Log.d("Test", webView.toString())

        if (newsUrl != null) {
            webView.loadUrl(newsUrl)
        } else {
            val notEncodedHtml = "<html><body><h1>Failed loading URL</h1></body></html"
            val encodedHtml = Base64.encodeToString(notEncodedHtml.toByteArray(), Base64.NO_PADDING)
            webView.loadData(encodedHtml, "text/html", "base64")
        }
    }
}