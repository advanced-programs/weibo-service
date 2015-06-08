package zx.soft.weibo.mapred.hdfs;

public class FileExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileExistsException(String file) {
		super("File " + file + " exists");
	}

}
