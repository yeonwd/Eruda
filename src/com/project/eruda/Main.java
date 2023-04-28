package com.project.eruda;

import java.util.Scanner;
import com.project.eruda.service.*;
import com.project.eruda.data.*;
import com.project.eruda.view.*;
/**
 * 프로그램이 실행되는 메인클래스, 프로그램의 첫 시작
 * @author 5조
 *
 */
public class Main {
	
	public static boolean loginStatus = false; 
	
	// 둘중에 하나만 null이 아닙니다. 만약에 유저를 찾으려면 currentAdmin이 null이면 현재 접속한 사람이 유저
	/**
	 * 두개의 클래스 변수로 현재 로그인한 사용자 구분
	 */
	public static User  currentUser;     
	public static Admin currentAdmin;    	
	
	static {
		currentUser = null;
		currentAdmin = null;
	}
	
	
	/**
	 * 처음 시작화면으로 진입하는 main 메서드
	 * @param args 메인함수인자
	 * @throws Exception 예외받음
	 */
	public static void main(String[] args) throws Exception {
		
		Scanner scan = new Scanner(System.in);
		Data.load();
		boolean loop = true;
		while (loop) {
		
			Logo.printLogo();
			
			if(! loginStatus ) 
				Menu.printMain();   // 바뀜 
			
			Menu.printSelect();
			String sel = scan.nextLine();
		
			if (sel.equals("1")) {
				LoginService.login();//
				if( Main.loginStatus == true) {          // mypage로 진입
					if(currentUser != null)
						UserService.welcomePage();       // Student로 이동
					if(currentAdmin != null)
						AdminService.welcomePage();
				}
			} else if (sel.equals("2")) { // 회원 가입
				RegisterService.register();
				
			} else if (sel.equals("3")) { // 비밀번호 변경
				PasswordChangeService.InformationValid();
				
			} else if (sel.equals("4")) { // 프로그램 종료
				loop = false;
			} else {
				System.out.println("▶ 올바른 입력을 해주세요. ");
			}
		}
		
		DrawUtils.printEndLine();
		System.out.println("▶ 프로그램이 종료되었습니다.");
		System.out.println();
		scan.close(); 
		
	}

}
