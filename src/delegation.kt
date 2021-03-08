interface Base {
    fun print()
    fun printHello()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }
    override fun printHello() { println("Hello")}
}

class Derived(val b: Base) : Base by b {
    override fun print() {
        b.print()
        println("ok")
    }
}

fun main() {
    val b = BaseImpl(10)
    val d = Derived(b)
    d.print()
    d.printHello()
}