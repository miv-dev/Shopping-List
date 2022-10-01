package com.miv_dev.shopping_list.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.miv_dev.shopping_list.R
import com.miv_dev.shopping_list.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemVH>() {

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    class ShopItemVH(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvCount: TextView = view.findViewById(R.id.tv_count)
        val shopItemCard: MaterialCardView = view.findViewById(R.id.shop_item_card)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemVH {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_shop, parent, false)
        return ShopItemVH(view)
    }


    override fun onBindViewHolder(holder: ShopItemVH, position: Int) {
        val shopItem = shopList[position]
        holder.shopItemCard.isChecked = shopItem.enabled

        holder.tvName.text = shopItem.name
        holder.tvCount.text = "Count: ${shopItem.count}"

        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        holder.view.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
    }

    override fun getItemCount() = shopList.size


}
