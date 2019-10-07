
fun main() {
    val nums = arrayOf(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5)

    println(nums.all { it is Int }) // true
    println(nums.all { it > 0 }) // false
    println(nums.any { it > 0 }) // true
    println(nums.count { it > 0 }) // 5
    println(nums.find { it > 0 }) // 1
}
