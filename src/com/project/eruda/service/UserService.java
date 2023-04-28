package com.project.eruda.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.project.eruda.Main;
import com.project.eruda.data.*;
import com.project.eruda.view.*;
/**
 * 학생 계정용 기능 수행 클래스입니다.
 * @author 조연우, 남건욱
 */
public class UserService {
	
	public static final String ATTENDEE = ".\\dat\\attend.txt";
	private final static String MSG = "올바른 숫자를 입력해 주세요.";
	
	/**
	 * 학생 계정 홈화면 메소드입니다.
	 */
	public static void welcomePage() {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		boolean logout = false;
		while (loop) {
			Menu.printUserMenu();
			Menu.printSelect();
			String sel = scan.nextLine();
			
			switch (sel) {
			case "1":
				// 출결관리
				System.out.println("▶ 출결관리가 선택되었습니다.");
				UserService.AttendeeCheck();
				break;
			case "2":
				// 오늘의 할 일
				TodoListService.todoList();
				break;
			case "3":
				// 마이 페이지
				UserService.myPage();
				break;
			case "4":
				// 수업 정보
				CourseService.readCourse();
				break;
			case "5":
				// 취업 정보
				JobCrawlerService.crawlerService();
				break;
			case "6":
				// 공지사항
				NoticeService.list("<  공지사항  >", 0);
				break;
			case "7":
				// 건의게시판 
				SuggestService.list("<  건의사항  >", 0);
				break;
			case "8":
			    // 질문게시판 > 여기에 
				QboardService.qboard();
				break;
			case "9":
				// 로그아웃
				logout = true;
				break;
			}
			if (logout) {
				Main.loginStatus = false;
				Main.currentUser = null;
				break;
			}
		}

	}
	
	/**
	 * 계정에 등록된 개인 정보를 출력하는 메소드입니다.
	 */
	
	public static void myPage() {
		boolean loop = true;

		while (loop) {
			Menu.printMyPageMenu();
			System.out.println();
			DrawUtils.centerAlignText("[ 0. 이전 페이지로 돌아가기, 1. 정보 수정하기 ]");
		
	
			Scanner scan = new Scanner(System.in);
			Menu.printSelect();
			String sel = scan.nextLine();

			if (sel.equals("1")) {
				editInfo();
				break;
			} else if (sel.equals("0"))
				break;
			else
				System.out.println(MSG);
		}
	}
	
	/**
	 * 출석체크를 하는 메서드
	 */
	public static void AttendeeCheck() {
		Scanner scan = new Scanner(System.in);
		DrawUtils.printEndLine();
		
		System.out.println();
		DrawUtils.centerAlignText("[ 수강생 : " + Main.currentUser.getName()+" ]");
		System.out.println();
		DrawUtils.centerAlignText("[ 수강과정: " + Menu.nullCheck(Main.currentUser.getCourse()) +" ]");
		System.out.println();
		String time="";
		if( ( time=checkAttendee(0) ) !="" )
			DrawUtils.centerAlignText("[ 출석시간 : " + time+ " ]");
		if( ( time=checkAttendee(1) ) !="" )
			DrawUtils.centerAlignText("[ 퇴실시간 : " + time+ " ]");
		DrawUtils.printAttendeeCheck();
		Menu.printSelect();
		String sel = scan.nextLine();
		
		if( sel.equals("1")) { // 
			attendeeIn();
		} else if(sel.equals("2")) {
			attendeeOut();
		} //
		
		DrawUtils.pause();
	}
	
