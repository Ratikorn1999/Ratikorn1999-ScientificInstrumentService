package Manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Class_End_Project.Login;
import Class_End_Project.Member;
import Class_End_Project.Personnel;
import LoginMJU.PersonnelMJU;
import LoginMJU.Student;
import projectutil.ConnectionDB;

public class LoginManager {

	public Member doLogin(String uname, String pwd) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;
		Member member = null;

		try {
			stmt = con.createStatement();

			String sql = "SELECT m.member_ID,m.member_Prefix,m.member_FirstName,m.member_LastName,m.typeMember,m.position,m.email,m.phonenumber,m.studentID,m.degree,m.branch,m.faculty,m.university,m.advisor_name,m.emailadvisor,m.company_name,l.username,l.password FROM member m inner join login l on m.username = l.username where l.username = '"
					+ uname + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				String password = rs.getString(18);
				if (pwd.equals(password)) {
					String member_ID = rs.getString(1);
					String member_Prefix = rs.getString(2);
					String member_FirstName = rs.getString(3);
					String member_LastName = rs.getString(4);
					String typeMember = rs.getString(5);
					String position =  rs.getString(6);
					String email = rs.getString(7);
					String phonenumber = rs.getString(8);
					String studentID = rs.getString(9);
					String degree = rs.getString(10);
					String branch = rs.getString(11);
					String faculty = rs.getString(12);
					String university = rs.getString(13);
					String advisor_name = rs.getString(14);
					String emailadvisor = rs.getString(15);
					String company_name = rs.getString(16);
					String username = rs.getString(17);


					Login login = new Login(username, password);
					member = new Member(member_ID, member_Prefix, member_FirstName, member_LastName, email,
							typeMember,position, phonenumber, studentID, degree, branch, faculty, university, advisor_name,
							emailadvisor, company_name);
					member.setLogin(login);
				}
			} else {
				member = null;
			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return member;
	}

	public Personnel doLoginAdmin(String key) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;
		Personnel personnel = null;

		try {
			stmt = con.createStatement();

			String sql = "SELECT personnel_ID,personnel_Prefix,personnel_FirstName,personnel_LastName,email,position,personnel_type,faculty"
						+ " FROM personnel " 
						+ " where email like '" + key + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				String personnel_ID = rs.getString(1); 
				String personnel_Prefix = rs.getString(2);
				String personnel_FirstName = rs.getString(3);
				String personnel_LastName = rs.getString(4);
				String email = rs.getString(5);
				String position = rs.getString(6);
				String personnel_type = rs.getString(7);	
				String faculty = rs.getString(8); 
				personnel = new Personnel(personnel_ID,personnel_Prefix,personnel_FirstName,personnel_LastName,email,position,personnel_type,faculty);
				
				
			}
			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return personnel;
	}
	
	public Student doLoginStudent(String key) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;
		Student student = null;

		try {
			stmt = con.createStatement();

			String sql = "	SELECT studentID,nameTh,surnameTh,programNameTh,facultyNameTh,statusName,status,levelName,idcard,advisor_name,emailadvisor,phonenumber,email" + 
						" FROM student" 
						+ " where studentID like '" + key + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				String studentID = rs.getString(1);
				String nameTh = rs.getString(2);
				String surnameTh = rs.getString(3);
				String programNameTh = rs.getString(4);
				String facultyNameTh = rs.getString(5);
				String statusName = rs.getString(6);
				String status = rs.getString(7);
				String levelName = rs.getString(8);
				String idcard = rs.getString(9);
				String advisor_name = rs.getString(10);
				String emailadvisor = rs.getString(11);
				String phonenumber = rs.getString(12);
				String email = rs.getString(13);
				
				student = new Student(studentID,nameTh,surnameTh,programNameTh,facultyNameTh,statusName,status,levelName,idcard);
				student.setAdvisor_name(advisor_name);
				student.setEmail(email);
				student.setEmailadvisor(emailadvisor);
				student.setPhonenumber(phonenumber);
			}
			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}
	
	public boolean checkStudent(Student stu) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;
		boolean resule = false;
		  try {
			stmt = con.createStatement(); 
			String sql = " select studentID from student where studentID like '"+ stu.getStudentID()+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next()) {
				System.out.println("Don't have Student in sql");
				CallableStatement stmt2 = con.prepareCall("{call insertStudent(?,?,?,?,?,?,?,?,?)}");
				stmt2.setString(1, stu.getStudentID());
				stmt2.setString(2, stu.getNameTh());
				stmt2.setString(3, stu.getSurnameTh());
				stmt2.setString(4, stu.getProgramNameTh());
				stmt2.setString(5, stu.getFacultyNameTh());
				stmt2.setString(6, stu.getStatusName());
				stmt2.setString(7, stu.getStatus());
				stmt2.setString(8, stu.getLevelName());
				stmt2.setString(9, stu.getIdcard());

				
				stmt2.execute();
				
				resule = true ;
				stmt2.close();
				
				
			}else {
				System.out.println("Have Student in sql");
			}
			resule = true ;
			
		  con.close();
		  stmt.close();
		  
		  } catch (SQLException e) {
			  System.out.println("catch"); 
			  e.printStackTrace();
		  }
		return resule;
	}
	
	public boolean insertPersonnel(PersonnelMJU p) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;
		boolean resule = false;
		  try {
				CallableStatement stmt2 = con.prepareCall("{call insertPersonnel(?,?,?,?,?,?,?)}");
				stmt2.setString(1, p.getPositionCode());
				stmt2.setString(2, p.getTitlePosition());
				stmt2.setString(3, p.getFirstName());
				stmt2.setString(4, p.getLastName());
				stmt2.setString(5, p.getE_mail());
				stmt2.setString(6, p.getPosition());
				stmt2.setString(7, p.getFaculty());
				stmt2.execute();
				resule = true ;
				stmt2.close();
			
		  con.close();
		  
		  
		  } catch (SQLException e) {
			  System.out.println("catch"); 
			  e.printStackTrace();
		  }
		return resule;
	}

}
