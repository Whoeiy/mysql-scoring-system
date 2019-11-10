package tw.yz.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tw.yz.domain.ExtraName;
import tw.yz.domain.Student;
import tw.yz.utils.DBUtils;

public class AdminCheckDAO {
	public Map<Integer,String> getYears(){
		Connection con = null;
		Map<Integer,String> years = new HashMap<Integer,String>();
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtils.getConnection();
			cstmt = con.prepareCall("{call proc_get_extra_year()}");
			rs = cstmt.executeQuery();
			int i = 0;
			while(rs.next())
			{
				String year = rs.getString("year");
				years.put(++i,year);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(null,cstmt,rs);
		}
		return years;
	}

	public Map<String,String> getClasses(String y){
		Connection con = null;
		Map<String,String> classes = new HashMap<String, String>();
//		List<String> classes = new ArrayList<String>();
//		Map<String,Map<String,String>> shows = new HashMap<String,Map<String,String>>();
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtils.getConnection();
			cstmt = con.prepareCall("{call proc_get_extra_classes(?)}");
			cstmt.setString(1, y);
			
			rs = cstmt.executeQuery();
			while(rs.next())
			{
				String dptmt = rs.getString("department");
				String major = rs.getString("major");
				String keystr = rs.getString("class");
				String year = keystr.substring(0, 2);
				String cls = keystr.substring(2);
				String show = dptmt + major + year + "级" + cls + "班";
				classes.put(keystr, show);
//				shows.put(i, );
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(null,cstmt,rs);
		}
		return classes;
		
	}


}
