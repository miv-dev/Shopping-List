package com.miv_dev.shopping_list.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.miv_dev.shopping_list.domain.ShopItem
import com.miv_dev.shopping_list.domain.ShopListRepository
import java.lang.RuntimeException

object ShopListRepositoryImpl : ShopListRepository {
    private val shopList = sortedSetOf<ShopItem>({ p0, p1 -> p0.id.compareTo(p1.id) })
    private val shopListLD = MutableLiveData<List<ShopItem>>()

    private var autoIncrementId = 0


    init {
        for (i in 0..100) {

            addShopItem(ShopItem("Name $i", i, false))
        }
    }


    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItemById(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun getShopItemById(id: Int): ShopItem {
        return shopList.find { it.id == id }
            ?: throw RuntimeException("Element with id $id not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateList() {
        shopListLD.value = shopList.toList()
    }
}
