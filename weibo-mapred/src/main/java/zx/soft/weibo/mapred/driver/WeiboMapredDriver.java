package zx.soft.weibo.mapred.driver;

import org.apache.hadoop.util.ProgramDriver;

import zx.soft.weibo.mapred.mapfile.MapFileFixer;
import zx.soft.weibo.mapred.mapfile.MapFileReader;
import zx.soft.weibo.mapred.mapfile.MergeSequenceFiles;
import zx.soft.weibo.mapred.rank.SinaUserFriendsCount;
import zx.soft.weibo.mapred.sina.followers.SinaUserFollower;

public class WeiboMapredDriver {

	public static void main(String argv[]) {

		int exitCode = -1;
		ProgramDriver pgd = new ProgramDriver();
		try {
			pgd.addClass("sinaUserFollower", SinaUserFollower.class, "新浪用户粉丝倒排索引");
			pgd.addClass("mergeSequenceFiles", MergeSequenceFiles.class, "新浪粉丝序列化文件合并");
			pgd.addClass("mapFileFixer", MapFileFixer.class, "将序列化文件转换成MapFile文件");
			pgd.addClass("mapFileReader", MapFileReader.class, "在MapFile文件中查找某个key");
			pgd.addClass("sinaUserFriendsCount", SinaUserFriendsCount.class, "计算新浪用户的关注数量");
			pgd.driver(argv);

			// Success
			exitCode = 0;
		} catch (Throwable e) {
			e.printStackTrace();
		}

		System.exit(exitCode);
	}
}
