
## this
* this 要在执行时才能确认值, 定义时无法确认
```javascript
var a = {
    name:'A',
    fn: function(){
      console.log(this.name)
    }
}

a.fn()//this === a
a.fn.call({name: 'B'})//this === {name: 'B'}
var fn1 = a.fn
fn1() // this === window

```

* this变化场景
  * 作为构造函数执行
  * 作为对象属性执行
  * 作为普通函数执行
  * call apply bind函数

  ## 作用域
* 没有块级作用域
* 函数作用域和全局作用域
* 作用域链
* 闭包
 * 函数作为返回值
 * 函数作为参数传递

 ## 异步
 * 异步和同步的区别
  * alert会阻塞程序运行
  * 异步和单线程
 * setTimeout
  ```JavaScript
    console.log(100)
    setTimeout(function(){
      console.log(200)
    }, 1000)//一秒后打印
    console.log(300)
  ```
 * 前端异步场景
  * 需要等待的时候就需要异步
  * 定时任务:setTimeout毫秒单位,valsetInver
    * 所有类如setTimeout异步场景的函数都会被后加载
  * 网络请求:ajax请求,外部文件加载,动态`<img>`加载
  * 事件绑定
*   
