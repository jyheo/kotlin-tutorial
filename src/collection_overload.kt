data class Three(var x: Int, var y: Int, var z: Int) : Iterable<Int> {
    operator fun get(idx: Int) : Int {
        return when(idx) {
            0 -> x
            1 -> y
            2 -> z
            else ->
                throw IndexOutOfBoundsException("Invalid index $idx")
        }
    }
    operator fun set(idx: Int, value: Int) {
        when(idx) {
            0 -> x = value
            1 -> y = value
            2 -> z = value
            else ->
                throw IndexOutOfBoundsException("Invalid index $idx")
        }
    }

    operator fun contains(value: Int) = (x == value || y == value || z == value)

    inner class MyIterator : Iterator<Int> {
        var curIdx = 0
        override fun next(): Int {
            val ret = this@Three[curIdx]
            curIdx++
            return ret
        }

        override fun hasNext(): Boolean {
            return curIdx <= 2
        }

    }

    override fun iterator(): Iterator<Int> = MyIterator()
}

fun main() {
    val three = Three(1, 2, 3)

    println("${three[0]}, ${three[1]}, ${three[2]}")
    println((3 in Three(1, 2, 3)))
    for (i in three)
        println(i)
}
