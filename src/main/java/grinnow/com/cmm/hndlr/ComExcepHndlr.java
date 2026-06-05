package grinnow.com.cmm.hndlr;

import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComExcepHndlr implements ExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComExcepHndlr.class);

    public void occur(Exception ex, String packageName) {
    	LOGGER.error(packageName, ex);
	}
}
