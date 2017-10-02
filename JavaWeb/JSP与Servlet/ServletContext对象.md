# ServletContext上下文对象
## 概述

* 服务器会在启动时为每个应用创建一个ServetContext对象，在关闭时会将他们销毁。存在于整个服务器运行期间。
* 一个项目只有一个ServletContext对象，故常取名application。
* ServletContext对象的作用是在整个Web应用的动态资源之间共享数据。我们可以在多个Servlet中获取这个唯一的对象传递数据，使用它给对个Servlet传递数据。
* 例如：AServlet中向ServletContext对象中保存了一个值，然后在BServet中就可以获取这个值。起到一个“中间商”的作用，降低了程序之间的耦合。

## 获取ServletContext

* 1.在Servlet中获取ServletContext对象：利用ServletConfig类的getServletContext()方法可以获取ServletContext对象
  ```java
  public class AServlet implements Servlet{
    public void init(ServletConfig config){
      ServletContext context= config.getServletContext();
    }
  }
  ```
* 2.在GenericServlet或HttpServlet中获取ServletContext对象：
  * 在GenericServlet类中有 `getServletContext()`方法，可以直接使用`this.getServletContext()`来获取
  ```java
  public class AServlet extends HttpServlet{
    public void doGet(HttpServletRequsrt hSrequest, HttpServletResponse hSreponse){
      ServletContext context= this.getServletContext();
      //相当于this.getServletConfig().getServletContext();
    }
  }
  ```
* HttpSession的getServletContext()方法
* ServletContextEvement的getServletContext()方法
* ... ...

## 域对象的功能
JavaWeb四大域对象：  
  > PageContext
  >ServletRequsrt
  >ServletContext
  >HttpSession
JSP会全部包含，所有的域对象都有存取数据的能力，因为域对象内部有一个Map用来存储数据。  
下面ServletContext对象用来操作数据的方法：  

* `void setAttribute(String name,Object value)`:用来存储一个对象，也可以称之为存储一个域属性
  * **例如**：servletContext.setAttribute(“xxx”, “XXX”)，在ServletContext中保存了一个域属性，域属性名称为xxx，域属性的值为XXX。
  * _请注意_，如果多次调用该方法，并且使用相同的name，那么会覆盖上一次的值，这一特性与Map相同；
* `Object getAttribute(String name)`：用来获取ServletContext中的数据，当前在获取之前需要先去存储才行
    * **例如**：String value = (String)servletContext.getAttribute(“xxx”);，获取名为xxx的域属性XXX；
* `void removeAttribute(String name)`：用来移除ServletContext中的域属性,删除键值对,如果参数name指定的域属性不存在，那么本方法什么都不做；
* `Enumeration getAttributeNames()`：获取该Servlet中所有域属性的名称

## 获取应用初始化参数

* Servlet也可以获取在web.xml文件中配置的应用初始化参数，但它是局部的参数；也就是说，一个Servlet只能获取自己的初始化参数，不能获取别人的，即初始化参数只为自己的一个Servlet准备！
* 可以配置公共的初始化参数，为所有Servlet而用！这需要使用ServletContext来才能获取！

```xml
<web-app>
<context-param>
  <param-name>paramName1</param-name>
  <param-value>paramValue1</param-value>
</context-param>
<context-param>
  <param-name>paramName2</param-name>
  <param-value>paramValue2</param-value>
</context-param>
<!-- 
<servlet></servlet>
... ...
<servlet-mapping></servlet-mapping>
 -->
</web-app>
```

```java
ServletContext context = this.getServletContext();
//获取Context对象
String value1 = context.getInitParameter("paramName1");
System.out.println(value1);
/***/
Enumeration names = context.getInitParameterNames();
while(names.hasMoreElements()) {
  System.out.println(names.nextElement());
}

```

## 获取真实路径
还可以使用ServletContext对象来获取Web应用下的资源，例如在hello应用的根目录下创建a.txt文件，现在想在Servlet中获取这个资源，就可以使用ServletContext来获取。

* 获取a.txt的真实路径：String realPath = this.getServletContext.getRealPath(“/a.txt”)，realPath的值为a.txt文件的绝对路径：F:\tomcat6\webapps\hello\a.txt；
* 获取b.txt的真实路径：String realPath = this.getServletContext.getRealPath(“/WEB-INF/b.txt”)；

###　获取资源流
不只可以获取资源的路径，还可以通过ServletContext获取资源流，即把资源以输入流的方式获取：

* 获取a.txt资源流：InputStream in = this.getServletContext.getResourceAsStream(“/a.txt”)；
* 获取b.txt资源流：InputStream in = this.getServletContext.getResourceAsStream(“/WEB-INF/b.txt”)；

### 获取指定目录下所有资源路径
还可以使用ServletContext获取指定目录下所有资源路径，例如获取/WEB-INF下所有子资源的路径(只一层)：
  ```java
  Set<String> set = this.getServletContext.getResourcePaths("/WEB-INF");
  System.out.println(set);
  //[/WEB-INF/lib/, /WEB-INF/classes/, /WEB-INF/b.txt, /WEB-INF/web.xml]
  ```
**注意，本方法必须以“/”开头！**  

## 练习：访问量统计
一个项目中所有的资源被访问都要对访问量进行累加
创建一个int()类型的变量，用来保存访问量，然后把它保存到ServletContext的域中，这样可以保存所有的Servlet都可以访问

* 最初，ServletContext中没有保存访问量相关的属性
* 当本站第一次被访问时，创建一个变量，设置其值为1；保存到ServletContext中；
* 当以后的访问时，就可以从ServletContext中获取这个变量，然后在其基础之上加１
* 获取ServletContext对象，查看是否存在名为count的属性，如果存在，说明不是第一次访问，如果不存在，说明是第一次访问；
  * 第一次访问：调用Servletcontext的setAttribute()传递一个属性，名为count，值为1；
  * 第2~N次访问：调用ServletContext的getAttribute()方法获取原来的访问量，给访问量加1，再调用Servletcontext的setAttribute()方法完成设置。

