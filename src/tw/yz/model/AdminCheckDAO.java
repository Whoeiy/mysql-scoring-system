package tw.yz.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tw.yz.domain.Extra;
import tw.yz.domain.ExtraName;
import tw.yz.domain.Student;
import tw.yz.domain.Summary;
import tw.yz.utils.DBUtils;

public class AdminCheckDAO {
	/**
	 * 1. 获取年份
	 * @return
	 */
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

	/**
	 * 2. 获取班级
	 * @return
	 */
	
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

	/**
	 * 3. 获得加分项明细信息
	 * @param year
	 * @param cls
	 * @return
	 */
	
	public Map<Integer, Extra> queryExtrasBy(String year, String cls) {
		// TODO Auto-generated method stub
		Connection con = null;
		CallableStatement cstmt = null;
		Map<Integer, Extra> extras = new HashMap<Integer, Extra>();
		ResultSet rs = null;
		Extra r_etr = null;
		
		try {
			con = DBUtils.getConnection();
			cstmt = con.prepareCall("{call proc_admin_get_extras(?,?)}");
			cstmt.setString(1, year);
			cstmt.setString(2, cls);
			rs = cstmt.executeQuery();
			int i = 0;
			while(rs.next())
			{
				String sno = rs.getString("s_no");
				String name = rs.getString("s_name");
				String extra = rs.getString("extra");
				String detail = rs.getString("name");	//此处detail存储的是加分项的名称
				String rmrks = rs.getString("remarks");
				String sp = rs.getString("s_point");
				String gp = rs.getString("detail");		//此处gp存储的是加分项对应的编号detail
				String sts = rs.getString("status");
				String pas = rs.getString("pass");
				r_etr = new Extra(name,sno,extra,detail,rmrks,sp,gp,sts,pas);	//此处为了方便，用year属性存放学生姓名
				extras.put(++i,r_etr);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(null,cstmt,rs);
		}
		return extras;
	}

	
	/**
	 * 4. 审核一条加分项信息
	 * @param sno
	 * @param extra
	 * @param detail
	 * @param pas
	 * @param gp
	 * @return
	 */
	
	public int checkOneExtra(String sno, String extra, String detail,String pas, String gp) {
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		int flag = 0;
		
		try {
			con = DBUtils.getConnection();
			cstmt = con.prepareCall("{call proc_admin_check_one_extra(?,?,?,?,?,?)}");
			cstmt.setString(1, sno);
			cstmt.setString(2, extra);
			cstmt.setString(3, detail);
			cstmt.setString(4, pas);
			cstmt.setString(5, gp);
			cstmt.registerOutParameter(6, java.sql.Types.INTEGER);
			cstmt.executeQuery();
			
			flag = cstmt.getInt(6);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(null,cstmt,rs);
		}
		return flag;
	}

	/**
	 * 5. 获得学生总体综测情况
	 * @param year
	 * @param cls
	 * @return
	 */
	
	public Map<Integer, Summary> collectAll(String year, String cls) {
		// TODO Auto-generated method stub
		Connection con = null;
		CallableStatement cstmt = null;
		Map<Integer, Summary> r_smry = new HashMap<Integer, Summary>();
		ResultSet rs = null;
		Summary smry = null;
		
		try {
			con = DBUtils.getConnection();
			cstmt = con.prepareCall("{call proc_admin_collect(?,?)}");
			cstmt.setString(1, year);
			cstmt.setString(2, cls);
			rs = cstmt.executeQuery();
			int i = 0;
			while(rs.next())
			{
				String sno = rs.getString("s_no");
				String name = rs.getString("s_name");
				String sme = rs.getString("s_me");
				String sie = rs.getString("s_ie");
				String spe = rs.getString("s_pe");	
				String sttl = rs.getString("s_total");
				String sg = rs.getString("s_grade");
				smry = new Summary(sno,name,sme,sie,spe,sttl,sg);	//此处为了方便，用year属性存放学生姓名
				r_smry.put(++i,smry);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(null,cstmt,rs);
		}
		return r_smry;
	}
}
