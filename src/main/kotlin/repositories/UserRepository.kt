package repositories

import data.User

object UserRepository {

    private val users = mutableListOf<User>()

    init {
        users.add(User(1504L, "BRIAN_BAYARRI", "abc123", "Brian", "Bayarri", 350.50, "2022/12/10"))
        users.add(User(2802L, "AHOZ", "lock_password", "Aylen", "Hoz", 200.50, "2021/01/11"))
        users.add(User(1510L, "Diegote", "@12345", "Diego", "Gonzales", 12.0, "2018/04/15"))
        users.add(User(2772L, "marrahenzo", "pelado123", "Hernán", "Marrapodi", 420.69, "2023/04/25"))
        users.add(User(2901L, "emaDev", "hulkVerde", "Emanuel", "Cisterna", 990.69, "2023/04/25"))
    }

    fun getById(userId: Long): User? {
        return users.first { it.id == userId }
    }

    fun login(): User {
        var usuario: String?
        var password: String?
        var usuarioEncontrado: User?
        var usuarioLogueado: User? = null;
        println("-----INICIO DE SESIÓN-----")
        do {
            print("Ingrese el usuario: ")
            usuario = readln()
            usuarioEncontrado = users.firstOrNull { it.nickName == usuario }
            if (usuarioEncontrado == null) {
                println("Usuario no encontrado")
                continue
            }

            print("Ingrese la contraseña: ")
            password = readln()
            if (usuarioEncontrado.password == password) {
                println("Usuario logueado correctamente.")
                usuarioLogueado = usuarioEncontrado
            } else
                println("Contraseña incorrecta.")
        } while (usuarioLogueado == null)
        return usuarioLogueado
    }
}