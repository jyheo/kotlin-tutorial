class NoInherit(val name: String)

// class Child2(_name: String) : NoInherit(_name) // Compile Error

open class Inherit(val name: String) {
    fun first() = println("first")
    open fun second() = println("second")
}

open class Child(_name: String) : Inherit(_name) {
    // override fun first() = println("override first") // Compile Error
    final override fun second() = println("override second")
    open fun third() = println("third")
}

class GrandChild : Child("") {
    // override fun second() = println("")  // Compile Error
    override fun third() = println("override third")
}

fun main() {
    val c = Child("child")
    c.first() // first
    c.second() // override second
    c.third() // third

    val g = GrandChild()
    g.first() // first
    g.second() // override second
    g.third() // override third
}