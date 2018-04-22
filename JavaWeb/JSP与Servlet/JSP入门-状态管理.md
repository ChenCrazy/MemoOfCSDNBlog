## HTTP协议的无状态性
保存用户状态的两大机制 session Cookie
* Cookie简介
* Cookie的创建与使用
* Session与Cookie的对比

无状态是超文本传输协议的先天不足，无状态是指服务器响应客户端请求后无记忆功能，无法保存用户的状态，无法分辨同状态下客户端的再一次请求

## Cookie的简介

### 保存用户状态的两大机制
* Session会话对象保存
    * JSP的九大内置对象中保存用户状态的一种机制
* Cooike客户端技术保存
    * 是Web服务器保存在客户端的一系列文本信息
    * 可用来判断注册用户是否已经登陆过网站，是否保留用户的一些登陆信息
    用户最近浏览记录的保存
* Cooike的作用
    * 对特定对象的追踪
    * 保存用户网页浏览记录与习惯
    * 简化登陆
    * 具有安全风险：容易泄漏用户信息
* JSP中创建使用Cookie
  * 创建Cookie对象
    * Cookie newCookie = new Cookie(String key,Object value);
  * 写入Cookie对象
    * response.addCookie(new Cookie);
  * 读取Cookie对象
    * Cookie[] cookies = requs=est.getCookies();

### 常用方法
* `void setMaxAge(int expiry)`:设置cookie的有效期，以秒为单位
* `void setValue(String value)`:在cookie创建后，对cookie进行赋值
* `String getName()`: 获取cookie的名称
* `String getValue()`: 获取cookie的值
* `int getMaxAge()`: 获取cookie的有效时间，以秒为单位

Session与Cookie的对比
* session和Cookie都能保护用户信息，都有生存期限
* Session是保存在服务器端的            
  * Cookie以文本文件的形式用户信息保存在客户端
* Session中保存的是Object类型            
  * Cookie保存的是String类型
* Session随会话的结束而将器存储的数据销毁            
  * Cookie可以长期保存在客户端
* Session保存重要的信息             
  * Cookie保存不重要的用户信息，爱好，浏览记录
* Session的安全性要更高


    