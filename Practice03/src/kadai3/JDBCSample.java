package kadai3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCSample {   
	    public static void main(String[] args) {

	        final String URL 
	        = "jdbc:postgresql://127.0.0.1:5432/postgres";
	        final String USER = null;
	        final String PASS = null;
	        final String SQL = "select * from omikuji order by unseicd asc;";
//	        final String SQL = "select * from unseimaster where unseicd = ?;";  
//	        final String SQL = "select * from unseimaster where unseiname = ?;";        
	        Connection con = null;
	        try {
	            con = DriverManager.getConnection(URL, USER, PASS);
	            PreparedStatement ps = con.prepareStatement(SQL);
	            
//	            ps.setString(1, "1");
//	            ps.setString(1,"大吉");
	            
	            ResultSet rs = null;
	            rs = ps.executeQuery();
            	
	            for(int i = 1;i < 50;i++) {
            		System.out.println(i);
            	}
	            
	            while (rs.next()) {
//	                System.out.println(
	            		
//	                   rs.getInt("unseicd") + " " +
//	                   rs.getString("unseiname"));
	            }      
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                con.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}