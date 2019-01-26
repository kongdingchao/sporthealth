## Java运动健康系统
## How to play

 1. 我目前有时间都会参与这个练习项目，主要功能就是实现多个设备统计用户的运动信息，
 如跑步、游泳、散步等运动记录，把这些信息当成大数据去处理，分析出目标用户的作息情况，
 按指标等其它算法把数据展现给用户（这些后面再考虑），前期工作就是统计信息
 
## Develop environment
IDEA+Maven+SSM框架。  

## Written in front of words


完成这个运动健康系统，需要完成四个模块的代码编写，分别是:  

- 1.Java高并发APi之业务分析与DAO层代码编写
- 2.Java高并发APi之Service层代码编写。
- 3.Java高并发APi之Web层代码编写。
- 4.Java高并发APi之高并发优化。


接下来我将从如何用maven创建我们的运动健康系统sporthealth项目开始到完成，详细介绍自己完成它的过程。  

## 1.相关技术介绍
**Oracle:**1.这里我们采用手写代码创建相关表，掌握这种能力对我们以后的项目二次上线会有很大的帮助；2.SQL技巧；3.事务和行级锁的理解和一些应用。  

**MyBatis:**1.DAO层的设计与开发。2.MyBatis的合理使用，使用Mapper动态代理的方式进行数据库的访问。3.MyBatis和Spring框架的整合:如何高效的去整合MyBatis和Spring框架。  

**Spring:**1.Spring IOC帮我们整合Service以及Service所有的依赖。2.声明式事务。对Spring声明式事务做一些分析以及它的行为分析。  

**Spring MVC:**1.Restful接口设计和使用。Restful现在更多的被应用在一些互联网公司Web层接口的应用上。2.框架运作流程。3.Spring Controller的使用技巧。  

**前端:**1.交互设计。2.bootstrap。3.JQuery。设计到前端的页面代码我们直接拷贝即可，毕竟真正开发中这样一个项目是由产品经理、前端工程师、后端工程师一起完成的。  

**高并发:**1.高并发点和高并发分析。2.优化思路并实现。  

练习目的：
1、使用thrift生成接口约定压解包；
2、使用redis缓存学生信息和步数信息；
3、定时存储数据到oracle关系型数据库；
4、使用rabbitmq通讯接收步数信息；
5、使用thrift RPC支持请求服务；
6、日志使用ELK日志分析平台; 
7、使用Shiro安全验证

## 2.Java高并发运动健康APi之业务分析与DAO层代码编写

### 2.1用Maven创建我们的项目sporthealth






