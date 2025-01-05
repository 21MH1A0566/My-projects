package com.harsha.Projectsuccess;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="studentslist")
public class StudentEntity {
	@Id
	@Column
	public int id;
	@Column
	public String name;
	@Column 
	public String college;
	@Column
	public String branch;
	@Column
	public String city;
	@Column
	public String gender;
	@Column
	public String skills;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	@Override
	public String toString() {
		return "StudentEntity [id=" + id + ", name=" + name + ", college=" + college + ", branch=" + branch + ", city="
				+ city + ", gender=" + gender + ", skills=" + skills + "]";
	}
}
