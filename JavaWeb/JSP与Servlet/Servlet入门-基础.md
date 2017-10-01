Servlet基础

[Servlet API]( https://javaee.github.io/javaee-spec/javadocs/javax/servlet/package-summary.html)  
[http API]( https://javaee.github.io/javaee-spec/javadocs/javax/servlet/http/package-summary.html)  
[JSP API]( https://javaee.github.io/javaee-spec/javadocs/javax/servlet/jsp/package-summary.html)  
[JSON API]( https://javaee.github.io/javaee-spec/javadocs/javax/json/package-summary.html )   

## 一.Servlet简介  

* 1)Servlet是按照Servlet规范编写的Java类。
* 2)Servlet应用请求/响应模型，扩展了服务器的功能。
* 3)Servlet是WEB应用程序中的一个组件。

一个Servlet就是一个Java类，是在服务器上运行的程序，并且可以通过 “请求-响应”编程模型来访问这个驻留在服务器内存里的Servlet程序。  
Servlet是一种独立于平台和协议的服务器端的Java技术，可以用来生成动态的Web页面与传统的CGI（公共网关接口）和许多其他类似CGI技术相比，Servlet具有更好的可移植性、更强大的功能，更少的投资，更高的效率，更好的安全性等特点。  
注意：Servlet不是从命令行启动的，而是由包含Java虚拟机的Web服务器进行加载  


### TomCat容器分为四个等级，Servet的容器管理Context容器，一个Context对应一个Web工程  

> Container容器 ——包含——> Engine引擎容器 ——包含——> HOST主机容器 ——包含——> Servlet容器
>————> 多个Context容器 ——包含——> 多个Wrapper包装类

Servlet可以管理很多上下文Context容器，一个Context就是一个Web工程，Servlet会为每个应用创建一个ServletContext对象，用于管理应用和动态资源之间共享数据。

## 二.手工编写Servlet三个步骤
    
1️⃣首先编写一个Java类，继承父类，常用HttpServlet。  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Servlet(interface) ---> GencricServlet(Abstract Class) ---> HttpServlet(Abstract Class) --->自定义Servlet**  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.`Servlet`接口：三个生命周期方法 Init() service() desrtoy()  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.`GencricServlet`继承类: 与协议无关的Servlet，获取配置信息，最少只需要实现`public void service(ServletRequest sRequest, ServletResponse sResponse)`一个方法  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.`HttpServlet`: 实现了Http的Servlet 至少需要override覆盖doGet()/doPost()方法  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.自定义Servlet: 以上三个类/接口可选择合适的继承  
2️⃣重写doGet()/doPost方法，取决于用户提交信息的方式  
3️⃣在web.xml中注册这个Servlet  
        ```xml
        <servlet>
            <servlet-name>BServlet</servlet-name>//Servlet名称
            <servlet-class>cn.test.web.servlet.BServlet</servlet-class>//类所在的地址
        </servlet>
        <servlet-mapping>
            <servlet-name>BServlet</servlet-name>
            <url-pattern>/BServlet</url-pattern>//Servlet的虚拟URL地址
        </servlet-mapping>
    ```

## 三.Servlet执行流程  
以Get方式请求执行顺序  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请求HelloServlet ————> 用户点击超链接`<a herf="servlet/Servlet"></a>`  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请求提交后，服务器就会在web.xml文件中寻找与之相对应的URL地址  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;以此地址`<url-pattern>`寻找它的Servlet名字`<servlet-name>`   
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;以此名字寻找`<servlet-class>`Servlet这个处理类，  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;然后根据用户的具体提交请求方式，由于使用的是超链接，服务器分析请求完毕，选择使用doGet/doPost来执行这个类中重写的方法  


## 四.Servlet生命周期(服务器会自动调用的方法)
**初始化阶段**：调用类的构造方法创建Servlet实例，  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;然后调用Init(ServletConfig)方法，若有实例就不会在创建、调用，直接执行下一步。Servlet创建Servlet实例时(构造方法后)，首先创建Servlet类的初始化方法Init(ServletConfig)，在整个生命周期内，此初始化方法只被调用一次，**注意init()带参，只有带参才会被服务器自动调用**  
**响应客户端请求**: 调用Service()方法  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;由Service方法根据提交方式选择执行doGet/doPost方法  
**终止阶段**: 调用destroy()方法  
  
* 1、WEB服务器(Tomcat)首先会找到该Servlet并装载该Servlet  
* 2、WEB服务器(Tomcat)会创建该Servlet的实例  
* 3、WEB服务器(Tomcat)会调用实例对象的init()方法  
* 4、WEB服务器(Tomcat)创建一个封装HTTP请求消息的
> HttpServletRequest对象和一个代表HTTP响应消息的HttpServletResponse对象，然后调用service()方法并将请求对象和响应对象作为参数传递进去。(实现是通过多线程技术)
* 5、WEB服务器在某种情况，停止对该Servlet支持，Servlet引擎将卸载该Servlet，在卸载之前会调用Servlet的destroy()方法进行销毁  

## 五.TomCat容器装载Servlet()的三种情况 
1.Servlet容器启动时会自动装载某些Servlet，实现它只需要在web.xml文件中配置  
&nbsp;&nbsp;&nbsp;&nbsp; 当服务器启动时，所有的Servlet都被加载，而它们之间也会有一个加载顺序，在 `<Servlet></Servlet>`之间添加`<loadon-startup>1</loadon-startup>`代码**数字越小表示优先级越高**

2.当Servlet容器启动后，客户端首次向Servlet发送请求后，TomCat服务器也会自动加载  

3.Servlet类文件被更新后，服务器会重新装载Servlet。Servlet是长期驻留在内存的，当Servlet在服务器中被加载之后，这个Servlet就会长期被保存在服务器的内存中  


## 六.使用Servlet获取九大内置对象  

   | Jsp对象                |      使用Servlet的获取方式 |
   |     ---      |  ---------       |
   | Out                   |       response.getWriter() |
   | request         |         service方法中的request参数 |
   | response        |        service方法中的response参数 |
   |  session        |           request.getSession()函数 |
   |  application    |          getServletContext()函数 |
   | exception      |           Throwable |
   |  page          |       this |
   |  pageContext     |        pageContext |
   |  Config       |         getServletConfig函数 |

## 七.JSP和Servlet的区别
1、JSP是Servlet技术的扩展，本质上就是Servlet的简易方式。但是两者的创建方式不一样。  
2、Servlet完全是JAVA程序代码构成，擅长于流程控制和事务处理，通过Servlet来生成动态网页很不直观.  
3、Servlet和JSP最主要的不同点在于，Servlet的应用逻辑是在Java文件中，并且完全从表示层中的HTML里分离开来。  
&nbsp;&nbsp;&nbsp;&nbsp; 而JSP是Java和HTML标签构成一个扩展名为.jsp的文件，可以方便地编写动态网页。  

JSP侧重于视图，Servlet主要用于控制逻辑。  
因此在实际应用中采用Servlet来控制业务流程，而采用JSP来生成动态网页.在struts框架中，JSP位于MVC设计模式的视图层，而Servlet位于控制层.  

## 八.另Servlet细节

1.Servlet中尽量不要创建成员，创建局部变量即可  
2.Servlet中尽量创建无状态成员，  
3.Servlet可以创建有状态成员，但状态必须为只读，即只提供读取方法而没有存储方法  

