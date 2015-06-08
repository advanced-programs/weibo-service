package cc.pp.sina.mapred.userrank;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
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

/**
 * 首次迭代计算，各节点的初始UserRank值为1
 * @author wgybzb
 *
 */
public class SinaUserRank {

	private static final Long ALL_USER_NUM = 5_8000_0000L;
	private static final Float ALPHA = 0.75f;

	public static class SinaUserRankMapper extends Mapper<Text, Text, LongWritable, DoubleWritable> {

		private final LongWritable outKey = new LongWritable();
		private final DoubleWritable outValue = new DoubleWritable();

		@Override
		protected void map(Text uid, Text fans, Context context) throws IOException, InterruptedException {

			double count = 0.0;
			Iterator<String> it = Splitter.on(',') //
					.trimResults() //
					.omitEmptyStrings() //
					.split(fans.toString()) //
					.iterator();
			while (it.hasNext()) {
				//				count = count + (float) 1 / selectFriendsByUid(it.next());
				count++;
			}
			if (uid.toString().length() > 1) {
				outKey.set(Long.parseLong(uid.toString()));
				outValue.set(count);
				context.write(outKey, outValue);
			}
		}
	}

	public static class SinaUserRankCombiner extends
			Reducer<LongWritable, DoubleWritable, LongWritable, DoubleWritable> {

		private final DoubleWritable sumValue = new DoubleWritable();

		@Override
		protected void reduce(LongWritable uid, Iterable<DoubleWritable> scores, Context context) throws IOException,
				InterruptedException {

			double sum = 0.0;
			for (DoubleWritable score : scores) {
				sum = sum + score.get();
			}
			sumValue.set(sum);
			context.write(uid, sumValue);
		}
	}

	public static class SinaUserRankReducer extends Reducer<LongWritable, DoubleWritable, LongWritable, DoubleWritable> {

		private final DoubleWritable userRank = new DoubleWritable();

		@Override
		protected void reduce(LongWritable uid, Iterable<DoubleWritable> scores, Context context) throws IOException,
				InterruptedException {

			double sum = 0.0;
			for (DoubleWritable score : scores) {
				sum = sum + score.get();
			}
			userRank.set(ALPHA * sum + (1 - ALPHA) / ALL_USER_NUM);
			context.write(uid, userRank);
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (otherArgs.length != 2) {
			System.err.println("Usage: SinaUserRank <intput_file> <output_file>");
			System.exit(2);
		}

		Job job = new Job(conf, "Sina User Rank");
		job.setJarByClass(SinaUserRank.class);
		job.setMapperClass(SinaUserRankMapper.class);
		job.setCombinerClass(SinaUserRankCombiner.class);
		job.setReducerClass(SinaUserRankReducer.class);
		job.setInputFormatClass(IgnoreEofSequenceFileInputFormat.class);
		job.setOutputFormatClass(SequenceFileOutputFormat.class);
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(DoubleWritable.class);

		job.setNumReduceTasks(1);

		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

		System.out.println(job.waitForCompletion(true) ? 0 : 1);
	}


}
