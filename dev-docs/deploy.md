## Tips for deployment 
### Requirements
>*1、java version 17*\
>*2、Mysql 8.0*\
>*3、Redis*\
>*4、Maven*\
>*5、lombok*\
>*6、nodejs*\
>*7、npm*\


### 1 Pull the project
https://github.com/1197151063/ccbu_ebc.git

### 2 Start the backend
#### 2.1 execute sql script
#### 2.2 start redis
#### 2.3 import project as maven project in IDE
#### 2.4 modify configuration

(1)modify jdbc parameters
```
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/smart-admin-dev?autoReconnect=true&useServerPreparedStmts=false&rewriteBatchedStatements=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&serverTimezone=UTC
spring.datasource.username=erp
spring.datasource.password=listen1015
```
(2)modify redis parameters
```
spring.redis.database=1
spring.redis.host=127.0.0.1
spring.redis.jedis.pool.max-active=100
spring.redis.jedis.pool.min-idle=5
spring.redis.jedis.pool.max-idle=10
spring.redis.jedis.pool.max-wait=30000ms
spring.redis.port=6379
spring.redis.timeout=10000ms
spring.redis.password=
```
#### 2.5 using maven to build the project


### 3 Start the frontend
before starting the frontend, you need to install nodejs and npm

#### 3.1 install dependencies
command line execute `npm install` in `/smart-admin-web` directory\
after that, execute `npm run build` to build the project

#### 3.2 start successfully
http://localhost:8080  username:sa password:123456



---
**About me:**\
New to java, but willing to learn and share. 
---