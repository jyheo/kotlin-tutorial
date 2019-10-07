# Kotlin Tutorial (Basic)

## Contents

* Kotlin 설치
* Kotlin 컴파일러 직접 설치하기
* Kotlin, Teach yourself
* 코틀린의 특징
* 코틀린 철학
* 패키지(Packages)
* 변수와 값(Variables & Value)
* 타입 추론(Type Inference))
* 타입(Types)
* if 문
* 타입 체크(is), 타입 변환(as)
* Any 타입
* 문자열(String)
* 비교 연산(== or ===)
* 배열
* for and Iteration
* Function
* Function - Lambda
* When
* Null Safety
* Exception
* Collections

# Reference

* **Kotlin in Action** by Dmitry Jemerov, Svetlana Isakova
* **The Kotlin Guide for the Busy Java Developer** by Renaud Cerrato

---

## Kotlin 설치

* 코틀린 컴파일러를 쉽게 설치해보려면..
* IntelliJ IDEA를 쓰자. 커뮤니티 버전은 무료, 학생 인증 받으면 Ultimate 버전도 무료
* https://kotlinlang.org/docs/tutorials/getting-started.html

---

## Kotlin 컴파일러 직접 설치하기

* IntelliJ IDEA를 쓰면 편하긴 하지만 커맨드 라인에서 간단히 해보고 싶은 경우
* Github Kotlin Release에서 다운로드
  * https://github.com/JetBrains/kotlin/releases/tag/v1.3.50
  * kotlin-compiler-1.3.50.zip : jar로 만들 수 있는 JVM 기반 버전, JDK가 필요함
  * kotlin-native-????-1.3.50.tar.gz : 네이티브용 kotlin
* 다운로드 받은 파일 압축을 푼 후 bin/kotlinc 를 실행하면 됨
  * 편의를 위해 bin 폴더를 PATH에 넣어둔다.

```bash
JVM기반 kotlinc
$ kotlinc main.kt -include-runtime -d main.jar
$ java -jar main.jar

Native Kotlinc
$ kotlinc main.kt -o main
$ ./main.kexe
```

---

## Kotlin, Teach yourself

* Koans in Website
   * https://play.kotlinlang.org/koans/overview
* EduTools in Intellij IDEA
  * Installing the EduTools plugin 
  * Choosing Kotlin Koans course in Welcome dialog

---

## 코틀린의 특징

* 다양한 플랫폼 지원
  * JVM 기반의 서버, 안드로이드
  * 코틀린 자바스크립트
  * 코틀린-native(iOS, MacOS, Android, Windows, Linux)
* 정적 타입 언어
  * statically typed lang. (java, kotlin)
  * dynamically typed lang. (python)
* Type inference

    ```kotlin
        val x = 1 // x는 integer
        val x = "Hello" // x는 String
    ```

* 함수형 언어 (Functional Lang)
* 오픈 소스

---

## 코틀린 철학

* 실용성
  * 이미 검증되고 많이 사용되는 언어 기능을 채택
* 간결성
  * getter/setter
* 안정성(Null Safety)
* Interoperability

---

## 패키지(Packages)

* 자바와 다르게 코틀린은 소스 파일의 위치와 이름을 정하는데에 제한이 없다.
  * 한 파일에 여러개의 클래스를 넣을 수 있다.
  * 패키지 구조와 디렉토리 구조가 일치할 필요 없다.

```kotlin
package com.example.mykotlin
/* 이 파일이 com/example/mykotlin/에 있을 필요 없다.
   이 파일의 이름은 마음대로 지으면 됨 */

fun main() {
    println("Hello, Kotlin")
    // 코틀린은 세미콜론이 없어도 된다.
}
// 자바와 달리 main()함수가 독립적으로 존재
```

---

## 변수와 값(Variables & Value)

* 코틀린은 변수를 선언하는 키워드가 val, var
* 변수 선언 키워드 다음에 변수 명과 콜론, 그리고 타입을 쓴다.
* val
  * val로 선언된 변수(?)는 변경 불가
  * 변수라고 부르지 않고 값이라고 부름
  * val 자체가 value의 줄임말
* var
  * var로 선언된 변수는 변경 가능
  * variable의 줄임말

```kotlin
val a: Int = 10
var b: Int = 12
// a = 11 // compile error
b = 13 // OK
```

---

## 타입 추론(Type Inference))

* 코틀린 변수를 선언할 때 타입을 쓰지 않아도 대입되는 값에 따라 컴파일러가 알아서 타입을 정해준다.

```kotlin
val integerVal : Int = 10  // 타입 명시할 필요 없음
val stringVal : String = "a string" // 타입 명시할 필요 없음
val typeInference = 10  // Int
val typeInference2 = "a string"  // String
```

---

## 타입(Types)

