import java.security.MessageDigest
import kotlin.math.absoluteValue

class MyHashMap <K, V> () {
    private var dict = Array<MutableMap<K, V>?>(Int.MAX_VALUE / divider, { null })
    companion object {
        val divider = 2
        fun <K> hash(key: K): Int { // TODO: set private modifier
            return MessageDigest
                .getInstance("SHA-256")
                .digest(key.toString().toByteArray())
                .fold("", { str, it -> str + "%02x".format(it) }).toBigInteger(16).toInt().absoluteValue / divider
        }
    }

    fun push(key: K, value: V) {
        val bucketIndex = hash(key)
        val pair = dict[bucketIndex]
        if (pair != null) {
            if (pair.containsKey(key)) pair[key] = value
        } else {
            dict[bucketIndex] = mutableMapOf(key to value)
        }
    }

    fun get(key: K): V? = dict[hash(key)]?.get(key)
}

fun main() {
    println("hash: " + MyHashMap.hash("words"))
    val myHashMap = MyHashMap<Int, String>()
    myHashMap.push(1, "test")
    println(myHashMap.get(1))
}