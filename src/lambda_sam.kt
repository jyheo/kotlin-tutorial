fun doSomething(runnable: Runnable) = runnable.run()
fun doSomething2(runnable: Runnable) = runnable.run()

fun main() {
    doSomething( Runnable { println("SAM") } )

    doSomething(object : Runnable { // java
        override fun run() {
            println("Java way")
        }
    })

    val runnable = Runnable { println("SAM") }
    doSomething(runnable)
    doSomething2(runnable)
}