* 코틀린은 자바와 달리  원시타입(primitive types)과 wrapper type을 구분하지 않는다.
  * Byte, Short, Int, Long, Float, Double, Char, Boolean
  * UByte, UShort, UInt, ULong
* 코틀린은 서로 다른 타입의 값을 자동으로 변환 해주지 않기 때문에 명시적으로 값 변환을 해줘야 한다.

```kotlin
val i = 10
val i2 : UInt = 10U
// val l : Long = i  // Compile Error
val l2 : Long = i.toLong()  // 명시적 값 변환
```

---

## if 문

* 자바와 문법은 비슷하지만,
* 코틀린에서 if는 statement가 아니라 expression으로 사용 가능함
* 즉 if 문의 결과로 어떠한 값을 받을 수 있음

```kotlin
val x = if (a > 10) "big" else "small"
```

---

## 타입 체크(is), 타입 변환(as)

* A is 타입
* 타입A as 타입B

```kotlin
// src/is_as.kt
fun main() {
    fun test(obj : Any) {
        if (obj is Int) println("obj is Int.")
        if (obj is String) println("obj is String.")
        // println((obj as String))
        // Not Safe, ClassCastException for non-string
        println("print obj as string > ${(obj as? String ?: "")}") 
        // Elvis(?:) returns empty string if 'as' fail.
    }

    test(1)
    // obj is Int.
    // print obj as string > 
    test("strings")
    // obj is String.
    // print obj as string > strings
}
```

---

## Any 타입

* 자바의 Object와 비슷한 개념으로 코틀린에는 Any가 있음
* 코틀린은 원시타입(primitive types)에 대해서도 Any가 가능함

```kotlin
val t : Any = 10
println((t is Int)) // true
```

---

## 문자열(String)

* 자바에서 문자열 중간에 변수값을 추가하고 싶으면 + 연산자를 사용했지만, 코틀린은 문자열 내부에 변수(심지어 expression까지도)를 넣을 수 있다.
* 3중 따옴표를 쓰는 문자열
    * 어떤 문자든 이스케이프없이 그대로 쓸 수 있음
    * 여러 행의 문자열도 가능
    
```kotlin
// src/string.kt
fun main() {
    val version = "1.3.50"
    val javaStyle = "Hello, Kotlin " + version + "!"
    val kotlinStyle = "Hello, Kotlin ${version}!"
    println(javaStyle)
    println(kotlinStyle)

    val num = 10
    println("val num is equal to 10: ${num == 10}.")

    println("""\$""") // \$
    println("\$")     // $
    println("""
        |hello
        |my name is kotlin.
    """.trimMargin())
}
```

---

## 비교 연산(== or ===)

* 자바와 달리 객체 내용 비교에 == 연산자를 사용하고, 객체 레퍼런스 비교에는 === 연산자를 사용한다.
  * 자바에서는 객체 내용 비교에 equals()를 사용했었다.
* 문자열 비교도 당연히 == 연산자를 쓸 수 있다.

```kotlin
// src/compare.kt
data class MyClass(val a: Int, val b: String)
// data class auto-generates equals/hashCode/toString/copy

fun main() {
    val str1 = "Hello, Kotlin"
    val str2 = "Hello, Kotlin"
    val class1 = MyClass(10, "class1")
    val class2 = MyClass(10, "class1")
    val class3 = MyClass(20, "class2")

    println(str1 == str2) // true
    println(class1 == class2)  // true
    println(class1 == class3)  // false
    println(class1 === class2) // false
}

```

---

## 배열

* 배열은 arrayOf(), arrayOfNulls(), emptyArray()로 생성

```kotlin
// src/array_basic.kt
fun main() {
    val intArrays = arrayOf(1, 2, 3, 4, 5)
    val strArrays = arrayOfNulls<String>(5)
    val dbArrays = emptyArray<Double>()

    println(intArrays[0]) // 배열 인덱스에 해당하는 값
    intArrays[0] = 10 // val로 정의된 배열이지만, 배열 내의 값은 변경이 가능함
    println(intArrays[0])

    for (s in strArrays) { // for를 이용한 배열 순회
        print("$s, ")
    }
    println("")

    println(dbArrays.size) // 배열 크기
}
```

---

## for and Iteration

* for문은 iterator와 같이 사용하는 것이 편리함
* 특정 범위의 iterator를 만들기 위해 Range를 사용할 수 있음
  * Range는 시작과 끝 숫자(또는 문자)를 .. 로 연결, 예) 1..10,  'a'..'z'
  
