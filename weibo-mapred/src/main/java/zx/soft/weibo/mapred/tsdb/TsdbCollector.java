package zx.soft.weibo.mapred.tsdb;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class TsdbCollector {

	public static void main(String[] args) {
		Properties ps = new Properties();
		try {
			ps.load(TsdbCollector.class.getClassLoader().getResourceAsStream("tsdb.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		final String host = ps.getProperty("tsdb.host");
		final int port = Integer.parseInt(ps.getProperty("tsdb.port"));

		final int period = Integer.parseInt(ps.getProperty("collect.period"));
		final Map<String, Integer> prgs = new HashMap<String, Integer>();
		for (String hostAndPort : ps.getProperty("collect.prgs").split(",")) {
			String[] split = hostAndPort.trim().split(":");
			prgs.put(split[0], Integer.parseInt(split[1]));
		}

		Timer timer = new Timer("TsdbCollectorThread");
		long now = System.currentTimeMillis();
		long start = now - now % period + period; // 下一个时间单元的整点时间

		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				Sender sender = new Sender(host, port);
				for (Entry<String, Integer> entry : prgs.entrySet()) {
					sender.sendStatus(entry.getKey(), entry.getValue());
				}
			}
		}, new Date(start), period);
	}

}
