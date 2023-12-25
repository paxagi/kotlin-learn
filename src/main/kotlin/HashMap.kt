import java.security.MessageDigest

class HashMap <K, V> (key: K, value: V) {
    var dict = emptyArray<List<Pair<K, V>>>()
    fun hash(key: K): String {
        return MessageDigest
            .getInstance("SHA-256")
            .digest(key.toString().toByteArray())
            .fold("", { str, it -> str + "%02x".format(it) })
    }

}