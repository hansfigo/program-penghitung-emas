open class Emas(
    open val perGram: Double = 0.0,
    open val min: Double = 0.0,
    open val max: Double = 0.0
) {
    // Definisikan metode untuk mengecek apakah ada NPWP
    open fun hasNPWP(input: String): Boolean {
        return input == "y"
    }
 
    // Definisikan metode untuk menghitung pajak
    fun hitungPajak(hasNPWP: Boolean, total: Double): Double {
        val pajak: Double = if (hasNPWP) 0.0015 else 0.03
        return total * pajak
    }
}
 
// Buat subkelas 'Transaksi' yang mewarisi dari 'Emas'
class Transaksi(perGram: Double, min: Double, max: Double, private val transaksi: String) : Emas(perGram, min, max) {
 
    // Definisikan properti untuk mendapatkan perGram, min, dan max dari kelas induk
    val getPerGram: Double
        get() = perGram
 
    val getMin: Double
        get() = min
 
    val getMax: Double
        get() = max
 
   // Definisikan method untuk menghitung TotalAkhir 
    protected fun hitungTotal(grams: Double, hasNPWP: Boolean): Map<String, Int> {
        val totalHarga: Double = perGram * grams
        val pajak: Double = hitungPajak(hasNPWP, totalHarga)
        return mapOf("hargaDiskon" to (totalHarga - pajak).toInt(), "totalHarga" to totalHarga.toInt(), "pajak" to pajak.toInt())
    }
 
    // Definisikan method untuk menampilkan output 
    public fun mulai(grams: Double, hasNPWP: Boolean) {
        val total: Map<String, Int> = hitungTotal(grams, hasNPWP)
 
        println("Harga $transaksi : ")
        println("Total : " + total["totalHarga"])
        println("Diskon : " + total["pajak"])
        println("Harga Diskon : " + total["hargaDiskon"])
 
    }
}
 
fun main(args: Array<String>) {
    //Validasi input user 
    if (args.isEmpty() || args.size != 3) {
        println("Penggunaan: java -jar <nama_file_jar>.jar <aksi(jual/beli)> <grams> <hasNPWP(y/n)>")
        return
    }
 
    if (args[2].toString() != "y" && args[2].toString() != "n") {
        println("Tidak ada perintah yang sesuai!")
        println("Penggunaan: java -jar <nama_file_jar>.jar <aksi(jual/beli)> <grams> <hasNPWP(y/n)>")
        return
    }
 
    //Inisialissasi Objek
    val emas = Emas()
    val beli = Transaksi(850000.00, 0.05, 80.0, "Beli")
    val jual = Transaksi(900000.00, 1.0, 100.0, "Jual") 

    try { 
        // Mendapatkan Aksi(jual/beli), jumlah gram, dan punya NPMW atau tidak dari Args
        val aksi: String = args[0].toString() 
        val grams: Double = args[1].toDouble()
        val hasNPWP: Boolean = emas.hasNPWP(args[2].toString())
    
        when (aksi) {
            "jual" -> {
                if (grams in jual.getMin..jual.getMax) jual.mulai(grams, hasNPWP) else print("Jumlah gram harus berada dalam range ${beli.getMin} hingga ${beli.getMax}.")
            }
            "beli" -> {
                if (grams in beli.getMin..beli.getMax) beli.mulai(grams, hasNPWP) else print("Jumlah gram harus berada dalam range ${beli.getMin} hingga ${beli.getMax}.")
            }
            else -> {
                print("Aksi tidak valid (jual/beli)")
            }
        }
    } catch(e: NumberFormatException){
        println("Error: Jumlah gram harus berupa angka.")
        return
    }
}