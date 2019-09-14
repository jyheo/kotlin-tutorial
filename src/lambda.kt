// data class MyClass(val a: Int, val b: String)
fun main()
{
    val array = arrayOf(MyClass(10, "class1"), MyClass(20, "class2"), MyClass(30, "class3"))
    println(array.filter({ c: MyClass -> c.a < 15 })) // [MyClass(a=10, b=class1)]
    // 람다가 함수 인자의 마지막으로 사용되면, 함수의 괄호 밖으로 뺄 수 있음.
    array.filter() { c: MyClass -> c.a < 15}
    // 람다가 함수의 유일한 인자이면, 함수의 괄호를 생략 할 수 있음.
    array.filter { c: MyClass -> c.a < 15 }
    // 인자 타입 생략 가능한 경우
    array.filter { c -> c.a < 15 }
    // 티폴트 매개변수 이름으로 it를 사용할 수 있음
    array.filter { it.a < 15 } // 일반적으로 많이 사용되는 형태
}
