# MySQL学习记录 —— OrangeCH3
## 1.1 初识MySQL

> 为什么要学习数据库

- 岗位技能需求
- 现在的世界,得数据者得天下
- 存储数据的方法
- 程序,网站中,大量数据如何长久保存
- **数据库是几乎软件体系中最核心的一个存在**

> 什么是数据库

数据库(**DataBase**, 简称**DB**)

**概念**: 长期存放在计算机内,有组织,可共享的大量数据的集合,是一个数据 "仓库"

**作用**: 保存,并能安全管理数据(如:增删改查等),减少冗余...

**数据库总览**：

- 关系型数据库(SQL)
    + MySQL, Oracle, SQL Server, SQLite, DB2, ...
    + 关系型数据库通过外键关联来建立表与表之间的关系
- 非关系型数据库(NOSQL)
    + Redis, MongoDB, ...
    + 非关系型数据库通常指数据以对象的形式存储在数据库中，而对象之间的关系通过每个对象自身的属性来决定

> 什么是DBMS    

数据库管理系统(**D**ata**B**ase **M**anagement **S**ystem)

数据库管理软件, 科学组织和存储数据, 高效地获取和维护数据

> MySQL简介

**概念**: 是现在流行的开源的,免费的 关系型数据库

**历史**: 由瑞典 MySQL AB 公司开发，目前属于 Oracle 旗下产品

**特点**：

- 免费, 开源数据库
- 小巧, 功能齐全
- 使用便捷
- 可运行于 Windows 或 Linux 操作系统
- 可适用于中小型甚至大型网站应用

## 1.2 DML/DQL语言学习

> 结构化查询语句分类

- DDL(数据定义语言): CREATE, DROP, ALTER
- **DML**(数据操作语言): INSERT, UPDATE, DELETE
- **DQL**(数据查询语言): SELECT
- DCL(数据控制语言): GRANT, commit, rollback

> DML语言学习

```mysql
INSERT INTO sunditto.student
VALUES (10, 'WangWu', '654321', '男', '1999-01-01', 'Spark','123456@qq.com');

INSERT INTO sunditto.student
VALUES (7, 'WangKe', '654321', '女', '1999-11-11', 'Spark','wangke1111@qq.com');

INSERT INTO sunditto.student
VALUES (6, 'WangKe', '654321', '男', '1999-11-11', 'Spark','wangke1111@qq.com');

-- 这是一条分割线
UPDATE sunditto.student
SET email='sunlin0824@qq.com' 
WHERE id = 8;

UPDATE sunditto.student
SET email='hanjie1225@qq.com' 
WHERE id = 9;

UPDATE sunditto.student
SET email='wangwu0101@qq.com' 
WHERE id = 10;

UPDATE sunditto.student
SET birthday=TIME(NOW())
WHERE id = 7;

-- 这是一条分割线
DELETE FROM sunditto.student 
WHERE id = 6;

-- 测试delete和truncate的区别
CREATE TABLE test (
id INT NOT NULL AUTO_INCREMENT,
coll VARCHAR(20) NOT NULL,
PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET = utf8mb4;

INSERT INTO test (coll)
VALUES ('1'), ('2'), ('3');

DELETE FROM test; -- 不会影响主键自增

INSERT INTO test (`coll`)
VALUES ('1'), ('2'), ('3');

TRUNCATE TABLE test; -- 主键自增会重新计数

INSERT INTO test (coll)
VALUES ('1'), ('2'), ('3');
```

> DQL语言学习

