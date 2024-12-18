package com.example.mobilebrowser

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BrowserPage : AppCompatActivity() {
    lateinit var mainTB: Toolbar
    lateinit var pageWV: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_browser_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mainTB = findViewById(R.id.mainTB)
        pageWV = findViewById(R.id.pageWV)
        setSupportActionBar(mainTB)
        title = "Мобильный браузер"
        pageWV.webViewClient = WebViewClient()
        val adress = intent.getStringExtra("adress")
        if (adress != null) {
            pageWV.loadUrl(adress)
        } else {
            pageWV.loadUrl("https://www.google.com") // В случае если адрес не передан
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.exitApp -> {
                finishAffinity()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}