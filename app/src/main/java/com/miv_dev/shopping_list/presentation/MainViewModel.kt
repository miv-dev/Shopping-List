package com.miv_dev.shopping_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miv_dev.shopping_list.data.ShopListRepositoryImpl
import com.miv_dev.shopping_list.domain.DeleteShopItemUseCase
import com.miv_dev.shopping_list.domain.EditShopItemUseCase
import com.miv_dev.shopping_list.domain.GetShopListUseCase
import com.miv_dev.shopping_list.domain.ShopItem

class MainViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl


    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()


    fun changeEnableState(shopItem: ShopItem) {

        val newItem = shopItem.copy(enabled = !shopItem.enabled)

        editShopItemUseCase.editShopItem(newItem)
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }
}