```mysql
SELECT * FROM sunditto.student
WHERE id = 7;
CREATE DATABASE IF NOT EXISTS `school`;
-- 创建一个school数据库
USE `school`;-- 创建学生表
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`(
    `studentno` INT NOT NULL COMMENT '学号',
    `loginpwd` VARCHAR(20) DEFAULT NULL,
    `studentname` VARCHAR(20) DEFAULT NULL COMMENT '学生姓名',
    `sex` TINYINT DEFAULT NULL COMMENT '性别，0或1',
    `gradeid` INT DEFAULT NULL COMMENT '年级编号',
    `phone` VARCHAR(50) NOT NULL COMMENT '联系电话，允许为空',
    `address` VARCHAR(255) NOT NULL COMMENT '地址，允许为空',
    `borndate` DATETIME DEFAULT NULL COMMENT '出生时间',
    `email` VARCHAR (50) NOT NULL COMMENT '邮箱账号允许为空',
    `identitycard` VARCHAR(18) DEFAULT NULL COMMENT '身份证号',
    PRIMARY KEY (`studentno`),
    UNIQUE KEY `identitycard`(`identitycard`),
    KEY `email` (`email`)
)ENGINE=MYISAM DEFAULT CHARSET=utf8mb4;

-- 创建年级表
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`(
    `gradeid` INT NOT NULL AUTO_INCREMENT COMMENT '年级编号',
    `gradename` VARCHAR(50) NOT NULL COMMENT '年级名称',
    PRIMARY KEY (`gradeid`)
) ENGINE=INNODB AUTO_INCREMENT = 6 DEFAULT CHARSET = utf8mb4;

-- 创建科目表
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`(
    `subjectno`INT NOT NULL AUTO_INCREMENT COMMENT '课程编号',
    `subjectname` VARCHAR(50) DEFAULT NULL COMMENT '课程名称',
    `classhour` INT DEFAULT NULL COMMENT '学时',
    `gradeid` INT DEFAULT NULL COMMENT '年级编号',
    PRIMARY KEY (`subjectno`)
)ENGINE = INNODB AUTO_INCREMENT = 19 DEFAULT CHARSET = utf8mb4;

-- 创建成绩表
DROP TABLE IF EXISTS `result`;
CREATE TABLE `result`(
    `studentno` INT NOT NULL COMMENT '学号',
    `subjectno` INT NOT NULL COMMENT '课程编号',
    `examdate` DATETIME NOT NULL COMMENT '考试日期',
    `studentresult` INT  NOT NULL COMMENT '考试成绩',
    KEY `subjectno` (`subjectno`)
)ENGINE = INNODB DEFAULT CHARSET = utf8mb4;

-- 插入学生数据 其余自行添加 这里只添加了2行
INSERT INTO `student` (`studentno`,`loginpwd`,`studentname`,`sex`,`gradeid`,`phone`,`address`,`borndate`,`email`,`identitycard`)
VALUES
(1000,'123456','张伟',0,2,'13800001234','北京朝阳','1980-1-1','text123@qq.com','123456198001011234'),
(1001,'123456','赵强',1,3,'13800002222','广东深圳','1990-1-1','text111@qq.com','123456199001011233');

-- 插入成绩数据  这里仅插入了一组，其余自行添加
INSERT INTO `result`(`studentno`,`subjectno`,`examdate`,`studentresult`)
VALUES
    (1000,1,'2013-11-11 16:00:00',85),
    (1000,2,'2013-11-12 16:00:00',70),
    (1000,3,'2013-11-11 09:00:00',68),
    (1000,4,'2013-11-13 16:00:00',98),
    (1000,5,'2013-11-14 16:00:00',58);

-- 插入年级数据
INSERT INTO `grade` (`gradeid`,`gradename`) VALUES(1,'大一'),(2,'大二'),(3,'大三'),(4,'大四'),(5,'预科班');

-- 插入科目数据
INSERT INTO `subject`(`subjectno`,`subjectname`,`classhour`,`gradeid`)VALUES
    (1,'高等数学-1',110,1),
    (2,'高等数学-2',110,2),
    (3,'高等数学-3',100,3),
    (4,'高等数学-4',130,4),
    (5,'C语言-1',110,1),
    (6,'C语言-2',110,2),
    (7,'C语言-3',100,3),
    (8,'C语言-4',130,4),
    (9,'Java程序设计-1',110,1),
    (10,'Java程序设计-2',110,2),
    (11,'Java程序设计-3',100,3),
    (12,'Java程序设计-4',130,4),
    (13,'数据库结构-1',110,1),
    (14,'数据库结构-2',110,2),
    (15,'数据库结构-3',100,3),
    (16,'数据库结构-4',130,4),
    (17,'C#基础',130,1);

