fun interface doInterface { // SAM
    fun doIt()
}

fun doSomething(di: doInterface) = di.doIt()

fun main() {
    doSomething(object : doInterface { // java-like
        override fun doIt() {
            println("Java-like way")
        }
    })
    
    doSomething( doInterface { println("SAM") } )
    doSomething { println("SAM 1") }
    val doi = { println("SAM 2") }
    doSomething(doi)
}