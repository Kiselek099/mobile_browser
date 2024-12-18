package com.example.mobilebrowser

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem

import android.widget.GridView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var mainTB: Toolbar
    lateinit var pagesGV: GridView
    private val websites = listOf(
        Website(
            "Yandex",
            "https://yandex.ru",
            R.drawable.ya
        ),
        Website("Gismeteo", "https://www.gismeteo.ru", R.drawable.gis),
        Website("Google", "https://www.google.com", R.drawable.goo),
        Website("YouTube", "https://www.youtube.com", R.drawable.you)
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mainTB = findViewById(R.id.mainTB)
        pagesGV = findViewById(R.id.pagesGV)
        setSupportActionBar(mainTB)
        title = "Мобильный браузер"
        pagesGV = findViewById(R.id.pagesGV)
        pagesGV.adapter = WebsiteAdapter(this, websites)
        pagesGV.setOnItemClickListener { _, _, position, _ ->
            val website = websites[position]
            val intent = Intent(this, BrowserPage::class.java)
            intent.putExtra("adress", website.adress)
            startActivity(intent)
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