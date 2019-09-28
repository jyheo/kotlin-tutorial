//interface ClickListener {
//    fun onClick()
//}

object ClickListenerImpl : ClickListener {
    override fun onClick() = println("clicked")
}

fun setClickListener(listener: ClickListener) = listener.onClick()

class Touch() { // the number of Touch objects
    val objectNums : Int
        get() = num

    init {
        num++
    }

    companion object {
        var num : Int = 0
    }
}

fun main() {
    setClickListener(ClickListenerImpl)  // clicked
    setClickListener(object : ClickListener {  // clicked2
        override fun onClick() = println("clicked2")
    })
    Touch()
    Touch()
    println(Touch().objectNums) // 3
}
