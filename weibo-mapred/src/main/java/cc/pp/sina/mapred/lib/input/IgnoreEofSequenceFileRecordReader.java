package cc.pp.sina.mapred.lib.input;

import java.io.EOFException;
import java.io.IOException;

import org.apache.hadoop.mapreduce.lib.input.SequenceFileRecordReader;

public class IgnoreEofSequenceFileRecordReader<K, V> extends SequenceFileRecordReader<K, V> {

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		try {
			return super.nextKeyValue();
		} catch (EOFException e) {
			// ignore
		}
		return false;
	}

}
