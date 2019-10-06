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

## Infix Function

## Inline Function

## Extension

## 고차 함수

## Collection, lambda

## Collection lazy,

## Collection 연산자 오버로딩
* [] get, set
* in contains
* ... rangeTo
* iterator


## apply, with, let, also, run