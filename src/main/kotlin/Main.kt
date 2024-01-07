class Stack <T>(){
    private class Element<E>(var value: E, var next: Element<E>?)
    private var last: Element<T>? = null
    var size: Int = 0
        private set

    //private var last: Element<T>? = if ( firstValue != null) Element<T>(firstValue, null) else null
    fun push(value: T) {
        val next = last
        last = Element(value, next)
        size++
    }

    fun get(): T? = last?.value

    fun pop(): T? {
        val result = last
        last = last?.next
        if (size > 0) size--
        return result?.value
    }
}

// Test
fun main() {
    val empty = Stack<String>()
    val alone = Stack<String>()
    alone.push("one")

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
