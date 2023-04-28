package com.project.eruda.service;

import java.time.LocalDate;
import java.util.Scanner;

import com.project.eruda.Main;
import com.project.eruda.data.*;
import com.project.eruda.view.*;

/**
 * 
 * <h1>건의사항 게시판의 업무 구현 클래스</h1>
 * 
 * @author hyerin
 *
 */
public class SuggestService {

	
	/**
	 * 
	 * <h1>건의사항 글 목록을 출력하기 위한 메소드</h1>
	 * 한 페이지에 10개의 글이 출력되고 다음페이지, 이전페이지 출력 가능
	 * 
	 * 
	 * @param title 게시판 이름
	 * @param idx	첫번째로 출력될 글 번호
	 */
public static void list(String title, int idx) {
		
		Scanner scan = new Scanner(System.in);
		
		DrawUtils.subMenu(title);
		
		//글 목록 10개씩 출력
		int cnt = idx+10;
		for (int i=idx; i<cnt; i++) {
			
			// 데이터 없으면 NULL로 출력
			if (i >= Data.suggestList.size()) {
				//
			} else if (i < 0 ){ //이전 페이지 없으면 이전 페이지 넘겨도 안넘어가게 함
				//
			}
			else if (cnt-10 >= Data.suggestList.size()) {
				//
			} else {
				//System.out.printf("[ %2s\t%-12s\t%-4s\t%-10s]\n","번호","아이디","이름","생년월일");
				Suggest s = Data.suggestList.get(i);
				
				System.out.printf("\t  %2s\t%18s\t\t   %-6s \t%-9s\n"
									, s.getIndex()
									, titleTrim(s.getTitle())
									, s.getId()
									, s.getDate());
				System.out.println("\t------------------------------------------------------------------");
			}
		}
		
		DrawUtils.centerAlignText("[ 이전 페이지 : 2 ] [ 다음 페이지 : 3 ]");
		System.out.println("\t------------------------------------------------------------------");
		DrawUtils.centerAlignText("[ 글열람 : 0 ] [ 새 글 작성 : 1 ] [ 메인으로 : -1]");
		System.out.println("\t==================================================================");
		System.out.println();
		
		System.out.print("\t▶ 원하는 항목을 선택해주세요 : ");
		String n = scan.nextLine();
			
		if (n.equals("0")) {
			System.out.println("\n\t==================================================================\n");
			System.out.print("\t▶ 열람하고 싶은 글 번호를 입력해주세요 : ");
			String num = scan.nextLine();
			SuggestService.read(num);
			
		} else if (n.equals("1")) {
			SuggestService.add();
		} else if (n.equals("2")) {
			System.out.println(idx);
			if( idx-10 < 0) {
				SuggestService.list("<  건의사항  >", 0);
			} else {
				idx -= 10;
			    SuggestService.list("<  건의사항  >", idx);
			}
		} else if (n.equals("3")) {
			
			if(idx+10 < Data.suggestList.size() ) {
				idx += 10;
				SuggestService.list("<  건의사항  >", idx);
			}
			else 
				SuggestService.list("<  건의사항  >", idx);
		} else if (n.equals("-1")) {
			//System.out.println("와@");
		}
		
	}//list

	/**
	 * <h1>열람하고 싶은 건의사항 글을 선택해 본문을 열람하기 위한 메소드</h1>
	 * 입력받은 글 번호에 해당되는 글이 없을 땐 다시 글 번호 입력받음<br><br>
	 * 0 선택 시 > {@link SuggestService#list(String, int)} 호출해서 글목록 출력<br>
	 * 1 선택 시 > {@link SuggestService#edit(String)} 호출해서 글 수정<br>
	 * 2 선택 시 > {@link SuggestService#delete(String)} 호출해서 글 삭제<br>
	 * 
	 * 
	 * @param num 열람하고 싶은 글 번호
	 */
	private static void read(String num) {

			
		Scanner scan = new Scanner(System.in);

		for (Suggest s : Data.suggestList) {
			
			if (Integer.parseInt(num) > Data.suggestList.size()) {
				System.out.println("\n\t==================================================================\n");
				System.out.println("\t해당 번호의 글이 존재하지 않습니다. 다시 입력해주세요\n");
				System.out.print("\t▶ 열람하고 싶은 글 번호를 입력해주세요 : ");
				String n = scan.nextLine();
				SuggestService.read(n); // ** 재귀 쓰지말기 > 함수 호출스텍 꼬임
				break; // for문 안에 있는 함수는 조심
			}
			
			if (num.equals(s.getIndex())) {
				String title = s.getTitle();

				DrawUtils.printTop(title);
				System.out.println();
				System.out.println();

				s.TextSetting(s.getText());
				System.out.printf("\t\t%s", s.getText());
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.printf("\t\t\t\t\t작성자 : %s\t날짜 : %s\n", s.getId(), s.getDate());
				System.out.println("\t------------------------------------------------------------------");
				System.out.println("\t [뒤로가기 : 0]\t\t    [글 수정 : 1]\t\t    [글 삭제 : 2]");
				System.out.println("\t==================================================================");
				System.out.println();

				System.out.print("\t▶ 원하는 항목을 선택해주세요 : ");
				String n = scan.nextLine();

				if (n.equals("0")) {
					SuggestService.list("<  건의사항  >", 0);

				} else if (n.equals("1")) { // 
					SuggestService.edit(s.getIndex());
					break;
				} else if (n.equals("2")) { //
					SuggestService.delete(s.getIndex());
					break; //이것도 재귀인데 break; 안걸면 호출스텍 꼬임
				}

				System.out.println();
				System.out.println();
				System.out.println();
			}
		}

	}// read
	
