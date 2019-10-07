data class Student(val name: String, val age: Int)

fun main() {
    val data = listOf(Student("Jun", 21), Student("James", 25),
        Student("Tom", 21), Student("Jane", 23), Student("John", 23))
    println(data.filter { it.age >= 22 }) // [Student(name=James, age=25), Student(name=Jane, age=23), Student(name=John, age=23)]
    println(data.map { it.age - 20 }) // [1, 5, 1, 3, 3]
    println(data.filter { it.age >= 22}.map(Student::name)) // [James, Jane, John]
    // data.filter { it.age >= 22}.map { it.name }
    println(data.groupBy { it.age })
    // {21=[Student(name=Jun, age=21), Student(name=Tom, age=21)], 25=[Student(name=James, age=25)], 23=[Student(name=Jane, age=23), Student(name=John, age=23)]}

    val words = arrayOf("hello", "hi", "hot", "apple", "orange", "access", "order", "about")
    println(words.groupBy { it.first() })
    // {h=[hello, hi, hot], a=[apple, access, about], o=[orange, order]}
}
