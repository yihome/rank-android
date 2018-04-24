package sit.yihome.rankandroid.datastructure

public class HashMapTest{

    public  class MyHashMap<K,V>(){
        private var modCount:Int = 0
        private var keySet:Set<K> ? = null
        private var table:Node<K,V> ?=null
        private var size:Int = 0

        init {

        }

        class Node<K,V>(var next: Node<K,V>,var key:K,var value:V,var hash:Int){

        }

        fun put(key:K,value:V):V?{
          return putVal(hash(key),key,value)
        }

        private fun putVal(hash: Int, key: K, value: V): V? {

            return null
        }

        internal fun hash(key: K): Int {
            return if (key == null)
                0
            else{
                val h: Int = key.hashCode()
                h xor h.ushr(16)
            }
        }

        private fun resize(){

        }
    }

}