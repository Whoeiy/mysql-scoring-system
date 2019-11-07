package tw.yz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tw.yz.domain.ExtraName;
import tw.yz.utils.DBUtils;

public class declareDAOImpl implements declareDAO{
	Connection con = null;
	ExtraName etr = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	

	@Override
	public List<ExtraName> queryAllExtras() {
		// TODO Auto-generated method stub
		con = DBUtils.getConnection();
		List e_list = new ArrayList();
		String sql = "select extra, detail, name from extra_name";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				String extra = rs.getString("extra");
				String detail = rs.getString("detail");
				String name = rs.getString("name");
				
				etr = new ExtraName(extra,detail,name);
				e_list.add(etr);
			}
		}
		return null;
	}

}
