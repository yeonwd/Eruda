package com.project.eruda.data;
/**
 * 퀴즈정보를 저장하는 클래스
 * @author 5조
 *
 */
public class Quiz {
	
	
	String teacher;
	String quizFileName;
	String answer;
	String solution;
	

	public Quiz(String teacher, String quizFileName, String answer, String solution) {
		super();
		this.teacher = teacher;
		this.quizFileName = quizFileName;
		this.answer = answer;
		this.solution = solution;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}
	
	public String getTeacher() {
		return teacher;
	}
	
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	
	public String getQuizFileName() {
		return quizFileName;
	}
	
	public void setQuizFileName(String quizFileName) {
		this.quizFileName = quizFileName;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Override
	public String toString() {
		return "Quiz [teacher=" + teacher + ", quizFileName=" + quizFileName + ", answer=" + answer + "]";
	}
	
}
