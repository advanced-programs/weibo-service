package zx.soft.weibo.mapred.hdfs;

import java.io.Closeable;

public interface HdfsWriter extends Closeable {

	void write(String key, String value);

}
