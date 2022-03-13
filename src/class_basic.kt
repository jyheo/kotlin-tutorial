class Animal(val name: String)

class Person(val firstName: String, _lastName: String) {
    var lastName: String = _lastName
        get () = field.uppercase()
        set (value) {
            field = "[$value]"
        }
}

fun main() {
    val a = Animal("Dog")
    println("Animal: ${a.name}") // Animal: Dog
    val p = Person("Junyoung", "Heo")
    println("Name: ${p.firstName} ${p.lastName}") // Name: Junyoung HEO
    p.lastName = "HEO"
    println("Name: ${p.firstName} ${p.lastName}") // Name: Junyoung [HEO]
}
