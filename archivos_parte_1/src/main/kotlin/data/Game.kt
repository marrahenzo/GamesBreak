package data

data class Game(
    val id: Long,
    val name: String,
    val releaseDate: String,
    val genre: String,
    val price: Double,
    val permalink: String
) {
    override fun toString(): String {
        return "$id - $name - $${String.format("%.2f", price)} - $genre - $releaseDate"
    }
}