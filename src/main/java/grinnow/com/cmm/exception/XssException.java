package grinnow.com.cmm.exception;

import egovframework.rte.fdl.cmmn.exception.BaseRuntimeException;

public class XssException extends BaseRuntimeException {
    
	private static final long serialVersionUID = 1L;
	 
	public XssException(String message, String messageKey) {
		this.messageKey = messageKey;
		this.messageParameters = null;
		this.message = message;
		this.wrappedException = null;
	}
}
