package cc.pp.sina.mapred.userrank;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import cc.pp.sina.mapred.lib.input.IgnoreEofSequenceFileInputFormat;

public class SinaUserSum {

	enum CountInfo {
		SINA_USER_SUM
	}

	public static class SinaUserSumMapper extends Mapper<Text, Text, LongWritable, IntWritable> {

		private final LongWritable outKey = new LongWritable();
		private final IntWritable outValue = new IntWritable();

		@Override
		protected void map(Text uid, Text value, Context context) throws IOException, InterruptedException {

			if (uid.toString().length() > 1) {
				outKey.set(Long.parseLong(uid.toString()));
				outValue.set(1);
				context.write(outKey, outValue);
			}
		}

	}

	public static class SinaUserSumCombiner extends Reducer<LongWritable, IntWritable, LongWritable, IntWritable> {

		@Override
		protected void reduce(LongWritable uid, Iterable<IntWritable> values, Context context) throws IOException,
				InterruptedException {
			context.write(uid, new IntWritable(1));
		}
	}

	public static class SinaUserSumReducer extends Reducer<LongWritable, IntWritable, LongWritable, NullWritable> {

		@Override
		protected void reduce(LongWritable uid, Iterable<IntWritable> values, Context context) throws IOException,
				InterruptedException {
			context.setStatus("Sina users count.");
			context.getCounter(CountInfo.SINA_USER_SUM).increment(1);
			context.write(uid, NullWritable.get());
		}
	}

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (otherArgs.length != 2) {
			System.err.println("Usage: SinaUserSum <in> <out>");
			System.exit(2);
		}
		Job job = new Job(conf, "Sina User Sum");
		job.setJarByClass(SinaUserSum.class);
		job.setMapperClass(SinaUserSumMapper.class);
		job.setCombinerClass(SinaUserSumCombiner.class);
		job.setReducerClass(SinaUserSumReducer.class);
		job.setInputFormatClass(IgnoreEofSequenceFileInputFormat.class);
		job.setOutputFormatClass(SequenceFileOutputFormat.class);
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(NullWritable.class);

		job.setNumReduceTasks(1);

		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
