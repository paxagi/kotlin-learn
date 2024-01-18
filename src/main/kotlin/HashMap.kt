import java.security.MessageDigest
import java.util.LinkedList
import kotlin.math.absoluteValue

class MyHashMap <K, V> {
    class Bucket<K, V>(private var key: K, var value: V) {
        private val hash = key.hashCode()
        lateinit var next: Bucket<K, V>
    }
    private var dict = Array<LinkedList<Bucket<K, V>>?>(DEFAULT_SIZE) { null }

    companion object {
        const val DIVIDER = 2
        private const val DEFAULT_SIZE = 16
    }

    fun add(key: K, value: V) {
        val bucketIndex = key.hashCode()
        val pair = dict[bucketIndex]
        if (pair != null) {
            if (pair.containsKey(key)) pair[key] = value
        } else {
            dict[bucketIndex] = mutableMapOf(key to value)
        }
    }

    fun get(key: K): V? = dict[key.hashCode()]?.get(key)
}

fun main() {
    val myHashMap = MyHashMap<Int, String>()
    myHashMap.add(1, "test")
    myHashMap.add(1, "other string")
    println(myHashMap.get(1))
    println(myHashMap.get(2))
}