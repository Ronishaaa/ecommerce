//package service;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import model.Student;
//import utils.DatabaseConnectivity;
//import utils.PasswordHash;
//
//public class CustomerDao {
//	private Connection conn;
//	private PreparedStatement statement;
//	private ResultSet resultSet;
//	private boolean isSuccess;
//	private static final String[] errorMessage = new String[2];
//	private static final String insert_query = "insert into student_register"
//			+ "(firstName,lastName,username,dob,gender,email,phoneNumber,subject,password)"
//			+ " values(?,?,?,?,?,?,?,?,?)";
//
//	public StudentDao() {
//		conn = DatabaseConnectivity.getDbConnection();
//	}
//
//	public boolean saveStudent(Student student) {
//		try {
//			statement = conn.prepareStatement("select count(*) from student_register");
//			resultSet = statement.executeQuery();
//			if (resultSet.next()) {
//				if (check(student)) {
//					isSuccess = false;
//				} else {
//					int row = setData(student);
//					if (row > 0) {
//						isSuccess = true;
//					} else {
//						isSuccess = false;
//					}
//				}
//
//			} else {
//				int row = setData(student);
//				if (row > 0) {
//					isSuccess = true;
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return isSuccess;
//	}
//
//	public int setData(Student student) {
//		int row = 0;
//		try {
//			statement = conn.prepareStatement(insert_query);
//			statement.setString(1, student.getFirstName());
//			statement.setString(2, student.getLastName());
//			statement.setString(3, student.getUsername());
//			statement.setDate(4, student.getDob());
//			statement.setString(5, student.getGender());
//			statement.setString(6, student.getEmail());
//			statement.setLong(7, student.getPhoneNumber());
//			statement.setString(8, student.getSubject());
//			statement.setString(9, student.getPassword());
//			row = statement.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return row;
//	}
//
//	public boolean check(Student student) {
//		boolean isFind = false;
//		try {
//			statement = conn.prepareStatement("select username,email,phoneNumber from student_register");
//			resultSet = statement.executeQuery();
//
//			while (resultSet.next()) {
//				if (student.getUsername().equals(resultSet.getString("username"))) {
//					isFind = true;
//					break;
//				} else if (student.getEmail().equals(resultSet.getString("email"))) {
//					isFind = true;
//					break;
//				} else if (student.getPhoneNumber() == resultSet.getLong("phoneNumber")) {
//					isFind = true;
//					break;
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return isFind;
//	}
//
//	public boolean studentLogin(String username, String password) throws SQLException {
//		statement = conn.prepareStatement("select username,password,role_id from student_register where username=?");
//		statement.setString(1, username);
//		resultSet = statement.executeQuery();
//		boolean isSuccess = false;
//		if (resultSet.next()) {
//			String passwordFromDb = resultSet.getString("password");
//
//			if (PasswordHash.verifyPassword(password, passwordFromDb) && resultSet.getInt("role_id")==2) {
//				isSuccess = true;
//			} else {
//				isSuccess = false;
//			}
//
//		}
//		return isSuccess;
//
//	}
//	public List<Student> getAllStudent() throws SQLException {
//		statement=conn.prepareStatement("select * from student_register");
//		resultSet=statement.executeQuery();
//		List<Student> listOfStudent=new ArrayList<Student>();
//		while(resultSet.next())
//		{
//			int id=resultSet.getInt("id");
//			String firstName=resultSet.getString("firstName");
//			String lastName=resultSet.getString("lastName");
//			String username=resultSet.getString("username");
//			Date dob=resultSet.getDate("dob");
//			String gender=resultSet.getString("gender");
//			String email=resultSet.getString("email");
//			long phoneNumber=resultSet.getLong("phoneNumber");
//			String subject=resultSet.getString("subject");
//			
//			Student student=new Student();
//			student.setFirstName(firstName);
//			student.setLastName(lastName);
//			student.setUsername(username);
//			student.setDob(dob);
//			student.setGender(gender);
//			student.setEmail(email);
//			student.setPhoneNumber(phoneNumber);
//			student.setSubject(subject);
//			
//			listOfStudent.add(student);
//		}
//		return listOfStudent;
//	}
//	
//}
