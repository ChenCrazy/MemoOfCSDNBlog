### 基本语法

* **文档声明**    
    `<?xml varsion="1.0" encoding="utf-8" standalone="yes" ?>`  
    standalone声明文档是否独立，是否基于另外的文档  
 
* **XML元素**，标签<></> 
* **命名规范**：区分大小写，不能用数字 _ XML开头，不包括空格 ：。  

* **属性**：  
    一个标签可有多个属性，属性值用引号，命名规范  
    属性所代表的信息也可以被改成用子元素的形式来描述  

* **注释**  
    `<!-- 注释 -->`

* **CDATA区**
    在此区域内的内容不被解析原样输出  
    `<![CDATA[ 内容 ]]>`  
* **转义字符**  
对于一些单个字符，若想显示原始样式，也可以使用转义的形式予以处理  
    ```xml
    & ——> &amp;
    < ——> &lt;
    > ——> &gt;
    " ——> &quot;
    ' ——> &apos;
    ```

* **处理指令**  
 使解析引擎知道采用何种方式解析XML，比如使用`xml-stylesheet` 使用CSS文件显示XML  
`<?xml-stylesheet type="text/css" herf="1.css" ?>` 


~~简单至极~~

# XML 描述事物本身(可扩展)
# XSL 展现事物表现形式
# DTD 定义XML语言的语法，约束和校验

## DOM4J解析可以看官方实例
[DOM4J](https://dom4j.github.io)  
[Dom4j的使用(全而好的文章)](http://www.blogjava.net/i369/articles/154264.html)
