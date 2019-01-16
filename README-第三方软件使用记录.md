## 第三方软件使用笔记
## Git
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

## Redis
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

## Oracle
数据库使用Oracle 11g

##jenkins
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






