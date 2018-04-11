## 类及其成员的可见性
* private
* public(default)
* protected
* internal (模块/包内可见)   
## 伴生对象
* 包级别的函数,静态方法
* Java是面向对象语言,不允许方法存在于类的外面,但是总有些方法用不上创建单独的类来包装
```kotlin
  fun numberAA(){
    val a = minOf( //somelogic)
  }

  class Lati private constructor(var value:Double){
    companion object{
      //定义一个Lati的伴生对象,方法为ofDouble,这里相当于Java中的静态方法,若想java中可以直接使用可以加上@JvmStatic注解,否则就需要先调用Companion方法
      fun ofDouble(double: Double):Lati{
        return Lati(double)
      }
    }
  }
```
## 扩展成员
被扩展的类.扩展方法,类似静态方法
```kotlin
fun main(args: Array<String>){
  println("abc" * 12)
}

operator fun String.times(int: Int):String{//给String类扩展一个times方法接收一个数字,循环append最后返回
  val stringBuilder = StringBuilder()
  for(i in 0 until int){
    stringBilder.append(this)
  }
  return stringBuilder.toString()
}

```
* 为现有类添加方法.属性
* fun X.y():Z{...}
* val X.m 注意扩展属性不能初始化,类似接口属性

## 属性代理
```kotlin
class AAA{
  val hello by BBB()//对hello获取时,调用的是BBB的getValue
  var helle by BBB()//对helle获取值,设值时都是BBB的getsetValue方法
}

class BBB{//能够代理的类必须实现getsetValue方法,然后才是其他代理方法
  private var value:String? = null

  operator fun getValue(thisRef: Any?,property:KProperty<*>):String{
    return "Hello"
  }
  operator fun setValue(thisRef: Any?,property:KProperty<*>,value:String):String{
    this.value = value
  }
}
```
* `val/var <property name>: <Type> by <experssion>`