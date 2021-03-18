---
marp: true
theme: my-theme
paginate: true
headingDivider: 2
header: Kotlin Tutorial - OOP 
footer: https://github.com/jyheo/kotlin-tutorial
backgroundColor: #fff
---

# Object Oriented Programming in Kotlin
<!-- _class: lead -->
### 허준영(jyheo@hansung.ac.kr)

## Contents
<!-- _class: double -->
* Class
* Interface
* 기본적으로 상속, 오버라이드 금지
* 기본적으로 공개(public)
* 중첩(Nested) 클래스
* 봉인(sealed) 클래스
* Constructor
* Property와 getter/setter
* Data Class
* object 키워드
* Extension Method/Property
* 연산자 오버로딩
* 위임(Delegation)
* 위임 속성(Delegated Property)
* Generic


## Class

* 단순한 클래스 정의
  * class 클래스_이름 (생성자 인자 리스트)
  * 생성자 인자들 중 val/var이 붙은 것은 클래스의 속성이 됨
* 자바와 다르게
  * 코틀린은 기본적으로 public class 임
  * 객체를 생성할 때 new 키워드를 쓰지 않음
  * 속성 접근 메소드를 자동으로 만들어주며, 커스텀 접근 메소드를 만들 수도 있음

## Class - 예제

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



## Interface

* 자바의 인터페이스와 비슷함
* 구현이 있는 메소드 정의 가능
  * 자바처럼 default를 붙이진 않음
* 인터페이스 구현 클래스의 메소드 구현에서 override를 반드시 써야 함
  * 클래스를 상속 받을 때에도, 메소드 오버라이드할 때 override를 씀

## Interface - 예제

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

## 기본적으로 상속, 오버라이드 금지 - 예제

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


 
## 기본적으로 공개(public)

* 코틀린은 클래스와 메소드 모두 기본적으로 public
* 접근(access) 변경자
    * public: 기본 접근
    * internal: 같은 모듈(gradle의 모듈과 같이 같이 컴파일되는 단위)
    * protected: 상속 받은 클래스에서만 접근 가능
    * private: 같은 클래스에서만 접근 가능



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



## Constructor

* Primary constructor: 클래스 이름 옆에 정의하는 생성자
    * class Person(val name: String)
* Secondary constructor: 클래스 내부에 정의하는 생성자

## Constructor - 예제

```kotlin
// src/constructor.kt
class ConstEx1 constructor(_prop: Int) { // primary constructor
    val prop: Int
    val prop2: Int
    init {
        prop = _prop
        prop2 = _prop * 2
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

fun main() {
    ConstEx1(1)
    ConstEx2(1)
    ConstEx3(1)
    ConstEx4(1)
    ConstEx4(1, 2)
    ConstEx4(1, 2, 3)
    ConstEx5(1)
}
```



## Property와 getter/setter

* Property의 커스텀 setter와 getter를 정의할 수 있다.
    * public인 속성에 대해 setter를 private으로 하면 클래스 밖에서는 get만 가능함
* Interface에 property를 정의할 수 있으나, 이 property는 구현 클래스에서 반드시 오버라이드 해야 한다.

## Property와 getter/setter - 예제

```kotlin
// src/getset.kt
interface GetSetI {
    var prop: String // the implmentation class must be override this property
}

class GetSet(_prop: String) : GetSetI {
    override var prop: String = _prop
        set(value) {
            field = value.substringBefore('@')
        }
        get() = field.toUpperCase()
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
```



## Data Class

* class 정의 앞에 data 키워드 추가
* data class auto-generates equals/hashCode/toString/copy

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



## object 키워드

* 클래스 정의 없이 바로 객체를 생성하는 방법
    * 싱글톤을 만들거나
    * companion object를 만들거나
    * anonymous object를 만들때 사용
* Companion object는 이 객체를 포함하는 클래스의 private 멤버에 접근 가능

## object 키워드 - 예제
```kotlin
// src/object.kt
//interface ClickListener {
//    fun onClick()
//}

object ClickListenerImpl : ClickListener {
    override fun onClick() = println("clicked")
}

fun setClickListener(listener: ClickListener) = listener.onClick()

class Touch() { // the number of Touch objects
    val objectNums : Int
        get() = num

    init {
        num++
    }

    companion object {
        var num : Int = 0
    }
}

fun main() {
    setClickListener(ClickListenerImpl)  // clicked
    setClickListener(object : ClickListener {  // clicked2
        override fun onClick() = println("clicked2")
    })
    Touch()
    Touch()
    println(Touch().objectNums) // 3
}
```



