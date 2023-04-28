package com.project.eruda.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.project.eruda.data.Data;
import com.project.eruda.data.Lecture;
import com.project.eruda.data.Question;
import com.project.eruda.data.User;
import com.project.eruda.view.*;


/**
 * 
 * @author 5조
 * 학생 정보를 조회한다. 
 */
public class StudentManageService {

	public static void readMyStudent(Lecture lec) {
		
		int cnt=0;
		int i=1;
		Scanner scan = new Scanner(System.in);
		System.out.printf("[ %2s\t%-12s\t%-4s\t%2s\t%-10s]\n","번호","아이디","이름","성별","생년월일");

		for(User u : Data.userList) {
			if(lec!=null && u.getStart().equals(lec.getStartDate())) {
				cnt++;
				System.out.printf("> %2s\t%-12s\t%-4s\t%2s\t%-10s\n",i++, u.getId(), u.getName().trim(), u.getGender(), u.getAge());
			}
		} 
		DrawUtils.printMiddelLine();
		System.out.printf("▶ %d 명이 검색되었어요.\n", cnt);
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println();
			System.out.println("▶ 열람할 학생 번호를 입력하세요.( 뒤로가기 -1, 다시조회하기 -2) ");
			Menu.printSelect();
			String sel = scan.nextLine();
			int selInt = Integer.parseInt(sel);
			
			if (sel.equals("-1"))
				loop = false;
			else if (sel.equals("-2")) {
				readMyStudent(lec);
				break;
			} else if( selInt > 0 && selInt <=cnt) {
				User u = Data.userList.get(selInt-1);
				System.out.println("[이름] : "+u.getName());
				System.out.println("[성별] : "+u.getGender());
				System.out.println("[생년월일] : "+u.getAge());
				System.out.println("[전화번호] : "+u.getPhoneNumber());
				System.out.println("[이메일] : "+u.getEmail());
				System.out.println("[주소] : "+u.getAddress());
				System.out.println("[대학교] : "+Menu.nullCheck(u.getUniv()));
				System.out.println("[전공] : "+Menu.nullCheck(u.getMajor()));
				System.out.println("[과정이름] : "+Menu.nullCheck(u.getCourse()));
				System.out.println("[훈련시작] : "+Menu.nullCheck(u.getStart()));
				System.out.println("[훈련종료] : "+Menu.nullCheck(u.getEnd()));
				System.out.println("[취업여부] : "+Menu.nullCheck(u.getCompany()));
			}
		}

		
			
	}
	
	/**
	 * 전체 학생 정보를 조회한다.
	 * @param choice  정렬모드를 선택한다. choice가 1이면 나이순 정렬
	 */
	public static void readAllMember(int choice) {
		
	    try {
	    	
	    	DrawUtils.printStartLine();
			BufferedReader reader = new BufferedReader(new FileReader(Data.USER));
			String line = null;
		
	

				    	
			Scanner scan = new Scanner(System.in);
			boolean loop = true;
			
			while (loop) {
				
				int cnt=0;
				int i=1;
				System.out.printf("[ %2s\t%-12s\t%-4s\t%2s\t%-10s]\n","번호","아이디","이름","성별","생년월일");

				ArrayList<User> choicedList = choiceList(choice);
				
				for(User u : choicedList) {
					cnt++;
					System.out.printf(" %2s\t%-12s\t%-4s\t%2s\t%-10s\n",i++, u.getId(), u.getName().trim(), u.getGender(), u.getAge());
				} 

				DrawUtils.printMiddelLine();
				System.out.printf("▶ %d 명이 검색되었어요.\n", cnt);
				
				
				
				System.out.println();
				System.out.println("▶ 열람할 학생 번호를 입력하세요.( 뒤로가기 -1, 다시조회하기 -2, 나이순 정렬 -3, 수정하기 -4 ) ");
				Menu.printSelect();
				String sel = scan.nextLine();
				int selInt = Integer.parseInt(sel);
				
				if (sel.equals("-1"))
					loop = false;
				else if (sel.equals("-2")) {
					
					readAllMember(0);
					break;
					
				}else if (sel.equals("-3")) {
					readAllMember(1);
					break;
					
				}  else if (sel.equals("-4")){
					
					System.out.println("▶ 수정할 학생의 번호를 입력하세요. ");
					Menu.printSelect();
					String num = scan.nextLine();
					selInt = Integer.parseInt(num);
					User u = choicedList.get(selInt-1);
					System.out.println("[이름] : "+u.getName());
					System.out.println("[성별] : "+u.getGender());
					System.out.println("[생년월일] : "+u.getAge());
					System.out.println("[전화번호] : "+u.getPhoneNumber());
					System.out.println("[이메일] : "+u.getEmail());
					System.out.println("[주소] : "+u.getAddress());
					System.out.println("[대학교] : "+Menu.nullCheck(u.getUniv()));
					System.out.println("[전공] : "+Menu.nullCheck(u.getMajor()));	
					System.out.println("[담당강사] : " + Menu.nullCheck(getTeacherName(u.getStart())));
					System.out.println("[1. 과정 ] : "+Menu.nullCheck(u.getCourse()));
					System.out.println("[2. 훈련기간] : "+Menu.nullCheck(u.getStart())+"~"+Menu.nullCheck(u.getEnd()));
					System.out.println("[3. 취업여부] : "+Menu.nullCheck(u.getCompany()));
					//System.out.println("[3. 훈련종료] : "+);
					System.out.println("▶ 수정할 항목을 선택 하세요. ( -1: 취소하기 )");
					Menu.printSelect();
					sel = scan.nextLine();
					User u2 = choicedList.get(selInt-1);
					String rename="";
					switch (sel) {
					case "-1":
						System.out.println("▶ 수정을 취소합니다. ");
						break;

					case "1":
					case "2":
						// 현재 열린 과정 나열
						scan = new Scanner(System.in);
						System.out.println("▶ 과정 및 훈련기간을 선택하셨습니다.");
						int j=1;
						for(Lecture l : Data.lectureList) {
							System.out.printf("→ %d 강사: %s, 기간 : %s ~ %s %s\n",
									j++, l.getInstructor(), l.getStartDate(), l.getEndDate(), l.getCourseTitle());
						}
						
						System.out.print("▶ 원하시는 강좌의 번호를 선택하세요. : "); 
						sel = scan.nextLine().trim();
						
						
						Lecture lec = Data.lectureList.get(Integer.parseInt(sel)-1);
						u2.setCourse(lec.getCourseTitle());// 3개 변경
						u2.setStart(lec.getStartDate());
						u2.setEnd(lec.getEndDate());
						
						Data.saveUserList(); //원본을 저장
						System.out.print("▶ 정보를 변경하였습니다. : "); 
						break;
					case "3":
						System.out.print("▶ 변경할 내용을 입력하세요. ");
						System.out.print("[3. 취업여부 ] : "); 
						rename = scan.nextLine().trim();
						u2.setCompany(rename);
						Data.saveUserList();
						break;
					}
					
					
				} else if( selInt > 0 && selInt <=cnt) {
					User u = choicedList.get(selInt-1);
					System.out.println("[이름] : "+u.getName());
					System.out.println("[성별] : "+u.getGender());
					System.out.println("[생년월일] : "+u.getAge());
					System.out.println("[전화번호] : "+u.getPhoneNumber());
					System.out.println("[이메일] : "+u.getEmail());
					System.out.println("[주소] : "+u.getAddress());
					System.out.println("[대학교] : "+Menu.nullCheck(u.getUniv()));
					System.out.println("[전공] : "+Menu.nullCheck(u.getMajor()));	
					System.out.println("[담당강사] : " + Menu.nullCheck(getTeacherName(u.getStart())));
					System.out.println("[과정이름] : "+Menu.nullCheck(u.getCourse()));
					System.out.println("[훈련시작] : "+Menu.nullCheck(u.getStart()));
					System.out.println("[훈련종료] : "+Menu.nullCheck(u.getEnd()));
					System.out.println("[취업여부] : "+Menu.nullCheck(u.getCompany()));
					DrawUtils.pause();
				
				}
			}

				  reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * @param choice > 정렬모드를 선택한다. choice가 1이면 나이순 정렬
	 * @return 정렬된 리스트를 반환
	 */
	private static ArrayList<User> choiceList(int choice) {
		// 정렬 기능 추가 > ArrayList를 Deep copy? 굳이 필여 없을듯
		if (choice == 1) {
			ArrayList<User> sortedList = new ArrayList<User>(); 
	
			sortedList = (ArrayList<User>) Data.userList.clone();
			
			sortedList.sort(new Comparator<User>() { ///////////////
				@Override
				public int compare(User o1, User o2) {
					try {
						SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
						Date date = new Date(dateFormat.parse(o1.getAge()).getTime()); 
						Date today= new Date(dateFormat.parse(o2.getAge()).getTime());
						return date.compareTo(today);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return 0;
				}
			});
			return sortedList;
		}
			else 
				return Data.userList; 
		
	} 
	     
	/**
	 * 강사이름을 찾아서 반환
	 * @param 훈련시작날짜로 강의정보를 조회해서 강사이름을 찾아냄
	 * @return 강사이름 반환
	 */
	private static String getTeacherName(String startDate) {
		String teacher="null";
	    try {
	    	
			BufferedReader reader = new BufferedReader(new FileReader(Data.LECTURE));
			String line= null;
			
			while( (line=reader.readLine())!=null) {
				//0123
				String[] temp = line.split("■"); 
				if(startDate.equals(temp[3]))
					return temp[0];
			}
			
			reader.close();
	    }
	    catch(Exception e) {
	    	
	    }
	    return teacher;
	}
	
}
