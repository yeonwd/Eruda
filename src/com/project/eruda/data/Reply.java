package com.project.eruda.data;

/**
 * 질문게시판 댓글 데이터를 관리하는 클래스
 * @author 이채린
 *
 */
public class Reply {
	private String rnum;
	private String teacher;
	private String rtext;
	private String rdate;
	private String qnum;
	
	public Reply(String rnum, String teacher, String rtext, String rdate, String qnum) {
		super();
		this.rnum = rnum;
		this.teacher = teacher;
		this.rtext = rtext;
		this.rdate = rdate;
		this.qnum = qnum;
	}

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getRtext() {
		return rtext;
	}

	public void setRtext(String rtext) {
		this.rtext = rtext;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public String getQnum() {
		return qnum;
	}

	public void setQnum(String qnum) {
		this.qnum = qnum;
	}

	@Override
	public String toString() {
		return "Rlist [rnum=" + rnum + ", teacher=" + teacher + ", rtext=" + rtext + ", rdate=" + rdate + ", qnum=" + qnum
				+ "]";
	}
	
}
