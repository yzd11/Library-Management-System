/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.41 : Database - db_book
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_book` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_book`;

/*Table structure for table `t_admin` */

DROP TABLE IF EXISTS `t_admin`;

CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `userDesc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_admin` */

insert  into `t_admin`(`id`,`userName`,`password`,`userDesc`) values (1,'yzd11','123456','该用户很懒，什么介绍都没有.......'),(2,'CXK','IKUN','练习两年半');

/*Table structure for table `t_book` */

DROP TABLE IF EXISTS `t_book`;

CREATE TABLE `t_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookName` varchar(20) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `bookTypeId` int(11) DEFAULT NULL,
  `bookDesc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bookTypeId` (`bookTypeId`),
  CONSTRAINT `t_book_ibfk_1` FOREIGN KEY (`bookTypeId`) REFERENCES `t_booktype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `t_book` */

insert  into `t_book`(`id`,`bookName`,`author`,`sex`,`price`,`bookTypeId`,`bookDesc`) values (1,'活着','余华','男',19.9,3,'一本温馨的治愈书籍'),(3,'红楼梦','曹雪芹','男',59.7,3,'都云作者痴，谁解其中味？'),(4,'围城','钱锺书','男',29.9,3,'围在城里的人想逃出来，城外的人想冲进去，对婚姻也罢，职业也罢，人生的愿望大都如此。'),(5,'万历十五年','黄仁宇','男',40,11,'见微知著，历史观的颠覆'),(6,'全球通史','斯塔夫里阿诺斯','男',109.9,11,'全球通史是什么意思？就是西方人从西方的角度来解释世界的历史。'),(7,'明朝那些事','当年明月','男',79.9,11,'不拘一格的历史书写'),(8,'1984','乔治·奥威尔 ','男',35.7,3,'栗树荫下，我出卖你，你出卖我'),(9,'哈利·波特','J.K.罗琳 ','女',498,3,'从9¾站台开始的旅程'),(10,'动物农场','乔治·奥威尔','男',10,3,'太阳底下并无新事'),(11,'沉默的大多数','王小波','男',27.7,3,'沉默是沉默者的通行证'),(12,'上帝掷骰子吗','曹天元','男',32,4,'量子物理史话'),(13,'理想国','柏拉图','男',28,1,'《理想国》涉及柏拉图思想体系的各个方面，包括哲学、伦理、教育、文艺、政治等内容，主要是探讨理想国家的问题。'),(14,'苏菲的世界','乔斯坦·贾德','男',39.9,1,'人人都读懂的西方哲学');

/*Table structure for table `t_booktype` */

DROP TABLE IF EXISTS `t_booktype`;

CREATE TABLE `t_booktype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookTypeName` varchar(20) DEFAULT NULL,
  `bookTypeDesc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `t_booktype` */

insert  into `t_booktype`(`id`,`bookTypeName`,`bookTypeDesc`) values (1,'哲学','哲学，宗教类书籍'),(2,'军事','军事，战争类书籍'),(3,'文化','文学，艺术类书籍'),(4,'科学','物理，化学类书籍'),(5,'生物','生物科学类书籍'),(6,'医学','中西方医学书籍'),(7,'综合','综合性图书'),(8,'航天','航空航天类书籍'),(10,'地理','人文地理类书籍'),(11,'历史','人文历史类书籍'),(12,'建筑','人文建筑类书籍');

/*Table structure for table `t_borrow` */

DROP TABLE IF EXISTS `t_borrow`;

CREATE TABLE `t_borrow` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `borrow_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `return_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `t_borrow_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `t_borrow_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `t_book` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_borrow` */

insert  into `t_borrow`(`id`,`user_id`,`book_id`,`borrow_date`,`return_date`) values (1,2,1,'2023-06-01 14:29:38',NULL),(2,2,3,'2023-06-01 14:36:21',NULL),(5,2,11,'2023-06-02 11:00:21',NULL),(6,2,12,'2023-06-02 11:59:49',NULL),(8,4,5,'2023-06-07 16:27:55',NULL);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `userDesc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`userName`,`password`,`userDesc`) values (2,'小黑子','123456','我不是小黑子，我是真IKUN'),(4,'张三','123456','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
