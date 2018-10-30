package co.hk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn {
	public static Connection getConnection(){
		Connection con = null;
		
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"hr", "hkitedu");
			System.out.println("DB연결 성공!");
		}catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con, PreparedStatement ps, ResultSet rs){
		if(rs != null){
			try { rs.close(); } catch (SQLException e) {e.printStackTrace();}
		}
		if(ps != null){
			try { ps.close(); } catch (SQLException e) {e.printStackTrace();}
		}
		if(con != null){
			try { con.close(); } catch (SQLException e) {e.printStackTrace();}
		}
	}
}
