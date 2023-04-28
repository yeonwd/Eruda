package com.project.eruda.view;

import java.util.Calendar;
import java.util.Scanner;

/**
 * 다양한 출력을 정의해놓은 클래스
 * @author 5조
 *
 */
public class DrawUtils {
	
	public final static String LINE="──────────────────────────────────────────────────────────────────────────";
	
	public final static String DOTLINE="---------------------------------------------------------------------------";
	/**
	 * 출석체크 출력구문
	 */
	public static void printAttendeeCheck() {
		System.out.println();
		System.out.println();
		String text = "[ ▶ 1. 출석체크 / 2. 퇴실체크 / 3. 뒤로가기  ]\n";
		System.out.println(DrawUtils.alignCenter(text));
	}
	
	//*추가* 채린
	/**
	 * 게시판 윗부분 출력 구문
	 */
	public static void questionhead() {
	    System.out.println();
	    System.out.println(DrawUtils.LINE);
	    System.out.println("\t\t\t      <<질문 게시판>>");
	    System.out.println(DrawUtils.LINE);
	    System.out.println("| 글번호 |  담당강사   |              글제목              |  작성자  |    작성날짜   |");
	}
	
	//채린
	public static void printboardline() {
		System.out.println(DOTLINE);
	}
	
	// 혜린 
	/**
	 * 서브 메뉴 입력 
	 * @param title 제목인자로 받음
	 */
	public static void subMenu(String title) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t==================================================================");
		System.out.printf("\t\t\t\t   <%s>\n", title);
		System.out.println("\t==================================================================");
		System.out.println("\t| 번호 |\t\t   제목   \t\t|   작성자   |     날짜     |");
		System.out.println("\t------------------------------------------------------------------");
	}
	//혜린
	/**
	 * 윗 부분 제목 입력
	 * @param title 제목을 인자로 받음
	 */
	public static void printTop(String title) {
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t==================================================================");
		System.out.printf("\t\t\t\t   <  %s  >\n", title);
		System.out.println("\t==================================================================");
	}
	//혜린 
	/**
	 * 잠시 멈추고 텍스트 띄움
	 */
	public static void pause() {
		// 잠시 멈춤(메인 메뉴로 바로 돌아가지 못하게)
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.println("▶ 계속하시려면 [엔터]를 입력하세요.");
		System.out.println();
		scan.nextLine();
	}
	
	/**
	 * start 라인 긋고 시간정보도 표시
	 */
	public static void printStartLine() {
		Calendar c = Calendar.getInstance();
		System.out.println(LINE+"┐");
		System.out.printf("%48s %tF %tT %tA\n", "Time : ", c, c, c);
	}
	
	/**
	 * 수평선 긋는 메서드
	 */
	public static void printMiddelLine() {
		System.out.println(LINE+"─");
	}
	
	/**
	 * 텍스트를 출력하는데 가운데 정렬
	 * @param text 텍스트를 입력으로 받음
	 */
	public static void centerAlignText(String text) {
		String space = " ".repeat(LINE.length()/2-text.length()/2);
		System.out.println(space + text);
	}
	
	/**
	 * 종료라인을 그음
	 */
	public static void printEndLine() {
		System.out.println(LINE+"┘");
	}
	
	
	// 로고를 가운데 정렬하는 메서드
	/**
	 * 로고를 가운데 정렬
	 * @param str 로고를 입력으로 받음
	 * @return 정렬된 문자열을 반환
	 */
	public static String alignCenter(String str) {
		// 다른 기능 센터 정렬
		
		int calSpace=0;
		for(int i=0; i<str.length(); i++) {
			calSpace++;
			if(str.charAt(i) == '\n')  
				break;
		}
		
		String space = " ".repeat(LINE.length()/2-calSpace/2);
		
		String result = space;
		for(int i=0; i<str.length(); i++) {
			result += str.charAt(i)+"";
			if(str.charAt(i) == '\n')  
				result +=space;
		}
		return result;
	}
	
}
