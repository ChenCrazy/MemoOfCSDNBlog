## XML约束
编写一个文档来约束一个XML文档的书写规范，称为XML约束

## DTD(Document Type Definition)
全称为文档类型定义，保存文件为dtd格式

* XML文件使用DOCTYPE声明语句来指明引用的约束：
  * 当引用的文件在本地时，`<!DOCTYPE 文档根节点名 SYSTEM"DTD文件的URL">`
  * 当引用的文件是一个公共的文件时，`<!DOCTYPE 文档根节点名 PUBLIC"DTD名称" "DTD文件的URL">`
* 元素定义
  * 文档中使用ELEMENT声明一个XML元素元素类型可以是元素内容或类型
  * 若为元素内容，则要用写在()中

  ```xml
  <!ELEMENT 根节点名 (值名，即子节点名+)>
      <!ELEMENT 子节点名 (各个属性名组)>
      <!ELEMENT 各个属性 (#PCDATA)>
      //Parsed Character Data被解析的字符数据
  ```

  * 若为元素类型则直接书写，DTD规范类型
    * EMPTY:用于空元素例如`<br/><hr/>`
    * ANY:表示元素内容为任意类型
  * 元素属性组若用逗号分割则表示层级先后关系，使用|分割则表示任选其一,使用空格隔开则无顺序
  * 在元素内容中可以使用+*？等符号表示元素出现的次数
    * +表示一次或多次
    * *表示0次或多次
    * ?表示0次或一次
    * 可用()多层批量表示`<!ELEMENT ROOTNAME((TLTLE*,S1?,S2)*|S3)>`
* 属性定义：XML通过ATTLIST为其设置属性
  ```xml
  <!ATTLIST 元素名
      属性1  属性值类型CDATA普通文本字符串  #设置说明REQUIRED必须
      属性2  属性值类型ENUMERATED枚举  #设置说明IMPLTED可选
      属性3  属性值类型ID字母开头顺序  #设置说明FIXED固定值
      属性3  属性值类型ENTITY实体  #设置说明可省略则默认
      ...
    >
  ```
* 实体定义
  * 用于为一段内容创建一个标记名，以后在XML文档中就可以使用标记名引用这段内容
  * 在DTD定义中，一条<！ENTITY ...>语句定义一个实体，实体分为两种类型：引用实体和参数实体
  * 引用实体：主要在XML文档中应用
    * 语法格式：`<!ENTITY 实体名称 “实体内容”>`：直接转变成实体内容
    * 引用方式：&实体名称
  * 参数实体：被DTD文件自身使用
    * 语法`<!ENTITY %实体名称 “实体内容”>`
    * 引用方式：%名称

>还可参考W3C文档

## Shcema

* DTD语法怪异
* 没有数据类型
* 没有针对DTD的编程接口
* Schema简单，本身就是XML

### 示例

  ```xml
     <xs:schema xmlns:xs="http://www.w3.org/****/XMLSchema">
        <xs:element name="quantity" typ="nonNegativelnteger">
        </xs:element>
     </xs:schema>
     <quantity>5</quantity>
     <quantity>-15</quantity>
    ```