	/**
	 * <h1>새로운 건의사항을 추가하기 위한 메소드</h1>
	 * 제목과 본문 내용을 입력받아 데이터에 추가한다.<br>
	 * 현재 로그인 유저가 학생계정일 때만 글 작성이 가능하다.<br>
	 * 
	 */
	public static void add() {

		String no="";
		if(Data.suggestList.size()==0)
			no ="1";
		else 
			no = Data.suggestList.stream().mapToInt(s -> Integer.parseInt(s.getIndex())).max().getAsInt() + 1 + "";

		String date = "";

		DrawUtils.printTop("추가하기");
		System.out.println("\n\n\n\t\t새로 작성할 내용을 입력해주세요.(엔터를 치면 저장됩니다.)\n\n");

		Scanner scan = new Scanner(System.in);

		System.out.print("\t▶ 제목: ");
		String title = scan.nextLine();

		System.out.print("\t▶ 내용: ");
		String text = scan.nextLine();

		LocalDate now = LocalDate.now();
		
		if( Main.currentUser == null) {
			System.out.println("\t▶ 학생만 작성할 수 있습니다.");
			DrawUtils.pause();
			return;
		}
		String id = Main.currentUser.getId();

		date += now;
		Suggest s = new Suggest(no, date, id, title, text);
		Data.suggestList.add(s); // 회원 추가하기
		Data.saveSuggestList(); // 파일에 저장도 해라 
		
		SuggestService.list("<  건의사항  >", 0);


	}// add
	
	
	
	/**
	 * <h1>글을 내용을 수정하기 위한 메소드</h1>
	 * 
	 * {@link SuggestService#checkUser(int, String)} 메소드를 통해 선택한 글의 글쓴이와 현재 로그인 유저의 아이디가 같은지 확인하고 비밀번호를 입력받는다.<br><br>
	 * {@link SuggestService#checkPW(String)}메소드를 통해 비밀번호 비교를 하고 비밀번호가 일치하면 수정을 원하는 글의 본문 내용을 새로 입력받아서 다시 저장한다.<br><br>
	 * 		-> 비밀번호가 틀릴 시 수정이 불가하다.
	 * 
	 * 
	 *   
	 * @param num 수정할 글 번호
	 */
	private static void edit(String num) {

		int curIdx = Integer.parseInt(num);
		DrawUtils.printTop("수정하기");

		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t정말 수정하시겠습니까?\n\n\n\n\n");
		System.out.println("\t [ 취소 : 0 ]\t\t\t\t\t[ 확인 : 1 ]");
		System.out.println("\t==================================================================");
		System.out.print("\t▶ 원하는 항목을 선택해주세요 : ");
		String n = scan.nextLine();


		if (n.equals("0")) {
			SuggestService.list("<  건의사항  >", 0);
			
		} else if (n.equals("1")) {

			
			
			if (SuggestService.checkUser(curIdx, "수정") == true) {
				
				DrawUtils.printTop("수정하기");
				System.out.println("\n\n\n\n\t\t수정할 내용을 입력해주세요.(엔터를 치면 저장됩니다.)\n\n\t\t");
				
				String temp = scan.nextLine();
			
				for (Suggest s : Data.suggestList) {
					if (s.getIndex().equals(num)) {
						s.setText(temp);
						Data.saveSuggestList(); 
						break;
					}
				}

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("\t\t\t   --------------------------");
				System.out.println("\t\t\t   |\t  수정되었습니다!\t   |");
				System.out.println("\t\t\t   --------------------------");
				DrawUtils.pause();
				System.out.println();
				System.out.println();
				System.out.println();
				SuggestService.list("<  건의사항  >", 0);
				
			} else {
				SuggestService.list("<  건의사항  >", 0);
			}

			
		}

	}// edit
	
	
	/**
	 * <h1>원하는 건의사항 글을 삭제하기 위한 메소드</h1>
	 * {@link SuggestService#checkUser(int, String)}메소드를 통해 선택한 글의 글쓴이와 현재 로그인 유저의 아이디가 같은지 확인하고 비밀번호를 입력받는다.<br>
	 * 비밀번호가 일치하면 해당 글을 삭제한다.<br>
	 * 	 비밀번호가 틀릴 시 삭제가 불가하다.<br><br>
	 * 삭제 완료 후에는 리스트에 남아있는 글들의 인덱스를 다시 정렬한다.
	 * 
	 * 
	 * @param num 삭제하고 싶은 글의 번호
	 */
	public static void delete(String num) {

		int curIdx = Integer.parseInt(num);
		DrawUtils.printTop("삭제하기");

		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t정말 삭제하시겠습니까?\n\n\n\n\n");
		System.out.println("\t [ 취소 : 0 ]\t\t\t\t\t[ 확인 : 1 ]");
		System.out.println("\t==================================================================");
		
		
		Scanner scan = new Scanner(System.in);
		System.out.print("\t▶ 원하는 항목을 선택해주세요 : ");
		String n = scan.nextLine();

		if (n.equals("0")) {
			SuggestService.list("<  건의사항  >", 0);
		} else if (n.equals("1")) {
			
			
			if (SuggestService.checkUser(curIdx, "삭제") == true) {
				for (Suggest s : Data.suggestList) {
	
					if (s.getIndex().equals(num)) {
						Data.suggestList.remove(s); 
						Data.saveSuggestList(); 
						break;
					}
	
				}
	
				for (Suggest s : Data.suggestList) {
	
					int idx = Integer.parseInt(s.getIndex());
					if (idx > Integer.parseInt(num)) {
						idx -= 1;
						String temp = Integer.toString(idx);
						s.setIndex(temp);
					}
				}
				
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("\t\t\t   --------------------------");
				System.out.println("\t\t\t   |\t  삭제되었습니다!\t   |");
				System.out.println("\t\t\t   --------------------------");
	
				System.out.println();
				System.out.println();
				System.out.println();
				SuggestService.list("<  건의사항  >", 0);
				
			} else {
				SuggestService.list("<  건의사항  >", 0);
			}
		}

	}// delete
	
	
	public static String checkLength(String text, int length) {
		String result="";
		if ( text.length()> length ) { // 아무튼 글자가 길다는 얘기..
			result = text.substring(0, length-3)+"..";
			return result;
		} else
			return text;
	}
	
