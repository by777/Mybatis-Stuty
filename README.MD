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

ResultMap解决属性名于字段名不一致的问题

# P11 日志工厂

如果一个sq语句出现来的异常，曾经sout和debug，用日志工厂也可实现。

# P12 log4j

1. 先导入log4j的包
   在mybatis-04的pom.xml导入依赖
   ```xml
   <dependencies>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
   </dependencies>
   ```
2. 配置文件：resources/log4j.properties
   ```properties
   log4j.rootLogger=DEBUG,console,file
   # 控制台
   log4j.appender.console=org.apache.log4j.ConsoleAppender
   log4j.appender.console.Target=System.out
   log4j.appender.console.Threshold=DEBUG
   log4j.appender.console.layout=org.apache.log4j.PatternLayout
   log4j.appender.console.layout.ConversionPattern=[%c]-%m%n
   # 文件
   log4j.appender.file=org.apache.log4j.RollingFileAppender
   log4j.appender.file.File=./log/bai.log
   log4j.appender.file.MaxFileSize=10mb
   log4j.appender.file.Threshold=DEBUG
   log4j.appender.file.layout=org.apache.log4j.PatternLayout
   log4j.appender.file.layout.ConversionPattern=[%p]-[%d{yy-MM-dd}][%c]%m%n
   # 输出级别
   log4j.logger.org.mybatis=DEBUG
   log4j.logger.java.sql=DEBUG
   log4j.logger.java.sql.Statement=DEBUG
   log4j.logger.java.sql.ResultSet=DEBUG
   log4j.logger.java.sql.PreparedStatement=DEBUG
   ```
3. 配置log4j的日志实现（mybatis-config.xml）
   ```xml

   <settings>
           <!--        <setting name="logImpl" value="STDOUT_LOGGING"/>-->
           <setting name="logImpl" value="LOG4J"/>
   </settings>
   ```
4. 使用

# P12 Limit分页

```sql
select *
from mybatis.user
limit startIndex, pagesize;
```

# p15 使用注解开发

面向接口编程。使用注解编程，去除mapper.xml。

@Param():

* 使用基本类型的参数或者String类型，需要加上
* 引用类型不需要加
* 如果只有一个基本类型的话，可以忽略（不建议）
* 在SQL拼接字符串引用的就是这个Param

# P18 Lombok

一个java库，插件，构建工具。
不用再写get/set方法，只需要写一个注解就可以了。

1. idea中安装Lombok插件（可能自带）
2. mvn导包

```xml

<dependencies>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.10</version>
    </dependency>
</dependencies>
```

3. 重写pojo

```java

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String pwd;
}
```

# P19 复杂查询条件构建：mybatis-06

```sql
CREATE TABLE `teacher`
(
    `id`   INT(10) NOT NULL,
    `name` VARCHAR(30) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8

INSERT INTO teacher(`id`, `name`)
VALUES (1, '白老师');

CREATE TABLE `student`
(
    `id`   INT(10) NOT NULL,
    `name` VARCHAR(30) DEFAULT NULL,
    `tid`  INT(10)     DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fktid` (`tid`),
    CONSTRAINT `fktid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8;
INSERT INTO `student` (`id`, `name`, `tid`)
VALUES ('1', '小明', '1');
INSERT INTO `student` (`id`, `name`, `tid`)
VALUES ('2', '小红', '1');
INSERT INTO `student` (`id`, `name`, `tid`)
VALUES ('3', '小张', '1');
INSERT INTO `student` (`id`, `name`, `tid`)
VALUES ('4', '小李', '1');
INSERT INTO `student` (`id`, `name`, `tid`)
VALUES ('5', '小王', '1');
```

回顾多对一查询：

+ 子查询
+ 连表查询

关联-association：多对一

集合-collection：一对多

javaType：用来指定实体类中属性的类型

ofType：指定映射到List或者集合中的pojo类型。（泛型的约束类型）