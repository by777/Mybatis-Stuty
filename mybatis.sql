/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.19 : Database - mybatis
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = ''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS */`mybatis` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `mybatis`;

/*Table structure for table `blog` */

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog`
(
    `id`          varchar(50)  NOT NULL COMMENT '博客id',
    `title`       varchar(100) NOT NULL COMMENT '博客标题',
    `author`      varchar(30)  NOT NULL COMMENT '博客作者',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `views`       int(30)      NOT NULL COMMENT '浏览量'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `blog` */

insert into `blog`(`id`, `title`, `author`, `create_time`, `views`)
values ('1', '如此简单', 'BBBBB', '2022-04-23 17:12:17', 10000),
       ('2', 'Java 如此简单', 'Vu Bai', '2022-04-23 17:12:17', 2000),
       ('3', 'Spring 如此简单', 'Vu Vai', '2022-04-23 17:12:17', 1000),
       ('a2dd09a5ef0141a0bce796ae94afbbec', '微服务 如此简单', 'Vuu Vai', '2022-04-23 17:12:17', 9999);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student`
(
    `id`   int(10) NOT NULL,
    `name` varchar(30) DEFAULT NULL,
    `tid`  int(10)     DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fktid` (`tid`),
    CONSTRAINT `fktid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `student` */

insert into `student`(`id`, `name`, `tid`)
values (1, '小明', 1),
       (2, '小红', 1),
       (3, '小张', 1),
       (4, '小李', 1),
       (5, '小王', 1);

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher`
(
    `id`   int(10) NOT NULL,
    `name` varchar(30) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `teacher` */

insert into `teacher`(`id`, `name`)
values (1, '白老师');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`   int(20) NOT NULL,
    `name` varchar(30) DEFAULT NULL,
    `pwd`  varchar(30) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `user` */

insert into `user`(`id`, `name`, `pwd`)
values (1, 'hello', 'mybatis'),
       (2, 'name', 'pwd'),
       (3, 'bx', '123456'),
       (5, '哈哈', '12123'),
       (7, 'bxx', 'XXXX'),
       (17, NULL, 'XXXX'),
       (117, NULL, 'XXXX'),
       (1100, 'hello', 'mybatis');

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
