package com.miv_dev.shopping_list.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun getShopItemById(id: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}
