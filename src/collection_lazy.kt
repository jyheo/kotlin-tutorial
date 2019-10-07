import kotlin.system.measureTimeMillis

fun main() {
    val hugeNums = (1..10000000).toList()

    val elapsedTime = measureTimeMillis {
        hugeNums.map { it * it }.filter { it > 10 }.map { it * it }
    }

    val elapsedTime2 = measureTimeMillis {
        hugeNums.asSequence().map { it * it }.filter { it > 10 }.map { it * it }
    }

    val elapsedTime3 = measureTimeMillis {
        hugeNums.asSequence().map { it * it }.filter { it > 10 }.map { it * it }.toList()
    }

    println("$elapsedTime, $elapsedTime2, $elapsedTime3") // 1081, 15, 726
}
