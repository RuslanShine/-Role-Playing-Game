import Merchant.Goods

interface Seller {
    fun sell(goods: Goods?): String?
}