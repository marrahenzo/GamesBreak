package repositories

import data.User
import java.lang.reflect.Constructor

class Carrito(
        val importe: Double,
        var usuario: User,
        val detalle: PurchaseRepository
)


