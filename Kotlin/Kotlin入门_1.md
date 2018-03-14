## 类型
* Boolean
  * ture
  * false
* Number:将Java中的基本类型与包装类型合并,自动区别
  * Double:64bit
  * Float:32bit
  * Long:64bit
  * Int:32bit,包括Integer和int
  * Short:16bit
  * Byte:8bit
* String

## 声明

* 变量 `var`
* 函数 `fun`
* 类 `class`
  * 构造方法 `init`
  * 类继承 [可深究](https://www.jianshu.com/p/eb17d5e2528b)
* 字符串模版 `$xxx`

```kotlin
  //变量的声明
  var anInt: Int = 5
  //Java中long类型包含int类型会隐式转换,而Kotlin不允许隐式转换
  var aLong: Long = anInt.toLong()
  var aChar: Char = 'macbeth'
  var string: String = "macbeth"
  var rawString: String = '''这里可以输出一段换行文本
      但是不解析转义字符
      '''
  //函数的声明
  fun getAge(age: Int){
    //过程
  }
  //类的声明
  class AddClass constructor(var arg1: Int, var arg2: Int){
    // 当类只有一个构造器时constructor关键字可省略
    // 当类中无任何定义花括号可省略
    init{
      print("$arg1 + arg2 = $(arg1+arg2)")
    }
  }


  // new一个对象
  var addClass: AddClass = AddClass()

  //继承一个类
  //根据构造器和覆盖方法可分很多形式
  class FuClass(arg1: String, arg2: String){
    init{
      print("$arg1,$arg2父类")
    }
  }
  class ZiClass(arg1: String, arg2: String, var arg3: Int): FuClass(arg1, arg2){

  }
```

**Any是所有类的父类**
  

kotlin中 **==等价于equals**,**===才表示比较对象的引用值**