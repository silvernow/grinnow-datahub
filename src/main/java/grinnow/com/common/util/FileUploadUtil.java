/**
 * 파일업로드유틸
 */
package grinnow.com.common.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import grinnow.com.cmm.util.WebUtil;
import grinnow.com.common.util.base.FormBasedFileUtil;
import grinnow.com.common.util.base.FormBasedFileVo;

public class FileUploadUtil extends FormBasedFileUtil {

	public static List<FormBasedFileVo> uploadFiles(HttpServletRequest request, String where, long maxFileSize) throws Exception {
		List<FormBasedFileVo> list = new ArrayList<FormBasedFileVo>();

		//MultipartHttpServletRequest mptRequest = (MultipartHttpServletRequest) request;
		MultipartHttpServletRequest mptRequest = WebUtils.getNativeRequest(request,MultipartHttpServletRequest.class);
		
		Iterator<?> fileIter = mptRequest.getFileNames();

		while (fileIter.hasNext()) {
			MultipartFile mFile = mptRequest.getFile((String) fileIter.next());

			FormBasedFileVo vo = new FormBasedFileVo();

			String tmp = mFile.getOriginalFilename();

			if (tmp.lastIndexOf("\\") >= 0) {
				tmp = tmp.substring(tmp.lastIndexOf("\\") + 1);
			}

			vo.setFileName(tmp);
			vo.setContentType(mFile.getContentType());
			vo.setServerSubPath(getTodayString());
			vo.setPhysicalName(getPhysicalFileName());
			vo.setSize(mFile.getSize());

			if (tmp.lastIndexOf(".") >= 0) {
				vo.setPhysicalName(vo.getPhysicalName()); // 2012.11 KISA 보안조치
			}

			if (mFile.getSize() > 0) {
				InputStream is = null;
				try {
					is = mFile.getInputStream();
					saveFile(is, new File(WebUtil.filePathBlackList(where + SEPERATOR + vo.getServerSubPath() + SEPERATOR + vo.getPhysicalName())));
				} finally {
					if (is != null) {
						is.close();
					}
				}
				list.add(vo);
			}
		}
		return list;
	}
	
	public static List<FormBasedFileVo> uploadFilesExt(MultipartHttpServletRequest mptRequest, String where, long maxFileSize, String extensionWhiteList) throws Exception {
		List<FormBasedFileVo> list = new ArrayList<FormBasedFileVo>();

		Iterator<?> fileIter = mptRequest.getFileNames();

		while (fileIter.hasNext()) {
			MultipartFile mFile = mptRequest.getFile((String) fileIter.next());

			FormBasedFileVo vo = new FormBasedFileVo();

			String tmp = mFile.getOriginalFilename();

			if (tmp.lastIndexOf("\\") >= 0) {
				tmp = tmp.substring(tmp.lastIndexOf("\\") + 1);
			}
			String ext = "";
			if ( tmp.lastIndexOf(".") > 0 )
				ext = getFileExtension(tmp).toLowerCase();
			else
				throw new SecurityException("Unacceptable file extension."); // 허용되지 않는 확장자 처리
			if ( extensionWhiteList.indexOf(ext) < 0 )
				throw new SecurityException("Unacceptable file extension."); // 허용되지 않는 확장자 처리
			
			vo.setFileName(tmp);
			vo.setContentType(mFile.getContentType());
			vo.setServerSubPath(getTodayString());
			vo.setPhysicalName(getPhysicalFileName()+"."+ext);
			vo.setSize(mFile.getSize());

			if (tmp.lastIndexOf(".") >= 0) {
				vo.setPhysicalName(vo.getPhysicalName()); // 2012.11 KISA 보안조치
			}

			if (mFile.getSize() > 0) {
				InputStream is = null;

				try {
					is = mFile.getInputStream();
					saveFile(is, new File(WebUtil.filePathBlackList(where + SEPERATOR + vo.getServerSubPath() + SEPERATOR + vo.getPhysicalName())));
				} finally {
					if (is != null) {
						is.close();
					}
				}
				list.add(vo);
			}
		}

		return list;
	}
	
	public static String getFileExtension(String fileNamePath) {
		if (fileNamePath == null) return "";
		String ext = fileNamePath.substring(fileNamePath.lastIndexOf(".") + 1,fileNamePath.length());
		return (ext == null) ? "" : ext;
	}
	
	public static boolean checkFileExtension(String fileNamePath, String whiteListExtensions) {
		String extension = getFileExtension(fileNamePath);

		if ( "".equals(extension) ) return false;
		
		if ( whiteListExtensions == null ) return false;
		if ( "".equals(whiteListExtensions) ) return false;

		if ( whiteListExtensions.indexOf("."+extension) >= 0 ) return true;
		else return false;
	}
	
	/**
	 * 최대 파일 사이즈 허용유무를 검증한다.
	 * ex) 1048576 = 1M , 1K = 1024
	 */	
	public static boolean checkFileMaxSize(MultipartFile multipartFile, long maxFileSize) {
		
		if ( multipartFile == null ) return false;

		if ( multipartFile.getSize() <= maxFileSize ) return true;
		else return false;
	}
	
}
