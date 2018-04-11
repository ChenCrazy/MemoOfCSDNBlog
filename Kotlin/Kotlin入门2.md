 ## 数组
 为了放在频繁装箱拆箱耗费资源,内置了基本类型定制的Array,在Arrays.kt包中:
 * `ByteArray`,`CharArray`,`ShortArray`,`IntArray`,`LongArray`,`FloatArray`,`DoubleArray`,`BooleanArray`
 * 以上数组类型都有六个内置方法`constructor XXXArray(Int,(Int)->XXX)`,`get(Int:XXX)`,`set(Int,XXX):Unit`,`size:Unit,iterator():XXXIterator`,`clone():XXXArray`,XXX为对应类型
 * .joinToString()
 * 字符串切片
 * array.length

 ## 面向对象的基本概念
 ## 抽象类 接口
 ```kotlin
 interface Aaa{
   fun xxx()
 }
 interface Bbb:Aaa{//接口可以继承接口
 }
```

* 抽象类是已经具有了类的特征的,抽象类和普通类一样可以有内部的状态
  * 如抽象类的成员变量并可以赋值.而接口只能定义不能赋值,那么不赋值就不知道变量类型,所以显式声明其类型,此时却又相当于隐式声明了此变量的get,set接口方法而非变量.  
  * 抽象类的接口方法可以有具体实现供子类继承,而接口即使也能提供默认方法实现,但此方法却不能有状态,即没有具体逻辑实现
  * 类有构造方法,而接口没有,所以子类在继承父类时其实是在继承父类的构造器,如
  
  ```kotlin
    abstract class A{
      var a = 233
      open fun hello(){
        println(a)
      }
    }
    interface B{
      var i:Int
      fun hello(){
        println(i)//此处的输出并没有状态,因为i变量本身没有状态
      }     
    }
    interface C{
    }

    class D(override var i:Int):A() B C{//此处的A()即为继承的构造函数

    }

    ```

  * 设计思想上,抽象类反映本质,接口体现能力,

## 继承
* 一个类想要被继承,方法属性想要被覆写,需要先open,
* 覆写关键字都是override,而java只是用注解,并不严格
* 当然abstract类和abstract方法和接口和接口方法被复写时不需要open权限
