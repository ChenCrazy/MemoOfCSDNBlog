# Listener：监听器
AWT:ActionListener、SAX:xml的事件驱动解析方式

## 监听器特点：

* 是一个接口，内容由我们手动实现；
* 需要注册，例如注册在按钮上
* 监听器中的方法，会在特殊事件发生时被调用

## 观察者模式：

* 事件源；
  * 小偷
* 事件；
  * 偷东西
* 监听器；
  * 警察:注意在小偷身上,注册事件源
  * 监听器中的方法：抓捕,一个监听器可以对应事件源的状态有多个方法

## JavaWeb中的监听器

### 事件源：三大域ServletContext、HttpSession、ServletRequest

* 1.ServletContext
  * 生命周期监听：ServletContextListener，它有两个方法，一个在出生时调用，一个在死亡时调用；
    * void contextInitialized(ServletContextEvent sce)：创建SErvletcontext时
    * void contextDestroyed(ServletContextEvent sce)：销毁Servletcontext时
  * 属性监听：ServletContextAttributeListener，它有三个方法，一个在添加属性时调用，一个在替换属性时调用，最后一个是在移除属性时调用。
    * void attributeAdded(ServletContextAttributeEvent event)：添加属性时；
    * void attributeReplaced(ServletContextAttributeEvent event)：替换属性时；
    * void attributeRemoved(ServletContextAttributeEvent event)：移除属性时；
* 2.HttpSession
  * 生命周期监听：HttpSessionListener，它有两个方法，一个在出生时调用，一个在死亡时调用；
    * void sessionCreated(HttpSessionEvent se)：创建session时
    * void sessionDestroyed(HttpSessionEvent se)：销毁session时
  * 属性监听：HttpSessioniAttributeListener，它有三个方法，一个在添加属性时调用，一个在替换属性时调用，最后一个是在移除属性时调用。
    * void attributeAdded(HttpSessionBindingEvent event)：添加属性时；
    * void attributeReplaced(HttpSessionBindingEvent event)：替换属性时
    * void attributeRemoved(HttpSessionBindingEvent event)：移除属性时
* 3.ServletRequest
  * 生命周期监听：ServletRequestListener，它有两个方法，一个在出生时调用，一个在死亡时调用；
    * void requestInitialized(ServletRequestEvent sre)：创建request时
    * void requestDestroyed(ServletRequestEvent sre)：销毁request时
  * 属性监听：ServletRequestAttributeListener，它有三个方法，一个在添加属性时调用，一个在替换属性时调用，最后一个是在移除属性时调用。
    * void attributeAdded(ServletRequestAttributeEvent srae)：添加属性时
    * void attributeReplaced(ServletRequestAttributeEvent srae)：替换属性时
    * void attributeRemoved(ServletRequestAttributeEvent srae)：移除属性时

* 4.javaWeb中完成编写监听器：
  * 写一个监听器类：要求必须去实现某个监听器接口；
  * 注册，是在web.xml中配置来完成注册！
  * 可以在监听器中放一些在Tomcat启动时就要完成的代码
* 5.事件对象：
  * ServletContextEvent：ServletContext getServletContext()
  * HttpSessionEvent：HttpSession getSession()
    * ServletRequest：
    * ServletContext getServletContext()；
    * ServletReques getServletRequest()；
  * ServletContextAttributeEvent：
    * ServletContext getServletContext()；
    * String getName()：获取属性名
    * Object getValue()：获取属性值
  * HttpSessionBindingEvent：略
  * ServletRequestAttributeEvent ：略

感知监听（都与HttpSession相关）

* 它用来添加到JavaBean上，而不是添加到三大域上！
* 这两个监听器都不需要在web.xml中注册！

HttpSessionBindingListener：添加到javabean上，javabean就知道自己是否添加到session中了。

## 操作域属性的监听器

当对域属性进行增、删、改时，执行的监听器一共有三个：

* ServletContextAttributeListener：在ServletContext域进行增、删、改属性时调用下面方法。
  * public void attributeAdded(ServletContextAttributeEvent evt)
  * public void attributeRemoved(ServletContextAttributeEvent evt)
  * public void attributeReplaced(ServletContextAttributeEvent evt)
