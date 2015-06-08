package cc.pp.sina.mapred.fans;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import cc.pp.sina.mapred.fans.SinaUserFollower.SinaUserFollowerMapper;
import cc.pp.sina.mapred.fans.SinaUserFollower.SinaUserFollowerReducer;

public class SinaUserFollowerTest {

	private MapDriver<Text, Text, Text, Text> mapDriver;
	private ReduceDriver<Text, Text, Text, Text> reduceDriver;
	@SuppressWarnings("unused")
	private MapReduceDriver<Text, Text, Text, Text, Text, Text> mapReduceDriver;

	@Before
	public void setUp() {
		SinaUserFollowerMapper mapper = new SinaUserFollowerMapper();
		SinaUserFollowerReducer reducer = new SinaUserFollowerReducer();
		mapDriver = MapDriver.newMapDriver(mapper);
		reduceDriver = ReduceDriver.newReduceDriver(reducer);
		mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
	}

	@Test
	public void testMapper() {
		mapDriver.withInput(new Text("u1"), new Text("friend1,friend2,friend3"));
		mapDriver.withOutput(new Text("friend1"), new Text("u1"));
		mapDriver.withOutput(new Text("friend2"), new Text("u1"));
		mapDriver.withOutput(new Text("friend3"), new Text("u1"));
		mapDriver.runTest();
	}

	@Test
	public void testReducer() {
		List<Text> values = new ArrayList<Text>();
		values.add(new Text("follower1"));
		values.add(new Text("follower2"));
		values.add(new Text("follower3"));
		reduceDriver.withInput(new Text("u1"), values);
		reduceDriver.withOutput(new Text("u1"), new Text("follower1,follower2,follower3"));
		reduceDriver.runTest();
	}

	@Test
	public void testReducer_valueMoreThan1024() {
		List<Text> values = new ArrayList<Text>();
		for (int i = 0; i < 300; i++) {
			values.add(new Text("f" + i));
		}
		StringBuilder expected1 = new StringBuilder();
		for (int i = 0; expected1.length() < SinaUserFollowerReducer.MAX_VALUE_LENGTH; i++) {
			expected1.append("f").append(i).append(",");
		}
		expected1.deleteCharAt(expected1.length() - 1);
		// System.out.println(expected1.toString());

		StringBuilder expected2 = new StringBuilder();
		for (int i = 227; i < 300; i++) {
			expected2.append("f").append(i).append(",");
		}
		expected2.deleteCharAt(expected2.length() - 1);
		// System.out.println(expected2.toString());

		reduceDriver.withInput(new Text("u1"), values);
		reduceDriver.withOutput(new Text("u1"), new Text(expected1.toString()));
		reduceDriver.withOutput(new Text("u1"), new Text(expected2.toString()));
		reduceDriver.runTest();
	}

}
