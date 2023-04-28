package com.project.eruda.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.project.eruda.Main;
import com.project.eruda.data.*;
import com.project.eruda.view.DrawUtils;
import com.project.eruda.view.Menu;

/**
 * 수업관리에 관한 서비스  강사는 퀴즈 업로드
 * @author 5조
 *
 */
public class CourseService {

	public static void quizUpload() {
		
		//남궁성■(디지털컨버전스)자바 기반 AWS 클라우드 활용 Full-Stack 개발자 양성 과정■1■2023-01-26■2023-07-11■30■웹에 대한 모든걸 가르친다.
		
		
		System.out.println("▶ 수업관리 페이지입니다. ");
		DrawUtils.printMiddelLine();
		
		Menu.printQuizMenu();
		
		try {
			Scanner scan = new Scanner(System.in);
			BufferedReader reader = new BufferedReader(new FileReader(Data.LECTURE));
			String line = null;
			String course="";
			boolean isCourse = false;
			
			while ( (line = reader.readLine() ) != null) {
				
				String[] temp = line.split("■");
				String name = temp[0];
				
				if ( Main.currentAdmin.getName().equals(name) ) {
					isCourse=true;
					DrawUtils.centerAlignText("[ 강사 : "+name+" ]");
					DrawUtils.centerAlignText("[ 과목 : "+temp[1]+" ]");
					DrawUtils.centerAlignText("[ 기간 : ("+temp[3] +") ~ "+"("+temp[4]+") ]");
					
					System.out.println();
					int i=1;
					for(Quiz q : Data.quizList ) {
						DrawUtils.centerAlignText("[ 입력된 퀴즈 파일 "+i++ +":"+q.getQuizFileName()+" ]");
					}
					
					DrawUtils.centerAlignText("[1. 퀴즈 업로드하기, 2. 퀴즈 파일 삭제하기, 3. 제출된 퀴즈 조회, 4.뒤로가기 ]");

					Menu.printSelect();
					String sel = scan.nextLine();
					if(sel.equals("1")) {
						uploadQuizFile();
					} else if(sel.equals("2")) {
						System.out.print("▶ 삭제할 파일 번호를 입력하시오. :");
						sel = scan.nextLine();	
						Data.quizList.remove(Integer.parseInt(sel)-1);
						Data.saveQuizList();
					} else if(sel.equals("3")) {
						readQuizFile(name);
					} else if(sel.equals("4")) {
						// 나가기
					}
					
					break;
				}
			}
			
			if(!isCourse) {
				System.out.println();
				System.out.println();
				DrawUtils.centerAlignText("[ 예정된 수업이 없습니다. ]");
				System.out.println();
				System.out.println();
			}
			
			DrawUtils.pause();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 현제 제출한 퀴즈 확인
	 * @param teacher
	 */
	private static void readQuizFile(String teacher) {
		
		
		
		Scanner scan = new Scanner(System.in);
		
		try {
			
			String line = null;
			int cnt =0;
			for(Quiz q: Data.quizList) {
				if( q.getTeacher().equals(teacher) ) {
					cnt++;
					BufferedReader reader = new BufferedReader(new FileReader(q.getQuizFileName()));
					String text ="";
					while ( (line = reader.readLine() ) != null) {
						text+=line+"\n";
					}
					
					System.out.println("▶ 파일명 : " +q.getQuizFileName());
					System.out.println(text);
					System.out.println("[ 정답 ] : "+q.getAnswer());
					System.out.println();
					
				}
			}
				
			if( cnt==0 ) {
				System.out.println("▶ 업로드된 퀴즈가 없습니다.");
			}	
			
		} catch (Exception e) {
			
			
		}
		
		
		
	}

	/**
	 * 퀴즈 업로드
	 */
	public static void uploadQuizFile() {
		Scanner scan = new Scanner(System.in);
		
		while( true ) {
			
			System.out.print("▶ 업로드할 파일명을 입력하시오.( 뒤로가기: -1 ) :");
			String fName=scan.nextLine();
		
			
			if( fName.equals("-1")) {
				break;
			} else {
				File f1 = new File(fName);
				if(!f1.exists()) {
					System.out.println("▶ 파일이 존재하지 않습니다.");
				} else {
					
					System.out.print("▶ 정답을 입력하세요. : ");
					String answer = scan.nextLine();
					
					System.out.println("▶ 해설지를 입력하세요. 없으면 null 입력 :");
					String solution=scan.nextLine();
					File f2 = new File(solution);
					if( !solution.equals("null") && !f2.exists()) {
						System.out.println("▶ 파일이 존재하지 않습니다. ");
						continue;
					}
					Quiz q = new Quiz(Main.currentAdmin.getName(), fName, answer, solution);
					Data.quizList.add(q);
					Data.saveQuizList();
					break;
					
				}
			}
			
		}
		
	
	}
	
	/**
	 * 현재 강사가 진행중인 강의 조회
	 */
	public static void readCourse() {
	
		Scanner scan = new Scanner(System.in);
		System.out.println("▶ 수업 정보 페이지입니다. ");
		DrawUtils.printEndLine();
		Menu.printQuizMenu();

		System.out.println();
		if ( !Main.currentUser.getCourse().equals("null")) {
			DrawUtils.centerAlignText("[ 나의 수업 : "+Main.currentUser.getCourse()+" ]");
			
			
			// 1. member.txt의 강의 시작날짜 찾기 -> 2. lecture.txt에서 시작날짜 일치하는 행 찾기 >> 회차 정보, 강의 설명열람
			// print information
			String teacher = printCourseInformation();
			
			DrawUtils.printMiddelLine();
			DrawUtils.centerAlignText("[1. 퀴즈 응시하기, 2. 뒤로가기]");
			
			Menu.printSelect();
			String sel = scan.nextLine();

			
			if(sel.equals("1")) {
				//quiz
				examQuiz(teacher);
				DrawUtils.pause();
			}
			else {
				// 뒤로가기
			}
			
		}
		else {
			
			 DrawUtils.centerAlignText("▶ 조회된 수업이 없습니다. ");
			 DrawUtils.pause();
			 return ;
		}
		
	} // 제일 나중에
	
	
	
	/**
	 * 강의 정보 출력
	 * @return 선생 정보 찾아서 출력
	 */
	public static String printCourseInformation() {
		
		String teacher="";
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(Data.LECTURE));
			
			String line = null;
			
			for(Lecture l : Data.lectureList) {
				if(l.getStartDate().equals(Main.currentUser.getStart())) {
					
					DrawUtils.centerAlignText("[ 훈련 기간 : ("+l.getStartDate() +") ~ "+"("+l.getEndDate()+") ]");
					DrawUtils.centerAlignText("[ 담당 강사 : "+l.getInstructor()+" ]");
					DrawUtils.centerAlignText("[ 수강 인원 : "+courseNum(l)+" ]");
					System.out.println();
					teacher = l.getInstructor();
					break;
				}
					
			}
			
			reader.close();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return teacher;
	}
	
	/**
	 * 강의번호출력
	 * @param lec 강의정보 객체
	 * @return 강의번호 리턴
	 */
	public static String courseNum(Lecture lec) {
		int cnt=0;
		for(User u : Data.userList) {
			if(u.getStart().equals(lec.getStartDate())) {
				cnt++;
			}
		}
		return cnt+"";
	}
	
	/**
	 * 시험에 응시하고 정답 입력
	 * @param teacher 퀴즈를 응시하는 선생정보 입력
	 */
	public static void examQuiz(String teacher) {
		
		Scanner scan = new Scanner(System.in);
		
		try {
			String line = null;
			int cnt =0;
			int answer = 0;
			for(Quiz q: Data.quizList) {
				if( q.getTeacher().equals(teacher) ) {
					cnt++;
					BufferedReader reader = new BufferedReader(new FileReader(q.getQuizFileName()));
					String text ="";
					while ( (line = reader.readLine() ) != null) {
						text+=line+"\n";
					}
					System.out.println(text);
					
					System.out.print("정답 입력: > ");
					String sel = scan.nextLine();
					if(sel.equals(q.getAnswer())) {
						System.out.println("▶ 정답입니다.\n");
						answer++;
					} else {
						System.out.println("▶ 틀렸습니다.\n");
						if(q.getSolution().equals("null")) {
							DrawUtils.pause();
							continue;
						}
						reader = new BufferedReader(new FileReader(q.getSolution()));
						text ="";
						while ( (line = reader.readLine() ) != null) {
							text+=line+"\n";
						}
						System.out.println("▶ 해설 : "+text);
						DrawUtils.pause();
					}
				}
					
			}
			
			if(cnt>0) {
				System.out.printf("[ %d개 중에 %d개 맞았습니다. ]\n",cnt, answer );
			} else if( cnt==0 ) {
				System.out.println("▶ 업로드된 퀴즈가 없습니다.");
			}
		
			
			
		} catch (Exception e) {
			
			
		}
	}
}


