package com.project.eruda.data;

/**
 * 학생 계정 데이터 구성을 관리하는 클래스입니다.
 * @author 조연우
 *
 */

public class User implements Cloneable{
	
	private String id;
	private String pw;
	private String name;
	private String gender;
	private String age;
	private String email;
	private String phoneNumber;
	private String address;
	private String univ;
	private String major;
	private String course;
	private String start;
	private String end;
	private String company;
	
	/**
	 * 학생 계정 생성 메소드입니다.
	 * @param id 아이디
	 * @param pw 비밀번호
	 * @param name 이름
	 * @param gender 성별
	 * @param age 나이
	 * @param email 이메일
	 * @param phoneNumber 전화번호
	 * @param address 주소
	 * @param univ 대학
	 * @param major 전공
	 * @param course 수강 강의
	 * @param start 개강일
	 * @param end 종강일
	 * @param company 취업 회사
	 */
	
	public User(String id, String pw, String name, String gender, String age,  String email, String phoneNumber,
			String address, String univ, String major, String course, String start, String end, String company) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.univ = univ;
		this.major = major;
		this.course = course;
		this.start = start;
		this.end = end;
		this.company = company;
	}



	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUniv() {
		return univ;
	}
	public void setUniv(String univ) {
		this.univ = univ;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", pw=" + pw + ", name=" + name + ", gender=" + gender + ", age=" + age
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address + ", univ=" + univ
				+ ", major=" + major + ", course=" + course + "]";
	}
	
	@Override
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
	
}
