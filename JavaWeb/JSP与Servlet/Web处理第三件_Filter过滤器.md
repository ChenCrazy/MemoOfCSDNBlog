# JavaWeb三大组件

## 共同点
都需要在web.xml中进行配置

* Servlet
* Listener(2个感知监听器不需要配置)
* Filter

## 过滤器

* 它会在一组资源(jsp、servlet、.css、.html等等)的前面执行
* 它可以让请求得到目标资源,也可以不让请求达到
* 过滤器有拦截请求的能力

登录:允许它访问AServlet、BServlet、CServlet

-------------------------------

## 过滤器如何编写

1. 写一个类实现Filter接口
2. 在web.xml中进行配置

## Filter接口

* **void init(FilterConfig)**
  * 在服务器启动时会创建Filter实例,并且每个类型的Filter只创建一个实例,在创建完Filter实例后,会马上调用init()方法完成初始化工作,这个方法只会被执行一次
* **void destory()**
  * 销毁之前执行,服务器会在创建Filter对象之后,把Filter放到缓存中一直使用,通常不会销毁它。一般会在服务器关闭时销毁Filter对象,在销毁Filter对象之前,服务器会调用Filter对象的destory()方法
* **void doFilter(ServletRequest,ServletResponse,FilterChain)**
  * 这个方法会在用户每次访问"目标资源"`<url->pattern>index.jsp</url-pattern>`时执行,如果需要"放行",那么需要调用`FilterChain`的doFilter(ServletRequest,ServletResponse)方法,如果不调用FilterChain的doFilter()方法,那么目标资源将无法执行

## 特点
Filter是单例的

```xml
web.xml
<filter>
  <filter-name>xxx</filter-name>
  <filter-class>cn.itcast.web.filter.AFitler</fitler-class>
</filter>

<fitler-mapping>
  <filter-name>xxx</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>
```

-------------------------------

### FilterConfig

* FilterConfig-->与ServletConfig相似
  * 获取Filter的初始化配置:`String getInitParameter(String name)`,与<init-param>元素对应
  * 获取过滤器配置名称:`String getFilterName()`,与`<filter-name>`元素对应
  * 获取appliction:`ServletContext getServletContext()`

### FilterChain

* `doFilter(ServletRequest, ServletResponse)`:只有两参数。放行,就相当于调用了目标Servlet的service()方法

-------------------------------

## 多过滤器执行顺序
`<filter-mapping>`的配置顺序决定了过滤器的执行顺序  

FilterChain#doFilter()方法:执行目标资源,或是执行下一个过滤器如果没有下一个过滤器那么执行的是目标资源,如果有,那么就执行下一个过滤器

-------------------------------

## 过滤器的四种拦截方式

```xml
  <dispatcher>REQUEST</dispatcher> <!-- 默认的、请求 -->
  <dispatcher>FORWARD</dispatcher> <!-- 转发 -->
  <dispatcher>INCLUDE</dispatcher> <!-- 包含 -->
  <dispatcher>ERROR</dispatcher> <!-- 错误 -->
    <error-page>
      <error-code>404</error-code>
      <location>404.jsp</location>
    </error-page>>
```

在`<filter-mapping>`中进行配置


## 过滤器的应用场景

*	执行目标资源之前做预处理工作,例如设置编码,这种试通常都会放行,只是在目标资源执行之前做一些准备工作;例如,几乎是所有的Sevlet中都需要写request.setCharacterEndoing()可以把它放入到一个Filter中
*	通过条件判断是否放行,例如校验当前用户是否已经登录,或者用户IP是否已经被禁用
*	在目标资源执行后,做一些后续的特殊处理工作,例如把目标资源输出的数据进行处理,回程拦截



