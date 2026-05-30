package ro.tuiasi.pp.lab7


fun <T : Comparable<T>> getMaxRecord(a: T, b: T): T {
    return if (a > b) a else b
}

fun <K, V> findAndReplace(searchFor: V, replaceWith: V, map: MutableMap<K, V>) {
    val keysToUpdate = mutableListOf<K>()

    for ((key, value) in map) {
        if (value == searchFor) {
            keysToUpdate.add(key)
        }
    }

    for (key in keysToUpdate) {
        map[key] = replaceWith
    }
}