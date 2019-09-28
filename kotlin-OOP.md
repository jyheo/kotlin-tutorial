
# Object Oriented Programming in Kotlin

---

## Class

* 단순한 클래스 정의
  * class 클래스_이름 (생성자 인자 리스트)
  * 생성자 인자들 중 val/var이 붙은 것은 클래스의 속성이 됨
* 자바와 다르게
  * 코틀린은 기본적으로 public class 임
  * 객체를 생성할 때 new 키워드를 쓰지 않음
  * 속성 접근 메소드를 자동으로 만들어주며, 커스텀 접근 메소드를 만들 수도 있음

```kotlin
// src/class_basic.kt
class Animal(val name: String)

class Person(val firstName: String, _lastName: String) {
    var lastName: String = _lastName
        get () = field.toUpperCase()
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
```

---

## Interface

* 자바의 인터페이스와 비슷함
* 구현이 있는 메소드 정의 가능
  * 자바처럼 default를 붙이진 않음
* 인터페이스 구현 클래스의 메소드 구현에서 override를 반드시 써야 함
  * 클래스를 상속 받을 때에도, 메소드 오버라이드할 때 override를 씀

```kotlin
// src/interface_basic.kt
interface ClickListener {
    fun onClick()
    fun onTouch() = println("touched") // default implementation
}

class View(private val id: Int) : ClickListener {
    override fun onClick() = println("clicked $id")
}

fun main() {
    val v = View(10)
    v.onClick() // clicked 10
    v.onTouch() // touched
}
```

---

## 기본적으로 상속, 오버라이드 금지

* interface, abstract class 는 기본적으로 상속(구현), 메소드 오버라이드가 가능
* class 는 기본적으로 상속, 메소드 오버라이드 금지
    * 상속이나 오버라이드가 가능하게 하려면 open 키워드 사용
    * open class Test()
* 메소드 오버라이드 접근 변경
    * final : 오버라이드 금지, 기본적으로 final임
    * open :  오버라이드 가능, 명시적으로 open을 붙여야 오버라이드 가능
    * abstract : 반드시 오버라이드 해야 함, abstract class 내에서만 사용 가능
    * override : 오버라이드 가능
    
```kotlin
// src/method_override.kt
class NoInherit(val name: String)

// class Child2(_name: String) : NoInherit(_name) // Compile Error

open class Inherit(val name: String) {
    fun first() = println("first")
    open fun second() = println("second")
}

open class Child(_name: String) : Inherit(_name) {
    // override fun first() = println("override first") // Compile Error
    final override fun second() = println("override second")
    open fun third() = println("third")
}

class GrandChild : Child("") {
    // override fun second() = println("")  // Compile Error
    override fun third() = println("override third")
}

fun main() {
    val c = Child("child")
    c.first() // first
    c.second() // override second
    c.third() // third

    val g = GrandChild()
    g.first() // first
    g.second() // override second
    g.third() // override third
}
```

---
 
## 기본적으로 공개(public)

* 코틀린은 클래스와 메소드 모두 기본적으로 public
* 접근(access) 변경자
    * public: 기본 접근
    * internal: 같은 모듈(gradle의 모듈과 같이 같이 컴파일되는 단위)
    * protected: 상속 받은 클래스에서만 접근 가능
    * private: 같은 클래스에서만 접근 가능

---

## 중첩(Nested) 클래스

* 코틀린에서 중첩 클래스는 자바에서 정적(static) 중첩 클래스와 같음
* 자바처럼 내부 클래스를 만들려면 중첩 클래스 앞에 inner 키워드 사용

```kotlin
// src/nested_class.kt
class Outer(var m : Int = 0) {
    inner class Inner {
        fun doSomething() {
            this@Outer.m++
            println("Inner.doSomething ${this@Outer.m}")
        }
    }

    class NoInner {
        fun doSomething() {
            println("NoInner.doSomething")
            // this@Outer  // cannot access Outer reference
        }
    }
}

fun main() {
    val inner = Outer().Inner()
    inner.doSomething() // Inner.doSomething 1
    val noInner = Outer.NoInner()
    noInner.doSomething() // NoInner.doSomething
}
```

---

## 봉인(sealed) 클래스

* 특정 클래스를 상속하는 클래스를 제한할 수 있다.

```kotlin
// src/sealed_class.kt
sealed class Expr {
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
}

fun eval(e : Expr): Int =
    when (e) {
        is Expr.Num -> e.value
        is Expr.Sum -> eval(e.right) + eval(e.left)
        // else -> 0  // doesn't need else because Expr has Num and Sum only
    }

fun main() {
    val r = eval(Expr.Sum(Expr.Num(10), Expr.Num(10)))
    println(r) // 20
}
```

---

## Constructor

---

## Property

---

## Data Class

---

## object 키워드

---

## 연산자 오버로딩

---

## Generic

---

## Inheritance

---

## Extension Method

---

## Companion object

## 
