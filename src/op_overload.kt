data class Complex(val real: Double, val img: Double) {
    operator fun plus(other: Complex): Complex
            = Complex(real + other.real, img + other.img)
    override fun toString(): String = "$real+${img}i"
}

operator fun Complex.minus(other: Complex): Complex  // by function extension
        = Complex(real - other.real, img - other.img)

fun main() {
    val c1 = Complex(1.0, 1.0)
    val c2 = Complex(2.0, 2.0)
    val c3 = c1 + c2
    println(c3) // 3.0+3.0i
    println(c3 == Complex(3.0, 3.0)) // true
}