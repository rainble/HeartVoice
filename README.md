# HeartVoice
An AI music system. 


#Server Deploy
把整个项目传到10.141.212.21服务器/opt/hack文件夹里 密码用户名均为root
打开命令行cd到文件夹中
执行 mvn clean package
执行 docker-compose build
执行 docker-compose up   指令后面加-d可以切换后台启动
在浏览器输入10.141.212.21:14567/test测试服务
三个服务的端口分别为14567 14568 14569

执行docker-compose down关闭服务并删除

清除无关文件以防磁盘占满
docker volume rm $(docker volume ls -qf dangling=true)
docker images|grep none|awk '{print $3 }'|xargs docker rmi

一台新的主机需要安装maven docker与docker-compose环境才可执行命令
