# Functional Programming in Kotlin

---

## Functional Programming (FP) ?

* 함수형 프로그래밍이란?
    * 자료 처리를 수학적 함수의 계산으로 다루고
    * 상태 변경(changing state)과 가변 데이터(mutable data)를 쓰지 않는 프로그래밍 패러다임
* 명령형 프로그래밍(imperative programming)에서는 상태를 바꾸는 것을 강조하는 것과는 달리, 함수형 프로그래밍은 함수의 응용을 강조
* 1930년대에 계산가능성, 결정문제, 함수정의, 함수응용과 재귀를 연구하기 위해 개발된 형식체계인 람다 대수(lambda-calculus)에 근간을 두고 있다.
* 수학적 함수와 명령형 프로그래밍에서 사용되는 함수
    * 명령형의 함수는 프로그램의 상태의 값을 바꿀 수 있는 부작용이 생길 수 있다.
    * 이 때문에 명령형 함수는 참조 투명성이 없고, 같은 코드라도 실행되는 프로그램의 상태에 따라 다른 결과값을 낼 수 있다.
    * 반대로 함수형 코드에서는 함수의 출력값은 그 함수에 입력된 인수에만 의존하므로 인수 x에 같은 값을 넣고 함수 f를 호출하면 항상 f(x)라는 결과가 나온다.
    * 부작용을 제거하면 프로그램의 동작을 이해하고 예측하기가 훨씬 쉽게 된다.
    * 이것이 함수형 프로그래밍으로 개발하려는 핵심 동기중 하나이다.
* 함수형 프로그래밍 기본 요소
    * pure function: 부작용(side-effect)이 없는 함수, thread-safe하여 병렬 계산 용이
    * anonymous function: 익명 함수
        * 코틀린에서는 ```{x -> x * x}```
    * higher-order function: 고차(고계) 함수, 함수를 인자나 리턴으로 다루는 함수
        * 코틀린에서는 ```(1..10).map { it * it }```

