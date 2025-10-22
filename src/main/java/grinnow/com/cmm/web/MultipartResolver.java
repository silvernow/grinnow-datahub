package grinnow.com.cmm.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import grinnow.com.cmm.service.GnnProperties;
import grinnow.com.common.util.FileUploadUtil;

public class MultipartResolver extends CommonsMultipartResolver {

	private static final Logger LOGGER = LoggerFactory.getLogger(MultipartResolver.class);

	public MultipartResolver() {
	}

	public MultipartResolver(ServletContext servletContext) {
		super(servletContext);
	}

	@Override
	protected MultipartParsingResult parseFileItems(List<FileItem> fileItems, String encoding) {

		// 스프링 3.0변경으로 수정한 부분
		MultiValueMap<String, MultipartFile> multipartFiles = new LinkedMultiValueMap<String, MultipartFile>();
		Map<String, String[]> multipartParameters = new HashMap<String, String[]>();
		String whiteListFileUploadExtensions = GnnProperties.getProperty("Globals.fileUpload.Extensions");

		// Extract multipart files and multipart parameters.
		for (Iterator<FileItem> it = fileItems.iterator(); it.hasNext();) {
			FileItem fileItem = it.next();

			if (fileItem.isFormField()) {

				String value = null;
				if (encoding != null) {
					try {
						value = fileItem.getString(encoding);
					} catch (UnsupportedEncodingException ex) {
						LOGGER.warn("Could not decode multipart item '{}' with encoding '{}': using platform default",
								fileItem.getFieldName(), encoding);
						value = fileItem.getString();
					}
				} else {
					value = fileItem.getString();
				}
				String[] curParam = (String[]) multipartParameters.get(fileItem.getFieldName());
				if (curParam == null) {
					// simple form field
					multipartParameters.put(fileItem.getFieldName(), new String[] { value });
				} else {
					// array of simple form fields
					String[] newParam = StringUtils.addStringToArray(curParam, value);
					multipartParameters.put(fileItem.getFieldName(), newParam);
				}
			} else {

				CommonsMultipartFile file = createMultipartFile(fileItem);
				multipartFiles.add(file.getName(), file);

				LOGGER.debug("Found multipart file [{" + file.getName() + "}] of size {" + file.getSize()
						+ "} bytes with original filename [{" + file.getOriginalFilename() + "}], stored {"
						+ file.getStorageDescription() + "}");

				String fileName = file.getOriginalFilename();
				String fileExtension = FileUploadUtil.getFileExtension(fileName);
				LOGGER.debug("Found File Extension = "+fileExtension);
				if (whiteListFileUploadExtensions == null || "".equals(whiteListFileUploadExtensions)) {
					LOGGER.debug("The file extension whitelist has not been set.");
				} else {
					if (fileName == null || "".equals(fileName)) {
						LOGGER.debug("No file name.");
					} else {
						if ("".equals(fileExtension)) { // 확장자 없는 경우 처리 불가
							throw new SecurityException("[No file extension] File extension not allowed.");
						}
						if ((whiteListFileUploadExtensions+".").contains("."+fileExtension.toLowerCase()+".")) {
							LOGGER.debug("File extension allowed.");
						} else {
							throw new SecurityException("["+fileExtension+"] File extension not allowed.");
						}
					}
				}

			}
		}
		return new MultipartParsingResult(multipartFiles, multipartParameters, null);
	}
}
