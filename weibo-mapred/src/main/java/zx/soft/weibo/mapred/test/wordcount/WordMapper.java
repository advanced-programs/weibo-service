package zx.soft.weibo.mapred.test.wordcount;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordMapper extends Mapper<Object, Text, Text, IntWritable> {

	private final Text word = new Text();
	private final static IntWritable one = new IntWritable(1);

	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		StringTokenizer wordList = new StringTokenizer(value.toString());
		while (wordList.hasMoreTokens()) {
			word.set(wordList.nextToken());

			// key is password, one is int 1
			context.write(word, one);
		}
	}

}