-- 这是一条分割线
SELECT studentno, studentname FROM student;

SELECT CONCAT('姓名：',studentname) AS newname FROM student;

-- 去重操作
SELECT DISTINCT studentno FROM result;

-- 附加操作
SELECT VERSION(); -- 查询系统版本
SELECT 110*3-1 AS 结果; -- 用来计算结果
SELECT @@auto_increment_increment; -- 查询自增的步长
SELECT studentno, studentresult+2 AS '加上平时成绩后的分数' FROM result;

-- WHERE条件子句
SELECT studentno, studentresult FROM result
WHERE studentresult>=80 AND studentresult<=100;

SELECT studentno, studentresult FROM result
WHERE studentresult BETWEEN 80 AND 100;

SELECT studentno, studentresult FROM result
WHERE NOT studentresult=95;

-- 模糊查询
-- %->代表任意多个字符, _->代表一个字符
SELECT studentno,studentname FROM student
WHERE studentname LIKE '%张%';

-- IN关键字的应用
SELECT studentno,studentname FROM student
WHERE studentno IN (1000,1001);

-- IS NOT NULL的应用
SELECT studentno,studentname FROM student
WHERE phone IS NOT NULL;
```

> JOIN联表查询学习

![七种JOIN联表查询](./连接查询.png)

```mysql
-- JOIN联表查询
-- 查询参加了考试的同学（学号、姓名、科目编号、分数）
SELECT * FROM student;
SELECT * FROM result;

-- INNER JOIN查询方式
SELECT s.studentno,studentname,subjectno,studentresult
FROM student AS s
INNER JOIN result AS r
ON s.studentno = r.studentno;

-- RIGHT JOIN查询方式
SELECT s.studentno,studentname,subjectno,studentresult
FROM student s
RIGHT JOIN result r
ON s.studentno = r.studentno;

-- LEFT JOIN查询方式
SELECT s.studentno,studentname,subjectno,studentresult
FROM student s
LEFT JOIN result r
ON s.studentno = r.studentno;
```

|查询操作|具体描述|
|:---:|:---:|
|INNER JOIN|如果两个表中至少有一个匹配，就返回行结果|
|RIGHT JOIN|会从左表中返回查询行结果，即使右表中没有匹配|
|LEFT JOIN|会从右表中返回查询行结果，即使左表中没有匹配|

> 自连接查询学习

```mysql
CREATE TABLE school.category( 
categoryid INT NOT NULL COMMENT 'id',
pid INT NOT NULL COMMENT '父id,没有父则为1', 
categoryname VARCHAR(10) NOT NULL COMMENT '种类名字', 
PRIMARY KEY (categoryid) 
) ENGINE=INNODB CHARSET=utf8mb4;

INSERT INTO category (categoryid, pid, categoryname) 
VALUES (2, 1, '信息技术');
INSERT INTO category (categoryid, pid, categoryname) 
VALUES (3, 1, '软件开发');
INSERT INTO category (categoryid, pid, categoryname) 
VALUES (5, 1, '美术设计');
INSERT INTO category (categoryid, pid, categoryname) 
VALUES (4, 3, '数据库');
INSERT INTO category (categoryid, pid, categoryname) 
VALUES (8, 2, '办公信息');
INSERT INTO category (categoryid, pid, categoryname) 
VALUES (6, 3, 'Web开发');
INSERT INTO category (categoryid, pid, categoryname) 
VALUES (7, 5, 'PS技术');

-- 查询父子信息
SELECT fu.categoryname AS '父栏目',zi.categoryname AS '子栏目'
FROM category AS fu, category AS zi
WHERE fu.categoryid=zi.pid;
```

![自连接查询](./自连接查询.png)

> 分页和排序学习

```mysql
-- 排序ORDER BY
SELECT subjectname,classhour
FROM SUBJECT
ORDER BY classhour DESC;

