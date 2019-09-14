fun main()
{
    val array = arrayOf("Hello", "This", "is", "Kotlin")

    for (a in array) // iterator of array
        print("$a ")
    println("") // Hello This is Kotlin

    for ((idx, a) in array.withIndex()) // with index
        print("($idx, $a) ")
    println("") // (0, Hello) (1, This) (2, is) (3, Kotlin)

    for (i in 1..10) // with range
        print("$i ")
    println("") // 1 2 3 4 5 6 7 8 9 10

    for (i in 'a'..'f') // with range
        print("$i ")
    println("") // a b c d e f

    for (i in 1..10 step 2)  // with range and step
        print("$i ")
    println("") // 1 3 5 7 9

    (10 downTo 1).forEach{  // with lambda
        print("$it ")
    }
    println("") // 10 9 8 7 6 5 4 3 2 1

    // when we use 'while'
    var i = 0
    while (i < 10) {
        i++
        print("$i ")
    }
    println("") // 1 2 3 4 5 6 7 8 9 10
}
