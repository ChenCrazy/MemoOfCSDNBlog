## Lombok
官网[proojectlombok.org](proojectlombok.org)  
通过简单注解精简冗余的模版代码  
避免修改字段名称时对应方法名不一致  

## Maven引入Lombok
```xml
	<dependency><!-- dependencies -->
		<groupId>org.projectlombok</groupId>
		<artifactId>lombok</artifactId>
		<version>1.16.18</version>
	</dependency>
```
## 安装插件 
### IDEA
Setting-> Plugins-> Browse-> Lombok Plugin
### Eclipse
下载相应的jar管理员运行选择Eclipse,Linux/Mac需要sudo java -jar lombok.jar

## 注解(重点)
* @Data
* @Getter
* @Gettet(AccessLevel.PROTECTED)生成的get方法权限为protected
* @Setter
* @Setter(AccessLevel.PROTECTED)
* @NoArgsConstructor无参构造器
* @AllArgsConstructor全参构造器
* @ToString
* @ToString(exclude="column")属性的黑名单排除掉column属性不重写,多个用{}
* @ToString(of="column")属性的白名单,只对某些属性生成方法
* @EqualsAndHashCode同ToString 
* @Slf4j:logback日志框架的声明无需显示声明logger只需用log就能使用其方法
* @Log4j:log4j日志框架时使用

### @Data注解
@data注解其实包含了@Getter@Setter@ToString@EqualsAndHashCode,权限为protected的显式canEqual,5个方法的重写,所以为保证代码严谨需谨慎使用

---

## 反编译 
* [Java Decompiler](http://jd.benow.ca/)
* JD-GUI
* JD-Eclipse
* JD_Intellij
其中插件,因为我们使用maven,在中央仓库里已经有源码,所以插件其实没有必要,GUI可以装着玩玩