-- 分页LIMIT
SELECT subjectname,classhour
FROM SUBJECT
ORDER BY classhour DESC
LIMIT 0,5;

-- 综合查询
SELECT s.studentno, studentname, subjectname, studentresult
FROM student s
INNER JOIN result r
ON s.studentno = r.studentno
INNER JOIN `subject` sub
ON sub.subjectno = r.subjectno
WHERE subjectname = 'Java程序设计-1' AND studentresult>=60
ORDER BY studentresult DESC
LIMIT 0,10;
```

> 子/嵌套查询学习

```mysql
-- 1 使用连接查询
SELECT studentno, r.subjectno, studentresult
FROM result r
INNER JOIN `subject` sub
ON r.subjectno = sub.subjectno
WHERE subjectname = '高等数学-1'
ORDER BY studentresult DESC;

-- 2 使用子查询
SELECT studentno, subjectno, studentresult
FROM result
WHERE subjectno = (
	SELECT subjectno 
	FROM `subject`
	WHERE subjectname = '高等数学-1'
)
ORDER BY studentresult DESC;
```

## 1.3 MySQL函数学习

> 常用函数学习

```mysql
-- 数学运算
SELECT ABS(-8); -- 绝对值
SELECT CEILING(9.4); -- 向上取整
SELECT FLOOR(9.7); -- 向下取整
SELECT RAND(); -- 返回一个0~1之间的随机数
SELECT SIGN(10); -- 判断一个数的符号

-- 字符串函数
SELECT CHAR_LENGTH('OrangeCH3'); -- 字符串长度
SELECT CONCAT('OrangeCH3->','Sun'); -- 字符串拼接
SELECT INSERT('HelloWorld!',6,5,'OrangeCH3'); -- 字符串替换
SELECT LOWER('DIT-FFT'); -- 转换为大写
SELECT UPPER('earth'); -- 转换为小写
SELECT SUBSTR('DIF-FFT',3,4); -- 返回指定参数的字符串
SELECT REVERSE('Apple'); -- 字符串反转

-- 时间和日期函数
SELECT CURRENT_DATE(); -- 获取当前日期
SELECT CURDATE(); -- 同上
SELECT NOW(); -- 获取当前的时间
SELECT LOCALTIME(); -- 获取本地时间
SELECT SYSDATE(); -- 获取系统时间
SELECT DAY(NOW()); -- 获取当前时间的DAY信息

-- 系统函数
SELECT SYSTEM_USER(); -- 获取当前系统用户
SELECT USER(); -- 获取当前用户
SELECT VERSION(); -- 获取当前MySQL版本信息
```

> 聚合函数学习

```mysql
SELECT COUNT(studentname)
FROM student; -- 统计指定字段的数量，会忽略null值

SELECT COUNT(*)
FROM student; -- 统计行数，不会忽略null值

SELECT COUNT(1)
FROM student; -- 统计行数，不会忽略null值

-- 查询不同课程的平均分，最高分，最低分；要求平均分≥80分
SELECT subjectname, AVG(studentresult), MAX(studentresult), MIN(studentresult)
FROM result r
INNER JOIN `subject` sub
ON r.subjectno = sub.subjectno
GROUP BY r.subjectno
HAVING AVG(studentresult)>=80;
```

## 1.4 MySQL事务学习

> 事务管理原则(ACID)

- 原子性(**A**tomicity)——要么都成功，要么都失败
- 一致性(**C**onsistency)——事务前后的数据完整性要保证一致
- 隔离性(**I**solation)——多个用户对应多个事务，相互隔离
- 持久性(**D**urability)——事务提交则不可逆，会被持久化到数据库中

```mysql
/*
A在线买一款价格为500元商品,网上银行转账
A的银行卡余额为2000,然后给商家B支付500
商家B一开始的银行卡余额为10000
创建数据库shop和创建表account并插入2条数据
*/

