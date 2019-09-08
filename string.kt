fun main() {
    val version = "1.3.50"
    val java_style = "Hello, Kotlin " + version + "!"
    val kotlin_style = "Hello, Kotlin ${version}!"
    println(java_style)
    println(kotlin_style)

    val num = 10
    println("val num is equal to 10: ${num == 10}.")
}