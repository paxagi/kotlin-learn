import java.security.MessageDigest

class MyHashMap <K, V> () {
    private var dict = Array<MutableMap<K, V>?>(Int.MAX_VALUE / divider, { null })
    companion object {
        fun <K> hash(key: K): Int { // TODO: set private modifier
            return MessageDigest
                .getInstance("SHA-256")
                .digest(key.toString().toByteArray())
                .fold("", { str, it -> str + "%02x".format(it) }).toBigInteger(16).toInt()
        }
    }

    fun push(key: K, value: V) {
        val bucketIndex = hash(key)
        val pair = dict[bucketIndex]
        if (pair.containsKey(key)) pair[key] = value
    }
}

fun main() {
    println(MyHashMap.hash("words"))
    val myHashMap = MyHashMap<Int, String>()
    myHashMap.push(1, "test")
    println(myHashMap.toString())
}