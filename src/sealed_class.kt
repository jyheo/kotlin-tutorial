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