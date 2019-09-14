fun main()
{
    val array = arrayOf(1, 2, 3)
    val arrayList = array.toMutableList() // arrayListOf(1, 2, 3)
    val list = listOf(1, 2, 3)
    val mutableList = list.toMutableList() // mutableListOf(1, 2, 3)
    val set = setOf(1, 2, 3, 3)
    val mutableSet = set.toMutableSet() // mutableSetOf(1, 2, 3, 3)
    val map = mapOf("one" to 1, "two" to 2, "three" to 3)
    val mutableMap = map.toMutableMap() // mutableMapOf("one" to 1, "two" to 2, "three" to 3)

    arrayList.add(4)
    arrayList[arrayList.lastIndex]++
    mutableList.add(4)
    mutableList[0] = 0
    mutableSet.add(4)
    mutableMap["four"] = 4

    println(arrayList) // [1, 2, 3, 5]
    println(mutableList) // [1, 2, 3, 4]
    println(mutableSet) // [1, 2, 3, 4]
    println(mutableMap) // {one=1, two=2, three=3, four=4}
}