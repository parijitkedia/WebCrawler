package Configuration;

public class ConfigProperties {
	
	private static final String DATABASE_PATH = "/Users/Parijit/Projects/Java/sqlite/db/";
	private static final String TABLE_NAME = "movie";
	private static final String DATABASE_URL = "jdbc:sqlite:" + DATABASE_PATH + TABLE_NAME + ".db";
	
	public String getDatabaseUrl() {
		return DATABASE_URL;
	}
	
	public String getTableName() {
		return TABLE_NAME;
	}
}
