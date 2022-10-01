package com.miv_dev.shopping_list.domain

class GetShopItemByIdUseCase(
    private val shopListRepository: ShopListRepository
) {

    fun getShopItemById(id: Int): ShopItem = shopListRepository.getShopItemById(id)
}