```kotlin
// src/for_loop.kt
fun main()
{
    val array = arrayOf("Hello", "This", "is", "Kotlin")

    for (a in array) // iterator of array
        print("$a ")
    println("") // Hello This is Kotlin

    for ((idx, a) in array.withIndex()) // with index
        print("($idx, $a) ")
    println("") // (0, Hello) (1, This) (2, is) (3, Kotlin)

    for (i in 1..10) // with range
        print("$i ")
    println("") // 1 2 3 4 5 6 7 8 9 10

    for (i in 'a'..'f') // with range
        print("$i ")
    println("") // a b c d e f

    for (i in 1..10 step 2)  // with range and step
        print("$i ")
    println("") // 1 3 5 7 9

    (10 downTo 1).forEach{  // with lambda
        print("$it ")
    }
    println("") // 10 9 8 7 6 5 4 3 2 1

    // when we use 'while'
    var i = 0
    while (i < 10) {
        i++
        print("$i ")
    }
    println("") // 1 2 3 4 5 6 7 8 9 10
}
```

## Function

* fun 함수이름(매개변수 리스트) : 리턴타입 { 함수 정의 }
* fun 함수이름(매개변수 리스트) = expression
  * 함수 내용이 expression인 경우, 리턴타입이 추정 가능하므로 생략됨
* Default argument 지원
* Named argument 지원

```kotlin
// src/func_basic.kt
fun myFunc(arg1 : Int, arg2: String = "default", arg3: Int = 10)
{
    println("$arg1, $arg2, $arg3")
}

fun sumFunc(a: Int, b: Int) = a + b

fun main()
{
    myFunc(1, "hello", 5) // 1, hello, 5
    myFunc(arg1 = 2) // 2, default, 10
    myFunc(2, arg3 = 5) // 2, default, 5

    // local function
    fun localFunc(a: Int) : Int {
        return a + 10
    }

    println(sumFunc(1, 2)) // 3
    println(localFunc(10)) // 20
}
```

---

## Function - Lambda

* 람다(lambda) 함수는 익명 함수
* 람다 함수는 { 인자 -> 함수 내용 } 과 같은 형식으로 작성함
  * 인자가 한개인 경우 디폴트 인자를 써서 인자 생략 가능

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

## Function - Lambda

* 안드로이드 프로그래밍에서 흔히 사용되는 예

```kotlin
    val btn = findViewById<Button>(R.id.button1)!!
    btn.setOnClickListener {
        Toast.makeText(this, R.string.button_clicked_msg, Toast.LENGTH_SHORT).show()
    }
```

---

## When

* switch문과 비슷하지만
  * 케이스 마다 타입이 달라도 되며
  * expression으로 사용할 수 있음
  
```kotlin
// src/when.kt
fun test(arg : Any)
{
    when(arg) {
        10 -> println("10")
        in 0..9 -> println("0 ≤ x ≤ 9")
        is String -> println("Hello, $arg")
        !in 0..100 -> println("x < 0 and x > 100")
        else -> {
            println("unknown")
        }
    }
}

fun test2(arg : Int) : Int
{
    return when(arg) {
        in 0..9 -> 10
        in 10..19 -> 20
        in 20..29 -> 30
        else -> 40
    }
}

fun main()
{
    test(10) // 10
    test(5) // 0 ≤ x ≤ 9
    test("String") // Hello, String
    test(200) // x < 0 and x > 100
    test(50) // unknown

    println(test2(15)) // 20
    println(test2(50)) // 40
}
```

---

## Null Safety

* null이 가능한 타입과 불가능한 타입 구분
  * 타입 이름 뒤에 ?를 붙이면 nullable 타입
  
```kotlin
// src/null_safety.kt
import java.lang.NullPointerException

fun testNull(arg : String?)
{
    println(arg?.toUpperCase()) // arg? return null if arg is null
    println(arg?.toUpperCase() ?: "-") // Elvis(?:) return right operand("-") if left operand is null
}

fun main()
{
    var nullable: String? = null
    var nonNullable: String = "nonNullable"

    // nonNullable = null // Compile Error

    testNull(nonNullable) // NONNULLABLE NONNULLABLE
    testNull(nullable) // null -

    // nullable.toUpperCase() // Compile Error
    nullable?.toUpperCase() // return null because nullable is null
    try {
        nullable!!.toUpperCase() // cause Exception!
    } catch (e : NullPointerException) {
        println("NullPointerException")
    }
}
```

---

## Exception

* try, catch문은 자바와 거의 같으며 try, catch문을 expression을 사용할 수 있음
* 코틀린은 자바와 달리 모든 예외를 catch하게 강제하지 않음

```kotlin
// src/exception.kt
import java.lang.Integer.parseInt
import java.lang.NumberFormatException as NFE

fun main()
{
    val x = try {
        parseInt("10")
    } catch (e : NFE) {
        0
    }

    println(x)
}
```
---
## Collections

* Array, List, Set, Map (immutable)
* ArrayList, MutableList, MutableSet, MutableMap (mutable)

```kotlin
// src/collections.kt
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
```

