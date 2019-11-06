package tw.yz.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DBUtils {
	// 读取属性文件，获得连接
	static Connection con = null;
	static Properties ps = new Properties();
	static {
		// 读取属性文件
			InputStream in = DBUtils.class.getClassLoader().getResourceAsStream("mysql.properties");
			try {
				ps.load(in);
				String driver = ps.getProperty("driver");
				String url = ps.getProperty("url");
				String username = ps.getProperty("username");
				String pwd = ps.getProperty("pwd");
				System.out.println(driver + url + username + pwd);

				Class.forName(driver);
				con = DriverManager.getConnection(url, username, pwd);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public static Connection getConnection() {
			return con;
		}
		
		public static void closeAll(Connection con, Statement stmt, PreparedStatement pstmt, CallableStatement cstmt, ResultSet rs) {
			try {
				if(stmt != null)
				{
					stmt.close();
				}
				if(pstmt != null)
				{
					pstmt.close();
				}
				if(cstmt != null)
				{
					cstmt.close();
				}
				if(rs != null)
				{
					rs.close();
				}
				if(con != null)
				{
					con.close();
				}
			}catch(SQLException e){
				System.out.println(e);
			}
		}
		
		public static void close(Connection con, CallableStatement cstmt)
		{
			DBUtils.closeAll(con,null,null,cstmt,null);
		}
}