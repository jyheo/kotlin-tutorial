// method extension to Collection<T>
fun <T> Collection<T>.join(separator: String = " ") : String
{
    val result = StringBuilder()
    for ((index, element) in withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    return result.toString()
}

fun main()
{
    println(listOf("1", "2", "3").join())
    println(arrayListOf(1, 2, 3).join(", "))
}
