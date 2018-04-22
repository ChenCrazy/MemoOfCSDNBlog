## JSP内置对象
是Web容器创建的一组对象，不使用new关键字即可使用

## 九大内置对象
out request response session application  
page exception  pageContext  config

## out对象

out对象是JspWrite类的实例，是向客户端输出内容常用的对象
    
  * `void println()` 向客户端打印字符串
  * `void clear()` 清除缓冲区内容，如果在flush()之后调用会抛出异常
  * `void clearBuffer()` 清除缓冲区内容，不会向客户端输出任何内容，在flush()之前调用不会抛出任何异常
  * `void flush()` 将缓冲区内容输出到客户端
  * `int  getBuffersize()` 返回缓冲区字节数的大小，若不设置则为0
  * `int  getRemaining()` 返回剩余缓冲区可用字节大小容量
  * `boolean isAutoFlush()` 返回缓冲区已满时是自动清空还是抛出异常
  * `void close()`  关闭输出流

## 表单常用提交方式get post

* `<form name='loginForm' action='动作' method='提交方式' >`  
* `get` 明文，通过URL在报头中提交数据，不超过2KB。效率高、安全性低，适合检索查询等功能
* `post` 封装到报文头标签后的报文内容体中，安全性相对get高

## request对象

客户端的一次请求信息被封装在request对象中，通过它才能了解到客户的需求，然后做出响应。
它是HttpServletRequest类的实例。request对象具有请求域，即完成客户端的请求之前，该对象一直有效。
    
* `String getParameter(String name)` 返回name指定参数的参数值
* `String[] getParameterValues(String name)` 返回包含参数name的所有值的数组
* `void setAttribute(String, Object)` 存储此请求中的属性
* `object getAttribute(String name)` 返回指定属性的属性值
* `String getContenType()` 得到请求体中的MIME类型
* `String getProtocol()` 返回请求用的协议类型及版本号
* `String getServerName()` 返回接受请求的服务器主机名


## response对象

response对象包含了响应客户请求的有关信息，虽然此类很重要，但在JSP中很少直接用到它。  
它是HttpServletResponse类的实例。  
response对象具有页面作用域，即访问一个页面时，该页面内的response对象只能对这次访问有效，其他页面的response对象对当前页面无效  

  * `String getCharacterEncoding()` 返回响应用的何等字符编码
  * `void setContentType(String type)` 设置响应的MIME类型
  * `PrintWriter getWriter()` 返回可以向客户端输出字符的一个对象(注意比较:PrintWriter与内置out对象的区别)
  * `sendRedirect(java.lang.String location)` 重新定向向客户端的请求


### 请求转发和请求重定向
* 请求重定向：客户端行为，response.sendRedirect()，
  * 从本质上讲，等同于两次请求，前一次的请求对象不会报存，地址栏的URL地址会改变
* 请求转发：服务器行为，request.getRequestDispatch().forward(req,resp);  
  * 是一次请求，转发后对象会保存，地址栏的URL地址不会改变



## session对象

* session表示客户端与服务器的一次会话状态，
* session指的是用户在浏览某个网站时从连接到服务器进入网站开始到关闭所有页面离开服务器结束所经过的这段过程，被称为一次会话
  * 从上述定义中可以看到，session实际上是一个特定的时间概念，当用户请求访问服务器时，服务器会为每个不同的用户创建一个session，所有的session都由服务器管理

* session对象是JSP的一个内置对象，是HttpSession类的一个实例
在第一个JSP页面被装载是自动创建，完成会话期管理
当一个用户访问一个服务器时，可能会在服务器的几个页面之间切换，服务器应当通过某种方法知道这是一个客户，这时就需要session对象

  * `long getCreationTime()`: 返回session创建的时间
  * `public String getId()`: 返回session创建时JSP引擎为他设定的唯一ID号
  * `public Object setAttribute(String name, Object value)`: 使用指定名称将对象绑定到此会话
  * `public Object getAttribute(String name)`: 返回此会话中的指定名称绑定在一起的对象，如果没有对象绑定在该名称下，则返回null
  * `String[] getValueNames()`: 返回一个包含此session中所有可用属性的数组
  * `int getMaxInactiveInterval()`: 返回两次请求间隔对长时间此session被取消，即此session的有效时限，单位秒

