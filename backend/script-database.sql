--
DROP DATABASE IF EXISTS `web_project`;
--
CREATE DATABASE  IF NOT EXISTS `web_project` /*!40100 DEFAULT CHARACTER SET utf8 */;
--
USE `web_project`;
--
CREATE USER IF NOT EXISTS 'hibernate'@'localhost' IDENTIFIED BY 'hibernate';
--
GRANT ALL PRIVILEGES ON web_project.* TO 'hibernate'@'localhost';
--
