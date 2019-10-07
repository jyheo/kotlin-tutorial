fun main() {
    val list = listOf("abc", "cde", "efg")

    println(list.flatMap { it.toList() }) // [a, b, c, c, d, e, e, f, g]
    println(list.flatMap { it.toList() }.toSet()) // [a, b, c, d, e, f, g]

    class Classes(val name: String, val students: List<String>)
    val classes = listOf(Classes("Cprog", listOf("james", "john", "greg")),
            Classes("OS", listOf("james", "john", "jane", "tom")),
            Classes("Net", listOf("john", "jane", "alex", "sam")) )
    // list all student
    println(classes.flatMap { it.students }.toSet().sorted())

}