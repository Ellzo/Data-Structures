import java.util.*

class HashTable<T> {
    private var table = Array(2) {
        LinkedList<Pair<Int, T>>()
    }

    private val p = 100000007
    private var aHash: Int = (0..table.size).random()
    private var bHash: Int = (0..table.size).random()
    private var size = 0

    fun add(id: Int, data: T) {
        addToTable(id, data, table)
        size++
        rehash()
    }

    private fun addToTable(id: Int, data: T, t: Array<LinkedList<Pair<Int, T>>>) {
        t[hash(id, t)].add(Pair(id, data))
    }

    private fun hash(id: Int, t: Array<LinkedList<Pair<Int, T>>> = table): Int {
        return ((aHash * id + bHash) % p) % t.size
    }

    fun delete(id: Int) {
        table[hash(id)].removeIf {
            it.first == id
        }
        size--
        rehash()
    }

    fun find(id: Int): T? {
        return search(table[hash(id)], id)
    }

    private fun search(list: LinkedList<Pair<Int, T>>, id: Int): T? {
        for (item in list) {
            if (item.first == id)
                return item.second
        }
        return null
    }

    private fun rehash() {
        if (table.size < 2)
            return
        val loadFactor = size.toDouble() / table.size

        if (loadFactor > 0.5 && loadFactor < 1)
            return

        val newT = if (loadFactor > 1) {
            Array(2 * table.size) {
                LinkedList<Pair<Int, T>>()
            }
        } else {
            Array(table.size / 2) {
                LinkedList<Pair<Int, T>>()
            }
        }

        aHash = (0..table.size).random()
        bHash = (0..table.size).random()

        for (l in table) {
            for (p in l) {
                addToTable(p.first, p.second, newT)
            }
        }

        table = newT
    }


}