package cc.pp.sina.mapred.mapfile;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.SequenceFile;


/**
 * Sequence文件转换为MapFile文件
 * @author wgybzb
 *
 */
public class MapFileFixer {

	@SuppressWarnings({ "resource", "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception {

		if (args.length != 1) {
			System.err.println("Usage: MapFileFixer <input file>");
			System.exit(-1);
		}
		String uri = args[0];

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		Path map = new Path(uri);
		Path mapData = new Path(map, MapFile.DATA_FILE_NAME);
		SequenceFile.Reader reader = new SequenceFile.Reader(fs, mapData, conf);
		//		WritableComparable<?> key = (WritableComparable<?>) ReflectionUtils.newInstance(reader.getKeyClass(), conf);
		//		Writable value = (Writable) ReflectionUtils.newInstance(reader.getValueClass(), conf);
		//		reader.close();
		//		long entries = MapFile.fix(fs, map, key.getClass(), value.getClass(), false, conf);
		Class keyClass = reader.getKeyClass();
		Class valueClass = reader.getValueClass();

		MapFile.Writer.setIndexInterval(conf, 512);

		long entries = MapFile.fix(fs, map, keyClass, valueClass, false, conf);

		System.out.printf("Created MapFile %s with %d entries\n", map, entries);

	}

}