CREATE DATABASE `shop`CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `shop`;

CREATE TABLE `account` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(32) NOT NULL,
`cash` DECIMAL(9,2) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO account (`name`,`cash`)
VALUES('A',2000.00),('B',10000.00);

-- 转账实现
SET autocommit = 0; -- 关闭自动提交
START TRANSACTION;  -- 开始一个事务,标记事务的起始点
UPDATE account SET cash=cash-500 WHERE `name`='A';
UPDATE account SET cash=cash+500 WHERE `name`='B';
COMMIT; -- 提交事务
# rollback;
SET autocommit = 1; -- 恢复自动提交
```

## 1.5 MySQL索引学习

> 索引的作用

- 提高查询速度
- 确保数据的唯一性
- 可以加速表和表之间的连接, 实现表与表之间的参照完整性
- 使用分组和排序子句进行数据检索时, 可以显著减少分组和排序的时间
- 全文检索字段进行搜索优化

> 索引的分类

- 主键索引(Primary Key)
- 唯一索引(Unique)
- 常规索引(Index)
- 全文索引(FullText)

> 主键索引

主键: 某一个属性组能唯一标识一条记录

特点:

- 最常见的索引类型
- 确保数据记录的唯一性
- 确定特定数据记录在数据库中的位置

> 唯一索引

作用: 避免同一个表中某数据列中的值重复

与主键索引的区别：

- 主键索引只能有一个
- 唯一索引可能有多个

```mysql
CREATE TABLE `Grade`(
    `GradeID` INT(11) AUTO_INCREMENT,
    `GradeName` VARCHAR(32) NOT NULL UNIQUE,
    PRIMARY KEY (`GradeID`)
    -- 或 UNIQUE KEY `GradeID` (`GradeID`)
)
```

> 常规索引

作用: 快速定位特定数据

注意:

- index和key关键字都可以设置常规索引
- 应加在查询找条件的字段
- 不宜添加太多常规索引,影响数据的插入,删除和修改操作

```mysql
CREATE TABLE `result`(
   -- 省略一些代码
  INDEX `ind` (`studentNo`,`subjectNo`) -- 创建表时添加
)
```

```mysql
-- 创建后添加
ALTER TABLE `result` ADD INDEX `ind`(`studentNo`,`subjectNo`);
```

> 全文索引

作用: 快速定位特定数据

注意:

- 只能用于MyISAM类型的数据表
- 只能用于CHAR , VARCHAR , TEXT数据列类型
- 适合大型数据集

```mysql
/*
#方法一：创建表时
  　　CREATE TABLE 表名 (
               字段名1 数据类型 [完整性约束条件…],
               字段名2 数据类型 [完整性约束条件…],
               [UNIQUE | FULLTEXT | SPATIAL ]   INDEX | KEY
               [索引名] (字段名[(长度)] [ASC |DESC])
               );


#方法二：CREATE在已存在的表上创建索引
       CREATE [UNIQUE | FULLTEXT | SPATIAL ] INDEX 索引名
                    ON 表名 (字段名[(长度)] [ASC |DESC]) ;


#方法三：ALTER TABLE在已存在的表上创建索引
       ALTER TABLE 表名 ADD [UNIQUE | FULLTEXT | SPATIAL ] INDEX
                            索引名 (字段名[(长度)] [ASC |DESC]) ;
                           
                           
#删除索引：DROP INDEX 索引名 ON 表名字;
#删除主键索引: ALTER TABLE 表名 DROP PRIMARY KEY;


#显示索引信息: SHOW INDEX FROM student;
*/

/*增加全文索引*/
ALTER TABLE `school`.`student` ADD FULLTEXT INDEX `studentname` (`StudentName`);

/*EXPLAIN : 分析SQL语句执行性能*/
EXPLAIN SELECT * FROM student WHERE studentno='1000';

