fun main() {
    val str = with (StringBuilder()) {
        append("Hello, ")
        append("This ")
        append("is an example of ")
        append("lambda with.")
        toString()
        // "oops"
    } // with returns the last expression
    println(str)

    val str2 = StringBuilder().apply {
        append("Hello, ")
        append("This ")
        append("is an example of ")
        append("lambda with.")
    }.toString()  // apply returns StringBuilder
    println(str2)
}
