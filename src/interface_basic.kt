interface ClickListener {
    fun onClick()
    fun onTouch() = println("touched") // default implementation
}

class View(private val id: Int) : ClickListener {
    override fun onClick() = println("clicked $id")
}

fun main() {
    val v = View(10)
    v.onClick() // clicked 10
    v.onTouch() // touched
}
