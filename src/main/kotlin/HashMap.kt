import java.util.*

class MyHashMap <K, V> {
    class Node<K, V> (val key: K, var value: V) {
        var next: Node<K, V>? = null
    }

    var size: Int = 0
        private set
    private var dict = Array<LinkedList<Node<K, V>>?>(ARRAY_SIZE) { null }

    companion object {
        private const val ARRAY_SIZE = 16
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

    private fun keyToList(key: K): LinkedList<Node<K, V>>? = dict[key.hashCode() % ARRAY_SIZE]

    fun add(key: K, value: V) {
        val nodesList = keyToList(key)
        if (nodesList.isNullOrEmpty()) { // new list and node
            dict[key.hashCode() % ARRAY_SIZE] = LinkedList<Node<K, V>>()
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

    fun clear() {
        dict = Array<LinkedList<Node<K, V>>?>(ARRAY_SIZE) { null }
            .also { size = 0 }
    }

    operator fun get(key: K): V? = keyToNode(key)?.value
}

fun main() {
    val myHashMap = MyHashMap<Int, String>()
    myHashMap.add(1, "test")
    assert(myHashMap[1] == "test") { "wrong value" }

    myHashMap.add(1, "it's one")
    assert(myHashMap[1] == "it's one") { "wrong value" }

    myHashMap.add(2, "it's two")
    assert(myHashMap[2] == "it's two") { "wrong value" }
    assert(myHashMap.size == 2) { "wrong size" }

    myHashMap.remove(1)
    assert(myHashMap[1] == null) { "wrong value" }
    assert(myHashMap.size == 1) { "wrong size" }

    myHashMap.clear()
    assert(myHashMap.size == 0) { "wrong size" }
}