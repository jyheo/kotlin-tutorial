fun main() {
    val int_arrays = arrayOf(1, 2, 3, 4, 5)
    val str_arrays = arrayOfNulls<String>(5)
    val db_arrays = emptyArray<Double>()

    println(int_arrays[0]) // 배열 인덱스에 해당하는 값
    int_arrays[0] = 10 // val로 정의된 배열이지만, 배열 내의 값은 변경이 가능함
    println(int_arrays.get(0))

    for (s in str_arrays) { // for를 이용한 배열 순회
        print("$s, ")
    }
    println("")

    println(db_arrays.size) // 배열 크기
}