### session的生命周期
* 创建：
  * 当客户端第一次访问某个jsp或者Servlet的时候，服务器会为当前会话创建一个SessionId,每次客户端向服务端发送请求时，都会被服务器端校验此SessionId，进行辨识是否属于同一次会话之类
* 活动：
  * 某次会话当中通过超链接打开的新页面，属于同一次会话
  * 只要当前会话页面没有全部关闭，重新打开新的浏览器窗口访问同一项目资源时属于同一次会话
  * 除非本次会话的所有页面都关闭后再重新访问某个JSP或者Servlet将会创建新的会话
    注意：尽管创建了新的会话，原有的会话依然存在，只是这个旧的SessionId再也没有客户端会携带它交予服务端校验，所以创建新的会话并不代表旧的会话失效了，除非它已经超时或被销毁
* 销毁：
  * Session销毁只有三种方式：
    * 调用session.invalidate()方法
  * Session过期超时
    * 服务器重启，所有Session都被销毁

## application对象

* application实现了用户间数据的共享，可存放全局变量
* application开始于服务器的启动，终止于服务器的关闭
  * 在用户端前后连接或不同用户之间的连接中，可以对application对象的同一属性进行操作
* application对象是属于服务器的，在任何地方对对象属性操作都将影响到其他用户对此的访问
  * 服务器的启动和关闭决定了application对象的生命
* application对象的ServletContext类的实例

  * `public void setAttribute(String name,Object value)`: 使用指定名称将对象绑定到此会话
  * `public Object getAttribute(String name)`: 返回此会话中的指定名称绑定在一起的对象，如果没有对象绑定在该名称下，则返回null
  * `Enumeration getAtterbuteNames()`: 返回所有可用属性名的枚举
  * `String getServerInfo():` 返回JSP(servlet)引擎名及版本号



## page对象
page对象就是指向当前JSP页面本身，有点像类中this指针或者JS中的Window，它是java.lang.Object类的实例

  * `class getClass()`: 返回此Object的类
  * `int hashCode()`: 返回此Object的hash码
  * `boolean equals(Object obj)`: 判断此Object是否与指定的Object对象相等
  * `void copy(Object obj)`: 判断此Object是否与指定的Object对象相等
  * `Object clone()`: 克隆此Object对象
  * `String toString()`: 把此Object对象转换成String类的对象
  * `void notify()`: 唤醒一个等待的线程
  * `void notifyAll()`: 唤醒所有等待的线程
  * `void wait(int timeout)`: 使一个线程处于等待直到timeout结束或被唤醒
  * `void wait()`: 使一个线程处于等待直到被唤醒

## pageContext对象

* pageContext对象提供了对JSP页面内所有的对象及名字空间的访问
* pageContext对象可以访问到本页所在的session，也可以获取本页面所在的application的某一属性值，相当于页面中所有功能的集大成者
* pageContext对象的本类名也叫PageContext

  * `JspWriter getOut()`: 返回当前客户端响应被使用的JspWriter流(out)
  * `HttpSession getSession()`: 返回当前页中的HttpSession对象(session)
  * `Object getPage()`: 返回当前页的Object对象(page)
  * `ServletRequest getRequest()`: 返回当前页的ServletRequest对象(request)
  * `ServletResponse getResponse()`: 返回当前页的ServletResponse对象(response)
  * `void setAttribute(String name, int scope)`: 在指定范围内取属性值
  * `int getAttributeScope(String name, int name)`: 返回某属性的作用范围
  * `void forward(String relativeUrlPath)`: 使当前页面重导到另一页面
  * `void include(String relativeUrlPath)`: 在当前位置包含另一文件

## Exception对象

* exception对象是一个异常对象，当一个页面在运行过程中发生了异常，就产生这个对象。
如果一个JSP页面要应用到此对象，就必须把isErrorPage设为ture，否则无法编译。详细地，在page中添加属性errorPage="交付的处理页面.jsp"，在被交付页面的page中添加isErrorPage置为ture。
* 它实际上是java.lang.Throwable的实现对象。

  * `String getMessage()`: 返回描述异常的消息
  * `String toString()`: 返回关于异常的简短描述消息
  * `void printStackTrace()`: 显示异常及其栈轨迹
  * `Throwable FillInStackTrace()`: 重写异常的执行栈轨迹
 