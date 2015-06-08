package cc.pp.sina.mapred.mapfile;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.util.ReflectionUtils;

/**
 * 1000个新浪随机用户查找需要76s，查找库大小是660G，6亿条纪录
 * @author wgybzb
 *
 */
public class MapFileReader {

	private final Configuration conf;
	private final FileSystem fs;
	private final MapFile.Reader reader;
	private final WritableComparable<?> key;
	private final Writable value;
	private final String uri = "/user/hadoop/datacenter/sortedSinaUserFans";

	public MapFileReader() throws IOException {
		conf = new Configuration();
		conf.addResource("core-site.xml");
		fs = FileSystem.get(URI.create(uri), conf);
		reader = new MapFile.Reader(fs, uri, conf);
		key = (WritableComparable<?>) ReflectionUtils.newInstance(reader.getKeyClass(), conf);
		value = (Writable) ReflectionUtils.newInstance(reader.getValueClass(), conf);
	}

	/**
	 * 线上测试
	 */
	public static void main(String[] args) throws IOException {

		if (args.length != 1) {
			System.err.println("Usage: MapFileReaderDemo <input key>");
			System.exit(-1);
		}
		MapFileReader mapFileReader = new MapFileReader();
		WritableComparable<?> query_key = new Text(args[0]);

		mapFileReader.findValuesByKey(query_key);
		System.out.println(mapFileReader.findAllValuesByKey(query_key).size());
		//		mapFileReader.test();
	}

	/**
	 * 线上测试数据
	 */
	public void test() throws IOException {

		Text[] keys = new Text[] { new Text("1000000440"), new Text("1000000445"), new Text("1000000807"),
				new Text("1000000861") };
		for (Text querykey : keys) {
			findValuesByKey(querykey);
		}
	}

	/**
	 * 查找query_key的所有值
	 */
	public void findValuesByKey(WritableComparable<?> querykey) throws IOException {

		reader.get(querykey, value);
		System.out.println(querykey.toString() + "\t" + ((Text) value).toString());
		while (reader.next(key, value)) {
			if (key.toString().equals(querykey.toString())) {
				System.out.println(querykey.toString() + "\t" + ((Text) value).toString());
			} else {
				break;
			}
		}
	}

	public List<Long> findAllValuesByKey(WritableComparable<?> querykey) throws IOException {

		List<Long> result = new ArrayList<Long>();
		reader.get(querykey, value);
		addUids(result, ((Text) value).toString().split(","));
		while (reader.next(key, value)) {
			if (key.toString().equals(querykey.toString())) {
				addUids(result, ((Text) value).toString().split(","));
			} else {
				break;
			}
		}

		return result;
	}

	private void addUids(List<Long> result, String[] fans) {

		for (String uid : fans) {
			if ((uid != null) && (uid.length() > 1)) {
				result.add(Long.parseLong(uid));
			}
		}
	}

}
