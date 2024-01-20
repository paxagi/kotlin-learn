import java.security.MessageDigest
import java.util.LinkedList
import kotlin.math.absoluteValue

class MyHashMap <K, V> {
    class Node<K, V> (val key: K, var value: V) {
        val hash = key.hashCode()
        var next: Node<K, V>? = null
    }
    private var dict = Array<LinkedList<Node<K, V>>?>(DEFAULT_SIZE) { null }

    companion object {
        const val DIVIDER = 2
        private const val DEFAULT_SIZE = 16
    }

    fun add(key: K, value: V) {
        val bucketIndex = key.hashCode() % DEFAULT_SIZE
        val nodesList = dict[bucketIndex]

        if (nodesList.isNullOrEmpty()) {
            dict[bucketIndex] = LinkedList<Node<K, V>>().also { it.add(Node(key, value)) }
        } else {
            var node: Node<K, V>? = null
            for (element in nodesList) {
                if (element.key == key) {
                    node = element
                    break
                }
            }
           if (node == null) {
               nodesList.add(Node(key, value))
           } else {
               node.value = value
           }
        }
    }

    fun get(key: K): V? {
        val bucketIndex = key.hashCode() % DEFAULT_SIZE
        val nodesList = dict[bucketIndex]
        var node: Node<K, V>? = null
        if (nodesList != null) {
            for (element in nodesList) {
                if (element.key == key) {
                    node = element
                    break
                }
            }
        }
        return node?.value
    }
}

fun main() {
    val myHashMap = MyHashMap<Int, String>()
    myHashMap.add(1, "test")
    println(myHashMap.get(1))
    myHashMap.add(1, "it's one")
    println(myHashMap.get(1))
    myHashMap.add(2, "it's two")
    println(myHashMap.get(2))
}