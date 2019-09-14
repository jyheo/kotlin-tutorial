fun main() {
    val intArrays = arrayOf(1, 2, 3, 4, 5)
    val strArrays = arrayOfNulls<String>(5)
    val dbArrays = emptyArray<Double>()

    println(intArrays[0]) // 배열 인덱스에 해당하는 값
    intArrays[0] = 10 // val로 정의된 배열이지만, 배열 내의 값은 변경이 가능함
    println(intArrays[0])

    for (s in strArrays) { // for를 이용한 배열 순회
        print("$s, ")
    }
    println("")

    println(dbArrays.size) // 배열 크기
}