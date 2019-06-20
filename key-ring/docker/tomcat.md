使用的docker官方提供的tomcat:8.5.42-jdk8-adoptopenjdk-hotspot镜像

`docker pull tomcat:8.5.42-jdk8-adoptopenjdk-hotspot`

运行命令:
```
#!/bin/bash
docker container stop tomcat
docker container rm tomcat
docker run -d -p 80:8080 --name tomcat -v ~/webapps:/usr/local/tomcat/webapps tomcat:8.5.42-jdk8-adoptopenjdk-hotspot
```