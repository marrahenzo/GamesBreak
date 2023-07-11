package com.teamanotador.gamesbreak.repositories

import com.teamanotador.gamesbreak.data.User

object UserRepository {

    private val users = mutableListOf<User>()

    init {
        users.add(
            User(
                1504L,
                "BRIAN_BAYARRI",
                "abc123",
                "Brian",
                "Bayarri",
                350.50,
                "2022/12/10",
                "https://images.pexels.com/photos/16808856/pexels-photo-16808856/free-photo-of-dark-photo-of-a-mans-head.jpeg"
            )
        )
        users.add(
            User(
                2802L,
                "AHOZ",
                "lock_password",
                "Aylen",
                "Hoz",
                200.50,
                "2021/01/11",
                "https://images.pexels.com/photos/16808856/pexels-photo-16808856/free-photo-of-dark-photo-of-a-mans-head.jpeg"
            )
        )
        users.add(
            User(
                1510L,
                "Diegote",
                "@12345",
                "Diego",
                "Gonzales",
                12.0,
                "2018/04/15",
                "https://images.pexels.com/photos/16808856/pexels-photo-16808856/free-photo-of-dark-photo-of-a-mans-head.jpeg"
            )
        )
        users.add(
            User(
                2772L,
                "marrahenzo",
                "pelado123",
                "Hernán",
                "Marrapodi",
                420.69,
                "2023/04/25",
                "https://avatars.githubusercontent.com/u/69157896?v=4"
            )
        )
        users.add(
            User(
                2901L,
                "emaDev",
                "hulkVerde",
                "Emanuel",
                "Cisterna",
                990.69,
                "2023/04/25",
                "https://avatars.githubusercontent.com/u/101511719?v=4"
            )
        )
        users.add(
            User(
                1234L,
                "lucho",
                "123456789",
                "Luciano",
                "Ferreiros",
                20000.50,
                "2023/07/07",
                "https://images.pexels.com/photos/16808856/pexels-photo-16808856/free-photo-of-dark-photo-of-a-mans-head.jpeg"
            )
        )
        users.add(
            User(
                9999L,
                "dios",
                "",
                "Dios",
                "Dios",
                999.99,
                "2023/07/11",
                "https://images.pexels.com/photos/16808856/pexels-photo-16808856/free-photo-of-dark-photo-of-a-mans-head.jpeg"
            )
        )
    }

    fun getUsers(): List<User> {
        return this.users
    }

    fun getById(userId: Long): User {
        return users.first { it.id == userId }
    }

//    fun login(): User {
//        var usuario: String?
//        var password: String?
//        var usuarioEncontrado: User?
//        var usuarioLogueado: User? = null
//        println("-----INICIO DE SESIÓN-----")
//        do {
//            print("Ingrese el usuario: ")
//            usuario = readln()
//            print("Ingrese la contraseña: ")
//            password = readln()
//
//            usuarioEncontrado = users.firstOrNull { it.nickName == usuario }
//            if (usuarioEncontrado?.password == password) {
//                println("Usuario logueado correctamente.")
//                usuarioLogueado = usuarioEncontrado
//            } else
//                println("Nombre de usuario o contraseña incorrecta.")
//        } while (usuarioLogueado == null)
//        return usuarioLogueado
//    }
}