package zx.soft.weibo.mapred.tsdb;

import java.util.ArrayList;
import java.util.List;

public class TsdbReporterExample {

	public static void main(String[] args) {
		TsdbReporter reporter = new TsdbReporter("bigdata4", 4242);
		reporter.addReport(new Reportable() {
			@Override
			public List<Tsdb> report() {
				List<Tsdb> tsdbs = new ArrayList<Tsdb>();
				tsdbs.add(new Tsdb("proc.stat.cpu", 54.2, "host", "foo", "type", "user"));
				tsdbs.add(new Tsdb("proc.stat.cpu", 2.66, "host", "foo", "type", "nice"));
				return tsdbs;
			}
		});
		reporter.exec();
	}

}
