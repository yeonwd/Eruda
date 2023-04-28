package com.project.eruda.data;
/**
 * 강의 데이터 구성을 관리하는 클래스입니다.
 * @author 조연우
 *
 */
public class Lecture {
	
	private String instructor;
	private String courseTitle;
	private String times;
	private String startDate;
	private String endDate;
	private String capacity;
	private String lecDescription;
	
	/**
	 * 강의 데이터 생성 메소드입니다.
	 * @param instructor 강사
	 * @param courseTitle 강의명
	 * @param times 회차
	 * @param startDate 개강일
	 * @param endDate 종강일
	 * @param capacity 정원
	 * @param lecDescription 강의 설명
	 */
	
	public Lecture(String instructor, String courseTitle, String times, String startDate, String endDate,
			String capacity, String lecDescription) {
		this.instructor = instructor;
		this.courseTitle = courseTitle;
		this.times = times;
		this.startDate = startDate;
		this.endDate = endDate;
		this.capacity = capacity;
		this.lecDescription = lecDescription;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getLecDescription() {
		return lecDescription;
	}

	public void setLecDescription(String lecDescription) {
		this.lecDescription = lecDescription;
	}

	@Override
	public String toString() {
		return "Lecture [instructor=" + instructor + ", courseTitle=" + courseTitle + ", times=" + times
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", capacity=" + capacity + ", lecDescription="
				+ lecDescription + "]";
	}
	
	
	
}
