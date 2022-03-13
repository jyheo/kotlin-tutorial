interface GetSetI {
    var prop: String // the implmentation class must be override this property
}

class GetSet(_prop: String) : GetSetI {
    override var prop: String = _prop
        set(value) {
            field = value.substringBefore('@')
        }
        get() = field.uppercase()
    var prop2: Int = 0
        private set // prop2 cannot be changed outside of this class
}

fun main() {
    val getset = GetSet("test@removed")
    println(getset.prop) // TEST@REMOVED
    getset.prop = "ok@removed"
    println(getset.prop) // OK
    // getset.prop2 = 10 // compiler error
}
