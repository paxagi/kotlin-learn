import java.util.*

class MyHashMap <K, V> {
    class Node<K, V> (val key: K, var value: V) {
        var next: Node<K, V>? = null
    }

    var size: Int = 0
        private set
    private var dict = Array<LinkedList<Node<K, V>>?>(DEFAULT_SIZE) { null }

    companion object {
        private const val DEFAULT_SIZE = 16
    }

    private fun keyToNode(key: K): Node<K, V>? {
        val nodesList = keyToList(key)
        var node: Node<K, V>? = null
        if (nodesList != null) {
            for (element in nodesList) {
                if (element.key == key) {
                    node = element
                    break
                }
            }
        }
        return node
    }

    private fun keyToList(key: K): LinkedList<Node<K, V>>? = dict[key.hashCode() % DEFAULT_SIZE]

    fun add(key: K, value: V) {
        val nodesList = keyToList(key)
        if (nodesList.isNullOrEmpty()) { // new list and node
            dict[key.hashCode() % DEFAULT_SIZE] = LinkedList<Node<K, V>>()
                .also { it.add(Node(key, value)) }
                .also { size++ }
        } else {
            val node: Node<K, V>? = keyToNode(key)
            if (node == null) { // new node
                nodesList
                    .add(Node(key, value))
                    .also { size++ }
            } else { // rewrite a node
                node.value = value
            }
        }

    }

    fun remove(key: K): Boolean {
        val nodesList = keyToList(key)
        return nodesList?.remove(keyToNode(key)).also { size-- } ?: false
    }

    operator fun get(key: K): V? = keyToNode(key)?.value
}

fun main() {
    val myHashMap = MyHashMap<Int, String>()
    myHashMap.add(1, "test")
    println(myHashMap[1])
    myHashMap.add(1, "it's one")
    println(myHashMap[1])
    myHashMap.add(2, "it's two")
    println(myHashMap[2])
    println(myHashMap.size)
    myHashMap.remove(1)
    println(myHashMap[1])
    println(myHashMap.size)
}