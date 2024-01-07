import java.security.MessageDigest

class HashMap <K, V> (key: K, value: V) {
    private var dict = emptyArray<MutableMap<K, V>>()
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
    println(HashMap.hash("words"))
}