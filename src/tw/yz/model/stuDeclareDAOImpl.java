package tw.yz.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tw.yz.domain.Extra;
import tw.yz.domain.ExtraName;
import tw.yz.utils.DBUtils;

public class stuDeclareDAOImpl implements stuDeclareDAO{
	Connection con = null;
	ExtraName etr = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	

	@Override
	public Map<String,ExtraName> queryDetails(String etr) {
		// TODO Auto-generated method stub
		
		CallableStatement cstmt = null;
		Map<String,ExtraName> d_names = new HashMap<String,ExtraName>();
//		List <String> d_names = new ArrayList();
		ExtraName details = null;
		
		
		try {
			con = DBUtils.getConnection();
			cstmt = con.prepareCall("{call proc_get_extra_detail_name(?)}");
			cstmt.setString(1, etr);
			rs = cstmt.executeQuery();
			while(rs.next())
			{
				String extra = rs.getString("extra");
				String detail = rs.getString("detail");
				String name = rs.getString("name");
				String max = rs.getString("max");
				String min = rs.getString("min");
				details = new ExtraName(extra, detail, name, max, min);
				d_names.put(detail,details);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
//			DBUtils.close(con,cstmt,rs);
		}
		return d_names;
	}



	
	@Override
	public int insertExtra(Extra etr) {
		// TODO Auto-generated method stub
		CallableStatement cstmt = null;
		Extra show_etr = null;
		int flag = 0;
		
		try {
			con = DBUtils.getConnection();
			cstmt = con.prepareCall("{call proc_insert_extra(?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, etr.getYear());
			cstmt.setString(2, etr.getS_no());
			cstmt.setString(3, etr.getExtra());
			cstmt.setString(4, etr.getDetail());
			cstmt.setString(5, etr.getRemarks());
			cstmt.setString(6, etr.getS_point());
			cstmt.setString(7, etr.getG_point());
			cstmt.setString(8, etr.getStatus());
			cstmt.setString(9, etr.getPass());
			rs = cstmt.executeQuery();
			if(rs.next()) {
				flag = 1;
			}
			/*
			while(rs.next())
			{
				
				String year = rs.getString("year");
				String sno = rs.getString("s_no");
				String extra = rs.getString("extra");
				String detail = rs.getString("detail");
				String rmrks = rs.getString("remarks");
				String sp = rs.getString("s_point");
				String gp = rs.getString("g_point");
				String sts = rs.getString("status");
				String pas = rs.getString("pass");
				show_etr = new Extra(year,sno,extra,detail,rmrks,sp,gp,sts,pas);
				
			}
			*/
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(null,cstmt,rs);
		}
		return flag;
	}




	
	@Override
	public Map<Integer, Extra> queryExtrasBy(String sno, String etr) {
		// TODO Auto-generated method stub
		CallableStatement cstmt = null;
		Map<Integer, Extra> extras = new HashMap<Integer, Extra>();
		Extra r_etr = null;
		
		try {
			con = DBUtils.getConnection();
			cstmt = con.prepareCall("{call proc_get_extras_by_n_extra(?,?)}");
			cstmt.setString(1, sno);
			cstmt.setString(2, etr);
			rs = cstmt.executeQuery();
			int i = 0;
			while(rs.next())
			{
				String year = rs.getString("year");
//				String s_no = rs.getString("s_no");
//				String extra = rs.getString("extra");
				String detail = rs.getString("name");	//此处detail存储的是加分项的名称
//				String rmrks = rs.getString("remarks");
				String sp = rs.getString("s_point");
				String gp = rs.getString("g_point");
				String sts = rs.getString("status");
				String pas = rs.getString("pass");
				r_etr = new Extra(year,sno,etr,detail,null,sp,gp,sts,pas);
				extras.put(++i,r_etr);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
//			DBUtils.close(null,cstmt,rs);
		}
		return extras;
	}

	


}


