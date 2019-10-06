data class Result(val result: Int, val status: String)
fun calcSomething(): Result {
    // computations
    return Result(404, "Not Found")
}

fun main() {
    val (num, str) = 1 to "one"
    println("$num, $str") // 1, one
    val collection = mapOf(1 to "one", 2 to "two")
    for ((a, b) in collection) {
        println("$a, $b")
    }
    val (result, status) = calcSomething()
    println("$result, $status") // 404, Not Found
}
