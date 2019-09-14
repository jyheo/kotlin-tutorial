fun main() {
    fun test(obj : Any) {
        if (obj is Int) println("obj is Int.")
        if (obj is String) println("obj is String.")
        // println((obj as String)) // Not Safe, ClassCastException for non-string
        println("print obj as string > ${(obj as? String ?: "")}") 
        // Elivs(?:) returns empty string if 'as' fail.
    }

    test(1)
    // obj is Int.
    // print obj as string > 
    test("strings")
    // obj is String.
    // print obj as string > strings
}