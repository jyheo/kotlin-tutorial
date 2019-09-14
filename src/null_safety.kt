import java.lang.NullPointerException

fun testNull(arg : String?)
{
    println(arg?.toUpperCase()) // arg? return null if arg is null
    println(arg?.toUpperCase() ?: "-") // Elvis(?:) return right operand("-") if left operand is null
}

fun main()
{
    var nullable: String? = null
    var nonNullable: String = "nonNullable"

    // nonNullable = null // Compile Error

    testNull(nonNullable) // NONNULLABLE NONNULLABLE
    testNull(nullable) // null -

    // nullable.toUpperCase() // Compile Error
    nullable?.toUpperCase() // return null because nullable is null
    try {
        nullable!!.toUpperCase() // cause Exception!
    } catch (e : NullPointerException) {
        println("NullPointerException")
    }
}