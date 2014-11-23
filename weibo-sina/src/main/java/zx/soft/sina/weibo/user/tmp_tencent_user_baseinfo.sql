-- phpMyAdmin SQL Dump
-- version 2.11.11.3
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2014 年 11 月 23 日 13:25
-- 服务器版本: 5.1.73
-- PHP 版本: 5.3.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `weibo_sina`
--

-- --------------------------------------------------------

--
-- 表的结构 `tmp_tencent_user_baseinfo`
--

CREATE TABLE IF NOT EXISTS `tmp_tencent_user_baseinfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` char(50) NOT NULL,
  `nick` char(50) NOT NULL,
  `openid` char(40) NOT NULL,
  `fansnum` int(8) NOT NULL,
  `idolnum` int(8) NOT NULL,
  `favnum` int(8) NOT NULL,
  `tweetnum` int(8) NOT NULL,
  `birth_day` int(8) NOT NULL,
  `birth_month` int(8) NOT NULL,
  `birth_year` int(8) NOT NULL,
  `city_code` char(10) NOT NULL,
  `comp` mediumtext NOT NULL,
  `country_code` char(10) NOT NULL,
  `edu` mediumtext NOT NULL,
  `email` char(50) NOT NULL,
  `exp` int(8) NOT NULL,
  `head` char(150) NOT NULL,
  `homecity_code` char(10) NOT NULL,
  `homecountry_code` char(10) NOT NULL,
  `homepage` char(150) NOT NULL,
  `homeprovince_code` char(10) NOT NULL,
  `hometown_code` char(10) NOT NULL,
  `https_head` char(150) NOT NULL,
  `industry_code` int(10) NOT NULL,
  `introduction` char(250) NOT NULL,
  `isent` int(2) NOT NULL,
  `isrealname` int(2) NOT NULL,
  `isvip` int(2) NOT NULL,
  `level` int(3) NOT NULL,
  `location` char(30) NOT NULL,
  `mutual_fans_num` int(8) NOT NULL,
  `province_code` char(10) NOT NULL,
  `regtime` int(10) NOT NULL,
  `send_private_flag` int(2) NOT NULL,
  `sex` int(2) NOT NULL,
  `tag` mediumtext NOT NULL,
  `verifyinfo` char(250) NOT NULL,
  `lasttime` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `province_city` (`province_code`,`city_code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 导出表中的数据 `tmp_tencent_user_baseinfo`
--

