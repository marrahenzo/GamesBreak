package com.teamanotador.gamesbreak.repositories

import com.teamanotador.gamesbreak.data.Game
import com.teamanotador.gamesbreak.enumerador.Genero
import com.teamanotador.gamesbreak.exceptions.JuegoInexistenteException

object GameRepository {

    private val games = mutableListOf<Game>()

    init {
        games.add(
            Game(
                1L,
                "Resident Evil 4",
                "Es un videojuego de acción-aventura del estilo survival horror. Por primera vez en la serie, el ambiente cerrado y lineal se deja a un lado para incluir escenarios más grandes y dinámicos, debido a que la historia se desarrolla en un poblado de España. El sistema de juego es diferente e innovador a comparación de las entregas anteriores de la serie.",
                "2005",
                Genero.SURVIVAL_HORROR,
                350.50,
                5.0,
                "https://media.vandal.net/m/67022/resident-evil-4-20193420315720_1.jpg",
                listOf()
            )
        )
        games.add(
            Game(
                2L,
                "Minecraft",
                "Minecraft es un juego de mundo abierto, y no tiene un fin claramente definido. Esto permite una gran libertad en cuanto a la elección de su forma de jugar. A pesar de ello, el juego posee un sistema que otorga logros por completar ciertas acciones. La cámara es en primera persona, aunque los jugadores tienen la posibilidad de cambiarla a una perspectiva de tercera persona en cualquier momento.",
                "2011",
                Genero.SANDBOX,
                150.75,
                5.0,
                "https://www.proandroid.com/wp-content/uploads/2016/12/minecraft.jpg",
                listOf()
            )
        )
        games.add(
            Game(
                3L,
                "FIFA 23",
                "FIFA 23 es un videojuego de simulación de fútbol publicado por Electronic Arts. Es la trigésima entrega de la serie FIFA desarrollada por EA Sports, y la última entrega bajo el estandarte de FIFA, y lanzada en todo el mundo el 30 de septiembre de 2022 para PC, Nintendo Switch, PlayStation 4, PlayStation 5, Xbox One, Xbox Series X/S y Google Stadia.",
                "2022",
                Genero.DEPORTE,
                1800.00,
                1.0,
                "https://sm.ign.com/ign_es/screenshot/default/image002-1_3te9.jpg",
                listOf()
            )
        )
        games.add(
            Game(
                4L,
                "Silent Hill 3",
                "Silent Hill 3 es un videojuego de terror japonés de acción-aventura, desarrollado por Team Silent y publicado por Konami. Representa la tercera parte de la serie Silent Hill y es una continuación directa de la trama del juego original. Fue lanzado para PlayStation 2 en Europa el 23 de mayo de 2003, en Japón el 3 de julio, en América del Norte el 5 de agosto y editado para PC por Konami Computer Entertainment Tokyo ese mismo año.",
                "2003",
                Genero.SURVIVAL_HORROR,
                70.00,
                4.5,
                "https://m.media-amazon.com/images/I/91AQS6et4QL._AC_UF894,1000_QL80_.jpg",
                listOf()
            )
        )
        games.add(
            Game(
                5L,
                "Call of Duty: Black Ops 2",
                "Call of Duty: Black Ops II es un videojuego de disparos en primera persona desarrollado por Treyarch y distribuido por Activision en 2012. Fue lanzado el 13 de noviembre de 2012 para PlayStation 3, Xbox 360 y Microsoft Windows, el 18 de noviembre de 2012 en América del Norte, y finalmente el 30 de noviembre de 2012 en Europa y Australia para la Wii U.",
                "2012",
                Genero.SHOOTER,
                600.00,
                4.0,
                "https://cdn.game.tv/game-tv-content/images_3/41666c262d8f858a9ebe021ac4dbadb5/41666c262d8f858a9ebe021ac4dbadb5/GameTile.jpg",
                listOf()
            )
        )
        games.add(
            Game(
                6L,
                "Mortal Kombat 11",
                "Mortal Kombat 11 (comúnmente abreviado como MK11) es un videojuego de lucha desarrollado por NetherRealm Studios y publicado por Warner Bros. Interactive Entertainment. Se ejecuta en una versión muy modificada de Unreal Engine 3, es la undécima entrega principal de la serie Mortal Kombat y una secuela de 2015 Mortal Kombat X. Anunciado en The Game Awards 2018, el juego se lanzó en Norteamérica y Europa el 23 de abril de 2019 para Microsoft Windows, Nintendo Switch, PlayStation 4 y Xbox One. La versión Switch se retrasó en Europa y se lanzó el 10 de mayo de 2019.",
                "2019",
                Genero.PELEA,
                315.50,
                4.7,
                "https://as01.epimg.net/meristation/imagenes/2019/01/10/noticias/1547149102_234157_1547149165_noticia_normal.jpg",
                listOf()
            )
        )
        games.add(
            Game(
                7L,
                "The Last of Us 2",
                "The Last of Us Part II es un videojuego de terror y de acción y aventuras de 2020 desarrollado por Naughty Dog y publicado por Sony Interactive Entertainment para PlayStation 4. Ambientado cinco años después de The Last of Us (2013), el juego se centra en dos personajes jugables en un Estados Unidos post-apocalíptico cuyas vidas se entrelazan: Ellie, que busca venganza después de sufrir una tragedia, y Abby, una soldado que se ve envuelta en un conflicto entre su milicia y un culto religioso. El juego se juega desde la perspectiva de la tercera persona y le permite al jugador luchar contra enemigos humanos e infectados con armas de fuego, armas improvisadas y sigilo.",
                "2020",
                Genero.SURVIVAL_HORROR,
                8500.00,
                3.5,
                "https://image.api.playstation.com/vulcan/img/rnd/202010/2618/w48z6bzefZPrRcJHc7L8SO66.png",
                listOf()
            )
        )
        games.add(
            Game(
                8L,
                "Gran Turismo 7",
                "Gran Turismo 7 es un videojuego de simulación de carreras de 2022 desarrollado por Polyphony Digital y publicado por Sony Interactive Entertainment. El juego es la octava entrega principal de la serie Gran Turismo. El juego se anunció el 11 de junio de 2020 en el evento de presentación de PlayStation 5 y se lanzó el 4 de marzo de 2022 para PlayStation 4 y PlayStation 5, lo que lo convierte en la primera entrega multiconsola de la serie.",
                "2022",
                Genero.CARRERAS,
                800.00,
                4.2,
                "https://edicion.parentesis.com:8082/imagesPosts/gt00.jpg",
                listOf()
            )
        )
        games.add(
            Game(
                9L,
                "Pokemon: Let's Go Pikachu!",
                "Pokémon Let's Go, Pikachu! y Let's Go, Eevee! son dos videojuegos RPG pertenecientes a la franquicia Pokémon desarrollados por Game Freak y publicados por Nintendo y The Pokémon Company para Nintendo Switch. Los juegos están basados en Pokémon Amarillo y cuentan con ciertas mecánicas tomadas de Pokémon GO. Son la primera entrega de la saga principal de Pokémon para Nintendo Switch, así como la primera para una consola de sobremesa.",
                "2018",
                Genero.AVENTURA,
                210.20,
                2.5,
                "https://juegosdigitalesargentina.com/files/images/productos/1639183282-pokemon-lets-go-pikachu-nintendo-switch.jpg",
                listOf()
            )
        )
        games.add(
            Game(
                10L,
                "GTA San Andreas",
                "Grand Theft Auto: San Andreas es un videojuego de acción-aventura de mundo abierto británico desarrollado por Rockstar North y publicado por Rockstar Games. Fue confirmado oficialmente por la mencionada Rockstar Games a principios de marzo de 2004, y su fecha de lanzamiento tentativa se programó para el 19 y 22 de octubre de ese año para América del Norte y Europa. Distribuido por Take-Two Interactive, San Andreas fue lanzado originalmente para PlayStation 2 el 26 de octubre de 2004, aunque posteriormente se publicaron distintas versiones, tanto para videoconsolas de sexta, séptima y octava generación, así como ordenadores, y teléfonos inteligentes.",
                "2004",
                Genero.ACCION,
                1800.00,
                4.8,
                "https://media.vandal.net/m/3903/2005610224436_1.jpg",
                listOf()
            )
        )
        games.add(
            Game(
                11L,
                "Mario Kart 8",
                "Mario Kart 8 es un videojuego de carreras desarrollado y publicado por Nintendo para la consola Wii U. Es la undécima entrega de la serie Mario Kart, octava en consolas de Nintendo. Fue lanzado en todo el mundo a finales de mayo de 2014, con 1,2 millones de copias vendidas en sus primeros cuatro días, convirtiéndose en el juego más rápidamente vendido de Wii U hasta la fecha.",
                "2014",
                Genero.CARRERAS,
                6500.00,
                4.6,
                "https://media.vandal.net/m/45256/mario-kart-8-deluxe-201742811181_45.jpg",
                listOf()
            )
        )
        games.add(
            Game(
                12L,
                "Dark Souls 3",
                "Dark Souls III es un videojuego de rol de acción desarrollado por FromSoftware y publicado por Bandai Namco Entertainment para PlayStation 4, Xbox One y Microsoft Windows. Es la tercera entrega en la saga Souls, Dark Souls III fue lanzado en Japón en marzo de 2016, y de manera mundial en abril del mismo año.",
                "2016",
                Genero.ACCION,
                50.75,
                4.9,
                "https://as.com/meristation/imagenes/2020/04/07/game_cover/136602131586253551.jpg",
                listOf()
            )
        )
        games.add(
            Game(
                13L,
                "God of War: Ragnarök",
                "God of War Ragnarök es un videojuego de acción y aventuras desarrollado por Santa Monica Studio y publicado por Sony Interactive Entertainment (SIE). Se lanzó en todo el mundo el 9 de noviembre de 2022 para PlayStation 4 y PlayStation 5, lo que marca el primer lanzamiento entre generaciones de la serie. Es la novena entrega de la saga de God of War, la novena cronológicamente y la secuela de God of War de 2018. Basado libremente en la mitología nórdica, el juego se desarrolla en la antigua Escandinavia y presenta al protagonista de la serie Kratos y su hijo adolescente Atreus. Concluyendo la era nórdica de la serie, el juego cubre el Ragnarök, el evento escatológico que es central en la mitología nórdica y se predijo que sucedería en el juego anterior después de que Kratos matara al dios Æsir Baldur.",
                "2022",
                Genero.AVENTURA,
                5350.00,
                4.0,
                "https://assets-prd.ignimgs.com/2022/07/25/9781506733494-1658716557072.jpg",
                listOf()
            )
        )
        games.add(
            Game(
                14L,
                "Kingdom Hearts II",
                "Kingdom Hearts II es un ARPG desarrollado por Square Enix y publicado por la misma empresa y Buena Vista Games para la consola PlayStation 2. Es la secuela directa de Kingdom Hearts: Chain of Memories. Apenas un mes después de su salida logró vender más de un millón de copias en Norteamérica, convirtiéndose en el segundo juego más vendido del año 2006.",
                "2005",
                Genero.RPG,
                150.00,
                5.0,
                "https://cdn.mobygames.com/covers/6198391-kingdom-hearts-ii-playstation-2-front-cover.jpg",
                listOf()
            )
        )
        games.add(
            Game(
                15L,
                "Bloodborne",
                "Bloodborne es un videojuego de rol de acción dirigido por Hidetaka Miyazaki, desarrollado por From Software y distribuido por JapanStudio y Sony Computer Entertainment para la plataforma de PlayStation 4. El videojuego sigue las acciones del personaje del jugador, el Cazador, a través de Yharnam, una ciudad ficticia de estilo victoriano, cuyos habitantes han sido afectados con una enfermedad de transmisión sanguínea anormal. Al despertar en Yharnam durante la noche de «la caza» tras recibir un tratamiento de la conocida «sangre milagrosa», el Cazador busca algo conocido solo como paleblood (\"sangre pálida\" en español) para terminar la cacería.",
                "2015",
                Genero.ACCION,
                10.00,
                5.0,
                "https://cdn.mobygames.com/covers/3620172-bloodborne-playstation-4-other.jpg",
                listOf()
            )
        )
        games.add(
            Game(
                16L,
                "Sekiro: Shadows Die Twice",
                "Sekiro: Shadows Die Twice es un videojuego de acción y aventura desarrollado por From Software y distribuido por Activision. El juego fue lanzado el 22 de marzo de 2019 en las plataformas PlayStation 4, Xbox One y Microsoft Windows. El juego sigue a un shinobi del Período Sengoku, conocido como Lobo, que intenta vengarse de un clan de samuráis que atacó y secuestró a su maestro.",
                "2019",
                Genero.ACCION,
                20.00,
                5.0,
                "https://cdn.mobygames.com/covers/8507229-sekiro-shadows-die-twice-xbox-one-front-cover.jpg",
                listOf()
            )
        )
        games.add(
            Game(
                17L,
                "Elden Ring",
                "Elden Ring es un videojuego de rol de acción desarrollado por FromSoftware y publicado por Bandai Namco Entertainment. El videojuego surge de una colaboración entre el director y diseñador Hidetaka Miyazaki y el novelista de fantasía George R. R. Martin. Fue lanzado a nivel mundial el 25 de febrero de 2022 para las plataformas Xbox One, Xbox Series X/S, Microsoft Windows, PlayStation 4, PlayStation 5. Actualmente se encuentra en desarrollo una expansión titulada Shadow of the Erdtree, anunciada en febrero de 2023.",
                "2022",
                Genero.ACCION,
                30.00,
                5.0,
                "https://cdn.mobygames.com/covers/10371758-elden-ring-xbox-one-front-cover.jpg",
                listOf()
            )
        )
        games.add(
            Game(
                18L,
                "Shadow of the Colossus",
                "Shadow of the Colossus es un videojuego de acción-aventura desarrollado y publicado por Sony Computer Entertainment (SCEI) para PlayStation 2. El juego fue publicado en Norteamérica y Japón en octubre de 2005, y posteriormente en Europa en febrero de 2006. Fue creado por el Estudio Internacional de Producción 1 de SCEI, el mismo grupo de desarrollo responsable del éxito de culto Ico.",
                "2005",
                Genero.AVENTURA,
                40.00,
                5.0,
                "https://cdn.mobygames.com/covers/4317156-shadow-of-the-colossus-playstation-2-front-cover.jpg",
                listOf()
            )
        )
        games.add(
            Game(
                19L,
                "Persona 5 Royal",
                "Persona 5 es un videojuego de rol desarrollado por Atlus para las consolas PlayStation 3 y PlayStation 4. Cronológicamente se trata del sexto videojuego de la saga Persona, que forma parte de la franquicia Megami Tensei. Fue distribuido por Atlus en Japón y América del Norte, y por Deep Silver en Europa y Australia, teniendo su lanzamiento en Japón el 15 de septiembre de 2016 y el 4 de abril de 2017 en el resto del mundo. Una versión extendida del juego con nuevo contenido, Persona 5 Royal, fue lanzada para PlayStation 4 en Japón en octubre de 2019 y en el resto del mundo en marzo de 2020. Esta misma versión saldría a la venta las plataformas PlayStation 5, Xbox One, Xbox Series X/S, Switch y Windows el 21 de octubre de 2022.",
                "2020",
                Genero.JRPG,
                50.00,
                5.0,
                "https://cdn.mobygames.com/covers/8842019-persona-5-royal-playstation-4-front-cover.jpg",
                listOf()
            )
        )
        games.add(
            Game(
                20L,
                "Portal 2",
                "Portal 2 es el segundo videojuego mezcla de videojuego de lógica y de disparos en primera persona, de la saga Portal. Fue desarrollado por Valve Corporation.",
                "2011",
                Genero.PUZZLE,
                60.00,
                5.0,
                "https://cdn.mobygames.com/covers/6296552-portal-2-windows-front-cover.jpg",
                listOf()
            )
        )
    }

    fun get(): List<Game> {
        return games
    }

    fun getById(id: Long): Game {
        try {
            return games.first { it.id == id }
        } catch (e: NoSuchElementException) {
            throw JuegoInexistenteException("El juego con id $id no existe")
        }
    }

    fun getGamesByNameContains(text: String): List<Game> {
        return games.filter { it.name.contains(text, ignoreCase = true) }
    }
}