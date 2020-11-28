CREATE DATABASE files;

USE files;


DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

insert  into `t_user`(`id`,`username`,`password`) values
(1,'xiaochen','123456'),
(2,'xiaohei','123456');

CREATE TABLE `t_files` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oldFileName` varchar(200) CHARACTER SET gbk DEFAULT NULL,
  `newFileName` varchar(200) CHARACTER SET gbk DEFAULT NULL,
  `ext` varchar(20) CHARACTER SET gbk DEFAULT NULL COMMENT '文件后缀',
  `path` varchar(300) CHARACTER SET gbk DEFAULT NULL,
  `size` varchar(200) CHARACTER SET gbk DEFAULT NULL,
  `type` varchar(200) CHARACTER SET gbk DEFAULT NULL,
  `isimg` varchar(8) CHARACTER SET gbk DEFAULT NULL,
  `downloadCounts` int(6) DEFAULT NULL,
  `uploadTime` datetime DEFAULT NULL,
  `userId` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=greek;