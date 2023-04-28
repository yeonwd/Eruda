package com.project.eruda.data;



/**
 * 관리자에 대한 정보를 담는 클래스
 * @author 5조
 *
 */
public class Admin {
	
	private String id;
	private String pw;
	private String name;
	private String position; //"강사", "매니저", "운영자"
	private String birth;
	private String phone;
	private String email;
	private String address;
	private String univ;
	private String major;
	
	/**
	 * 관리자 계정 생성 메소드입니다.
	 * @param id 아이디
	 * @param pw 비밀번호
	 * @param name 이름
	 * @param position 계정 구분(강사/매니저/운영자)
	 * @param birth 생년월일
	 * @param phone 전화번호
	 * @param email 이메일
	 * @param address 주소
	 * @param univ 대학
	 * @param major 전공
	 */
	public Admin(String id, String pw, String name, String position, String birth, String phone, String email,
			String address, String univ, String major) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.position = position;
		this.birth = birth;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.univ = univ;
		this.major = major;
	}


	@Override
	public String toString() {
		return "Admin [id=" + id + ", pw=" + pw + ", name=" + name + ", position=" + position + ", birth=" + birth
				+ ", phone=" + phone + ", email=" + email + ", address=" + address + ", univ=" + univ + ", major="
				+ major + "]";
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


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
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
	
 
}