출처: [위키피디아](https://ko.wikipedia.org/wiki/함수형_프로그래밍)

---

## Lambda

* 어원: 람다 대수
* 이름이 없는 함수
* 형식:  { 파리미터 -> 함수 바디 }
    * { x: Int, y: Int -> x + y }
    * 함수 바디에서 마지막 수식(expression)이 리턴 값이 됨
* Lambda에서 로컬 변수 참조
    * val, var 구분 없이 모든 로컬 변수 참조가 가능함
    
```kotlin
// src/lambda.kt
// data class MyClass(val a: Int, val b: String)

fun lambdaTest(a : (Int) -> Int) : Int {
    return a(10)
}

fun main()
{
    val sum = { x: Int, y: Int -> x + y }
    println(sum(10, 20))

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

    print(lambdaTest { it + 10 })

    val title = "Num:"
    val list = listOf(1, 2, 3, 4)
    list.forEach { println("$title $it") } // access title in outside of the lambda
}
```

---

## Collection filter, map, groupBy와 lambda

* 함수형 프로그래밍에서 Collection을 다루는 방법
* lambda가 편리하게 사용됨
* filter, map
* filter: 특정 조건을 만족하는 원소만 포함하는 Collection을 생성/리턴
    * filter에 주어진 람다를 모든 원소에 수행하여 true를 리턴하는 경우만 모음
    * filter에 넘겨주는 람다는 Boolean을 결과로 하는 수식이어야 함
* map: 모든 원소에 대해 특정 연산을 수행한 결과를 모아서 Collection을 생성/리턴
    * map에 주어진 람다를 모든 원소에 대해 수행하고 그 결과를 모음
* groupBy: 주어진 조건에 따라 Collection을 그룹으로 나눈 후 map을 생성/리턴

```kotlin
// src/collection_filtermap.kt
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
```

---

## Collection all, any, count, find와 lambda

* all, any, count, find
* all: 모든 원소가 특정 조건을 만족하면 true, 그렇지 않은면 false
* any: 한 원소라도 특정 조건을 만족하면 true, 그렇지 않으면 false
* count: 특정 조건을 만족하는 원소의 갯수를 리턴
* find: 특정 조건을 만족하는 가장 처음 원소를 리턴

```kotlin
// src/collection_allany.kt

fun main() {
    val nums = arrayOf(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5)

    println(nums.all { it is Int }) // true
    println(nums.all { it > 0 }) // false
    println(nums.any { it > 0 }) // true
    println(nums.count { it > 0 }) // 5
    println(nums.find { it > 0 }) // 1
}
```

---

## Collection flatMap과 lambda

* 중첩된 Collection을 하나의 리스트로 생성/리턴
* flatMap에 주어진 람다는 Iterable 객체를 리턴
    * 이 Iterable을 모두 연결하여 하나의 List로 만든다.
    * ```public inline fun <T, R> Iterable<T>.flatMap(transform: (T) → Iterable<R> ): List<R>```

```kotlin
// src/collection_flat.kt
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
```

---

## Collection asSequence

* filter, map 등의 함수를 부를 때, 바로 다른 리스트를 생성하게 됨
* 이런 함수를 매우 큰 데이터에 대해 여러번 이어서 사용하면 매우 느려질 것임
* 이런 경우 Sequence를 사용하면 
    * 리스트 생성을 최대한 늦추게 하는 방법임
    * 실제 리스트 생성 작업을 최대한 늦추기 때문에 lazy 연산이라고 보통 부름
* Sequence를 사용하여 연산을 끝낸 후에, 다시 toList()로 Collection을 바꾸어 사용함
* 참고: [generateSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/generate-sequence.html)

```kotlin
// src/collection_lazy.kt
import kotlin.system.measureTimeMillis

fun main() {
    val hugeNums = (1..10000000).toList()

    val elapsedTime = measureTimeMillis {
        hugeNums.map { it * it }.filter { it > 10 }.map { it * it }
    }

    val elapsedTime2 = measureTimeMillis {
        hugeNums.asSequence().map { it * it }.filter { it > 10 }.map { it * it }
    }

    val elapsedTime3 = measureTimeMillis {
        hugeNums.asSequence().map { it * it }.filter { it > 10 }.map { it * it }.toList()
    }

    println("$elapsedTime, $elapsedTime2, $elapsedTime3") // 1081, 15, 726
}
```

---

## lambda와 SAM

* SAM: Single Abstract Method의 줄임말, 인터페이스가 하나의 메소드만 가진 경우
    * View.OnClickListener, Runnable 등 많은 인터페이스가 하나의 메소드만 가짐
* 코틀린에서 이런 SAM인 경우 무명 클래스 인스턴스를 만드는 것이 아니라 lambda로 처리할 수 있음

```kotlin
// src/lambda_sam.kt
fun doSomething(runnable: Runnable) = runnable.run()
fun doSomething2(runnable: Runnable) = runnable.run()

fun main() {
    doSomething( Runnable { println("SAM") } )

    doSomething(object : Runnable { // java
        override fun run() {
            println("Java way")
        }
    })

    val runnable = Runnable { println("SAM") }
    doSomething(runnable)
    doSomething2(runnable)
}
```

---

## lambda with, apply

* with, apply
* 객체의 이름을 반복하지 않고 그 객체에 대해 여러 연산을 수행할 수 있음
* 참고: https://takhyeongmin.github.io/2018/12/03/kotlinUsefulFunction2/

```kotlin
// src/lambda_with.kt
fun main() {
    val str = with (StringBuilder()) {
        append("Hello, ")
        append("This ")
        append("is an example of ")
        append("lambda with.")
        toString()
        // "oops"
    } // with returns the last expression
    println(str)

    val str2 = StringBuilder().apply {
        append("Hello, ")
        append("This ")
        append("is an example of ")
        append("lambda with.")
    }.toString()  // apply returns StringBuilder
    println(str2)
}
```

---

## Collection 연산자 오버로딩
* [] 
    * get(idx), set(idx, value)
* in
    * contains(value)
* iterator
    * Iterator Implementation
    * iterator method

```kotlin
// src/collection_overload.kt
data class Three(var x: Int, var y: Int, var z: Int) : Iterable<Int> {
    operator fun get(idx: Int) : Int {
        return when(idx) {
            0 -> x
            1 -> y
            2 -> z
            else ->
                throw IndexOutOfBoundsException("Invalid index $idx")
        }
    }
    operator fun set(idx: Int, value: Int) {
        when(idx) {
            0 -> x = value
            1 -> y = value
            2 -> z = value
            else ->
                throw IndexOutOfBoundsException("Invalid index $idx")
        }
    }

    operator fun contains(value: Int) = (x == value || y == value || z == value)

    inner class MyIterator : Iterator<Int> {
        var curIdx = 0
        override fun next(): Int {
            val ret = this@Three[curIdx]
            curIdx++
            return ret
        }

        override fun hasNext(): Boolean {
            return curIdx <= 2
        }

    }

    override fun iterator(): Iterator<Int> = MyIterator()
}

fun main() {
    val three = Three(1, 2, 3)

    println("${three[0]}, ${three[1]}, ${three[2]}")
    println((3 in Three(1, 2, 3)))
    for (i in three)
        println(i)
}
```
---

## Higher order Function

* 고차 함수: 다른 함수를 인자로 받거나 리턴하는 함수를 말한다.
* Collection의 filter, map 등도 고차함수
* with, apply 등도 고차함수


---

# 기타

## 가변 인자
* 가변 인자
  * vararg를 매개 변수 이름 앞에 사용함
  * spread 연산자: *를 배열앞에 붙여서 가변 인자로 넘겨줄 수 있음

```kotlin
val list = listOf(1, 2, 3, 4)
// fun listOf<T>(vararg values: T) : List<T> { } // 가변 인자 선언
val args = arrayOf("1", "2", "3", "4")
val list2 = listOf("0", *args) // spread 연산자
println(list2) // 0, 1, 2, 3, 4
```

## Infix Function
* 함수 이름을 인자 중간에 넣어서 호출
* 예) mapOf(1 to "one", 2 to "two") 에서 to와 같은 함수

```kotlin
// Any를 확장(extension)하여 infix to를 정의한 것
infix fun Any.to(other: Any) = Pair(this, other)
```

## Destructuring declaration
* 값 2개를 리턴 받아 2개의 변수에 대입

```kotlin
// src/destruct.kt
data class Result(val result: Int, val status: String)
fun calcSomething(): Result {
    // computations
    return Result(404, "Not Found")
}

fun main() {
    val (num, str) = 1 to "one"
    println("$num, $str") // 1, one
    val collection = mapOf(1 to "one", 2 to "two")
    for ((a, b) in collection) {
        println("$a, $b")
    }
    val (result, status) = calcSomething()
    println("$result, $status") // 404, Not Found
}
```
