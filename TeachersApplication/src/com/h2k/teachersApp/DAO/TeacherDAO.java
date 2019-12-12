package com.h2k.teachersApp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.h2k.teachersApp.DTO.TeacherDTO;

public class TeacherDAO {
	
	private static String url =  "jdbc:mysql://localhost:3306/sakila";
	private static String userName = "root";
	private static String password = "test";
	private static String getTeacher="Select * from teacher where teacher_id=?";
	private static String insertTeacher="Insert into teacher(teacher_id,firstname,lastname,skill)values(?,?,?,?)";
	
	public static Connection getConnection() throws ClassNotFoundException
	{
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url, userName, password);
			
			
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return conn;
		
	}
	
	public TeacherDTO getTeacherById(String Id)
	{
		TeacherDTO teacher=null;
		
		try
		{
			Connection conn = getConnection();
			PreparedStatement pstat = conn.prepareStatement(getTeacher);
			pstat.setInt(1, Integer.parseInt(Id));
			ResultSet rs = pstat.executeQuery();
			while(rs.next())
			{
				teacher = new TeacherDTO();
				teacher.setTeacherId(rs.getInt("teacher_id"));
				teacher.setFirstName(rs.getString("firstName"));
				teacher.setLastName(rs.getString("lastName"));
				teacher.setLastName(rs.getString("skill"));
			}
			
	}catch(Exception ex)
		{
		ex.printStackTrace();
		}
		return teacher;
}
	public int saveTeacher(TeacherDTO teacher) {
		int teacherId=0;
        teacher=null;
		
		try {
			Connection conn=getConnection();
			teacher = new TeacherDTO();
			PreparedStatement pstat = conn.prepareStatement(insertTeacher);
			pstat.setInt(1,teacher.getTeacherId());
			pstat.setString(2, teacher.getFirstName());
			pstat.setString(3, teacher.getLastName());
			pstat.setString(4, teacher.getSkill());
			int rowsaffected = pstat.executeUpdate();
			System.out.println("No of rows affected :" +rowsaffected);
			PreparedStatement pstat1 = conn.prepareStatement(getTeacher);
		    ResultSet rs = pstat.executeQuery();
			if (rs!=null) {
				while(rs.next()) {
					teacherId = rs.getInt("teacher_id");
				}
			}
			
			conn.close();
		}catch(Exception sqlEx) {
			sqlEx.printStackTrace();
		}
		return teacherId;
			
		}
	}