/*使用全文索引*/
-- 全文搜索通过 MATCH() 函数完成。
-- 搜索字符串作为 against() 的参数被给定。搜索以忽略字母大小写的方式执行。对于表中的每个记录行，MATCH() 返回一个相关性值。即，在搜索字符串与记录行在 MATCH() 列表中指定的列的文本之间的相似性尺度。
EXPLAIN SELECT *FROM student WHERE MATCH(studentname) AGAINST('love');

/*
开始之前，先说一下全文索引的版本、存储引擎、数据类型的支持情况

MySQL 5.6 以前的版本，只有 MyISAM 存储引擎支持全文索引；
MySQL 5.6 及以后的版本，MyISAM 和 InnoDB 存储引擎均支持全文索引;
只有字段的数据类型为 char、varchar、text 及其系列才可以建全文索引。
测试或使用全文索引时，要先看一下自己的 MySQL 版本、存储引擎和数据类型是否支持全文索引。
*/
```

> 测试索引

建表app_user：

```mysql
CREATE TABLE `app_user` (
`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
`name` varchar(50) DEFAULT '' COMMENT '用户昵称',
`email` varchar(50) NOT NULL COMMENT '用户邮箱',
`phone` varchar(20) DEFAULT '' COMMENT '手机号',
`gender` tinyint(4) unsigned DEFAULT '0' COMMENT '性别（0:男；1：女）',
`password` varchar(100) NOT NULL COMMENT '密码',
`age` tinyint(4) DEFAULT '0' COMMENT '年龄',
`create_time` datetime DEFAULT CURRENT_TIMESTAMP,
`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='app用户表'
```

批量插入100w行数据：

```mysql
DROP FUNCTION IF EXISTS mock_data;
DELIMITER $$
CREATE FUNCTION mock_data()
RETURNS INT
BEGIN
DECLARE num INT DEFAULT 1000000;
DECLARE i INT DEFAULT 0;
WHILE i < num DO
  INSERT INTO app_user(`name`, `email`, `phone`, `gender`, `password`, `age`)
   VALUES(CONCAT('用户', i), '24736743@qq.com', CONCAT('18', FLOOR(RAND()*(999999999-100000000)+100000000)),FLOOR(RAND()*2),UUID(), FLOOR(RAND()*100));
  SET i = i + 1;
END WHILE;
RETURN i;
END;
SELECT mock_data();
```

索引效率测试:

```mysql
-- 无索引测试
SELECT * FROM app_user WHERE name = '用户9999'; -- 查看耗时
SELECT * FROM app_user WHERE name = '用户9999';
SELECT * FROM app_user WHERE name = '用户9999';

-- 创建索引
CREATE INDEX idx_app_user_name ON app_user(name);
```

![无索引测试结果](./无索引测试结果.png)
![创建索引后测试结果](./创建索引后测试结果.png)

> 索引准则

- 索引不是越多越好
- 不要对经常变动的数据加索引
- 小数据量的表建议不要加索引
- 索引一般应加在查找条件的字段

> 索引的数据结构

我们可以在创建上述索引的时候，为其指定索引类型，分两类:

- Hash类型的索引，查询单条快，范围查询慢
- B-tree类型的索引，b+树，层数越多，数据量指数级增长（我们就用它，因为innodb默认支持它）

不同的存储引擎支持的索引类型也不一样：

- InnoDB支持事务，支持行级别锁定，支持B-tree、Full-text等索引，不支持Hash索引
- MyISAM不支持事务，支持表级别锁定，支持B-tree、Full-text等索引，不支持Hash索引
- Memory不支持事务，支持表级别锁定，支持B-tree、Hash等索引，不支持Full-text索引
- NDB支持事务，支持行级别锁定，支持Hash索引，不支持B-tree、Full-text等索引
- Archive不支持事务，支持表级别锁定，不支持B-tree、Hash、Full-text等索引

## 1.6 权限管理和备份

TODO

## 1.7 规范数据库设计


## 1.8 JDBC-API学习


