package com.project.eruda.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.github.lalyos.jfiglet.FigletFont;
import com.project.eruda.Main;
import com.project.eruda.data.*;
import com.project.eruda.view.*;

/**
 * 로그인 클래스입니다. 
 * @author 조연우
 */
public class LoginService {
	
	/**
	 * 로그인 메소드입니다.
	 * 로그인 유효성을 검사하고, 로그인 실행 후 해당하는 유저의 데이터를 가져옵니다. 
	 */
	static public void login() { //

		System.out.println("▶ 로그인이 선택되었습니다.");
		DrawUtils.printEndLine();
		DrawUtils.printStartLine();

		Scanner scan = new Scanner(System.in);

		try {

			String asciiArt3 = FigletFont.convertOneLine("Login");
			System.out.println(DrawUtils.alignCenter(asciiArt3));
			DrawUtils.printMiddelLine();
			System.out.println();
			
			int countFailed = 0;
			boolean loop = true;

			// 검증로직
			while (loop) {
				// 로그인 검증
				System.out.println();
				System.out.println();
				System.out.print("▶ 아이디를 입력해주세요.\n");
				System.out.print("[ ID ] : ");
				String id = scan.nextLine().trim();
				
				System.out.println();
				
				System.out.print("▶ 비밀번호를 입력해주세요.\n");
				System.out.print("[ PW ] : ");
				String pw = scan.nextLine().trim();
				//////////////////////

				int validity = -2;
				boolean isAdmin = id.contains("admin");

				// 1. 어드민 or 학생 로그인 체크
				if (isAdmin) { // 어드민 로그인 체크
					// 강사 로그인 체크
					validity = validAdminLogin(Data.adminList, id, pw);
					if (validity >= 0) { //

						
						Main.loginStatus = true;
						Admin admin = Data.adminList.get(validity);
						if (admin.getPosition().equals("강사") || admin.getPosition().equals("매니저")
								|| admin.getPosition().equals("운영자"))
							Main.currentAdmin = admin;
						
						System.out.printf("\n▶ 환영합니다. %s %s님, 로그인 되셨습니다.\n\n",Main.currentAdmin.getPosition(), Main.currentAdmin.getName());
						DrawUtils.pause();
						
						break;
					}

				} else { // 학생 로그인 체크
					validity = validLogin(Data.userList, id, pw);
					if (validity >= 0) {
						
						
						Main.loginStatus = true;
						Main.currentUser = Data.userList.get(validity);
						
						System.out.printf("\n▶ 환영합니다. %s님, 로그인 되셨습니다.\n", Main.currentUser.getName());
						DrawUtils.pause();
						break;
					}
				}

				// 올바르지 않은 로그인을 하는 경우 아래로 빠진다. ↓
				if (validity == -1) {
					countFailed++;
					System.out.println("▶ 비밀번호가 틀립니다.");
					System.out.printf("▶ 5회 이상 오류 시 비밀번호를 재설정해야 합니다. (%d/5)\n", countFailed);
				}
				if (validity == -2) {
					System.out.println("▶ 회원정보가 없습니다.");
					countFailed++;
					System.out.printf("▶ 5회 이상 오류 시 비밀번호를 재설정해야 합니다. (%d/5)\n", countFailed);
				}
				
				if (countFailed == 5) {
					System.out.println();
					
					System.out.println("▶ 비밀번호를 5회 이상 잘못 입력했습니다. 비밀번호 재설정 화면으로 이동합니다.");
					/////////////////////////////////////////////////
					DrawUtils.pause();
					
					PasswordChangeService.InformationValid();
					/////////////////////////////////////////////////
					break;
				}

			}

		} catch (IOException e) {
			e.printStackTrace();

		}

	}
	
	/**
	 * 관리자 로그인 유효성 검사 메소드입니다.
	 * 아이디에 'admin'이 포함될 시 호출됩니다.
	 * @see Data#adminList
	 * @param adminList 관리자 계정 데이터 리스트
	 * @param id 아이디
	 * @param pw 비밀번호
	 * @return 유효 로그인일 시 adminList의 해당 계정 인덱스, 아이디만 일치할 시 -1, 회원 정보가 없을 시 -2 반환
	 */
	private static int validAdminLogin(List<Admin> adminList, String id, String pw) {

		for (int i = 0; i < adminList.size(); i++) {

			Admin admin = adminList.get(i);
			if (id.equals(admin.getId())) {
				if (pw.equals(admin.getPw()))
					return i;
				return -1;
			}
		}
		return -2;
	}
	
	/**
	 * 학생 로그인 유효성 검사 메소드입니다.
	 * 아이디에 'admin'이 포함되지 않을 시 호출됩니다. 
	 * @see Data#userList
	 * @param userList 학생 계정 데이터 리스트
	 * @param id 아이디
	 * @param pw 비밀번호
	 * @return 유효 로그인일 시 userList의 해당 계정 인덱스, 아이디만 일치할 시 -1, 회원 정보가 없을 시 -2 반환
	 */
	private static int validLogin(List<User> userList, String id, String pw) {
		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
			if (id.equals(user.getId())) {
				if (pw.equals(user.getPw())) {
					return i; // 로그인 성공
				}
				return -1; // 아이디만 일치
			}
		}
		return -2; // 회원 정보 없음
	}

}
