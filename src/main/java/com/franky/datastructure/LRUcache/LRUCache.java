package com.franky.datastructure.LRUcache;

/**
 * LRU页面置换算法：(least recently used)
 * 当缓存的页面数达到缓存容量上限的时候，要把最久未被使用过的页面置换掉
 * 实现策略：
 * 使用一个哈希表和一个双向链表
 * 新增页面的时候，要在hash表中记录，还要在双向链表中把它放在最前面（除了哑节点）
 * 查询页面的时候，在哈希表中查询，如果查得到，还要将它调到链表最前面
 * 删除页面的时候，直接删除最尾的一个节点，并在hash表中删除
 *
 * 可以自己实现双向链表和hashmap，也可以继承hashmap实现自己的LinkedHashMap
 */
public class LRUCache {
    private class MyLinkedHashMap<K, V> {

        private final Entry<K, V>[] table;
        int modCount;
        int size;

        class Entry<K, V> {
            int hash;
            K key;
            V value;
            Entry<K,V> next;
            Entry<K, V> before, after;
            public Entry() {
            }
            public Entry(int hash, K key, V value, Entry<K, V> node) {
                this.hash = hash;
                this.key = key;
                this.value = value;
                this.next = node;
            }
        }

        Entry<K, V> head;
        Entry<K, V> tail;

        public MyLinkedHashMap(int initialCapacity) {
            this.table = new Entry[initialCapacity];
            modCount = initialCapacity;
            size=0;
        }

        final int hash(Object key) {
            int h;
            return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        }

        Entry<K,V> newNode( K key, V value,Integer hash,Entry<K, V> next) {
            Entry<K,V> p= new Entry( hash,key, value,next);
            linkNodeLast(p);
            return p;
        }

        private void linkNodeLast(Entry<K,V> p){
            Entry<K,V> last=tail;
            tail=p;
            if(last==null){
                head=p;
            }else{
                p.before=last;
                last.after=p;
            }
        }
        public V get(Object key) {
            Entry<K,V> e;
            if ((e = getNode(hash(key), key)) == null)
                return null;
            afterNodeAccess(e);
            return e.value;
        }

        Entry<K,V> getNode(int hash, Object key) {
            Entry<K,V>[] tab; Entry<K,V> first, e; int n; K k;
            if ((tab = table) != null && (n = tab.length) > 0 &&
                    (first = tab[(n - 1) & hash]) != null) {
                if (first.hash == hash && // always check first node
                        ((k = first.key) == key || (key != null && key.equals(k))))
                    return first;
                if ((e = first.next) != null) {
                    do {
                        if (e.hash == hash &&
                                ((k = e.key) == key || (key != null && key.equals(k))))
                            return e;
                    } while ((e = e.next) != null);
                }
            }
            return null;
        }

        public V put(K key, V value) {
            Integer hash = hash(key);
            Entry<K, V> p;
            int n, i;
            n = modCount;
            if ((p = table[i = (n - 1) & hash]) == null) {
                table[i] = newNode(key,value,hash,null);
                size++;
            } else {
                Entry<K,V> e; K k;
                if (p.hash == hash &&
                        ((k = p.key) == key || (key != null && key.equals(k))))
                    e = p;
                else {
                    for (int binCount = 0; ; ++binCount) {
                        if ((e = p.next) == null) {
                            p.next = newNode(key, value, hash,null);
                            size++;
                            break;
                        }
                        if (e.hash == hash &&
                                ((k = e.key) == key || (key != null && key.equals(k))))
                            break;
                        p = e;
                    }
                }
                if (e != null) { // existing mapping for key
                    V oldValue = e.value;
                    e.value = value;
                    afterNodeAccess(e);
                    return oldValue;
                }
            }
            afterNodeInsertion();
            return null;
        }

        private void afterNodeInsertion() {
            Entry<K,V> first;
            if ( (first = head) != null && removeEldestEntry()) {
                K key = first.key;
                removeNode(hash(key), key,null, false, true);
            }
        }

        Entry<K,V> removeNode(int hash, Object key, Object value,
                              boolean matchValue, boolean movable) {
            Entry<K,V>[] tab; Entry<K,V> p; int n, index;
            if ((tab = table) != null && (n = tab.length) > 0 &&
                    (p = tab[index = (n - 1) & hash]) != null) {
                Entry<K,V> node = null, e; K k; V v;
                if (p.hash == hash &&
                        ((k = p.key) == key || (key != null && key.equals(k))))
                    node = p;
                else if ((e = p.next) != null) {
                    do {
                        if (e.hash == hash &&
                                ((k = e.key) == key ||
                                        (key != null && key.equals(k)))) {
                            node = e;
                            break;
                        }
                        p = e;
                    } while ((e = e.next) != null);
                }
                if (node != null && (!matchValue || (v = node.value) == value ||
                        (value != null && value.equals(v)))) {
                    if (node == p)
                        tab[index] = node.next;
                    else
                        p.next = node.next;
                    --size;
                    afterNodeRemoval(node);
                    return node;
                }
            }
            return null;
        }

        //从链表将该元素删除
        private void afterNodeRemoval(Entry<K,V> e) {
            Entry<K,V> p =e, b = p.before, a = p.after;
            p.before = p.after = null;
            if (b == null)
                head = a;
            else
                b.after = a;
            if (a == null)
                tail = b;
            else
                a.before = b;
        }
        private boolean removeEldestEntry() {
            return size>modCount;
        }

        private void afterNodeAccess(Entry<K,V> e) {
            Entry<K,V> last;
            if ((last = tail) != e) {
                Entry<K,V> p =e, b = p.before, a = p.after;
                p.after = null;
                if (b == null)
                    head = a;
                else
                    b.after = a;
                if (a != null)
                    a.before = b;
                else
                    last = b;
                if (last == null)
                    head = p;
                else {
                    p.before = last;
                    last.after = p;
                }
                tail = p;
            }
        }

        public V getOrDefault(Object key, V defaultValue) {
            V value;
            if ((value = get(key)) == null) {
                return defaultValue;
            }
            return value;
        }

    }

    private final MyLinkedHashMap<Integer, Integer> map;

    public LRUCache(int capacity) {
        map = new MyLinkedHashMap<>(capacity);
    }

    public int get(int key) {
        return map.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        map.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);

        int res1 = cache.get(1);
        System.out.println(res1);

        cache.put(3, 3);

        int res2 = cache.get(2);
        System.out.println(res2);

        int res3 = cache.get(3);
        System.out.println(res3);

        cache.put(4, 4);
        int res4 = cache.get(1);
        System.out.println(res4);

        int res5 = cache.get(3);
        System.out.println(res5);

        int res6 = cache.get(4);
        System.out.println(res6);
    }
}





