package grinnow.com.common.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import grinnow.com.common.service.CommonService;

@Controller
@RequestMapping(value="/common")
public class CommonController {

	@Resource(name = "commonService")
	private CommonService commonService;

	/**
   	 * 권한제한 화면 이동
   	 */
    @RequestMapping("/accessDenied")
    public String accessDenied() throws Exception {
    	return "common/error/access";
    }
}
