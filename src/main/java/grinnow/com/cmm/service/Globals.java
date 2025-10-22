package grinnow.com.cmm.service;

public class Globals {
	
	public static final String LOCALDATETIME_FORMAT = "MM/dd/yyyy hh:mi:ss.Z";
	
	//프로파일 유형
	public static final String SYSTEM_PROFILE = GnnProperties.getProperty("system.config.profile");
	//OS 유형
    public static final String OS_TYPE = GnnProperties.getProperty("Globals.OsType");
    //DB 유형
    public static final String DB_TYPE = GnnProperties.getProperty("Globals.DbType");
    //로그인페이지
    public static final String LOGIN_PAGE = GnnProperties.getProperty("Globals.LoginPage");
    
    //파일 업로드 원 파일명
	public static final String ORIGIN_FILE_NM = "originalFileName";
	//파일 확장자
	public static final String FILE_EXT = "fileExtension";
	//파일크기
	public static final String FILE_SIZE = "fileSize";
	//업로드된 파일명
	public static final String UPLOAD_FILE_NM = "uploadFileName";
	//파일경로
	public static final String FILE_PATH = "filePath";
	
  	public static final String MAIL_RESULT_CD_R = "R";
  	//메일발송응답코드 : 발송성공
  	public static final String MAIL_RESULT_CD_C = "C";
  	//메일발송응답코드 : 발송실패
  	public static final String MAIL_RESULT_CD_F = "F";
  	
    //발신전용메일
  	public static final String MAIL_SENDER_ADDRESS = "support@grinnow.co.kr";
  	
    //첨부 최대 파일 크기 지정
  	public static final long EDITOR_MAX_FILE_SIZE = 1024 * 1024 * 100; //업로드 최대 사이즈 설정 (100M)
  	
	//수목분류 : 교목
  	public static final String TREE_TY_AR = "AR";
  	//수목분류 : 관목
  	public static final String TREE_TY_SH = "SH";
  	//수목분류 : 묘목
  	public static final String TREE_TY_SA = "SA";
    //수목분류 : 기타
  	public static final String TREE_TY_ET = "ET";
  	
    //통계 - 나무명검색
  	public static final String SEARCH_TREE = "SEARCH_TREE";
  	
}
