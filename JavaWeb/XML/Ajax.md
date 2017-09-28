# AJAX
框架：DOJO DWR JSON
## AJAX概念

* Asynchronous Javascript and (XML) 异步传输
* 典型流程
  * 客户端触发异步操作
  * 创建新的XMLHttpRequest对象
  * 与Server进行连接
  * 服务器端进行连接
  * 返回包含处理结果的XML文档
  * XMLHttpRequest对象接收处理结果并分析
  * 更新页面
* XMLHttpRequest
  * 重要的JS对象，通过它提起对服务器端的请求
  * 一般客户端到服务器端的请求都是通过浏览器提交获取，获取整页，
  * 现在可以通过JS提起请求获取数据，如果要提起多个请求，需要多个XHR对象
  * 请求的结果被预先定义好的方法处理
  * 方法：
    * `open(method,url[,async])`:method can be "GET""POST""PUT""DELETE",async defaullts to ture
    * `send(body)`:sends HTTP request ,
    * `abort()` called after send() to cancel request,
    * `void setRequestHeader(name,value)`,`String getResponseHeader(name)`,`String getAllResponseHeaders()`等等方法