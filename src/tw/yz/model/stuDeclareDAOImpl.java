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

import tw.yz.domain.ExtraName;
import tw.yz.utils.DBUtils;

public class stuDeclareDAOImpl implements stuDeclareDAO{
	Connection con = null;
	ExtraName etr = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	

	@Override
	public Map<String,String> queryDetails(String etr) {
		// TODO Auto-generated method stub
		
		CallableStatement cstmt = null;
		Map<String,String> d_names = new HashMap<String,String>();
//		List <String> d_names = new ArrayList();
		
		try {
			con = DBUtils.getConnection();
			cstmt = con.prepareCall("{call proc_get_extra_detail_name(?)}");
			cstmt.setString(1, etr);
			rs = cstmt.executeQuery();
			while(rs.next())
			{
				String detail = rs.getString("detail");
				String name = rs.getString("name");
				d_names.put(detail,name);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
//			DBUtils.close(con,cstmt,rs);
		}
		return d_names;
	}

}
