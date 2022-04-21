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

# P5 Map和模糊查询

```xml
<!--UserMapper.(xml|java|Test.java)-->
<map>
    <!-- 通过pojo，总要new一个完整的对象-->
    <insert id="addUser" parameterType="com.bai.pojo.User">
        insert into mybatis.user(id, name, password)
        values (#{id}, #{name}, #{password});
    </insert>
    <!-- 通过map，只需要提供部分属性-->
    <insert id="addUser2" parameterType="Map">
        insert into mybatis.user (id, password)
        values (#{uid}, #{password});
    </insert>
</map>
```

# P6 配置文件优化

Mybatis可以配置多个环境，但是每次只能使用一个。

# P9 生命周期和作用域

SqlSessionFactoryBuild：
一旦创建了SqlSessionFactory，就不再需要它了。

SqlSessionFactory:
可以看作数据库连接池，一旦被创建就不必销毁。

SqlSession：
连接到连接池的一个请求。不是线程安全的，不能被共享。所以放到一个方法里面，绝对不能static，
甚至一个类的实例变量也不行。

# P10

解决属性名于字段名不一致的问题
