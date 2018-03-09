--
DROP DATABASE IF EXISTS `hibernate_project`;
--
DROP USER IF EXISTS 'hibernate'@'localhost';
--
CREATE DATABASE  IF NOT EXISTS `hibernate_project` /*!40100 DEFAULT CHARACTER SET utf8 */;
--
USE `hibernate_project`;
--
CREATE USER 'hibernate'@'localhost' IDENTIFIED BY 'hibernate';
--
GRANT ALL PRIVILEGES ON hibernate_project.* TO 'hibernate'@'localhost';
--
