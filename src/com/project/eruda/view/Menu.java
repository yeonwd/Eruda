package com.project.eruda.view;

import java.io.IOException;
import com.project.eruda.*;
import com.project.eruda.data.Lecture;
import com.project.eruda.data.User;
import com.github.lalyos.jfiglet.FigletFont;

/**
 * 메뉴에 대한 출력을 정의한 클래스
 * @author 5조
 *
 */
public class Menu {

	public static void printSelect() {
		DrawUtils.printMiddelLine();
		System.out.print("[ SELECT 🖊 ] : ");
	}

	/**
	 * 시작화면 
	 */
	public static void printMain() {

		System.out.println("\n\n\n");
		DrawUtils.centerAlignText("1. 로그인");
		System.out.println();
		DrawUtils.centerAlignText("2. 회원가입");
		System.out.println();
		DrawUtils.centerAlignText("3. PW 재설정");
		System.out.println();
		DrawUtils.centerAlignText("4. 종료하기");
		System.out.println("\n\n\n");

	}
	
	/**
	 * 퀴즈 기능과 관련된 메뉴
	 */
	public static void printQuizMenu() {
		try {

			String asciiArt3;
			DrawUtils.printStartLine();
			asciiArt3 = FigletFont.convertOneLine("Quiz");
			System.out.println(DrawUtils.alignCenter(asciiArt3));
			DrawUtils.printMiddelLine();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 유저메인화면 출력
	 */
	public static void printUserMenu() {

		try {

			String asciiArt3;
			DrawUtils.printStartLine();
			asciiArt3 = FigletFont.convertOneLine("Student");
			System.out.println(DrawUtils.alignCenter(asciiArt3));
			DrawUtils.printMiddelLine();
			System.out.println();
			DrawUtils.centerAlignText("1. 출결관리");
			System.out.println();
			DrawUtils.centerAlignText("2. 오늘의 할 일");
			System.out.println();
			DrawUtils.centerAlignText("3. 마이 페이지");
			System.out.println();
			DrawUtils.centerAlignText("4. 수업 정보");
			System.out.println();
			DrawUtils.centerAlignText("5. 취업 정보");
			System.out.println();
			DrawUtils.centerAlignText("6. 공지사항");
			System.out.println();
			DrawUtils.centerAlignText("7. 학생 건의 게시판");
			System.out.println();
			DrawUtils.centerAlignText("8. 질문 게시판");
			System.out.println();
			DrawUtils.centerAlignText("9. 로그아웃");
			System.out.println();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 강사 메인화면
	 */
	public static void printInstructorMenu() {

		try {

			String asciiArt3;
			DrawUtils.printStartLine();
			asciiArt3 = FigletFont.convertOneLine("Instructor");
			System.out.println(DrawUtils.alignCenter(asciiArt3));
			DrawUtils.printMiddelLine();
			System.out.println("\n");
			DrawUtils.centerAlignText("1. 나의 수업관리");
			System.out.println();
			DrawUtils.centerAlignText("2. 나의 학생정보 열람");
			System.out.println();
			DrawUtils.centerAlignText("3. 공지사항");
			System.out.println();
			DrawUtils.centerAlignText("4. 학생 건의게시판");
			System.out.println();
			DrawUtils.centerAlignText("5. 질문게시판");
			System.out.println();
			DrawUtils.centerAlignText("6. 로그아웃");
			System.out.println("\n\n\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 매니저 메인 페이지 메뉴 출력
	 */
	public static void printManagerMenu() {

		try {

			String asciiArt3;
			DrawUtils.printStartLine();
			asciiArt3 = FigletFont.convertOneLine("Manager");
			System.out.println(DrawUtils.alignCenter(asciiArt3));
			DrawUtils.printMiddelLine();
			
			System.out.println();
			DrawUtils.centerAlignText("1. 강의정보 열람");
			System.out.println();
			DrawUtils.centerAlignText("2. 학생정보 관리");
			System.out.println();
			DrawUtils.centerAlignText("3. 공지사항");
			System.out.println();
			DrawUtils.centerAlignText("4. 학생 건의게시판");
			System.out.println();
			DrawUtils.centerAlignText("5. 로그아웃");
			System.out.println("\n");
			
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
	
	/**
	 * 운영자 메인페이지 메뉴 출력
	 */
	public static void printOperatorMenu() {

		try {

			String asciiArt3;
			DrawUtils.printStartLine();
			asciiArt3 = FigletFont.convertOneLine("Operator");
			System.out.println(DrawUtils.alignCenter(asciiArt3));
			DrawUtils.printMiddelLine();
			
			System.out.println();
			DrawUtils.centerAlignText("1. 강사 및 매니저 관리");
			System.out.println();
			DrawUtils.centerAlignText("2. 강의정보 관리");
			System.out.println();
			DrawUtils.centerAlignText("3. 학생정보 관리");
			System.out.println();
			DrawUtils.centerAlignText("4. 공지사항");
			System.out.println();
			DrawUtils.centerAlignText("5. 학생 건의사항");
			System.out.println();
			DrawUtils.centerAlignText("6. 로그아웃");
			System.out.println("\n");
			
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
	
	
	/**
	 * 나의 개인정보 출력에 대한 메뉴
	 */
	static public void printMyPageMenu() {
		
		String asciiArt3;
		DrawUtils.printMiddelLine();
		try {
			asciiArt3 = FigletFont.convertOneLine( "MyPage");
			System.out.println(DrawUtils.alignCenter(asciiArt3));
			DrawUtils.printMiddelLine();
			User user = Main.currentUser;
			
			System.out.println();
			DrawUtils.centerAlignText("이름: " + user.getName());
			System.out.println();
			DrawUtils.centerAlignText("성별: " + user.getGender()+"성");
			System.out.println();
			DrawUtils.centerAlignText("생년월일: " + user.getAge());
			System.out.println();
			System.out.println();
			DrawUtils.centerAlignText("전화번호: " + user.getPhoneNumber());
			System.out.println();
			DrawUtils.centerAlignText("이메일: " + user.getEmail());
			System.out.println();
			DrawUtils.centerAlignText("주소: " + user.getAddress());
			System.out.println();
			System.out.println();
			DrawUtils.centerAlignText("대학: " + nullCheck(user.getUniv()));
			System.out.println();
			DrawUtils.centerAlignText("전공: " + nullCheck(user.getMajor()));
			System.out.println();
			DrawUtils.centerAlignText("수강과정: " + nullCheck(user.getCourse()));
			System.out.println();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	/**
	 * null string 체크 null 스트링을 '미입력'으로 바꿔줌
	 * @param value 문자열을 입력으로 넣음
	 * @return 검사해서 null이면 미입력으로 바꿔준 문자열을 리턴
	 */
	public static String nullCheck(String value) {
		if("null".equals(value))
			return "미입력";
		else
			return value;
		
	}
	
	
	/**
	 * 개인정보 수정 메뉴 출력구문
	 */
	static public void printEditInfoMenu() {
		
		try {
			String asciiArt3 = FigletFont.convertOneLine( "EditInfo");
			System.out.println(DrawUtils.alignCenter(asciiArt3));
			DrawUtils.printMiddelLine();
			User user = Main.currentUser;
			
			System.out.println();
			DrawUtils.centerAlignText("1. 이름: " + user.getName());
			System.out.println();
			DrawUtils.centerAlignText("2. 성별: " + user.getGender()+"성");
			System.out.println();
			DrawUtils.centerAlignText("3. 생년월일: " + user.getAge());
			System.out.println();
			DrawUtils.centerAlignText("4. 전화번호: " + user.getPhoneNumber());
			System.out.println();
			DrawUtils.centerAlignText("5. 이메일: " + user.getEmail());
			System.out.println();
			DrawUtils.centerAlignText("6. 주소: " + user.getAddress());
			System.out.println();
			DrawUtils.centerAlignText("7. 대학: " + nullCheck(user.getUniv()));
			System.out.println();
			DrawUtils.centerAlignText("8. 전공: " + nullCheck(user.getMajor()));
			System.out.println();

			
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	/**
	 * todoList 출력구문
	 */
	public static void printTodoListMenu() {
		try {
			String asciiArt3 = FigletFont.convertOneLine( "TodoList");     
			System.out.println(DrawUtils.alignCenter(asciiArt3));
			DrawUtils.printMiddelLine();
			
			System.out.println("\n\n");
			
			System.out.println();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	//채린]
	/**
	 * 질문게시판 초기 화면의 선택지를 출력하는 메소드
	 */
	public static void printQboard() {
		try {
		String asciiArt3 = FigletFont.convertOneLine( "Question");     
		System.out.println(DrawUtils.alignCenter(asciiArt3));
		DrawUtils.printMiddelLine();
		
		System.out.println("\n\n\n\n");
		DrawUtils.centerAlignText("1. 게시글 목록 보기");
		System.out.println();
		DrawUtils.centerAlignText("2. 글 작성");
		System.out.println();
		DrawUtils.centerAlignText("3. 뒤로가기");
		System.out.println();
		System.out.println("\n\n\n\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 수업관리에서 사용되는 출력구문
	 * @param lecture 강의정보 객체를 인자로 입력
	 */
	public static void managingClass(Lecture lecture) {
		System.out.println();
		System.out.println();
		DrawUtils.centerAlignText(Main.currentAdmin.getName() + " 강사님");
		System.out.println();
		DrawUtils.centerAlignText("[ 강의 과목: " + lecture.getCourseTitle()+" ]");
		DrawUtils.centerAlignText("[ 강의 기간: " + getTerm(lecture.getStartDate(), lecture.getEndDate())+" ]");
		DrawUtils.centerAlignText("[ 강의설명 : " +lecture.getLecDescription()+" ]");
		
		System.out.println();
		DrawUtils.centerAlignText("1. 문제 출제");
		DrawUtils.centerAlignText("0. 뒤로 가기");
		System.out.println();
	}
	
	/**
	 * 날짜 출력구문
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private static String getTerm(String startDate, String endDate) {
		String[] temp1 = startDate.split("-");
		String[] temp2 = endDate.split("-");
		return String.format("%s년 %s월 %s일 - %s년 %s월 %s일", temp1[0], temp1[1], temp1[2], temp2[0], temp2[1], temp2[2]);
	}

}
