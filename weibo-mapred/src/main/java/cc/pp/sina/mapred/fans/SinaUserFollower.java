package cc.pp.sina.mapred.fans;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import cc.pp.sina.mapred.lib.input.IgnoreEofSequenceFileInputFormat;

import com.google.common.base.Splitter;

public class SinaUserFollower {

	public static class SinaUserFollowerMapper extends Mapper<Text, Text, Text, Text> {

		private final Text friendUid = new Text();

		@Override
		protected void map(Text uid, Text value, Context context) throws IOException, InterruptedException {
			Iterator<String> it = Splitter.on(',') //
					.trimResults() //
					.omitEmptyStrings() //
					.split(value.toString()) //
					.iterator();
			while (it.hasNext()) {
				friendUid.set(it.next());
				context.write(friendUid, uid);
			}
		}

	}

	public static class SinaUserFollowerReducer extends Reducer<Text, Text, Text, Text> {

		static final int MAX_VALUE_LENGTH = 1024;
		private final Text followerIds = new Text();

		@Override
		protected void reduce(Text uid, Iterable<Text> followers, Context context) throws IOException,
				InterruptedException {
			StringBuilder result = new StringBuilder();
			for (Text followerId : followers) {
				if (result.length() > MAX_VALUE_LENGTH) {
					result.deleteCharAt(result.length() - 1);
					followerIds.set(result.toString());
					context.write(uid, followerIds);

					result.setLength(0);
				}
				result.append(followerId.toString()).append(",");
			}
			result.deleteCharAt(result.length() - 1);
			followerIds.set(result.toString());
			context.write(uid, followerIds);
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (otherArgs.length != 2) {
			System.err.println("Usage: sinaUserFollower <in> <out>");
			System.exit(2);
		}
		Job job = new Job(conf, "sina user follower");
		job.setJarByClass(SinaUserFollower.class);
		job.setMapperClass(SinaUserFollowerMapper.class);
		job.setCombinerClass(SinaUserFollowerReducer.class);
		job.setReducerClass(SinaUserFollowerReducer.class);
		job.setInputFormatClass(IgnoreEofSequenceFileInputFormat.class);
		job.setOutputFormatClass(SequenceFileOutputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setNumReduceTasks(40);

		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
