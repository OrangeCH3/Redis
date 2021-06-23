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

## 1.2 DML语言

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

> DQL语言

```mysql
SELECT * FROM sunditto.student
WHERE id = 7;
```