* HttpSessionAttributeListener：在HttpSession域进行增、删、改属性时调用下面方法
  * public void attributeAdded(HttpSessionBindingEvent evt)
  * public void attributeRemoved (HttpSessionBindingEvent evt)
  * public void attributeReplaced (HttpSessionBindingEvent evt) 
* ServletRequestAttributeListener：在ServletRequest域进行增、删、改属性时调用下面方法
  * public void attributeAdded(ServletRequestAttributeEvent evt)
  * public void attributeRemoved (ServletRequestAttributeEvent evt)
  * public void attributeReplaced (ServletRequestAttributeEvent evt)

下面对这三个监听器的事件对象功能进行介绍：

* ServletContextAttributeEvent
  * String getName()：获取当前操作的属性名；
  * Object getValue()：获取当前操作的属性值；
  * ServletContext getServletContext()：获取ServletContext对象。
* HttpSessionBindingEvent
  * String getName()：获取当前操作的属性名；
  * Object getValue()：获取当前操作的属性值；
  * HttpSession getSession()：获取当前操作的session对象。
* ServletRequestAttributeEvent
  * String getName()：获取当前操作的属性名；
  * Object getValue()：获取当前操作的属性值；
  * ServletContext getServletContext()：获取ServletContext对象；
  * ServletRequest getServletRequest()：获取当前操作的ServletRequest对象。

## HttpSession的监听器
还有两个与HttpSession相关的特殊的监听器，这两个监听器的特点如下:

* 不用在web.xml文件中部署；
* 这两个监听器不是给session添加，而是给Bean添加。即让Bean类实现监听器接口，然后再把Bean对象添加到session域中。

下面对这两个监听器介绍一下：
>HttpSessionBindingListener：当某个类实现了该接口后，可以感知本类对象添加到session中，以及感知从session中移除。例如让
Person类实现HttpSessionBindingListener接口，那么当把Person对象添加到session中，或者把Person对象从session中移除时会调用下面两个方法：

* public void valueBound(HttpSessionBindingEvent event)：当把监听器对象添加到session中会调用监听器对象的本方法；
* public void valueUnbound(HttpSessionBindingEvent event)：当把监听器对象从session中移除时会调用监听器对象的本方法；

这里要注意，HttpSessionBindingListener监听器的使用与前面介绍的都不相同，当该监听器对象添加到session中，或把该监听器对象从session移除时会调用监听器中的方法。并且无需在web.xml文件中部署这个监听器。
示例步骤：

* 编写Person类，让其实现HttpSessionBindingListener监听器接口；
* 编写Servlet类，一个方法向session中添加Person对象，另一个从session中移除Person对象；
* 在index.jsp中给出两个超链接，分别访问Servlet中的两个方法。

## session的序列化与反序列化：持久化

* HttpSessionActivationListener：Tomcat会在session从时间不被使用时钝化session对象，所谓钝化session，就是把session通过序列化的方式保存到硬盘文件中。当用户再使用session时，Tomcat还会把钝化的对象再活化session，所谓活化就是把硬盘文件中的session在反序列化回内存。当session被Tomcat钝化时，session中存储的对象也被纯化，当session被活化时，也会把session中存储的对象活化。如果某个类实现了HttpSessionActiveationListener接口后，当对象随着session被钝化和活化时，下面两个方法就会被调用：
  * public void sessionWillPassivate(HttpSessionEvent se)：当对象感知被活化时调用本方法；
  * public void sessionDidActivate(HttpSessionEvent se)：当对象感知被钝化时调用本方法；

HttpSessionActivationListener监听器与HttpSessionBindingListener监听器相似，都是感知型的监听器，例如让Person类实现了HttpSessionActivationListener监听器接口，并把Person对象添加到了session中后，当Tomcat钝化session时，同时也会钝化session中的Person对象，这时Person对象就会感知到自己被钝化了，其实就是调用Person对象的sessionWillPassivate()方法。当用户再次使用session时，Tomcat会活化session，这时Person会感知到自己被活化，其实就是调用Person对象的sessionDidActivate()方法。<br>
注意，因为钝化和活化session，其实就是使用序列化和反序列化技术把session从内存保存到硬盘，和把session从硬盘加载到内存。这说明如果Person类没有实现Serializable接口，那么当session钝化时就不会钝化Person，而是把Person从session中移除再钝化！这也说明session活化后，session中就不在有Person对象了。

## 国际化
略