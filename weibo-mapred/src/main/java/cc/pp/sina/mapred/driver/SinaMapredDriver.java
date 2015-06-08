package cc.pp.sina.mapred.driver;

import org.apache.hadoop.util.ProgramDriver;

import cc.pp.sina.mapred.fans.SinaUserFollower;
import cc.pp.sina.mapred.mapfile.MapFileFixer;
import cc.pp.sina.mapred.mapfile.MapFileReader;
import cc.pp.sina.mapred.mapfile.MergeSequenceFiles;
import cc.pp.sina.mapred.userrank.SinaUserFriendsCount;

public class SinaMapredDriver {

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
