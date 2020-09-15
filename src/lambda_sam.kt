fun doSomething(runnable: Runnable) = runnable.run()

fun main() {
    doSomething(object : Runnable { // java-like
        override fun run() {
            println("Java-like way")
        }
    })
    
    doSomething( Runnable { println("SAM") } )
    doSomething( { println("SAM 1") })
    val runnable = { println("SAM 2") }
    doSomething(runnable)
}