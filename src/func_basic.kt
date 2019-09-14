fun myFunc(arg1 : Int, arg2: String = "default", arg3: Int = 10)
{
    println("$arg1, $arg2, $arg3")
}

fun main()
{
    myFunc(1, "hello", 5) // 1, hello, 5
    myFunc(arg1 = 2) // 2, default, 10
    myFunc(2, arg3 = 5) // 2, default, 5
}