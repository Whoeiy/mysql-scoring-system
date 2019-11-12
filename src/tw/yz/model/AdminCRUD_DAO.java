package tw.yz.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.yz.domain.Student;
import tw.yz.utils.DBUtils;

public class AdminCRUD_DAO{

	/**
	 * 1. 增加学生信息
	 * @param stu
	 * @return
	 */
	
	public int addStudent(Student stu) {
		// TODO Auto-generated method stub
		Connection con = null;
		CallableStatement cstmt = null;
		int flag = 0;
		try {
			con = DBUtils.getConnection();
			cstmt = con.prepareCall("{call proc_insert_student(?,?,?,?,?,?)}");
			cstmt.setString(1, stu.getS_no());
			cstmt.setString(2, stu.getS_name());
			cstmt.setString(3, stu.getS_class());
			cstmt.setString(4, stu.getS_pro());
			cstmt.setString(5, stu.getS_feature());
			cstmt.registerOutParameter(6, java.sql.Types.INTEGER);
			cstmt.executeQuery();
			
			flag = cstmt.getInt(6);
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			DBUtils.close(null,cstmt);
		}
		return flag;
	}
	
	/**
	 * 2. 删除学生信息
	 * @param sno
	 * @return
	 */
	
	public int deleteStudent(String sno) {
		// TODO Auto-generated method stub
		Connection con = null;
		CallableStatement cstmt = null;
		int flag = 0;
		try {
			con = DBUtils.getConnection();
			cstmt = con.prepareCall("{call proc_delete_student(?,?)}");
			cstmt.setString(1, sno);
			cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
			cstmt.executeQuery();
			
			flag = cstmt.getInt(2);
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			DBUtils.close(null,cstmt);
		}
		return flag;
	}

	/**
	 * 3. 修改学生信息
	 * @param sno
	 * @param update
	 * @param flag
	 * @return
	 */
	
	public int updateStudent(String sno, String update, int flag) {
		// TODO Auto-generated method stub
		Connection con = null;
		CallableStatement cstmt = null;
		try {
			con = DBUtils.getConnection();
			cstmt = con.prepareCall("{call proc_update_student(?,?,?)}");
			cstmt.setString(1, sno);
			cstmt.setString(2, update);
			cstmt.setInt(3, flag);
			cstmt.registerOutParameter(3, java.sql.Types.INTEGER);
			cstmt.executeQuery();
			
			flag = cstmt.getInt(3);
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			DBUtils.close(null,cstmt);
		}
		return flag;	
	}

	/**
	 * 4. 获得学生信息
	 * @param sselect
	 * @param flag
	 * @return
	 */
	public List<Student> getStudent(String sselect, int flag) {
		// TODO Auto-generated method stub
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		List<Student> list = new ArrayList<Student>();
		
		try {
			con = DBUtils.getConnection();
			cstmt = con.prepareCall("{call proc_select_student(?,?)}");
			cstmt.setString(1, sselect);
			cstmt.setInt(2, flag);
			rs = cstmt.executeQuery();
			
			Student stu = null;
			while(rs.next())
			{
				String s_no = rs.getString("s_no");
				String s_name = rs.getString("s_name");
				String s_class = rs.getString("s_class");
				String s_pro = rs.getString("s_pro");
				String s_feature = rs.getString("s_feature");
				stu = new Student(s_no,s_name,s_class,s_pro,s_feature);
				list.add(stu);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			DBUtils.close(con,cstmt,rs);
		}
		return list;
	}

}
