import java.lang.NullPointerException

fun testNull(arg : String?)
{
    println(arg?.uppercase()) // arg? return null if arg is null
    println(arg?.uppercase() ?: "-") // Elvis(?:) return right operand("-") if left operand is null
}

fun main()
{
    var nullable: String? = null
    var nonNullable: String = "nonNullable"

    // nonNullable = null // Compile Error

    testNull(nonNullable) // NONNULLABLE NONNULLABLE
    testNull(nullable) // null -

    // nullable.uppercase() // Compile Error
    nullable?.uppercase() // return null because nullable is null
    try {
        nullable!!.uppercase() // cause Exception!
    } catch (e : NullPointerException) {
        println("NullPointerException")
    }
}