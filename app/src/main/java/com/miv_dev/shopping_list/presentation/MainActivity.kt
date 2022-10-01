package com.miv_dev.shopping_list.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.miv_dev.shopping_list.R

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel
    private lateinit var adapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        vm = ViewModelProvider(this).get(MainViewModel::class.java)
        vm.shopList.observe(this){
            adapter.shopList = it
        }
    }

    private fun setupRecyclerView(){
        val rvShopList = findViewById<RecyclerView>(R.id.ry_shop_list)

        adapter = ShopListAdapter()
        rvShopList.adapter = adapter

    }

}
