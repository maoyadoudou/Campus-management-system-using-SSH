package Utils;

import java.util.UUID;

public class UploadUtils {
	public static String getUUIDName(String fileName) {
		int index = fileName.lastIndexOf(".");
		String typeName = fileName.substring(index, fileName.length());
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid + typeName;
	}

}
