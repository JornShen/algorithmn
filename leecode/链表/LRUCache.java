/*
leetcode 146. LRU Cache
Design and implement a data structure for Least Recently Used (LRU) cache. 
It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key 
if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. 
When the cache reached its capacity, it should invalidate the least recently used item 
before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

*/

// 链表　+ hash 但是没有进行优化，　特别是更新的节点
class LRUCache {

    private int cap = 0;
    private List<Integer> list;
    private Map<Integer, Integer> map;

    //时钟算法
    public LRUCache(int capacity) {
        cap = capacity;
        list = new LinkedList<>();
        map = new HashMap<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            list.remove(new Integer(key));
            list.add(0, key);
            return map.get(key);
        } else return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
                list.remove(new Integer(key));
                list.add(0, key);
                map.put(key, value);
                return;
        }
        if (list.size() == cap) {
            int d = list.remove(cap - 1);
            map.remove(new Integer(d));
        }
        list.add(0, key);
        map.put(key, value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// 双向链表 +　hash 表
// hash 存放节点的指针

class LRUCache {

    class Node {
        int key;
        int value;
        Node pre;
        Node next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int cap = 0;
    private Node head, tail;
    private Map<Integer, Node> map;

    //时钟算法
    public LRUCache(int capacity) {
        cap = capacity;
        head = null;
        tail = null;
        map = new HashMap<>();
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        // 尾部表示最新访问的节点，　头部表示最不常用的节点
        if (node != tail) {
            if (node == head) {
                head = head.next;
            } else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
            tail.next = node;
            node.pre = tail;
            node.next = null;
            tail = node;
        }
        return node.value;
    }

    public void put(int key, int value) {
        
        Node node = map.get(key);
        
        if (node != null) {
            get(key); // 调换位置
            node.value = value;
            return;
        }
        
        Node newOne = new Node(key, value);
        // 已经满了
        if (cap == 0) { 
            map.remove(new Integer(head.key)); // 删除最近不常访问的节点
            head = head.next;
            cap++;
        }

        if (head == null && tail == null) {
            head = newOne;
        } else {
            tail.next = newOne;
            newOne.pre = tail;
        }

        map.put(key, newOne);
        tail = newOne;
        cap--;
    }
}
