fun <T : Number> myPlus(op1: T, op2: T): String = "$op1$op2"

class MyGeneric<T : Number>(private val prop: T) {
    fun test(arg: T): String {
        println(arg)
        return myPlus(prop, arg)
    }
}

fun main() {
    println(myPlus(1, 1)) // 11
    println(myPlus(1.0, 1.0)) // 1.01.0
    // we can omit <Int> because of kotlin's type inference
    println(MyGeneric(10).test(10)) // 10
                                               // 1010
}