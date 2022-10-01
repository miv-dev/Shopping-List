package com.miv_dev.shopping_list.domain

class AddShopItemUseCase(
    private val shopListRepository: ShopListRepository
) {
    fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }
}
