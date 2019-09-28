class Outer(var m : Int = 0) {
    inner class Inner {
        fun doSomething() {
            this@Outer.m++
            println("Inner.doSomething ${this@Outer.m}")
        }
    }

    class NoInner {
        fun doSomething() {
            println("NoInner.doSomething")
            // this@Outer  // cannot access Outer reference
        }
    }
}

fun main() {
    val inner = Outer().Inner()
    inner.doSomething() // Inner.doSomething 1
    val noInner = Outer.NoInner()
    noInner.doSomething() // NoInner.doSomething
}
