package grinnow.com.cmm.service;

public class Globals {
	
	public static final String LOCALDATETIME_FORMAT = "MM/dd/yyyy hh:mi:ss.Z";
	
	//프로파일 유형
	public static final String SYSTEM_PROFILE = GnnProperties.getProperty("system.config.profile");
	//OS 유형
    public static final String OS_TYPE = GnnProperties.getProperty("Globals.OsType");
    //DB 유형
    public static final String DB_TYPE = GnnProperties.getProperty("Globals.DbType");
    //Amazon DocumentDB 사용여부
    public static final String AMAZON_DOCUMENTDB = GnnProperties.getProperty("Globals.documentdb");
    
    public static final String MONGO_CLUSTER = GnnProperties.getProperty("Globals.MongoDB.ClusterUrl");
    public static final String MONGO_DB = GnnProperties.getProperty("Globals.MongoDB.Database");
    public static final String MONGO_USER = GnnProperties.getProperty("Globals.MongoDB.UserName");
    public static final String MONGO_PW = GnnProperties.getProperty("Globals.MongoDB.Password");
    public static final String MONGO_SSL = GnnProperties.getProperty("Globals.MongoDB.Ssl");
    public static final String MONGO_USE_YN = GnnProperties.getProperty("Globals.MongoDB.UseYn");
  
}

