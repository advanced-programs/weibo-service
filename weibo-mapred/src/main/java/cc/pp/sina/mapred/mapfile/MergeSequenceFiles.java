package cc.pp.sina.mapred.mapfile;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.util.ReflectionUtils;

/**
 *  对于621.58G的文件，
 *      1、使用MapReduce（Reducer为1个），耗时28个小时
 *      2、使用SequenceFile.Sorter，耗时
 */
public class MergeSequenceFiles {

	public static void main(String[] args) throws IOException {

		if (args.length != 1) {
			System.err.println("Usage: MergeSequenceFiles <input path>");
			System.exit(-1);
		}
		String inPath = args[0];

		String outPath = inPath + "/data";
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(inPath), conf);

		FileStatus[] status = fs.listStatus(new Path(inPath));
		Path[] tempFiles = FileUtil.stat2Paths(status);
		/*
		 * HDFS读文件的时候，忽略以下划线（“_”）开头的文件
		 */
		Path[] inFiles = new Path[tempFiles.length - 2];

		int i = 0;
		for (Path p : tempFiles) {
			if (p.getName().contains("part")) {
				inFiles[i++] = p;
				System.out.println(p.getName());
			}
		}

		SequenceFile.Reader reader = new SequenceFile.Reader(fs, inFiles[0], conf);
		WritableComparable<?> key = (WritableComparable<?>) ReflectionUtils.newInstance(reader.getKeyClass(), conf);
		Writable value = (Writable) ReflectionUtils.newInstance(reader.getValueClass(), conf);
		SequenceFile.Sorter sorter = new SequenceFile.Sorter(fs, key.getClass(), value.getClass(), conf);

		sorter.sort(inFiles, new Path(outPath), false);

		IOUtils.closeStream(reader);

		System.out.println("Finish!");
	}

}
