CREATE DATABASE `mybatis`;
USE `mybatis`;
CREATE TABLE `user`
(
    `id`   INT(20) NOT NULL PRIMARY KEY,
    `name` VARCHAR(30) DEFAULT NULL,
    `password` VARCHAR(30) DEFAULT NULL

)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `user`(`id`, `name`, `password`)
VALUES (1, '狂神', '123456'),
       (2, '白旭', '123456'),
       (3, 'bx', '123456'),
       (4, 'bxx', '123456');
SELECT *
FROM mybatis.`user`;

UPDATE mybatis.user
SET 'name'=哈哈, password=1
WHERE id =1;