package com.dongfang.dsa.structure.ch6_map;


/*
 * TreeMap分析
 * 时间复杂度（平均）
 * 添加、删除、搜索：O(logn)
 *
 * 特点：
 * 	key必须是具备可比较性
 * 	元素的分布是有顺序的
 *
 * 在实际应用中，很多时候的需求
 * 	Map中存储的元素不需要讲究顺序
 * 	Map中的key不需要具备可比较性
 *
 * 不考虑顺序、不考虑key的可比较性，Map有更好的实现方案，平均时间复杂度可以达到O(1)
 * 添加、删除、搜索的时间复杂度要求是O(1)
 * 通过电话号码找公司信息，将公司电话号码作为数组的index，这样空间复杂度非常大，
 * 空间使用率极其低，非常浪费内存空间
 *
 *
 *
 * 哈希表（Hash Table）
 * 	哈希表也叫做散列表（hash有剁碎的意思）
 *
 * 	添加、搜索、删除的流程都是类似的
 * 		1、利用哈希函数生成key对应的index O(1)
 * 		2、根据index操作定位数组元素 O(1)
 *
 * 	哈希表是空间换时间的典型应用
 * 	哈希函数，也叫做散列函数
 * 	哈希表内部的数组元素，很多了方也叫做Bucket（桶），整个数组叫做Buckets或者Bucket Array
 *
 * 哈希冲突（Hash Collision）
 * 	哈希冲突也叫做哈希碰撞
 * 		1、两个不同的key，经过哈希函数计算出相同的结果
 * 		2、key1 != key2  hash(key1) == hash(key2)
 * 	解决哈希冲突的常见方法
 * 		1、开放地址法（Open addressing)
 * 			按照一定的规则向其他地址探测，直到遇到空桶（线性探测，平方探测），这个地址上有元素了，再去找其他地址
 * 	    2、再哈希法（Re-hashing）
 * 			设计多个哈希函数，用一个hash1，两个key的hash值相同，再尝试用另一个hash2来计算
 * 		3、链地址法（Separate Chaining)
 * 			比如通过链表将hash后同一index的元素串起来，大家依然用一个索引
 * 	JDK1.8的哈希冲突解决方案
 * 		1、默认使用单向链表将元素串起来
 * 		2、在添加元素时，可能会由单向链表转为红黑树来存储元素
 * 			比如当哈希表容量>= 64 且单向链表的节点数量大于8时
 * 		3、当红黑树的节点少到一定程度时，又会转为单向链表
 * 		4、JDK1.8中的哈希表是使用链表+红黑树解决哈希冲突
 * 		5、为什么使用单链表
 * 			添加时插入到链表尾部，因为插入时，要查看此时插入的key是不是已经在链表中，
 * 			如果已经存在，要覆盖，直接在头部插入，无法知道已经存在，无法覆盖
 * 				1、每次都是从头节点开始遍历
 * 				2、单向链表比双向链表少一个指针，可以节省内存空间
 * 				3、key要存储在节点中
 *
 * 哈希函数
 * 	1、先生成key的哈希值（必须是整数）int类型
 * 	2、再让key的哈希值跟数组的大小进行相关运算，生成一个索引值
 * 			hash_code(key) % table.length
 * 		1、为了提高效率，可以使用&位运算取代%运算，前提：数组的长度设计为2的幂，这样length-1二进制全为1
 * 			hash_code(key) & (table.length - 1)
 * 	3、良好的哈希函数
 * 		让哈希值更加均匀分布，减少哈希冲突的次数，提升哈希表的性能
 *
 * 	如何生成key的哈希值
 * 		key常见的各类可能有
 * 			整数、浮点数、字符串、自定义对象
 * 			不同各类的key，哈希值的生成方式是不一样的，但是目标是一致的
 * 				1、尽量让每个key的哈希值是唯一的
 * 				2、尽量让key的所有信息都参与运算
 * 		Java中，HashMap的key必须实现hashCode equals方法，也允许key为null
 *
 * 		1、整数 32位
 * 			整数值当作哈希值
 * 			比如10的哈希值就是10
 * 		2、浮点数 32位
 * 			将存储的二进制格式转为整数值Float.floatToIntBits(floatValue)
 * 		3、Long Double 64位
 * 			(int) (value ^ (value >>> 32))
 * 			>>> 无符号右移，前面补0
 * 			^异或 相同为0，不同为1，用& | 有可能只利用高位或者低位的信息
 * 			高32位与低32位混合计算出32位的哈希值
 * 			充分利用所有信息计算出哈希值
 * 		4、字符串的哈希值
 * 			1、整数5489 = 5*10^3 + 4 *10^2 + 8*10^1 + 9*10^0
 * 			2、字符串是由若干个字符组成的
 * 				1、比如字符口中jack，是由j a c k四个字符组成，字符的本质是一个整数
 * 				2、因此，jack的哈希值可以表示为 i*n^3 + a*n^2 + c*n^1 + k*n^0 等价于[(j*n + a)*n + c]*n + k
 * 				3、在JDK中，乘数n为31，为什么使用31
 * 					31是一个奇素数，jvm会将 31*i == (i<<5)-i 优化成位运算
 * 		5、自定义对象的哈希值
 * 			1、将对象中每个字段的hash值算出来
 * 			2、跟字符串一样执行相同的多项式计算
 * 				1、哈希值太大，整型溢出怎么办，不用做任何处理，溢出了也是整数，我们就是要一个32位的整数
 * 				2、不重写hashCode方法有什么后果，用Object方法中的hashCode方法，返回与对象内存地址相关的一个整数
 * 				3、两个对象，可能算出的hash值相同，因此不能用hash比较相等，HashMap中的链表上，所有entry的hash值或者索引都一样
 * 		    3、hashCode与equals方法
 * 				1、hashCode在计算索引的时候调用
 * 				2、equals方法在发生冲突的时候，比较两个对象是否相等的时候调用
 *          4、自定义对象作为key
 *              1、最好同时重写hashCode equals方法
 *                  1、equals：用以判断两个key是否为同一个key
 *                  2、hashCode：必须保证equals为true的2个key的哈希值一样
 *                  3、反过来hashCode相等的key，不一定equals为true
 *                  4、不重写hashCode方法只重写equals会有什么后果可能 会导致2个equals为true的key同时存储在哈希表中
 *
 */
/**
 * Map在有些编程语言中也叫做字典，dictionary
 *
 * Map的每一个key都是唯一的
 *
 *      类似Set，Map可以直接利用之前学习的链表，二叉搜索树（AVL树，红黑树）等数据结构来实现
 * @param <K>
 * @param <V>
 */
public interface Map<K, V> {
    int size();

    boolean isEmpty();

    void clear();

    V put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    boolean containsValue(V value);

    void traversal(Visitor<K, V> visitor);

    public static abstract class Visitor<K, V> {
        boolean isStop;

        public abstract boolean visit(K key, V value);
    }
}
