class Stack <T>(initValue: T? = null){
    class Element<E>(var value: E, var next: Element<E>?)
    private var last: Element<T>? = null
    var size: Int = 0
        private set

    init {
        if (initValue != null) push(initValue)
    }

    //private var last: Element<T>? = if ( firstValue != null) Element<T>(firstValue, null) else null
    fun push(value: T): Element<T> {
        val next = last
        last = Element(value, next)
        size++ // should be protected
        return last as Element<T>
    }

    fun get(): T? = last?.value

    fun pop(): Element<T>? {
        val result = last
        last = last?.next
        if (size > 0) size--
        return result
    }
}

// Test
fun main() {
    var empty = Stack<String>()
    var alone = Stack<String>("one")

    println("l: ${empty.size}, ${alone.size}")

    empty.push("new")
    alone.push("new")
    println("get: ${empty.get()}")
    println("get: ${alone.get()}")
    println("l: ${empty.size}, ${alone.size}")

    empty.pop()
    alone.pop()
    println("get: ${empty.get()}")
    println("get: ${alone.get()}")
    println("l: ${empty.size}, ${alone.size}")

    empty.pop()
    alone.pop()
    println("get: ${empty.get()}")
    println("get: ${alone.get()}")
    println("l: ${empty.size}, ${alone.size}")

    empty.push("new")
    alone.push("new")
    println("get: ${empty.get()}")
    println("get: ${alone.get()}")
    println("l: ${empty.size}, ${alone.size}")
}
