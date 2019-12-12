package com.h2k.teachersApp.DTO;

public class TeacherDTO {

	public TeacherDTO() {
		// TODO Auto-generated constructor stub
	}
	
	private int teacherId;
	private String firstName;
	private String lastName;
	private String skill;
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
}
