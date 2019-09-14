fun main() {
    val version = "1.3.50"
    val javaStyle = "Hello, Kotlin " + version + "!"
    val kotlinStyle = "Hello, Kotlin ${version}!"
    println(javaStyle)
    println(kotlinStyle)

    val num = 10
    println("val num is equal to 10: ${num == 10}.")
}