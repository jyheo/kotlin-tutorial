
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

```
JVM기반 kotlinc
$ kotlinc main.kt -include-runtime -d main.jar
$ java -jar main.jar

Native Kotlinc
$ kotlinc main.kt -o main
$ ./main.kexe
```

---

## Kotlin, Teach yourself

* Koans
* EduTools

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
val integer_val : Int = 10  // 타입 명시할 필요 없음
val string_val : String = "a string" // 타입 명시할 필요 없음
val type_inference = 10  // Int
val type_inference2 = "a string"  // String
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

## 문자열(String)

* 자바에서 문자열 중간에 변수값을 추가하고 싶으면 + 연산자를 사용했지만, 코틀린은 문자열 내부에 변수(심지어 expression까지도)를 넣을 수 있다.

```kotlin
// string.kt
val version = "1.3.50"
val java_style = "Hello, Kotlin " + version + "!"
val kotlin_style = "Hello, Kotlin ${version}!"

val num = 10
println("val num is equal to 10: ${num == 10}.")
```

---

## 비교 연산(== or ===)

* 자바와 달리 객체 내용 비교에 == 연산자를 사용하고, 객체 레퍼런스 비교에는 === 연산자를 사용한다.
  * 자바에서는 객체 내용 비교에 equals()를 사용했었다.
* 문자열 비교도 당연히 == 연산자를 쓸 수 있다.

```kotlin
// compare.kt
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

* 