package grinnow.com.common.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;

public class CommonUtil {

	private CommonUtil() {
        super();
    }
	
	/**
	 * Null을 "" 으로 변환
	 */
	public static String isNull(Object param) {
		String str = "";
		
		if (param == null) {
			return "";
		}
		
		if (param instanceof String) {
			str = (String) param;
		} else if (param instanceof String[]) {
			str = ((String[]) param)[0];
		} else if (param instanceof Date) {
			str = ((Date)param).toString();
		} else {
			str = String.valueOf(param);
		}
		
		if (str.equals("")) {
			return "";
		} else {
			return str.trim();
		}
	}
	
	/**
	 * Null을 "" 으로 변환
	 */
	public static String isZeroToNull(String param) throws Exception{
		String str = "";
		if(isNull(param).equals("0")) {
			str = "";
		}else {
			str = isNull(param);
		}
		return str;
	}
	
	/**
	 * Null을 double 0 으로 변환
	 */
	public static double isDoubleNull(Object param) throws Exception{
		String str = "";
		
		if (param == null) {
			return 0;
		}

		if (param instanceof String) {
			str = (String) param;
		} else if (param instanceof String[]) {
			str = ((String[]) param)[0];
		} else {
			str = String.valueOf(param);
		}
		
		if (str.equals("")) {
			return 0;
		} else {
			return Double.parseDouble(str);
		}
	}
	
	/**
	 * Null을 float 0 으로 변환
	 */
	public static float isFloatNull(Object param) throws Exception{
		String str = "";
		
		if (param == null) {
			return 0;
		}

		if (param instanceof String) {
			str = (String) param;
		} else if (param instanceof String[]) {
			str = ((String[]) param)[0];
		} else {
			str = String.valueOf(param);
		}
		
		if (str.equals("")) {
			return 0;
		} else {
			return Float.parseFloat(str);
		}
	}
	
	/**
	 * Null을 int 0 으로 변환
	 */
	public static int isIntNull(Object param) throws Exception{
		String str = "";
		
		if (param == null) {
			return 0;
		}

		if (param instanceof String) {
			str = (String) param;
		} else if (param instanceof String[]) {
			str = ((String[]) param)[0];
		} else {
			str = String.valueOf(param);
		}
		
		if (str.equals("")) {
			return 0;
		} else {
			return Integer.parseInt(str);
		}
	}
	
	public static LocalDateTime isTimeStamp(String Param) throws Exception{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		LocalDateTime dateTime = LocalDateTime.parse(Param, formatter);
		return dateTime;
	}
	
	/**
	 * 금액문자열을 숫자로
	 */
	public static BigDecimal isAmt(Object param) throws Exception{
		String str = "";
		
		if (param == null) {
			return new BigDecimal("0.01");
		}

		if (param instanceof String) {
			str = (String) param;
		} else if (param instanceof String[]) {
			str = ((String[]) param)[0];
		} else {
			str = String.valueOf(param);
		}
		
		if (str.equals("")) {
			return new BigDecimal("0.01");
		} else {
			return new BigDecimal(str);
		}
	}
	
	/**
	 * 금액문자열을 숫자로
	 */
	public static BigDecimal isAmtNull(Object param) throws Exception{
		String str = "";
		
		if (param == null) {
			return null;
		}

		if (param instanceof String) {
			str = (String) param;
		} else if (param instanceof String[]) {
			str = ((String[]) param)[0];
		} else {
			str = String.valueOf(param);
		}
		
		if (str.equals("")) {
			return null;
		} else {
			return new BigDecimal(str);
		}
	}
	
	/**
	 * 클라이언트 IP조회
	 */
	public static String getUserIp(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");
	    String result = "";
	    if (ip == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if (ip == null) {
	        ip = request.getRemoteAddr();
	    }
	    result = ip.split(",")[0].trim();
	    return result;
	}
	
    /**
     * 포멧별 오늘날짜 "yyyyMMddHHmmss"
     */
    public static String getToayDate(String format) {
    	Date now = new Date(System.currentTimeMillis());
    	SimpleDateFormat sfmt = new SimpleDateFormat(format);
    	return sfmt.format(now); 
    }

    /**
     * 클라이언트(Client)의 웹브라우저 종류를 조회하는 기능
     */
    public static String isClntWebKind(HttpServletRequest request) throws Exception{
        String headerStr = request.getHeader( "User-Agent" ).toUpperCase();
        String browser = "";

        if ( headerStr.indexOf("TRIDENT") > -1 || headerStr.indexOf("MSIE") > -1 ) { //IE
            if ( headerStr.indexOf("TRIDENT/7") > -1 ) {
                browser = "IE 11";
            } else if ( headerStr.indexOf("TRIDENT/6") > -1 ) {
                browser = "IE 10";
            } else if ( headerStr.indexOf("TRIDENT/5") > -1 ) {
                browser = "IE 9";
            } else if ( headerStr.indexOf("TRIDENT/4") > -1 ) {
                browser = "IE 8";
            } else if ( headerStr.indexOf("EDG") > -1 || headerStr.indexOf("EDGE") > -1 ) {
                browser = "IE Edge";
            }
        } else if ( headerStr.indexOf("WHALE") > -1 ) { //네이버 WHALE
            browser = "Whale";
        } else if ( headerStr.indexOf("OPERA") > -1 || headerStr.indexOf("OPR") > -1 ) { //오페라
            browser = "Opera";
        } else if ( headerStr.indexOf("FIREFOX") > -1 ) { //파이어폭스
            browser = "Firefox";
        } else if ( headerStr.indexOf("SAFARI") > -1 && headerStr.indexOf("CHROME") == -1 ) { //사파리
            browser = "Safari";
        } else if ( headerStr.indexOf("CHROME") > -1 ) { //크롬
            browser = "Chrome";
        } else {
            browser = "Other";
        }
        return browser;
    }
	
	/**
	 * String to Date
	 */
	public static Date isStringToDate(String data) throws Exception{
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = transFormat.parse(data);
		return dt;
	}
	
	/**
	 * 오늘날짜
	 */
	public static String isCurrentDate(String formatter) throws Exception{
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(formatter));
		
	}
	
