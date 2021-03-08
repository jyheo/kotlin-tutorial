import kotlin.properties.Delegates

class User(val map: MutableMap<String, Any?>) {
    val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }
    
    var name: String by map
    var age: Int     by map
    
    var nameOb: String by Delegates.observable("<no name>") {
        prop, old, new ->
        println("$old -> $new")
    }
}

fun main() {      
    val user = User(mutableMapOf(
        "name" to "John Doe",
        "age"  to 25
    ))
    
    println(user.lazyValue)  // computed!  Hello
    println(user.lazyValue)  // Hello
    
    println(user.name) // John Doe
	println(user.age)  // 25    
    user.age = 30
    println(user.map)  // {name=John Doe, age=30}
    
    user.nameOb = "first"   // <no name> -> first
    user.nameOb = "second"  // first -> second
}
