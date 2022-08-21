package com.example.webviewtest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.webkit.WebViewClient
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

//PopupMenu.OnMenuItemClickListener

class MainActivity : AppCompatActivity() ,View.OnClickListener {

    private var data2:String?="baidu"
    private lateinit var popup:PopupMenu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivity.setBackgroundResource(R.drawable.repeat_bg)
        search_button.setOnClickListener(this)
        select_button.setOnClickListener(this)
        //supportActionBar?.hide()
        openOptionsMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        //menu?.setGroupCheckable(R.menu.main,true,true)
        menuInflater.inflate(R.menu.menu2,menu)

        return true

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.search_button->{
                val data1 = search_text.text?.toString()
                val intent = Intent(this,web_activity::class.java)
                intent.putExtra("data1",data1)
                intent.putExtra("data2",data2)
                startActivity(intent)

            }
//            R.id.select_button->{
//                popup= PopupMenu(this,p0)
//                popup.menuInflater.inflate(R.menu.main,popup.menu)
//                popup.setOnMenuItemClickListener(this)
//                popup.show()
//            }
        }
    }

//    override fun onMenuItemClick(item: MenuItem?): Boolean {
//        when(item?.itemId){
//            R.id.single_menu_01->{
//                data2="baidu"
//
//            }
//            R.id.single_menu_02->{
//                data2="bing"
//
//            }
//        }
//        return true
//
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu2_baidu->{
                data2="baidu"
            }
            R.id.menu2_bing->{
                data2="bing"
            }
        }
        return when(item.itemId){
            R.id.menu2_baidu,R.id.menu2_bing->{
                item.isChecked=!item.isChecked
                Toast.makeText(this,"${item.title}",Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }








}