## 动态线程池监控和修改中间件

### 测试

1. 使用docs文件夹下的dev-ops构建redis的docker环境
2. 修改各个项目中redis路径的配置
3. 先启动dynamic-thread-pool-test中的ApiTest
4. 启动dynamic-thread-pool-admin
5. 打开docs文件夹下的front文件夹下的index.html

### 正式使用

1. 在自己的项目中引入dynamic-thread-pool-spring-boot-start
2. 参照dynamic-thread-pool-test配置修改自己的项目配置文件
3. 修改dynamic-thread-pool-admin的redis配置
4. 启动dynamic-thread-pool-admin
5. 打开docs文件夹下的front文件夹下的index.html 