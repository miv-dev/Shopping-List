package com.miv_dev.shopping_list.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miv_dev.shopping_list.R

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel
    private lateinit var adapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        vm = ViewModelProvider(this).get(MainViewModel::class.java)
        vm.shopList.observe(this) {
            adapter.submitList(it)
        }
        val fabAddShopItem = findViewById<FloatingActionButton>(R.id.fab_add_shop_item)
        fabAddShopItem.setOnClickListener {
            val intent = ShopItemActivity.newIntentAddItem(this)
            startActivity(intent)
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
            val intent = ShopItemActivity.newIntentEditItem(this, it.id)
            startActivity(intent)
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
                val item = adapter.currentList[viewHolder.adapterPosition]
                vm.deleteShopItem(item)
            }

        }).attachToRecyclerView(rvShopList)
    }

}
