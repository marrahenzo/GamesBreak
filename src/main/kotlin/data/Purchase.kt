package data

data class Purchase(
    val id: Long,
    val userId: Long,
    val gameId: Long,
    val amount: Double,
    val createdDate: String,
)/*{
    companion object {
        var lastId:Long = 0
            private set
    }
    val id:Long =  ++lastId
}*/
