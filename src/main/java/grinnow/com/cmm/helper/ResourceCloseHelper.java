package grinnow.com.cmm.helper;

import java.io.Closeable;

public class ResourceCloseHelper {

	public static void close(Closeable  ... resources) {
		for (Closeable resource : resources) {
			if (resource != null) {
				try {
					resource.close();
				} catch (Exception ignore) {
					ignore.getMessage();
				}
			}
		}
	}
}
