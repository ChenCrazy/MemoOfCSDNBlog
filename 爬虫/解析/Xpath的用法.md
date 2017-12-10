## 语法

表达式 | 说明
---------|----------
 article | 选取所有article元素的所有子节点
 /article | 选取根元素article
 article/a | 选取所有属于article的子元素的a元素
 //div | 选取所有div子元素(不论出现在文档任何地方)
 article//div | 选取所有属于article元素的后代div,不管其出现在article之下的任何位置
 //@class | 选取所有名为class的属性

 ## 定位


表达式 | 说明
---------|----------
 /article/div[1] | 选取属于article子元素的第一个div元素
 /article/div[last()] | 选取属于article子元素的最后一个div元素
 /article/div[last()-1] | 选取属于article子元素的倒数第二个div元素
 //div[@lang] | 选取所有拥有lang属性的div元素
 //div[@lang='eng'] | 选取所有lang属性的eng的div元素

 ## 符号

表达式 | 说明
---------|----------
 /div/* | 选取属于div元素的所有子节点 
 //* | 选取所有元素
 //div[@*] | 选取所有带属性的titlle元素
 /div/a \| //div/p | 选取所有div元素的a和p元素
//span \| //ul | 选取文档中的span和ul元素
article/div/p \| //span | 选取所有属于article元素的div元素的p元素以及文档中所有的span元素

## 函数

**text():** 提取文本  
**extract():** 
  ```python 
    item['title'] = sel.xpath('div[2]/div[2]/text()')
    item['title'] = sel.xpath('div[2]/div[2]/text()').extract()
    item['title'] = sel.xpath('div[2]/div[2]/text()').extract()[0]
    item['title'] = sel.xpath('div[2]/div[2]/text()').[0].extract()
    item['title'] = sel.xpath('div[2]/div[2]/text()').[0].extract()[0]
  ```
  1. 返回一个[SelectorList对象](http://scrapy-chs.readthedocs.io/zh_CN/0.24/topics/selectors.html#selectorlist).将文本提取出来,返回一个数组
  
  ```python
  SelectorList 类是内建 list 类的子类，提供了一些额外的方法:
  xpath(query)
  css(query)
  extract()
  re()
  __nonzero__()
  ```
  2. 返回一个list(就是系统自带的那个) 里面是一些你提取的内容   
  3.  返回2中list的第一个元素(如果list为空抛出异常)  
  4. 返回1中SelectorList里的第一个元素(如果list为空抛出异常),和3达成的效果一致  
  5. 4返回的是一个str(如果Python2为unicode应该), 所以5会返回str的第一个字符   

**strip():** 去除换行等格式符号  
**contains(@class,'123'):"** 包含123字符串的class属性