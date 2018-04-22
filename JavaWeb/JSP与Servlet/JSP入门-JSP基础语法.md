## JSP基础语法
元素构成: 静态内容 指令 表达式  脚本  声明  注释 

## 指令instruction 
1. page  `<%@pages 属性="值" %>`页面属性设置
	* language默认Java、import、contenType默认text/html,ISO-8859-1
2. inclide  `<%@inclide    %>`导入其他JSP文件
3. taglib  `<%taglib    %>` 标签库 自定义标签

## 注释annotation
* `<!--HTML注释 -->`客户端可见
* `<%--JSP注释 --%>`客户端不可见
* `//`JSP脚本 `/*JSP脚本注释*/`

## 表达式Expression
`<%= 表达式，无分号结束 %>`

## 脚本Script
`<% Java代码，末尾有分号; %>`

声明statement
`<%! Java代码 %>` 定义变量定义方法

## JSP生命周期：
- 用户请求index.jsp ————> 服务器判断是否为第一次请求  
	- 若是 ————> JSP引擎把JSP文件转换成一个Servlet，java类文件 即编译Java代码，该构造构造，  
	- 该调用调用————> 执行jspInit()生成字节码文件(存放在TomCatWork文件夹)，
	- 若不是第一次请求这个资源则直接访问此字节码文件
- 最后————> 解析执行jspservice()方法
        
- jspservice()被调用来处理客户端的请求，对每个请求都创建一个新的进程，每个客户端请求对应一个进程,提高系统的并发性及响应时间，但也要注意多线程的同步问题,由于该Selvlet始终驻于内存所以响应很快。

