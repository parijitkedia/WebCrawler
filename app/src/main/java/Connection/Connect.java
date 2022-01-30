package Connection;

import java.sql.*;
import java.util.List;

import Configuration.ConfigProperties;
import Model.Movie;

public class Connect {	
	
	private static String DATABASE_URL = "";
	private static String TABLE_NAME = "";
		
	public static void connect() {
        Connection conn = null;
        try {

            // create a connection to the database
            conn = DriverManager.getConnection(DATABASE_URL);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println("Connection Error: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException Error: " + ex.getMessage());
            }
        }
    }
	
	public static void createNewDatabase() {
		
		 try (Connection conn = DriverManager.getConnection(DATABASE_URL)) {
	            if (conn != null) {
	                DatabaseMetaData meta = conn.getMetaData();
	                System.out.println("The driver name is " + meta.getDriverName());
	                System.out.println("A new database has been created.");
	            }

        } catch (SQLException e) {
            System.out.println("Create DB Error: " + e.getMessage());
        }
	}
	
	public static void createNewTable() {
		
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(id integer PRIMARY KEY, name text NOT NULL, url text NOT NULL)";
		
		try (Connection conn = DriverManager.getConnection(DATABASE_URL)) {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			stmt.close();
			System.out.println("Movie Table has been created");
		} catch(SQLException e) {
			System.out.println("Create Table Error: " + e.getMessage());
		}
	}
	
	public static void createConnection() {
		
		ConfigProperties prop = new ConfigProperties();
		DATABASE_URL = prop.getDatabaseUrl();
		TABLE_NAME = prop.getTableName();
		
		createNewDatabase();
        connect();
        createNewTable();
	}
	
	
	 public void insertToDb(List<Movie> movies) {
		createConnection();
		 
    	String sql = "INSERT INTO " + TABLE_NAME + "(name, url) values(?,?)";
    	
    	 try (Connection conn = DriverManager.getConnection(DATABASE_URL)) {
    		 PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    		 
    		 int count=0;
    		 
    		 for(Movie movie: movies) {
    			 // Can check if the movie is already present. Also, there can be multiple movies of the same name but posters can be different.
    			 pstmt.setString(1, movie.getName());
    			 pstmt.setString(2, movie.getUrl());
    			 
    			 pstmt.addBatch();
    			 count++;
    			 
    			 if (count %100 == 0 || count == movies.size()) {
    				 pstmt.executeBatch();
    			 }
    		 }
    		 System.out.println("Data has been inserted");
    		 
    	 } catch (SQLException e) {
             System.out.println("Insert DB Error: " + e.getMessage());
         }
	 }
	
	public static void main(String[] args) {
		createConnection();
    }
}
