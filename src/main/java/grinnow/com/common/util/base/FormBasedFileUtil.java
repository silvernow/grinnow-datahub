package grinnow.com.common.util.base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import grinnow.com.cmm.helper.ResourceCloseHelper;
import grinnow.com.cmm.util.WebUtil;

public class FormBasedFileUtil {

	public static final int BUFFER_SIZE = 8192;

	public static final String SEPERATOR = File.separator;

	private static final Logger LOGGER = LoggerFactory.getLogger(FormBasedFileUtil.class);

	public static String getTodayString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

		return format.format(new Date());
	}

	public static String getPhysicalFileName() {
		return FormBasedUUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

	protected static String convert(String filename) throws Exception {
		//return java.net.URLEncoder.encode(filename, "utf-8");
		return filename;
	}

	public static long saveFile(InputStream is, File file) throws IOException {
		if (file.getParentFile() == null) {
			LOGGER.debug("file.getParentFile() is null");
			throw new RuntimeException("file.getParentFile() is null");
		}
		
		if (!file.getParentFile().exists()) {
			if(file.getParentFile().mkdirs()){
				LOGGER.debug("[file.mkdirs] file : Directory Creation Success");
			}else{				
				LOGGER.error("[file.mkdirs] file : Directory Creation Fail");
			}
		}

		OutputStream os = null;
		long size = 0L;

		try {
			os = new FileOutputStream(file);

			int bytesRead = 0;
			byte[] buffer = new byte[BUFFER_SIZE];

			while ((bytesRead = is.read(buffer, 0, BUFFER_SIZE)) != -1) {
				size += bytesRead;
				os.write(buffer, 0, bytesRead);
			}
		} finally {
			ResourceCloseHelper.close(os);
		}
		return size;
	}

	public static void downloadFile(HttpServletResponse response, String where, String serverSubPath, String physicalName, String original) throws Exception {
		String downFileName = where + SEPERATOR + serverSubPath + SEPERATOR + physicalName;

		File file = new File(WebUtil.filePathBlackList(downFileName));

		if (!file.exists()) {
			throw new FileNotFoundException(downFileName);
		}

		if (!file.isFile()) {
			throw new FileNotFoundException(downFileName);
		}

		byte[] b = new byte[BUFFER_SIZE];

		original = original.replaceAll("\r", "").replaceAll("\n", "");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + convert(original) + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;

		try {
			fin = new BufferedInputStream(new FileInputStream(file));
			outs = new BufferedOutputStream(response.getOutputStream());

			int read = 0;

			while ((read = fin.read(b)) != -1) {
				outs.write(b, 0, read);
			}
		} finally {
			ResourceCloseHelper.close(outs, fin);
		}
	}

	public static void viewFile(HttpServletResponse response, String where, String serverSubPath, String physicalName, String mimeTypeParam) throws Exception {
		String mimeType = mimeTypeParam;
		String downFileName = where + SEPERATOR + serverSubPath + SEPERATOR + physicalName;

		File file = new File(WebUtil.filePathBlackList(downFileName));

		if (!file.exists()) {
			throw new FileNotFoundException(downFileName);
		}

		if (!file.isFile()) {
			throw new FileNotFoundException(downFileName);
		}

		byte[] b = new byte[BUFFER_SIZE];

		if (mimeType == null) {
			mimeType = "application/octet-stream;";
		}

		response.setContentType(WebUtil.removeCRLF(mimeType));
		response.setHeader("Content-Disposition", "filename=image;");

		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;

		try {
			fin = new BufferedInputStream(new FileInputStream(file));
			outs = new BufferedOutputStream(response.getOutputStream());

			int read = 0;

			while ((read = fin.read(b)) != -1) {
				outs.write(b, 0, read);
			}
		} finally {
			ResourceCloseHelper.close(outs, fin);
		}
	}
}
