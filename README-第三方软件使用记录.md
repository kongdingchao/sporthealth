## 第三方软件使用笔记
## Git
功能：开源版本库管理
#初始本地仓库
git init

#查看状态
git status

#添加文件
git add *

#提交文件到本地仓库
git commit -m "init projects..."

#绑定远程仓库
git remote add origin https://github.com/kongdingchao/sporthealth.git

#提交到远程仓库
git push -u origin master

#更新远程仓库到本地
git pull origin

#git忽略文件配置
git config --global core.excludesfile ~/.gitignore

#让.gitignore文件立刻生效
git rm -r –cached . #清除缓存

git add . #重新按照ignore的规则add所有的文件

git commit -m “update .gitignore” #提交和注释

## Redis
功能：支持6种数据类型得数据缓存，支持高存取性能压力
linux centos 7环境下
通过配置redis.conf，实现其它地址访问：

1、屏蔽bind 127.0.0.1;

2、保障安全及正常访问，protected-mode yes，或则新增密码：
    $ ./redis-cli -h 127.0.0.1 -p 6379，config set requirepass newPassword，
    $ ./redis-cli -h 127.0.0.1 -p 6379 -a myPassword
    
3、关闭防火墙：

firewalld的基本使用

启动： systemctl start firewalld

关闭： systemctl stop firewalld

查看状态： systemctl status firewalld 

开机禁用  ： systemctl disable firewalld

开机启用  ： systemctl enable firewalld

## RabbitMQ
功能：实现不同程序的数据传输与交互；

## Oracle
功能：关系型数据库实现数据存储；
数据库使用Oracle 11g

##jenkins
功能：实现项目自动化代码走查、编译、测试、发布；
http://localhost:8080
kdc/123456

schedual:

每隔5分钟构建一次
H/5 * * * *

每两小时构建一次
H H/2 * * *

每天中午下班前定时构建一次
0 12 * * *

每天下午下班前定时构建一次
0 18 * * *

第一个*表示分钟，取值0~59 

第三个*表示一个月的第几天，取值1~31 

第四个*表示第几月，取值1~12 

第五个*表示一周中的第几天，取值0~7，其中0和7代表的都是周日

自动话部署请查看：doc/jenkins.txt

##haproxy
功能：实现服务4层、7层服务代理，负载均衡
请参考配置：doc/haproxy




