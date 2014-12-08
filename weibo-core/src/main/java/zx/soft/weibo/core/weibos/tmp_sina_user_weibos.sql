-- phpMyAdmin SQL Dump
-- version 3.3.8
-- http://www.phpmyadmin.net
--
-- 主机: 192.168.1.43:3306
-- 生成日期: 2014 年 11 月 23 日 19:59
-- 服务器版本: 5.5.13
-- PHP 版本: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `pp_fenxi`
--

-- --------------------------------------------------------

--
-- 表的结构 `tmp_sina_user_weibos`
--

CREATE TABLE IF NOT EXISTS `tmp_sina_user_weibos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',
  `wid` bigint(20) unsigned NOT NULL COMMENT '微博id',
  `username` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `repostscount` mediumint(8) unsigned NOT NULL COMMENT '转发数量',
  `commentscount` mediumint(8) unsigned NOT NULL COMMENT '评论数量',
  `attitudescount` mediumint(8) unsigned NOT NULL COMMENT '赞数量',
  `text` varchar(300) NOT NULL COMMENT '微博内容',
  `createat` int(10) unsigned NOT NULL COMMENT '微博创建时间',
  `owid` bigint(20) unsigned NOT NULL,
  `ousername` int(10) unsigned NOT NULL COMMENT '被转发或原创用户d',
  `favorited` tinyint(1) NOT NULL COMMENT '是否已收藏',
  `geo` varchar(300) NOT NULL COMMENT '地理信息字段',
  `latitude` float NOT NULL COMMENT '经度',
  `longitude` float NOT NULL COMMENT '维度',
  `originalpic` varchar(500) NOT NULL COMMENT '原始图片地址',
  `source` varchar(30) NOT NULL COMMENT '微博来源',
  `visible` varchar(30) NOT NULL COMMENT '微博的可见性及指定可见分组信息',
  `mlevel` smallint(8) unsigned NOT NULL COMMENT '微博等级',
  `lasttime` int(10) unsigned NOT NULL COMMENT '采集时间',
  PRIMARY KEY (`id`),
  KEY `wid` (`wid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='新浪的用户微博信息' AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `tmp_sina_user_weibos`
--

