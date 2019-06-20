使用的docker官方提供的mysql:5.6镜像

`docker pull mysql:5.6`

运行命令:
```
#/bin/bash
docker container stop mysql
docker container rm mysql 
docker run -v ~/mysql-data:/var/lib/mysql -d -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=123456 mysql:5.6 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
```