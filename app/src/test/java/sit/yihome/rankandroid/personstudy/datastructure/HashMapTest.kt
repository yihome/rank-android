package sit.yihome.rankandroid.personstudy.datastructure

import org.junit.Test

public class HashMapTest {

    @Test
    public fun testHashMap(){
        var hashMap = MyHashMap<String,String>()
        hashMap.put("dd","ee")
        hashMap.put("d","dd")
        hashMap.put("ss","xx")
        hashMap.put("f","ff")
        hashMap.put("gg","dddd")
        hashMap.put("x","dd")
        hashMap.put("d","dddd")
        hashMap.put("g","ss")
        hashMap.put("dd","dddd")
        hashMap.put("x","dd")
        hashMap.put("g","ff")
        hashMap.put("j","ff")
        hashMap.put("kg","ff")
        hashMap.put("lg","ff")
        hashMap.put("hkg","ff")
        hashMap.put(";y","ff")
        hashMap.put("y","ee")
        hashMap.put("gl","ee")
        hashMap.printHashMap()
    }

    public class MyHashMap<K, V>() {
        private var modCount: Int = 0
        private var keySet: Set<K>? = null
        private var table: Array<Node<K, V>?>? = null
        private var size: Int = 0
        private var shreshold: Int = 0

        internal val DEFAULT_INITIAL_CAPACITY = 1 shl 4 // aka 16

        internal val DEFAULT_LOAD_FACTOR = 0.75f

        init {

        }

        class Node<K, V>(var next: Node<K, V>?, var key: K, var value: V, var hash: Int)

        fun put(key: K, value: V): V? {
            return putVal(hash(key), key, value)
        }

        private fun putVal(hash: Int, key: K, value: V): V? {
            var tab = table
            var node: Node<K, V>?
            if (tab == null || tab.isEmpty()) {
                tab = resize()
            }
            val index = hash and (tab.size-1)
            if (tab[index] == null) {
                tab[index] = Node(null, key, value, hash)
            } else {
                node = tab[index]!!
                while (node != null) {
                    if (node.key == key && node.hash == hash) {
                        val oldValue = node.value
                        node.value = value
                        return oldValue
                    }
                    node = node.next
                }
                tab[index] = Node(tab[index], key, value, hash)
            }
            if (++size > shreshold) {
                resize()
            }
            return value
        }

        internal fun hash(key: K): Int {
            return if (key == null)
                0
            else {
                val h: Int = key.hashCode()
                h xor h.ushr(16)
            }
        }

        private fun resize(): Array<Node<K, V>?> {
            val oldTab: Array<Node<K, V>?>? = table
            val oldCap = if (table == null) 0 else table!!.size
            val oldThr = shreshold
            var newCap: Int = 0
            var newThr: Int = 0
            when {
                oldCap > 0 -> {
                    newCap = oldCap shl 1
                    newThr = oldThr shl 1
                }
                oldThr > 0 -> newCap = oldThr
                else -> {
                    newCap = DEFAULT_INITIAL_CAPACITY
                    newThr = (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR).toInt()
                }
            }
            if (newThr == 0) {
                newThr = (DEFAULT_LOAD_FACTOR * newCap).toInt()
            }
            var newTab: Array<Node<K, V>?> = arrayOfNulls<Node<K, V>>(newCap)
            oldTab?.forEach {
                var node = it
                var index: Int
                var head: Node<K, V>?
                while (node != null) {
                    index = node.hash and (newCap-1)
                    head = node.next
                    if (newTab[index] == null) {
                        newTab[index] = node
                        if (node.next != null) {
                            node.next = null
                        }
                    } else {
                        node.next = newTab[index]!!
                        newTab[index] = node
                    }
                    node = head
                }
            }
            table = newTab
            shreshold = newThr
            return newTab
        }

        public fun printHashMap(){
            table?.forEach {
                var node = it
                while (node!=null){
                    print("["+node.key.toString()+" : "+node.value.toString()+"] ->")
                    node = node.next
                }
                println()
            }
            println("size: "+size+" shreshold: "+shreshold+" table size: "+table!!.size)
        }
    }

}