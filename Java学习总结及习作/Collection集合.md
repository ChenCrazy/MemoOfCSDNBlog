## 1.Collection集合
比较完备的集合关系图可帮助记忆参考  
[Java集合关系图](https://github.com/zxiaofan/JDK-Study/blob/master/src/java1/util/Java%E9%9B%86%E5%90%88%E5%85%B3%E7%B3%BB%E5%9B%BE.vsdx)  
参考文章  
[JAVA集合类汇总](https://www.cnblogs.com/leeplogs/p/5891861.html)  
[迭代器Iterable与Iterator](https://www.jianshu.com/p/de2bb93a386f)  
### 实现关系
java.util.  
├Collection(extends Iterable<E>)  
│ &nbsp;&nbsp;├List **\*\***  
│ &nbsp;&nbsp;│ ├LinkedList  
│ &nbsp;&nbsp;│ ├ArrayList **\*\***  
│ &nbsp;&nbsp;│ └Vector  
│ &nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;└┄Stack  
│ &nbsp;&nbsp;├Queue   
│ &nbsp;&nbsp;│ └┄Dueue  
│ &nbsp;&nbsp;└┄Set **\*\*\***              
└Map  
&nbsp;&nbsp;&nbsp;├┄AbstractMap  
&nbsp;&nbsp;&nbsp;│ &nbsp;&nbsp;├┄HashMap(implements Map<K,V>, Serializable, Cloneable) **\*\*\***  
&nbsp;&nbsp;&nbsp;│   &nbsp;&nbsp;│   &nbsp;&nbsp;&nbsp;└┄LinkedHashMap  
&nbsp;&nbsp;&nbsp;│   &nbsp;&nbsp;├EnumMap(implements Serializable, Cloneable, Map<K,V>)   
&nbsp;&nbsp;&nbsp;│   &nbsp;&nbsp;├TreeMap(implements NavigableMap<K,V>, Cloneable, Serializable)  
&nbsp;&nbsp;&nbsp;│   &nbsp;&nbsp;└┄WeakHashMap  
&nbsp;&nbsp;&nbsp;└┄Hashtable(implements Map<K,V>, Serializable, Cloneable **extends Dictionary<K,V>**) **\*\*\***  
  
  
> List\<E>, Queue\<E>, Set\<E>都实现Collection接口(和Iterable接口),而Collection接口继承Iterable接口, 它们同属于java.util包下的对外接口.    

接口Collection

> 接口Collection是所有集合类的根类型,主要的一个接口方法：boolean add(Ojbect c).虽返回的是boolean,但不是表示添加成功与否,因为Collection规定：一个集合拒绝添加这个元素,无论什么原因,都必须抛出异常,这个返回值表示的意义是add()执行后,集合的内容是否改了（就是元素有无数量、位置等变化）。类似的addAll,remove,removeAll,remainAll也是一样的。

用Iterator模式实现遍历集合

> &nbsp;&nbsp;&nbsp;&nbsp;Collection有一个重要的方法：iterator(),用于遍历集合的所有元素。Iterator模式可以把访问逻辑从不同类的集合类中抽象出来,从而避免向客户端暴露集合的内部结构。  
&nbsp;&nbsp;&nbsp;&nbsp;要确保遍历过程顺利完成,必须保证遍历过程中不更改集合的内容（Iterator自己的remove()方法除外,属于迭代过程内）,所以,确保遍历可靠的原则是：只在一个线程中使用这个集合,或者在多线程中对遍历代码进行同步。

## 比较
HashMap和HashTable的区别:  
&nbsp;&nbsp;1.Hashtable是线程安全的,方法是Synchronized的,适合在多线程环境中使用,效率稍低；HashMap不是线程安全的,方法不是Synchronized的,效率稍高,适合在单线程环境下使用,所以在多线程场合下使用的话,需要手动同步HashMap,Collections.synchronizedMap()。  
&nbsp;&nbsp;2.HashMap的key和value都可以为null值,HashTable的key和value都不允许有Null值。  
&nbsp;&nbsp;3.HashMap中数组的默认大小是16,而且一定是2的倍数,扩容后的数组长度是之前数组长度的2倍。HashTable中数组默认大小是11,扩容后的数组长度是之前数组长度的2倍+1。  
&nbsp;&nbsp;4.哈希值的使用不同   
&nbsp;&nbsp;5 HashMap 重新计算 hash 值,而且用&代替求模  


(1) **HashMap**：它根据键的hashCode值存储数据,大多数情况下可以直接定位到它的值,因而具有很快的访问速度,但遍历顺序却是不确定的。HashMap最多只允许一条记录的键为null,允许多条记录的值为null。**当get()方法返回null值时,既可以表示HashMap中没有该键,也可以表示该键所对应的值为 null。因此,在HashMap中不能用get()方法来判断HashMap中是否存在某个键,而应该用containsKey()方法来判断。Hashtable的键值都不能为 null,所以可以用get()方法来判断是否含有某个键。** HashMap非线程安全,即任一时刻可以有多个线程同时写HashMap,可能会导致数据的不一致。如果需要满足线程安全,可以用Collections的`synchronizedMap`方法使HashMap具有线程安全的能力,或者使用ConcurrentHashMap。  
* 多线程下HashMap出现的问题：1.多线程put操作后,get操作导致死循环,导致cpu100%的现象。主要是多线程同时put 时,如果同时触发了rehash 操作,会导致扩容后的HashMap中的链表中出现循环节点,进而使得后面get的时候,会死循环。2.多线程put操作,导致元素丢失,也是发生在多个线程对hashMap扩容时。
* HashMap的扩容机制:<br/>
  而负载因子表示一个散列表的空间的使用程度,有这样一个公式：<br/>
  initailCapacity\*loadFactor=HashMap的容量。<br/>
所以负载因子越大则散列表的装填程度越高,也就是能容纳更多的元素,元素多了,链表大了,所以此时索引效率就会降低。
反之,负载因子越小则链表中的数据量就越稀疏,此时会对空间造成烂费,但是此时索引效率高。
当HashMap中的结点个数超过数组大小loadFactor（加载因子）时,就会进数组扩容,loadFactor的默认值为 0.75。也就是说,默认情况下,数组大小为16,那么当 HashMap中结点个数超过 16\*0.75=12的时候,就把数组的大小扩展为2\*16=32,即扩大一倍,然后重新计算每个元素在数组中的位置,并放进去,而这是一个非常消耗性能的操作。  

(2) **Hashtable**：Hashtable是遗留类,很多映射的常用功能与HashMap类似,不同的是它承自Dictionary类,并且是线程安全的,任一时间只有一个线程能写Hashtable,并发性不如ConcurrentHashMap,因为ConcurrentHashMap引入了分段锁。Hashtable不建议在新代码中使用,不需要线程安全的场合可以用HashMap 替换,需要线程安全的场合可以用ConcurrentHashMap替换。  
* HashTable的效率比较低的原因:在线程竞争激烈的情况下HashTable的效率非常低下。因为当一个线程访问HashTable的同步方法时,访问其他同步方法的线程就可能会进入阻塞或者轮询状态。如线程1使用put进行添加元素,线程2不但不能使用put方法添加元素,并且也不能使用get方法来获取元素,所以竞争越激烈效率越低  

(3) **LinkedHashMap**：LinkedHashMap是HashMap 的一个子类,保存了记录的插入顺序,在用Iterator 遍历LinkedHashMap时,先得到的记录肯定是先插入的,也可以在构造时带参数,按照访问次序排序。  
(4) **TreeMap**：TreeMap实现SortedMap接口,能够把它保存的记录根据键排序,默认是按键值的升序排序,也可以指定排序的比较器,当用Iterator 遍历TreeMap时,得到的记录是排过序的。如果使用排序的映射,建议使用TreeMap。在使用TreeMap 时,key 必须实现Comparable 接口或者在构造TreeMap 传入自定义的Comparator,否则会在运行时抛出java.lang.ClassCastException 类型的异常。

### 存储结构-字段
从结构实现来讲,HashMap是数组+链表+红黑树.HashMap就是使用哈希表来存储的。哈希表为解决冲突,可以采用开放地址法和链地址法等来解决问题,Java中HashMap 采用了链地址法。链地址法,简单来说,就是数组加链表的结合。在每个数组元素上都一个链表结构,当数据被Hash 后,得到数组下标,把数据放在对应下标元素的链表上。  
通过什么方式来控制map使得Hash碰撞的概率又小,哈希桶数组（Node[] table）占用空间又少呢？答案就是好的Hash算法和扩容机制。  
在HashMap中,哈希桶数组table的长度length大小必须为2的n 次方(一定是合数),这是一种非常规的设计,常规的设计是把桶的大小设计为素数。相对来说素数导致冲突的概率要小于合数, [具体证明可以参考](http://blog.csdn.net/liuqiyao_01/article/details/14475159),Hashtable初始化桶大小为11,就是桶大小设计为素数的应用（Hashtable扩容后不能保证还是素数）。HashMap采用这种非常规设计,主要是为了在取模和扩容时做优化,同时为了减少冲突,HashMap定位哈希桶索引位置时,也加入了高位参与运算的过程。  

Hash 算法本质上就是三步：取key 的hashCode 值、高位运算、取模运算。

## LinkedList 
[Java源码剖析之LinkedList](Java源码剖析之LinkedList.md)
## ArrayList
[Java源码剖析之ArrayList](Java源码剖析之ArrayList.md)
## HashMap
[Java 8系列之重新认识HashMap](https://tech.meituan.com/java-hashmap.html)  
[HashMap的死循环](http://pettyandydog.com/2016/08/28/HashMap_infinite_loop)  
[HashMap多线程死循环问题](https://blog.csdn.net/xuefeng0707/article/details/40797085)  

## ConcurrentHashMap
[ConcurrentHashMap总结](http://www.importnew.com/22007.html)  
[深入并发包 ConcurrentHashMap](http://www.importnew.com/26049.html)  
[漫画：什么是ConcurrentHashMap?](http://www.sohu.com/a/205451532_684445)

## 比较
[Java中的几个HashMap/ConcurrentHashMap实现分析](http://www.importnew.com/19685.html)  
[Java7//8中的HashMap和ConcurrentHashMap全解析](http://www.importnew.com/28263.html)  
## HashSet
[HashSet实现原理分析](https://www.jianshu.com/p/a5063dd843d5)

