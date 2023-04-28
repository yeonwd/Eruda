package com.project.eruda.data;

/**
 * 질문게시판 글 데이터를 관리하는 클래스
 * @author 이채린
 *
 */
public class Question {

	private String qnum;
	private String date;
	private String id;
	private String title;
	private String maintext;
	private String teacher;
	
	public Question(String qnum, String date, String id, String title, String maintext, String teacher) {
		super();
		this.qnum = qnum;
		this.date = date;
		this.id = id;
		this.title = title;
		this.maintext = maintext;
		this.teacher = teacher;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQnum() {
		return qnum;
	}

	public void setQnum(String qnum) {
		this.qnum = qnum;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMaintext() {
		return maintext;
	}

	public void setMaintext(String maintext) {
		this.maintext = maintext;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "Question [qnum=" + qnum + ", date=" + date + ", id=" + id + ", title=" + title + ", maintext="
				+ maintext + ", teacher=" + teacher + "]";
	}

	
}
