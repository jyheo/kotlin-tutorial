class ConstEx1 constructor(_prop: Int) { // primary constructor
    val prop: Int
    init {
        prop = _prop
    }
}

class ConstEx2 constructor(_prop: Int) { // primary constructor
    val prop = _prop
}

class ConstEx3 constructor(val prop: Int) // primary constructor

open class ConstEx4(val prop: Int, val prop2: Int = 0) { // primary constructor
    // secondary constructor
    constructor(_prop: Int, _prop2: Int, _prop3: Int) : this(_prop + _prop3, _prop2) {
        // do something else
    }
}

class ConstEx5(_prop: Int) : ConstEx4(_prop) // primary constructor
