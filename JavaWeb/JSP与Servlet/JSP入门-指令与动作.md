## include指令  
* include动作  
* include指令与include动作的区别  
* ```<jsp:forward>```动作  
* ```<jsp:param>```动作  
* ```<jsp:plugin>```动作  

#### include指令  
语法： ```<%@ include file="URL"%> ```  
#### include动作：动作标签  
语法： ```<jsp: include page="URL" flush="ture|false"/>  ```

#### include指令与include动作的区别  

     Null | include指令|include动作|
     --- | --- | --- |
    语法格式 |`<%@ include file="..."%>`  | `<jsp: include page="..."/>` |
     发生作用的时间 | 页面转换期间 | 请求期间 |
     包含的内容 | 文件的实际内容，被包含页面的源代码 | 被包含页面的输出结果 |
     转换成的Servlet | 主页面和包含页面转换为一个Servlet | 主页面和包含页面转换为独立的Servlet |
     编译时间 | 较慢——资源必须被解析 | 较快 | 
     执行时间 | 稍快 | 较慢——每次资源必须被解析 |

#### \<jsp:forward\>动作  
语法: ```<jsp:forward page="URL"/> ```  
等同于JSP中request内置对象的一个服务器跳转的方法：  
```request.getRequestDospatcher("/url").forward(request,response); ``` 
#### \<jsp:param\>动作  
```<jsp:param name="参数名" value="参数值">  ```  
常常与```<jsp:forward >```一起使用，作为其子标签  