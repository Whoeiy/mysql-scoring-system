package tw.yz.model;


import java.util.List;

import tw.yz.domain.Student;

public interface adminManageDAO {
	public int addStudent(Student stu);
	public int deleteStudent(String sno);
	public int updateStudent(String sno, String update, int flag);
	public List<Student> getStudent(String sselect, int flag);
}
