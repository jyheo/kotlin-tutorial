fun main() {
    
    val r1 = "hello".let {
        println("$it")
        it.length        
    }
    
    val r2 = "hello".run {
        println("$this")
        length
    }
    
    val r3 = "hello".also {
        println("$it")
    }.length
    
    println("$r1, $r2, $r3")
    
}