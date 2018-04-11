## 实际的项目环境
* 本地开发环境(Local)通查直接用开发环境
* 开发环境(Dev)
* 测试环境(Bate)
* 线上环境(Prod)
## 隔离环境之间存在差异的环境配置
* FTP服务器相关配置
* 数据库配置
* ...线上与本地间有差异的配置最好都给隔离开
## Maven环境隔离配置及原理
* pom.xml中build节点增加
```xml
<resources>
  <resource>
    <directory>src/main/resources.${deploy.type}</directory><!-- 声明一个变量 -->
    <excludes>
      <exclude>*.jsp</exclude><!-- 排除文件 -->
    </excludes>
    <resource>
      <directory>src/main/resources</directory>
    </resource>
  <resource>
</resources>

```
*  增加profiles节点
```xml
<profiles>
  <profile>
    <id>dev</id>
    <activation>
      <activeByDefault>true</activeByDefault><!--表示默认打包环境 -->
    </activation>
    <properties>
      <deploy.type>dev</deploy.type> <!-- 与上部分声明的变量同名,参数表示隔离环境的类型标识 -->
    </properties>
  </profile>
  <profile>
    <id>beta</id>
    <properties>
      <deploy.type>beta</deploy.type>
    </properties>
  </profile>
  <profile>
    <id>prod</id>
    <properties>
      <deploy.type>prod</deploy.type>
    </properties>
  </profile>
</profiles>
```
* 新建对应类型的文件夹,如`resources.dev` `resources.bate` `resources.prod`,并将要隔离的文件分开公共的留在resources下
* 在IDEA右侧Mavenproject选项里选中本地开发环境对应的环境,点击import change进行Maven更新
* 使用编译打包时使用参数`-P${环境标识}`
	* 如`mvn clean package -Dmaven.test.skip=true -Pdev`