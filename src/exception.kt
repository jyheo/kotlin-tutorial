import java.lang.Integer.parseInt
import java.lang.NumberFormatException as NFE

fun main()
{
    val x = try {
        parseInt("10")
    } catch (e : NFE) {
        0
    }

    println(x)
}