## Extension Method/Property

* 이미 만들어진 클래스의 메소드와 속성을 클래스를 상속하거나 수정하지 않고도 추가할 수 있음
* 이렇게 추가된 메소드는 오버라이드 안됨
* 자바의 기존 Collection들에 다양한 유틸 함수들을 이 방법으로 추가
    * String의 새로운 유틸 함수: substringBefore, substringAfter 등

```kotlin
// src/extension_m.kt
// method extension to Collection<T>
fun <T> Collection<T>.join(separator: String = " ") : String                                                
{
    val result = StringBuilder()
    for ((index, element) in withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    return result.toString()
}

fun main()
{
    println(listOf("1", "2", "3").join()) // 1 2 3
    println(arrayListOf(1, 2, 3).join(", ")) // 1, 2, 3
}
```
## Extension Method/Property - 계속
* 속성도 마찬가지로 확장 가능

```kotlin
// src/extension_p.kt
val String.lastChar: Char
    get() = get(length - 1)

fun main() {
    println("Hello".lastChar)
}
```


## 연산자 오버로딩

* 이항 산술 연산자 +, -, *, /, % 오버로딩 가능
    * 오버로딩할 때 연산자 이름 plus, minus, times, div, rem
* 단항연산자
    * unaryPlus, unaryMinus, not, inc, dec
* 비교 연산자
    * equals, compareTo

## 연산자 오버로딩 - 예제

```kotlin
// src/op_overload.kt
data class Complex(val real: Double, val img: Double) {
    operator fun plus(other: Complex): Complex
            = Complex(real + other.real, img + other.img)
    override fun toString(): String = "$real+${img}i"
}

operator fun Complex.minus(other: Complex): Complex  // by function extension
        = Complex(real - other.real, img - other.img)

fun main() {
    val c1 = Complex(1.0, 1.0)
    val c2 = Complex(2.0, 2.0)
    val c3 = c1 + c2
    println(c3) // 3.0+3.0i
    println(c3 == Complex(3.0, 3.0)) // true
}
```


## 위임(Delegation)
- 상속과 유사하지만 다른
    - 상속은 강한 결합, 위임은 약한 결합
    - 상속 불가여도 위임은 가능
    - 위임(상속)할 클래스와 동일한 인터페이스를 구현해야 함
- 위임 패턴은 위임(상속)할 객체를 멤버로 만들고 특정 메소스 호출시 위임 객체의 메소드로 포워드(호출)하는 방식으로 만듬
    - 위임 객체의 메소드 각각에 대해 메소드를 새로 만들어서 포워드 해야 함
- 코틀린은 by 키워드로 위임을 쉽게 지원함


---
```kotlin
interface Base {
    fun print()
    fun printHello()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }
    override fun printHello() { println("Hello")}
}

class Derived(val b: Base) : Base by b {
    override fun print() { // 오버라이드
        b.print()
        println("ok")
    }
}

fun main() {
    val b = BaseImpl(10)
    val d = Derived(b)
    d.print()      // Derived에서 오버라이드한 것을 사용
    d.printHello()  // Derived에서 구현하지 않았으나 b의 것을 사용 가능
}
```


## 위임 속성(Delegated Property)
- by 키워드를 써서 속성의 get/set을 위임 객체의 getValue/setValue로 위임함
- 이를 이용하여 특별한 성질을 갖는 속성을 만듬
    - Lazy 특성: 값을 처음 접근할 때 생성/계산함
    - Observable 특성: 값이 변경될  때 알려주는 리스너를 지정
    - Map을 이용하여 객체 속성 값을 저장
- 다른 속성으로 위임할 수도 있음
    ```kotlin
    class MyClass {
        var newName: Int = 0
        @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
        var oldName: Int by this::newName
    }
    ```

---
```kotlin
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
```
---
```kotlin
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

```

## Generic

* 자바 제네릭과 비슷함

```kotlin
// src/generic.kt
fun <T : Number> myPlus(op1: T, op2: T): String = "$op1$op2"

class MyGeneric<T : Number>(private val prop: T) {
    fun test(arg: T): String {
        println(arg)
        return myPlus(prop, arg)
    }
}

fun main() {
    println(myPlus(1, 1)) // 11
    println(myPlus(1.0, 1.0)) // 1.01.0
    // we can omit <Int> because of kotlin's type inference
    println(MyGeneric(10).test(10)) // 10
                                               // 1010
}
```
