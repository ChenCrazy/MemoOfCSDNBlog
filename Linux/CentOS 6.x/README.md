# 目录?
## 1.[文件处理命令](1.文件处理命令.md)
## 2.[文件搜索命令](2.文件搜索命令.md)
## 3.[压缩和解压缩命令](3.压缩和解压缩命令.md)
## 4.[帮助命令](4.帮助命令.md)
## 5.[其他:挂载与用户状态](5.其他常用命令.md)
## 6.[部分shell基础命令](6.shell基础.md)
输出重定向 管道负 通配符
## [vim命令](vim命令.md)
## [基本权限管理](基本权限管理.md)
## [特殊权限管理](特殊权限管理.md)
## [网络管理](https://www.imooc.com/learn/258)
## [软件安装管理](软件安装管理.md)
## [服务管理](服务管理.md)
## [系统管理](系统管理.md)

# 常用命令

## 常用目录作用
> `bin  cgroup etc  lib        media mnt opt  root selinux sys usr`  
> 命令保存   XXX    配置  库  空挂载光盘 主挂载U盘  XXX root主
> `boot dev    home lost+found misc  net  proc sbin srv tmp var `
> 启动 设备  用户主  XXX 空挂载磁带机 网络  XXX 
* 绝对路径相对于/根目录,相对路径相对的是 ● . 本身的路径/etc/sys, ./development
* `/`根目录最高级目录,/home普通用户的主目录,`/root`超级用户的家目录
* `/bin` 系统命令保存目录,`sbin(usr/bin,usr/sbin)`超级用户命令保存目录,
* `/etc`配置文件保存目录,
* `/lib`系统库保存目录,
* `/usr`系统软件资源目录 包含了一般不需要修改的应用程序,命令程序文件、程序库、手册和其它文档,
* `/var`系统相关文档目录,包含系统产生的经常变化的文件
* `/boot`启动目录,启动相关数据文件,
* `/dev`设备文件保存目录,特殊文件,
* `/sys和/proc`直接写入内存的过载点,存放存储进程和系统信息 
* `mnt/cdrom光盘 ,mnt/usbU盘,mnt/floppy软驱 ,/media`系统挂载目录,`/tmp`临时文件目录
## 命令基本格式
* 标识
`[root@localhost ~]#`
 > root&nbsp;&nbsp;: 当前登录用户  
 > localhost&nbsp;&nbsp;: 主机名称  
 > ~&nbsp;&nbsp;: 当前所在目录(家目录) pwd
 > \#/$&nbsp;&nbsp;: 超级/普通用户的提示符
* 格式
  * 命令 [选项] [参数]
  * 当有多个选项时可写在一起,个别命令不遵循
### 查询目录中内容:ls
`ls [选项] [文件或目录]`
* 选项
  * -a 显示所有文件,包括隐藏文件
  * -l 显示详细信息
  * -d 查看目录属性
  * -h 人性化显示文件大小
  * -i 显示inode,即文件的ID号
## 关机和重启

`[root@localhost ~]# shutdown [选项] 时间`正确保存系统数据关机

* -c:取消前一个关机命令
* -h:关机,忽略.不会直接关机
* -r:重启`shutdown -r now`

 其他关机~~halt/poweroff/init 0~~其他重启reboot/init 6

### 系统运行级别

0 关机<br>
1 单用户,启动最小运行,相当于Windows安全模式<br>
2 不完全多用户,不启动NFS服务<br>
3 完全多用户<br>
4 未分配<br>
5 图形界面<br>
6 重启<br>
`[root@localhost ~]# cat /etc//inittab`修改系统默认运行级别 最下一行定义系统默认级别 
> id:3:initdefault:  

`[root@localhost ~]#runlevel`查询系统运行级别
> N 3   ##N空

## 安全注销
`logout`
### 切换用户
`su - mac`