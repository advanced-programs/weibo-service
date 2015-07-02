
## 简介

> 基于MapReduce新浪用户数据分析和挖掘。

## 内容

* 抓取新浪用户基本信息
* 抓取新浪用户的关注信息
* 倒排出新浪的粉丝信息
* 建立新浪uid-fans的Mapfile文件

## 打包部署

```Java
mvn clean package

scp target/weibo-mapred-1.2.0-jar-with-dependencies.jar root@bigdata4:/opt/cloudera/parcels/CDH/jars
```

## CDH5运行

```Java
首次：
sudo -u hdfs hadoop jar /opt/cloudera/parcels/CDH/jars/weibo-mapred-1.2.0-jar-with-dependencies.jar zx.soft.weibo.mapred.sina.uids.SpiderSinaUidsMain 100 1862087393

再次：
sudo -u hdfs hadoop jar /opt/cloudera/parcels/CDH/jars/weibo-mapred-1.2.0-jar-with-dependencies.jar zx.soft.weibo.mapred.sina.uids.SpiderSinaUidsMain
```


