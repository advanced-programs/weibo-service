package cc.pp.sina.mapred.lib.input;

import java.io.IOException;

import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;

/**
 * 因为SequenceFile写入如果未正常关闭，容易在读取的时候出现EOF异常
 * 该类会忽略该异常
 *
 * @author chenwei
 *
 * @param <K>
 * @param <V>
 */
public class IgnoreEofSequenceFileInputFormat<K, V> extends SequenceFileInputFormat<K, V> {

	@Override
	public RecordReader<K, V> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException {
		return new IgnoreEofSequenceFileRecordReader<K, V>();
	}
}
