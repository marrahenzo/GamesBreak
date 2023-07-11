package com.teamanotador.gamesbreak.repositories

import com.teamanotador.gamesbreak.data.Purchase

object PurchaseRepository {

    private val purchases = mutableListOf<Purchase>()

    init {
        purchases.add(Purchase(1L, 1504L, 1L, 350.50, "2023/01/01"))
        purchases.add(Purchase(2L, 1504L, 4L, 100.75, "2023/02/02"))
        purchases.add(Purchase(3L, 2772L, 7L, 250.50, "2023/03/03"))
        purchases.add(Purchase(4L, 1504L, 10L, 50.00, "2023/04/04"))
        purchases.add(Purchase(5L, 1504L, 13L, 1350.15, "2023/05/05"))
        purchases.add(Purchase(6L, 2772L, 2L, 20.30, "2023/06/06"))
        purchases.add(Purchase(7L, 2802L, 9L, 450.75, "2023/07/07"))
        purchases.add(Purchase(8L, 2802L, 11L, 500.00, "2023/08/08"))
        purchases.add(Purchase(9L, 1510L, 3L, 350.50, "2023/09/09"))
        purchases.add(Purchase(10L, 1510L, 20L, 150.00, "2023/10/10"))
        purchases.add(Purchase(11L, 1510L, 12L, 150.00, "2023/11/11"))
        purchases.add(Purchase(12L, 1510L, 15L, 150.00, "2023/12/12"))
        purchases.add(Purchase(13L, 1234L, 13L, 150.00, "2023/12/13"))
        purchases.add(Purchase(14L, 1234L, 18L, 150.00, "2023/12/14"))
    }

    fun add(purchase: Purchase) {
        purchases.add(purchase)
    }

    fun get(): List<Purchase> {
        return purchases
    }

    fun obtenerUltimoId(): Long {
        return purchases.maxBy { it.id }.id
    }

    fun getByUserId(id: Long): List<Purchase> {
        var comprasDelUsuario = mutableListOf<Purchase>()
        for (purchase in purchases) {
            if (purchase.userId == id)
                comprasDelUsuario.add(purchase)
        }
        return comprasDelUsuario
    }
}