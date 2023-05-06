import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun formatMonto(monto: Double?): String {
        return String.format("%.2f", monto)
    }

    fun mostrarDateComoCadena(fecha: Date): String {
        val formatoFecha = SimpleDateFormat("yyyy-MM-dd")
        return formatoFecha.format(fecha)
    }
}