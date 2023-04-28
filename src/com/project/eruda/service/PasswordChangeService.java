package com.project.eruda.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

import com.github.lalyos.jfiglet.FigletFont;
import com.project.eruda.data.Data;
import com.project.eruda.data.User;
import com.project.eruda.view.*;



/**
 * 비밀변호 변경기능을 구현한 클래스
 * @author 5조 
 *
 */
public class PasswordChangeService {
	
	public static String tempName;
	public static void InformationValid() {
		
		Scanner scan = new Scanner(System.in);
		DrawUtils.printMiddelLine();
		System.out.println("▶ 비밀번호 재설정이 선택되었습니다.");
		DrawUtils.printEndLine();
		DrawUtils.printStartLine();
		String asciiArt3;
		
		try {
			
			boolean loop = true;
			asciiArt3 = FigletFont.convertOneLine("Pw Change");
			System.out.println(DrawUtils.alignCenter(asciiArt3));
			DrawUtils.printMiddelLine();
			System.out.println("[ 개인정보 확인 과정 ]");
			System.out.println();
			System.out.println("▶ 비밀번호 재설정을 위해 필요한 과정입니다. 개인정보를 입력해주세요.\n");
			
			while(loop) {
				
				boolean check = checkProfile();
				if(check) break;
				Menu.printSelect();
				String sel = scan.nextLine();
				if(sel.equals("-1")) {
					return ;
				}
			}
		
			DrawUtils.pause();
			// 비밀번호 재설정하는 것 구현
			pwChange();
			////////////////
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
	/**
	 * 비밀번호를 재설정하는 메서드
	 */
	public static void pwChange() {
		// 비밀번호 재설정 화면 단계 > 이부분을 구현하시면 됩니다. ///
		Scanner scan = new Scanner(System.in);
		System.out.println("▶ 개인정보가 확인되었습니다.\n▶ 새로운 비밀번호를 입력해주세요.");
		
		System.out.print("[ 새로운 비밀번호 ] : ");
		String pw = scan.nextLine();
		
		
		System.out.print("▶ 입력을 완료하시겠습니까? (yes or no) : ");
		String sel = scan.nextLine();
		
		if (sel.equals("yes")) {
			//정보 저장 후 메인 페이지로 이동
			//user.txt에 있는 회원정보 수정
			changeCostumerInfo(pw);
			
			System.out.println("▶ 입력이 완료되었습니다.\n▶초기 화면으로 이동합니다.");
			Menu.printSelect();
			
		} else if (sel.equals("no")) {
			//다시 비밀번호 재설정 화면 단계로 
			pwChange();
			DrawUtils.pause();
		}
		
		//////////////////////////////////////////
	}
	
	/**
	 * 회원 수정 내역 교체
	 * 추가는 되는데 기존것이 안사라짐
	 * @param pw
	 */
	private static void changeCostumerInfo(String pw) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(Data.USER));
			//qwer2■1234■이길동■남■1997-10-05■010-134-578■aaa@gmail.com■서울시 강북구 어디 아파트■비트대학교■사회복지■자바풀스텍
			for (User u : Data.userList)
				if(u.getId().equals(tempName)){
					u.setPw(pw);
					writer.write(String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s\n",
						u.getId(), pw,
						u.getName(), u.getGender(),
						u.getAge(), u.getPhoneNumber(),
						u.getEmail(), u.getAddress(), 
						u.getUniv(), u.getMajor(), u.getCourse(),
						u.getStart(),u.getEnd(),u.getCompany() ));
				} else {
					writer.write(String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s\n",
							u.getId(), u.getPw(),
							u.getName(), u.getGender(),
							u.getAge(), u.getPhoneNumber(),
							u.getEmail(), u.getAddress(), 
							u.getUniv(), u.getMajor(), u.getCourse(),
							u.getStart(),u.getEnd(),u.getCompany() ));
				}
			
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 개인정보를 확인하는 과정
	 * @return 입력된 정보가 확인되면 true
	 */
	private static boolean checkProfile() {
	// 해당 기능은 유저만 사용하는 기능입니다. 	
		Scanner scan = new Scanner(System.in);
		//System.out.println("Start------------");
		System.out.print("[ 아이디 ] : ");
		String id = scan.nextLine();
		System.out.print("[ 성명 ] : ");
		String name = scan.nextLine();
		System.out.print("[ 생년월일 ] : ");
		String age = scan.nextLine();
		System.out.print("[ 휴대전화 ] : ");
		String phoneNumber = scan.nextLine();
		
		for(User u : Data.userList) {
				
			if( u.getId().equals(id) && u.getName().equals(name)) { 
					if(  u.getAge().equals(age)  && u.getPhoneNumber().equals(phoneNumber)) { // id를 먼저 찾고
						System.out.println("\n▶ 입력한 정보가 일치합니다. 비밀번호 재설정 화면으로 이동합니다. ");
						tempName = id;
						return true;
					}
			}
		}
		
		System.out.println("\n▶ 입력한 정보가 일치하지 않습니다. 다시 입력해주세요.");
		System.out.println("( 계속 하시려면 1 입력, 메인화면으로 이동하려면 -1 입력 )");
		return false;
		
	}
	
	
	
	
}	
