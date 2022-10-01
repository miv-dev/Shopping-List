package com.miv_dev.shopping_list.domain

class DeleteShopItemUseCase(
    private val shopListRepository: ShopListRepository
) {
    fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}
