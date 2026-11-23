package com.kishan.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="personinfo")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PID")
	private Integer pid;
	@Column(name="PNAME")
	private String pname;
	@Column(name="DOB")
	private LocalDateTime dob;
	@Column(name="DOM")
	private LocalDateTime dom;
	@Column(name="DOJ")
	private LocalDate doj;
	@Lob
	private byte[] img;
	@Lob
	private char[] myData;
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public LocalDateTime getDob() {
		return dob;
	}
	public void setDob(LocalDateTime dob) {
		this.dob = dob;
	}
	public LocalDateTime getDom() {
		return dom;
	}
	public void setDom(LocalDateTime dom) {
		this.dom = dom;
	}
	public LocalDate getDoj() {
		return doj;
	}
	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public char[] getMyData() {
		return myData;
	}
	public void setMyData(char[] myData) {
		this.myData = myData;
	}
	@Override
	public String toString() {
		return "Person [pid=" + pid + ", pname=" + pname + ", dob=" + dob + ", dom=" + dom + ", doj=" + doj + ", img="
				+ Arrays.toString(img) + ", myData=" + Arrays.toString(myData) + "]";
	}
	
	
}
