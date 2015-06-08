package zx.soft.weibo.mapred.tsdb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sender {

	private static final Logger logger = LoggerFactory.getLogger(Sender.class);

	PrintWriter tsdbWriter;

	public Sender(String server, int port) {
		logger.info("tsdb server={}, port={}", server, port);
		try (Socket socket = new Socket(server, port);) {
			tsdbWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendStatus(String host, int port) {
		logger.debug("send status: host={}, port={}", host, port);
		try (Socket socket = new Socket(host, port);
				PrintWriter writer = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream())));
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			writer.println("status");
			writer.flush();
			do {
				send(reader.readLine());
			} while (reader.ready());
			this.tsdbWriter.flush();
		} catch (IOException e) {
			logger.warn(e.getMessage());
		}
	}

	void send(String tsdbData) {
		logger.debug(tsdbData);
		tsdbWriter.println("put " + tsdbData);
	}

}
