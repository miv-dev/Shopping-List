package com.miv_dev.shopping_list.domain

class GetShopListUseCase(
    private val shopListRepository: ShopListRepository
)  {

    fun getShopList(): List<ShopItem> = shopListRepository.getShopList()
}
