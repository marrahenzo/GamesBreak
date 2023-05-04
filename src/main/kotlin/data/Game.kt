package data

data class Game(
    val id: Long,
    val name: String,
    val releaseDate: String,
    val genre: String,
    val price: Double,
    val permalink: String
){
    override fun toString(): String {
        return "Game(id=$id, name='$name', releaseDate='$releaseDate', genre='$genre', price=$price)"
    }
}