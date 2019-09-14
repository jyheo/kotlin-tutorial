fun test(arg : Any)
{
    when(arg) {
        10 -> println("10")
        in 0..9 -> println("0 ≤ x ≤ 9")
        is String -> println("Hello, $arg")
        !in 0..100 -> println("x < 0 and x > 100")
        else -> {
            println("unknown")
        }
    }
}

fun test2(arg : Int) : Int
{
    return when(arg) {
        in 0..9 -> 10
        in 10..19 -> 20
        in 20..29 -> 30
        else -> 40
    }
}

fun main()
{
    test(10) // 10
    test(5) // 0 ≤ x ≤ 9
    test("String") // Hello, String
    test(200) // x < 0 and x > 100
    test(50) // unknown

    println(test2(15)) // 20
    println(test2(50)) // 40
}