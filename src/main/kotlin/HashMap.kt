import java.security.MessageDigest
import java.util.LinkedList
import kotlin.math.absoluteValue

class MyHashMap <K, V> {
    class Bucket<K, V>(private var key: K, var value: V) {
        private val hash = hash(key)
        lateinit var next: Bucket<K, V>
    }
    private var dict = Array<LinkedList<Bucket<K, V>>?>(DEFAULT_SIZE) { null }

    companion object {
        const val DIVIDER = 2
        private const val DEFAULT_SIZE = 16
        fun <K> hash(key: K): Int { // TODO: set private modifier
            return MessageDigest
                .getInstance("SHA-256")
                .digest(key.toString().toByteArray())
                .fold("") { str, it -> str + "%02x".format(it) }.toBigInteger(16).toInt().absoluteValue / DIVIDER
        }
    }

    fun add(key: K, value: V) {
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
    myHashMap.add(1, "test")
    myHashMap.add(1, "other string")
    println(myHashMap.get(1))
    println(myHashMap.get(2))
}