	/**
	 * 
	 * @param sel 출석모드, 퇴실모드로 나뉨
	 * @return 시간기록을 반환
	 */
	public static String checkAttendee(int sel) {
		String line = null;
		String id = "";
		String time = "";
		String status = "";

		try {
			BufferedReader reader = new BufferedReader(new FileReader(ATTENDEE));
	
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				id = temp[0];
				
				status = temp[2];
				if (sel == 0) {
					if(Main.currentUser.getId().equals(id)) {
						time = temp[1];
						break;
					}
				} else {
					if(status.equals("퇴")) {
						time = temp[1];
						break;
					}
				} 
			}
			
			reader.close();
		
		} catch(Exception e) {
			
		}
		return time;
	}
	
	/**
	 * 퇴실처리와 관련된 메서드
	 */
	public static void attendeeOut() {
		
		try {
				BufferedReader reader = new BufferedReader(new FileReader(ATTENDEE));
				String line = null;
				String id = "";
				String time = "";
				String status = "";
				
				while ((line = reader.readLine()) != null) {
					String[] temp = line.split("■");
					id = temp[0];
					time = temp[1];
					status = temp[2];
					
					if(Main.currentUser.getId().equals(id)) {
						break;
					}
				}
				
				reader.close();
				
				
				if(status=="") {
					System.out.println("\n▶ 출석체크를 하세요. ");
				} else if ( status.equals("출")) {
					System.out.println("\n▶ 퇴실처리 하겠습니다. ");
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        Date now = new Date();
			        String nowTime1 = sdf1.format(now);		
					BufferedWriter writer = new BufferedWriter(new FileWriter(ATTENDEE, true));
					writer.write(String.format("%s■%s■%s\r\n"
												, Main.currentUser.getId()
												, nowTime1
												, "퇴"));
					writer.close();
				}

				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	
	/**
	 * 출석처리와 관련된 메서드
	 */
	public static void attendeeIn() {
		
	try {
			BufferedReader reader = new BufferedReader(new FileReader(ATTENDEE));
			String line = null;
			String id = "";
			String time = "";
			String status = "";
			
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				id = temp[0];
				time = temp[1];
				status = temp[2];
				
				if(Main.currentUser.getId().equals(id)) {
					break;
				}
			}
			
			reader.close();
			
			if(status=="") {
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        Date now = new Date();
		        String nowTime1 = sdf1.format(now);		
				BufferedWriter writer = new BufferedWriter(new FileWriter(ATTENDEE));
				writer.write(String.format("%s■%s■%s\r\n"
											, Main.currentUser.getId()
											, nowTime1
											, "출"));
				System.out.println("\n▶ 출석 되었습니다. ");
				writer.close();
				
			} else if ( status.equals("출")) {
				System.out.println("\n▶ 이미 출석하셨습니다. ");
			}

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 개인정보 수정 메소드입니다.
	 * 수정값을 입력 받고, 갱신된 데이터를 저장합니다.
	 * @see Data#saveUserList()
	 */
	public static void editInfo() {
		Menu.printEditInfoMenu();
		DrawUtils.centerAlignText("[ 0. 메인으로 돌아가거나, 수정할 번호를 입력하세요. ]");
		Menu.printSelect();
		Scanner scan = new Scanner(System.in);
		String sel = scan.nextLine();
		
		User user = Main.currentUser;
		
		switch (sel) {
		case "0":
			System.out.println("▶ 메인으로 돌아갑니다. ");
			break;

		case "1":
			scan = new Scanner(System.in);
			System.out.print("▶ 변경할 이름을 입력하세요.\n▶ ");
			String rename = scan.nextLine().trim();
			if (reconfirm("이름", rename))
				user.setName(rename); // 변경 확정
			break;
		case "2":
			System.out.println("▶ 변경할 성별을 입력하세요. [ 1. 여성 2. 남성 ]");
			System.out.print("▶ ");
			int gender = scan.nextInt();
			String s = (gender == 1) ? "여" : "남";
			if (reconfirm("성별", s + "성"))
				user.setGender(s);
			break;
		case "3":
			System.out.println("▶ 생년월일을 '1990-01-01' 형태로 입력하세요.");
			String birth = scan.nextLine().trim();
			if (reconfirm("생년월일", birth))
				user.setAge(birth);
			
			break;
		case "4":
			System.out.println("▶ 전화번호를 '010-1234-1234' 형태로 입력하세요.");
			String mobile = scan.nextLine().trim();
			if (reconfirm("전화번호", mobile))
				user.setPhoneNumber(mobile);
			
			break;
		case "5":
			System.out.println("▶ 이메일을 'aaa@aaa.com' 형태로 입력하세요.");
			String email = scan.nextLine().trim();
			if(reconfirm("이메일", email)) user.setEmail(email);
			
			break;
		case "6":
			System.out.println("▶ 주소를 입력하세요.");
			String address =scan.nextLine();
			if(reconfirm("주소", address)) user.setAddress(address);
			
			break;
		case "7":
			System.out.println("▶ 대학교를 입력하세요.");
			String uni = scan.nextLine().trim();
			if(reconfirm("대학교", uni)) user.setUniv(uni);
			
			break;
		case "8":
			System.out.println("▶ 전공을 입력하세요.");
			String major = scan.nextLine().trim();
			if(reconfirm("전공", major)) user.setMajor(major);
			break;
			// 수강생 등록은 매니저가 할 일
//		case "9":
//			System.out.println("▶ 수강과정을 입력하세요.");
//			String course = scan.nextLine().trim();
//			if(reconfirm("수강과정", course)) user.setCourse(course);
//			break;	
			
		}
		//////////////////////// 기존의 값에다가 변경하는 작업////////////////////
		// 현재 리스트에다가 반영 후 파일에도 쓰는 작업
		Data.saveUserList();
		//////////////////////////////////
		DrawUtils.pause();
	}
	
	
	
	/**
	 * 변경여부를 묻는다.
	 * @param type 이전값
	 * @param to 변경할값
	 * @return 작업의 성공여부
	 */
	static private boolean reconfirm(String type, String to) {
		
		System.out.println();
		System.out.printf("▶ %s을(를) %s로 변경할까요?\n", type, to);
		Scanner scan = new Scanner(System.in);
		System.out.println("[ 0. 취소, 1. 변경 ]");
		Menu.printSelect();
		
		switch (scan.nextInt()) {
		case 1:
			return true;
		case 0:
			System.out.println("▶ 작업이 취소되었습니다.");
			return false;
		default:
			return false;
		}

	}
}
