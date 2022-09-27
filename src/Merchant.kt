class Merchant : Seller {
    override fun sell(goods: Goods?): String? {
        var result = ""
        if (goods == Goods.POTION) {
            result = "potion"
        }
        return result
    }

    enum class Goods {
        POTION
    }
}