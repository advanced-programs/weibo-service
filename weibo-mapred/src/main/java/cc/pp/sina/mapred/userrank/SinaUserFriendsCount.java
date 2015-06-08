package cc.pp.sina.mapred.userrank;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
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

public class SinaUserFriendsCount {

	enum SINA_USERS_SUM {
		sina_users_sum;
	}

	public static class SinaUserFriendsCountMapper extends Mapper<Text, Text, LongWritable, IntWritable> {

		private final LongWritable outKey = new LongWritable();
		private final IntWritable outValue = new IntWritable();

		@Override
		protected void map(Text uid, Text value, Context context) throws IOException, InterruptedException {
			Iterator<String> it = Splitter.on(',') //
					.trimResults() //
					.omitEmptyStrings() //
					.split(value.toString()) //
					.iterator();
			int count = 0;
			while (it.hasNext()) {
				count++;
			}
			if (uid.toString().length() > 1) {
				outKey.set(Long.parseLong(uid.toString()));
				outValue.set(count);
				context.write(outKey, outValue);
			}
		}
	}

	public static class SinaUserFriendsCountReducer extends
			Reducer<LongWritable, IntWritable, LongWritable, IntWritable> {

		private final IntWritable outValue = new IntWritable();

		@Override
		protected void reduce(LongWritable uid, Iterable<IntWritable> values, Context context) throws IOException,
				InterruptedException {
			int sum = 0;
			for (IntWritable value : values) {
				sum += value.get();
			}
			outValue.set(sum);
			context.getCounter(SINA_USERS_SUM.sina_users_sum).increment(1);
			context.write(uid, outValue);
		}
	}

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (otherArgs.length != 2) {
			System.err.println("Usage: sinaUserFriendsCount <input_path> <output_path>");
			System.exit(2);
		}

		Job job = new Job(conf, "Sina User Friends Count");
		job.setJarByClass(SinaUserFriendsCount.class);
		job.setMapperClass(SinaUserFriendsCountMapper.class);
		job.setCombinerClass(SinaUserFriendsCountReducer.class);
		job.setReducerClass(SinaUserFriendsCountReducer.class);
		job.setInputFormatClass(IgnoreEofSequenceFileInputFormat.class);
		job.setOutputFormatClass(SequenceFileOutputFormat.class);
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(IntWritable.class);

		job.setNumReduceTasks(40);

		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
