package com.miv_dev.shopping_list.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.miv_dev.shopping_list.R
import com.miv_dev.shopping_list.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel
    private lateinit var adapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        vm = ViewModelProvider(this).get(MainViewModel::class.java)
        vm.shopList.observe(this) {
            adapter.shopList = it
        }
    }

    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.ry_shop_list)

        adapter = ShopListAdapter()
        rvShopList.adapter = adapter
        adapter.onShopItemLongClickListener = {
            vm.changeEnableState(it)
        }
        adapter.onShopItemClickListener = {
            Log.d("MainActivity", "setupRecyclerView: Edit $it")
        }

        setupSwipeListener(rvShopList)
    }

    private fun setupSwipeListener(rvShopList: RecyclerView?) {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val item = adapter.shopList[viewHolder.adapterPosition]
                vm.deleteShopItem(item)
            }

        }).attachToRecyclerView(rvShopList)
    }

}
