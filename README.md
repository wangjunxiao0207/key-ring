# key-ring

这是一个密码管理系统，业务逻辑非常简单，这个项目主要用来练习所学的技术，以及作为新技术的训练场。这是后端代码仓库，还有一个App代码仓库。

此项目基于Spring Mvc 4.3构建，使用的MySQL5.6作为数据库，使用docker进行项目部署。由于时间原因，项目中所用技术还略显单调，后边会不断重构这个项目，
引入Dubbo RPC框架，使之成为现在流行的微服务架构。文档后边会介绍二期工程要引入的技术。

简单介绍以下项目目录结构：

<pre>
|keyring 项目的根模块，管理项目中的依赖
`---generator 代码生成模块，使用了Mybatis generator的java代码，用来生成dao,pojo代码和dao层对应的xml文件
`---key-ring  业务模块，整体的代码都在这个模块中
    `-------db     存放着sql文件
    `-------docker 存放着描述系统使用的docker镜像文件，目前就两个mysql和tomcat
    `-------src    项目代码
</pre>

目前项目中整合了以下技术：

* Mybatis & Mybatis generator & pageHelper
* Druid
* Swagger
* Thymeleaf
* Logback
* shiro

项目中常用的工具包：
* jackson
* jwt       
* lombok
* guava
* hibernate.validator
* joda.time
* commons.email
* commons.net
* commons.fileupload
* commons.lang3

等等吧。

# 二期工程

由于时间原因，项目中还有许多需要完善的地方，首先就是这个系统连web端都没有，^_^（先开发App,毕竟随手记录密码或者查询密码，App还是更方便)。
下面列一下重构时要引入的技术和完成的功能：

* Dubbo, RPC框架，把项目变成流行的微服务架构
* ZooKeeper, Dubbo的注册中心
* Redis, 目前打算是作为 JWT Authorization token的缓存，可能也作为权限缓存，以及其他token的缓存吧，比如重设密码的token等等
* Nginx, 做后台和移动web客户端的反向代理，以及负载均衡
* 完成PC web和移动 web客户端的开发
* web端使用扫描二维码登录方式(像微信)

这个是目前的计划，完成这些再使用Spring Cloud重构一遍？