	/**
	 * 오늘일시
	 */
	public static String isCurrentDateTime(String formatter) throws Exception{
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(formatter));
	}
	
	/**
	 * 이후날짜(년)
	 */
	public static String isBeforeYear(int years, String formatter) throws Exception{
		return LocalDateTime.now().minusYears(years).format(DateTimeFormatter.ofPattern(formatter));
	}
	
	/**
	 * 이후날짜(월)
	 */
	public static String isBeforeMonth(long month, String formatter) throws Exception{
		return LocalDateTime.now().minusMonths(month).format(DateTimeFormatter.ofPattern(formatter));
	}
	
	/**
	 * 이후날짜(일)
	 */
	public static String isBeforeDay(long day, String formatter) throws Exception{
		return LocalDateTime.now().minusDays(day).format(DateTimeFormatter.ofPattern(formatter));
	}
	
	/**
	 * 이후날짜(년)
	 */
	public static String isAfterYear(int years, String formatter) throws Exception{
		return LocalDateTime.now().plusYears(years).format(DateTimeFormatter.ofPattern(formatter));
	}
	
	/**
	 * 이후날짜(월)
	 */
	public static String isAfterMonth(long month, String formatter) throws Exception{
		return LocalDateTime.now().plusMonths(month).format(DateTimeFormatter.ofPattern(formatter));
	}
	
	/**
	 * 이후날짜(일)
	 */
	public static String isAfterDay(long day, String formatter) throws Exception{
		return LocalDateTime.now().plusDays(day).format(DateTimeFormatter.ofPattern(formatter));
	}
	
	/**
	 * URL에서 파일이름추출
	 */
    public static String getFileNameFromURL(String url) {
    	return "/" + url.substring(url.lastIndexOf('/') + 1, url.length());
    }

    /**
     * XSS 방지 처리.
     */
    public static String unscript(String data) {
        if (data == null || data.trim().equals("")) {
            return "";
        }
        
        String ret = data;
        
        ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
        ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");
        
        ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
        ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");
        
        ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
        ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");
        
        ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        
        ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
        ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");

        return ret;
    }
    
	public static String excelFileName(HttpServletRequest request) throws Exception{
		String filename = "";
		filename = CommonUtil.isNull(request.getSession().getAttribute("menuNm")) + "_" + isCurrentDateTime("yyyyMMddHHmmss");
		return filename;
	}
	
	public static String isCntrTitle(String cntrNo, String custNm) throws Exception{
		String result = "";
		if("".equals(isNull(custNm))) {
			result = cntrNo;
		}else {
			if(isNull(custNm).length() > 18) {
				result = isNull(custNm).substring(0, 18) + "...";
			}else {
				result = isNull(custNm) + "-" + cntrNo;
				if(result.length() > 18) {
					result = result.substring(0, 18) + "...";
				}
			}
		}
		return result;
	}
	
	
	public static String isStrTitle(String custNm) throws Exception{
		String result = "";
		if(custNm.length() > 18) {
			result = custNm.substring(0, 18) + "...";
		}else {
			result = custNm;
		}
		return result;
	}
	
	public static String generateAuthCode(int length) {
		String characters = "0123456789";
	    Random random = new Random();
	    StringBuilder codeBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            codeBuilder.append(randomChar);
        }
        return codeBuilder.toString();
    }
	
	public static String isDateFromFormat(String timestamp) throws Exception{
		String result = "";
		if(!"".equals(isNull(timestamp))) {
			result = isNull(timestamp.substring(0, 10)) + " 00:00:00.000";
		}
		return result;
	}
	
	public static String isDateToFormat(String timestamp) throws Exception{
		String result = "";
		if(!"".equals(isNull(timestamp))) {
			result = isNull(timestamp.substring(0, 10)) + " 23:59:59.999";
		}
		return result;
	}
	
	public static String genDateKeyCode(String code) throws Exception{
        LocalDateTime now = LocalDateTime.now();
        // 1부터 10까지 랜덤 숫자 생성
        int randomPart = ThreadLocalRandom.current().nextInt(1, 10);
        // 날짜 포맷팅 (필요 시 날짜 정보 추가)
        String datePart = now.format(DateTimeFormatter.ofPattern("mssSSS"));
        return code + datePart + randomPart;
    }
	
    public static String isSpecialRemove(String data) {
    	String result = isNull(data).replaceAll("[^a-zA-Z0-9\\s]", "");
        return result;
    }
    
    public static String isDayOfTheWeek(String date) throws Exception{
		String resultDate = date;
	    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	    
	    try {
	        Date dateFmt = inputFormat.parse(resultDate);
	        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy.MM.dd(E)", Locale.KOREA);
	        resultDate = outputFormat.format(dateFmt);
	    } catch (Exception e) {
	       e.getMessage();
	    }
		return resultDate;
	}
    
    public static String isClientType(HttpServletRequest request) {
        return isComMobile(request) ? "MOBILE" : "WEB";
    }
    
    public static boolean isComMobile(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null) {
            String ua = userAgent.toLowerCase();
            return ua.contains("mobile") || ua.contains("android") || ua.contains("iphone") || ua.contains("ipad");
        }
        return false;
    }
}