	/**
	 * <h1>게시판 글 목록을 출력할 때 제목을 적당한 길이로 잘라주는 메소드</h1>
	 * 게시판 글 목록 출력 시 제목이 긴 글은 양식에 맞춰지지 않고 출력된다.<br>
	 * 제목이 일정 길이보다 긴 경우 그 뒤 내용은 출력하지 않고 "..."를 붙여 출력한다.
	 * 
	 * @param text 게시판 글의 제목
	 * @return 글자 수 제한된 제목
	 */
	public static String titleTrim(String text) {
		String result = "";
		if (text.length() > 15) {
			result = text.substring(0, 15) + "...";
			return result;
		} else
			return text;
	}
	

	/**
	 * <h1>현재 로그인 유저와 선택한 건의사항의 작성자 ID를 비교하는 메소드</h1>
	 * 현재 로그인 유저와 선택한 글의 작성자의 ID를 비교하여 일치 여부를 T/F로 리턴한다.
	 * 
	 * @param num 선택한 글의 번호
	 * @return ID 일치 여부
	 */
	private static boolean checkID(int num) {

		
		Suggest s = Data.suggestList.get(num-1);
		if ( Main.currentUser != null && Main.currentUser.getId().equals(s.getId())) {
			return true;			
		} else {
			return false;
		}
		
	}
	
	/**
	 * <h1>패스워드를 입력받아 현재 로그인 계정 비밀번호와의 일치 여부를 알아보는 메소드</h1>
	 * 패스워드 String을 입력받은 뒤 현재 로그인 계정의 비밀번호와 같은지 확인하고 일치여부를 T/F로 리턴한다.
	 * 
	 * @param pw 입력받은 패스워드
	 * @return 패스워드 일치 여부
	 */
	private static boolean checkPW(String pw) {

		
		
		if (Main.currentUser.getPw().equals(pw)) {
			return true;			
		} else {
			return false;
		}
		
	}
	
	/**
	 * <h1>선택한 글의 작성자 ID와 현재 로그인 유저의 ID를 비교하여 비밀번호를 입력받는 메소드</h1>
	 * 
	 * 
	 * @param curIdx 선택한 글의 번호
	 * @param mode	현재 모드(삭제,수정)
	 * @return 비밀번호 입력 성공하면 TRUE, 비밀번호나 ID 일치하지 않을시엔 FALSE
	 */
	private static boolean checkUser(int curIdx, String mode) {
		
		Scanner scan = new Scanner(System.in);
		if (SuggestService.checkID(curIdx) == true) {
			System.out.println("\n\t==================================================================\n");
			System.out.print("\t▶ 비밀번호를 입력해주세요 :");
			String pw = scan.nextLine();
			
			if (SuggestService.checkPW(pw) == false ) {
				System.out.println("\n\t==================================================================\n");
				System.out.print("\t잘못된 비밀번호를 입력했습니다.");
				return false;
			} else {
				return true;			
			}
			
		} else {
			System.out.println("\n\t==================================================================\n");
			System.out.printf("\n\n\n\t게시글은 작성자만 %s할 수 있습니다.\n", mode);
			
			System.out.println();
			System.out.println("\t▶ 계속하시려면 [엔터]를 입력하세요.");
			System.out.println();
			scan.nextLine();

			return false;
		}
	}
}
