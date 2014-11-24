-- phpMyAdmin SQL Dump
-- version 2.11.11.3
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2014 年 11 月 24 日 11:25
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
-- 表的结构 `tmp_sina_user_baseinfo`
--

CREATE TABLE IF NOT EXISTS `tmp_sina_user_baseinfo` (
  `bid` int(10) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `screen_name` char(50) NOT NULL DEFAULT '0' COMMENT '微博昵称',
  `name` char(50) NOT NULL DEFAULT '0' COMMENT '友好显示名称',
  `province` int(5) NOT NULL DEFAULT '0' COMMENT '省份编码',
  `city` int(5) NOT NULL DEFAULT '0' COMMENT '城市编码',
  `location` char(50) NOT NULL DEFAULT '0' COMMENT '地址',
  `description` varchar(300) NOT NULL DEFAULT '0' COMMENT '个人描述',
  `url` char(200) NOT NULL DEFAULT '0' COMMENT '用户博客地址',
  `profile_image_url` char(200) NOT NULL DEFAULT '0' COMMENT '自定义头像',
  `domain` char(50) NOT NULL DEFAULT '0' COMMENT '用户个性化URL',
  `gender` char(2) NOT NULL DEFAULT '0' COMMENT '性别,m--男，f--女,n--未知',
  `followers_count` int(8) NOT NULL DEFAULT '0' COMMENT '粉丝数',
  `friends_count` int(8) NOT NULL DEFAULT '0' COMMENT '关注数',
  `statuses_count` int(8) NOT NULL DEFAULT '0' COMMENT '微博数',
  `favourites_count` int(8) NOT NULL DEFAULT '0' COMMENT '收藏数',
  `created_at` int(10) NOT NULL DEFAULT '0' COMMENT '用户创建时间',
  `verified` tinyint(1) NOT NULL DEFAULT '-1' COMMENT '加V与否，是否微博认证用户',
  `verified_type` int(5) NOT NULL DEFAULT '-1' COMMENT '认证类型',
  `avatar_large` char(200) NOT NULL DEFAULT '0' COMMENT '大头像地址',
  `bi_followers_count` int(8) NOT NULL DEFAULT '0' COMMENT '互粉数',
  `remark` char(250) NOT NULL DEFAULT '0' COMMENT '备注信息',
  `verified_reason` varchar(300) NOT NULL DEFAULT '0' COMMENT '认证原因',
  `weihao` char(50) NOT NULL DEFAULT '0' COMMENT '微信号',
  `lasttime` int(10) NOT NULL COMMENT '记录时间',
  PRIMARY KEY (`bid`),
  UNIQUE KEY `id` (`id`),
  KEY `province_city` (`province`,`city`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='新浪用户基础信息表（有用信息）' AUTO_INCREMENT=1 ;

--
-- 导出表中的数据 `tmp_sina_user_baseinfo`
--

