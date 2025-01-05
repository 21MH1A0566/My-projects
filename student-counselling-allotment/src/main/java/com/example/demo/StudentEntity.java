package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="Studentsregistered")
public class StudentEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
	@Column
	public String name;
	@Column
	public String email;
	@Column
	public String phone;
	@Column
	public String dob;
	@Column
	public double tenth;
	@Column
	public int inter;
	@Column(length = 65555)  // Storing Base64 string in the database
	@Lob
	private byte[] userPhoto;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public byte[] getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(byte[] userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public double getTenth() {
		return tenth;
	}
	public void setTenth(double tenth) {
		this.tenth = tenth;
	}
	public int getInter() {
		return inter;
	}
	public void setInter(int inter) {
		this.inter = inter;
	}
	@Transient
	private String base64Image;

	public String getBase64Image() {
	    return base64Image;
	}

	public void setBase64Image(String base64Image) {
	    this.base64Image = base64Image;
	}

}