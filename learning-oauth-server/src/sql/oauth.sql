/*
SQLyog Ultimate v11.42 (64 bit)
MySQL - 5.5.28 : Database - oauth
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oauth` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `oauth`;

/*Table structure for table `oauth_access_token` */

DROP TABLE IF EXISTS `oauth_access_token`;

CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `token` longblob,
  `authentication_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `user_name` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `client_id` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `authentication` longblob,
  `refresh_token` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `oauth_approvals` */

DROP TABLE IF EXISTS `oauth_approvals`;

CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `clientId` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `scope` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `oauth_client_details` */

DROP TABLE IF EXISTS `oauth_client_details`;

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `resource_ids` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `client_secret` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `scope` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `authorized_grant_types` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `authorities` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) COLLATE utf8_bin DEFAULT NULL,
  `autoapprove` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `oauth_client_token` */

DROP TABLE IF EXISTS `oauth_client_token`;

CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `token` longblob,
  `authentication_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `user_name` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `client_id` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `oauth_code` */

DROP TABLE IF EXISTS `oauth_code`;

CREATE TABLE `oauth_code` (
  `code` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `authentication` longblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `oauth_refresh_token` */

DROP TABLE IF EXISTS `oauth_refresh_token`;

CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `token` longblob,
  `authentication` longblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
