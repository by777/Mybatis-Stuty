原视频地址：

https://www.bilibili.com/video/BV1NE411Q7Nx?p=3&spm_id_from=pageDriver

参考链接：

https://mybatis.org/mybatis-3/zh/getting-started.html

# P3 CRUD

1. namespace中的包名要与Dao/Mapper接口名一致！
2. select语句
    1. id：就是对应的namespace中的方法名
    2. resultType：SQL语句的执行的返回值（Class int）
    3. parameterType：参数类型

NOTE: 在本例中，注意使用#而不要$