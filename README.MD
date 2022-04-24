原视频地址：

https://www.bilibili.com/video/BV1NE411Q7Nx?p=3&spm_id_from=pageDriver

参考文档链接：

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

ResultMap解决属性名于字段名不一致的问题（property-column）

# P11 日志工厂

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

面向接口编程。使用注解编写简单sql，省去mapper.xml。

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

# P22 动态SQL

[if choose trim foreach]标签

```sql
CREATE TABLE `blog`
(
    `id`          VARCHAR(50)  NOT NULL COMMENT '博客id',
    `title`       VARCHAR(100) NOT NULL COMMENT '博客标题',
    `author`      VARCHAR(30)  NOT NULL COMMENT '博客作者',
    `create_time` DATETIME     NOT NULL COMMENT '创建时间',
    `views`       INT(30)      NOT NULL COMMENT '浏览量'
) ENGINE = INNODB
  DEFAULT CHARSET = utf8;
```

利用java插入数据

<details>
<summary>展开查看JAVA代码</summary>
<pre><code> 
public class TestBlog {
    @Test
    public void testBlogInsert() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = new Blog();
        blog.setId(IDUtils.genID());
        blog.setCreate_time(new Date());
        blog.setTitle("Mybatis 如此简单");
        blog.setViews(10000);
        blog.setAuthor("Xu Bai");
        blogMapper.addBlog(blog);
        blog.setId(IDUtils.genID());
        blog.setCreate_time(new Date());
        blog.setTitle("Java 如此简单");
        blog.setViews(2000);
        blog.setAuthor("Vu Bai");
        blogMapper.addBlog(blog);
        blog.setId(IDUtils.genID());
        blog.setCreate_time(new Date());
        blog.setTitle("Spring 如此简单");
        blog.setViews(1000);
        blog.setAuthor("Vu Vai");
        blogMapper.addBlog(blog);
        blog.setId(IDUtils.genID());
        blog.setCreate_time(new Date());
        blog.setTitle("微服务 如此简单");
        blog.setViews(9999);
        blog.setAuthor("Vuu Vai");
        blogMapper.addBlog(blog);
        sqlSession.commit();
        sqlSession.close();
    }
}
 </code></pre>
</details>

# P24 动态SQL常用标签

想法步骤：先在Mysql中写出完整的SQL，再去修改成相应的动态SQL实现

利用where标签：

```xml

<select id="queryBlogChoose" parameterType="map" resultType="Blog">
   select *
   from mybatis.blog
   <where>
      <!--            使用where标签自动去除多余的and，但不主动添加            -->
      <if test="author!=null">
         <!--           这里的and会自动去除            -->
         and title=#{title}
      </if>
      <if test="author != null">
         <!--           这里的and不能省略            -->
         and author=#{author}
      </if>
   </where>
</select>
```

\<set>\<where>都有一个父级元素\<trim>，可以使用trim定制元素的一些功能，

```xml

<demo>
    <trim prefix="WHERE" prefixOverrides="AND | OR" suffix="" suffixOverrides="">
    </trim>
    <trim prefix="SET" suffixOverrides=",">
    </trim>
</demo>
```

其中：

+ prefix 表示在trim包括的SQL语句前面添加的指定内容
+ suffix 表示在trim包裹的SQL末尾添加指定内容
+ prefixOverrides 表示去掉（覆盖）trim包裹的SQL的指定首部内容
+ suffixOverrides 表示去掉（覆盖）trim包裹的SQL的指定尾部内容

SQL片段：
有些时候，可能将一些功能的部分抽取出来方便复用。

foreach：

1. SQL设计：

```sql
select *
from user
where 1 = 1
  and (id = 1 or id = 2 or id = 3)
```

2. XML：

```xml

<select id="queryBlogForeach" parameterType="Map" resultType="Blog">
   select *
   from mybatis.blog
   <where>
      <foreach collection="ids" item="id" open="and (" close=")" separator="or">
         id=#{id}
      </foreach>
   </where>
</select>
```

3. JAVA:

```java
public class TestBlog {
   @Test
   public void testQueryForeach() {
      SqlSession sqlSession = MybatisUtils.getSqlSession();
      BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
      HashMap map = new HashMap();
      ArrayList<String> ids = new ArrayList<String>();
      ids.add("1");
      ids.add("2");
      map.put("ids", ids);
      List<Blog> blogList = blogMapper.queryBlogForeach(map);
      for (Blog blog : blogList) {
         System.out.println(blog);
      }
      sqlSession.close();
   }
}
```

# P26 缓存

缓存：一次查询的结果，暂存到一个可以直接取到地方。（读走缓存，写不走缓存）。

+ Mybatis默认配置了一级缓存和二级缓存。
+ 默认情况下只会开启一句缓存（SqlSession级别的缓存，在open和close之间，也称为本地缓存，关不掉）
+ 二级缓存是基于namespace级别的缓存（一个Mapper接口）
+ 可以通过Mybatis的Cache接口实现自定义二级缓存。

缓存失效的情况：

1. 查询不一样的东西，会走两次sql（也就是说两次查询一个东西，只会走一次sql）
2. 即使两次查询的同一个用户，中间**只要有**修改操作（不论是否是针对要查询用户的），都会刷新缓存
3. 查询不一样的mapper.xml（别说一级缓存，二级缓存都失效了）
4. 手动清除缓存
   启用二级缓存，只需要在SQL中添加

二级缓存又称全局缓存，
工作机制：

+ 一个会话查询一条数据，这个会话就被放在当前会话的一级缓存中
+ 如果当前会话关闭了，这个会话的一级缓存就没了，但是我们想会话关闭了，一级缓存中的数据被保存在二级缓存中
+ 新的会话查询信息，就可以从二级缓存中获取内容
+ 不同的mapper查询的数据会放在自己对应的缓存中（map）

开启步骤：

1. 核心配置文件全局开启

```xml
 <!--        显式的开启二级缓存，（默认开启）-->
<setting name="cacheEnabled" value="true"/>
```

2. 在mapper.xml中配置标签

```xml

<cache eviction="LRU | FIFO | SOFT | WEAK" flushInterval="60000" size="512" readOnly="true"/>
```

# P30 自定义缓存Ehcache

```xml

<dependency>
   <groupId>org.mybatis.caches</groupId>
   <artifactId>mybatis-ehcache</artifactId>
   <version>1.1.0</version>
</dependency>
```

自己可以实现，比较难。