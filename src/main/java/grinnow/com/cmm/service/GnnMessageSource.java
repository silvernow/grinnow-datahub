package grinnow.com.cmm.service;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class GnnMessageSource extends ReloadableResourceBundleMessageSource implements MessageSource {

	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	public void setReloadableResourceBundleMessageSource(ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
	}
	
	public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
		return reloadableResourceBundleMessageSource;
	}
	
	public String getMessage(String code) {
		return getReloadableResourceBundleMessageSource().getMessage(code, null, Locale.getDefault());
	}
	
	public String getMessage(String code, Locale locale) {
		return getReloadableResourceBundleMessageSource().getMessage(code, null, locale);
	}
	
	public String getMessageArgs(String code, Object[] args) {
		return getReloadableResourceBundleMessageSource().getMessage(code, args, Locale.getDefault());
	}
	
	public String getMessageArgsLocale(String code, Object[] args, Locale locale) {
		return getReloadableResourceBundleMessageSource().getMessage(code, args, locale);
	}
}
