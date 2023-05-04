package repositories

import data.User

class Carrito(
    val importe: Double,
    var usuario: User,
    val detalle: PurchaseRepository
)


