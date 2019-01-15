## 第三方软件使用笔记
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





