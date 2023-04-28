package com.project.eruda.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.eruda.Main;
import com.project.eruda.view.DrawUtils;
import com.project.eruda.view.Menu;
import com.project.eruda.data.*;

/**
 * 관리자 계정용 기능 수행 클래스입니다.
 * @author 남건욱, 조연우
 */

enum WHO {
	TEACHER, MANAGER, OPERATOR
}


/**
 * 관리자 기능을 구현 
 * @author 5조
 *
 */
public class AdminService {
	
	/**
	 * 관리자 계정 홈화면 메소드입니다.
	 * 계정의 종류를 검사하고, 해당되는 메뉴를 출력합니다.
	 */
	public static void welcomePage() {

		Scanner scan = new Scanner(System.in);
		boolean loop = true;

		WHO position = WHO.TEACHER; //
		
		/**
		 * 직위에 따라 페이지 구분
		 */
		if (Main.currentAdmin.getPosition().equals("강사")) {
			position = WHO.TEACHER;
		} else if (Main.currentAdmin.getPosition().equals("매니저")) {
			position = WHO.MANAGER;
		} else if (Main.currentAdmin.getPosition().equals("운영자")) {
			position = WHO.OPERATOR;
		} else {

		}

		boolean logout = false;

		while (loop) {

			ArrayList<Lecture> allLectures = getAllLectures();

			if (position == WHO.TEACHER) {

				Menu.printInstructorMenu();
				Menu.printSelect();
				String sel = scan.nextLine();
				Lecture lecture = getLecture();

				switch (sel) {

				case "1":
					// 수업관리
					boolean b = true;
					while (b) {

						if (lecture == null) {
							System.out.println();
							System.out.println();
							DrawUtils.centerAlignText("[ 진행중인 수업이 없습니다. ]");
							System.out.println();
							System.out.println();
							DrawUtils.pause();
							break;
						}
						Menu.managingClass(lecture);
						Menu.printSelect();
						String selected = scan.nextLine();
						switch (selected) {
						///////// 학생-강의 연계 데이터 필요 기능들
						case "0":
							b = false;
							break;
						case "1":
							CourseService.quizUpload();
							break;

						default:
						}
					}
					break;
				case "2":
					// 내 수업 듣는 학생들 정보
					StudentManageService.readMyStudent(lecture);
					break;
				case "3":
					// 공지사항
					NoticeService.list("<  공지사항  >", 0);
					break;
				case "4":
					// 건의게시판
					SuggestService.list("<  건의사항  >", 0);
					break;
				case "5":
					// 질문게시판
					QboardService.qboard();
					break;
				case "6":
					// 로그아웃
					logout = true;
					System.out.println("▶ 로그아웃 되었습니다.");
					break;

				}
				if (logout) {
					Main.loginStatus = false;
					Main.currentAdmin = null;
					break;
				}

			} else if (position == WHO.MANAGER) {

				Menu.printManagerMenu();
				Menu.printSelect();
				String sel = scan.nextLine();

				switch (sel) {
				case "1":
					// 강의기록 열람 >> 전체 강의기록을 열람한다.
					previousLectures(allLectures);
					break;
				case "2": // ▶ 요거랑
					// 학생기록 열람
					StudentManageService.readAllMember(0);
					break;
				case "3":
					// 공지사항
					NoticeService.list("<  공지사항  >", 0);
					break;
				case "4":
					// 건의게시판
					SuggestService.list("<  건의사항  >", 0);
					break;
				case "5":
					// 로그아웃
					logout = true;
					System.out.println("▶ 로그아웃 되었습니다.");
					break;
				}

				if (logout) { // Main에서 가리키던 참조변수를 초기화
					Main.loginStatus = false;
					Main.currentAdmin = null;
					break;
				}

			} else if (position == WHO.OPERATOR) {

				Menu.printOperatorMenu();
				Menu.printSelect();
				String sel = scan.nextLine();

				switch (sel) {
				case "1":
					AdminService.manageTeacherAndManager();
					// System.out.println("강사 및 매니저 관리"); //
					break;

				case "2":
					// 강의기록 열람
					AdminService.manageLectures();
					System.out.println("강의기록");
					break;

				case "3":
					// 학생기록 열람 ▶ 요거
					StudentManageService.readAllMember(0);
					break;

				case "4":
					// 공지사항
					NoticeService.list("<  공지사항  >", 0);
					break;
				case "5":
					// 건의게시판
					SuggestService.list("<  건의사항  >", 0);
					break;

				case "6":
					// 로그아웃
					logout = true;
					System.out.println("▶ 로그아웃 되었습니다.");
					break;
				}

				if (logout) { // Main에서 가리키던 참조변수를 초기화
					Main.loginStatus = false;
					Main.currentAdmin = null;
					break;
				}
			}
		}
	}
	/**
	 * 강사 및 매니저 정보 관리
	 */
	private static void manageTeacherAndManager() {

		boolean loop = true;
		while (loop) {
			Scanner scan = new Scanner(System.in);
			System.out.println();
			DrawUtils.printStartLine();
			System.out.printf("[ %2s\t%4s\t%-3s\t%-12s\t%-12s\t%-17s]\n", "번호", "이름", "직책", "생년월일", "전화번호", "이메일");

			int Mcnt = 0;
			int Icnt = 0;
			int cnt = 1;
			for (Admin adm : Data.adminList) {
				if (!adm.getPosition().equals("운영자")) {
					System.out.printf(" %2s\t%4s\t%-3s\t%-12s\t%-12s\t%-10s\n", cnt++, adm.getName(),
							adm.getPosition().trim(), adm.getBirth(), adm.getPhone(), adm.getEmail());

					if (adm.getPosition().equals("강사"))
						Icnt++;

					else if (adm.getPosition().equals("매니저"))
						Mcnt++;
				}
			}
			System.out.println();
			DrawUtils.centerAlignText("[ 1. 정보 열람하기, 2. 강사 및 매니저 추가하기, (뒤로가기 : -1 ) ]");
			Menu.printSelect();
			String sel = scan.nextLine();
			if (sel.equals("-1")) { // 뒤로가기
				loop = false; // 루프 종료
			} else if (sel.equals("1")) { // 강사 정보확인하기
				System.out.print("▶ 번호를 입력하세요. : ");
				sel = scan.nextLine();
				int selInt = Integer.parseInt(sel);
				Admin adm=null;
				int i = 0;
				for (Admin temp : Data.adminList) {
					if (!temp.getPosition().equals("운영자")) {
						i++;
					}
					if(i==selInt) {
						adm=temp;
					}
						
				}
				
				if(adm == null) break;
				
				// admin_O3■0000■남림아■운영자■2003-02-09■011-974-9465■RxVy@hotmail.com■경기도 정대시
				// 차주동■추계예술대학교■일어일문과
				System.out.println(" ▣ 아아디  \t: " + adm.getId());
				System.out.println(" ▣ 이 름  \t: " + adm.getName());
				System.out.println(" ▣ 직 책  \t: " + adm.getPosition());
				System.out.println(" ▣ 생년월일\t: " + adm.getBirth());
				System.out.println(" ▣ 전화번호\t: " + adm.getPhone());
				System.out.println(" ▣ 이메일  \t: " + adm.getEmail());
				System.out.println(" ▣ 주 소  \t: " + adm.getAddress());
				System.out.println(" ▣ 대 학  \t: " + Menu.nullCheck(adm.getUniv()));
				System.out.println(" ▣ 전 공  \t: " + Menu.nullCheck(adm.getMajor()));
				boolean exist = false;

				for (Lecture lec : Data.lectureList) {
					if (lec.getInstructor().equals(adm.getName())) {
						System.out.println(" ▣ 담당강의  \t: " + lec.getCourseTitle());
						System.out.println(" ▣ 훈련기간  \t: " + lec.getStartDate() + " ~ " + lec.getEndDate());
						exist = true;
					}
				}

				if (adm.getPosition().equals("강사")) {
					if (!exist) {
						System.out.println(" ▣ 담당강의  \t: 진행중인 강의가 없습니다.");
					}
				}

				DrawUtils.pause();

			} else if (sel.equals("2")) {// 강사 추가하기
				// 추가 성공
				// admin_O3■0000■남림아■운영자■2003-02-09■011-974-9465■RxVy@hotmail.com■경기도 정대시
				// 차주동■추계예술대학교■일어일문과

				System.out.println("▶ 이름을 입력하시오, 취소: -1");
				System.out.print("[ 이름 ] : ");
				String name = RegisterService.inputName();
				if (name == null) {
					System.out.println("▶ 입력이 취소되었습니다. ");
					DrawUtils.pause();
					break;
				}

				System.out.println("▶ 직책을 입력하시오, '강사' or '매니저', 취소: -1");
				System.out.print("[ 직책 ] : ");
				String position = inputPosition();
				if (position == null) {
					System.out.println("▶ 입력이 취소되었습니다. ");
					DrawUtils.pause();
					break;
				}

				System.out.println("▶ 생년월일을 '-'를 포함하여 ex(1997-10-19)와 같이 입력, 취소: -1");
				System.out.print("[ 생년월일 ] : ");
				String birth = RegisterService.inputAge();
				if (birth == null) {
					System.out.println("▶ 입력이 취소되었습니다. ");
					DrawUtils.pause();
					break;
				}

				System.out.println("▶ '-' 를 포함하여 숫자 12~13자 입력, 취소: -1");
				System.out.print("[ 전화번호 ] : ");
				String phone = RegisterService.inputPhoneNumber();
				if (phone == null) {
					System.out.println("▶ 입력이 취소되었습니다. ");
					DrawUtils.pause();
					break;
				}

				System.out.println("▶ '@' 를 포함하여 입력, 취소: -1");
				System.out.print("[ 이메일 ] : ");
				String email = RegisterService.inputEmail();
				if (email == null) {
					System.out.println("▶ 입력이 취소되었습니다. ");
					DrawUtils.pause();
					break;
				}

				System.out.println("▶ 30 글자 이내 입력, 취소: -1");
				System.out.print("[ 주소 ] : ");
				String address = RegisterService.inputAddress();
				if (address == null) {
					System.out.println("▶ 입력이 취소되었습니다. ");
					DrawUtils.pause();
					break;
				}

				System.out.println("▶ 미입력을 원하면 null 입력, 취소: -1");
				System.out.print("[ 대학 ] : ");
				String univ = scan.nextLine();

				if (univ.equals("-1")) {
					System.out.println("▶ 입력이 취소되었습니다. ");
					DrawUtils.pause();
					break;
				}
				System.out.println("▶ 미입력을 원하면 null 입력, 취소: -1");
				System.out.print("[ 전공 ] : ");
				String major = scan.nextLine();

				if (major.equals("-1")) {
					System.out.println("▶ 입력이 취소되었습니다. ");
					DrawUtils.pause();
					break;
				}

				String id = "";
				if (position.equals("강사")) {
					id = "admin_T" + (Icnt + 1);
				} else if (position.equals("매니저")) {
					id = "admin_M" + (Mcnt + 1);
				}
				Admin newAdmin = new Admin(id, "0000", name, position, birth, phone, email, address, univ, major);
				Data.adminList.add(newAdmin);
				Data.saveAdminAppend(newAdmin);

				System.out.println("\n▶ 아이디 : " + id + ", 비밀번호 : 0000 으로 계정이 발급되었습니다.");

				DrawUtils.pause();

			} // 다시 루프 돈다.
		}
	}

	
	/**
	 * 강사 추가시에 직책입력
	 * @return
	 */
	private static String inputPosition() {
		Scanner scan = new Scanner(System.in);
		String position = null;
		while (true) {
			position = scan.nextLine().trim();
			// 1. 현재 수강선생에서 찾기 > 수강중 출력
			if (position.equals("-1")) {
				return null;
			}
			if (position.equals("강사")) {
				return "강사";
			} else if (position.equals("매니저")) {
				return "매니저";
			} else {
				System.out.println("▶ 올바른 값을 입력하시오, '강사' or '매니저', 취소: -1");
			}
		}
	}
	
	/**
	 * 강의정보를 가져옴
	 * @return
	 */
	private static Lecture getLecture() {

		int index = -1;

		for (int i = 0; i < Data.lectureList.size(); i++) {

			boolean isOnGoing = isOnGoing(i);
			// 강사명이 같고, 종강일이 현재 시점보다 미래일 때
			if (Data.lectureList.get(i).getInstructor().equals(Main.currentAdmin.getName()) && isOnGoing) {
				index = i;
				break;
			}
		}
		if (index != -1)
			return Data.lectureList.get(index);
		else
			return null;
	}
	
	/**
	 * 강의개설을 위한 메서드, 현재 수업을 맡지 않은 강사 배정
	 */
	public static void manageLectures() {

		boolean loop = true;

		while (loop) {
			// 전체 강의 열람
			System.out.printf("\n▶ 총 %d개의 강의가 검색되었습니다.\n\n", Data.lectureList.size());
			for (int i = 0; i < Data.lectureList.size(); i++) {
				Lecture l = Data.lectureList.get(i);
				String num = String.valueOf(i + 1);
				String title = l.getCourseTitle();
				String cap = l.getCapacity();
				System.out.println(" [  강사  ] : " + l.getInstructor());
				System.out.println(" [  번 호  ] : " + num);
				System.out.println(" [ 강의이름 ] : " + title);
				System.out.println(" [  정 원  ] : " + cap);
				System.out.println(" [ 수강기간 ] : [" + l.getStartDate() + "] ~ [" + l.getEndDate() + "]");
				System.out.println();
			}

			Scanner scan = new Scanner(System.in);
			DrawUtils.centerAlignText("[ 1. 강의 개설하기, (뒤로가기 : -1 ) ]");
			Menu.printSelect();
			String sel = scan.nextLine();

			if (sel.equals("-1")) {
				loop = false;
			} else if (sel.equals("1")) {

				System.out.println("▶ 현재 근무하는 강사입력, 취소: -1");
				System.out.print("[ 강사명 ] : ");
				String teacher = inputTeacher();
				if (teacher == null) {
					System.out.println("▶ 입력이 취소되었습니다. ");
					DrawUtils.pause();
					break;
				}

				System.out.println("▶ 강의이름을 입력해주세요. (최소 5 글자이상), 취소: -1");
				System.out.print("[ 강의이름 ] : ");
				String title = inputCourseTitle();

				if (title == null) {
					System.out.println("▶ 입력이 취소되었습니다. ");
					DrawUtils.pause();
					break;
				}

				System.out.println("▶ 최대로 수강 가능한 인원을 입력하세요.( 최소 15명 ~ 최대 30명 ), 취소: -1");
				System.out.print("[ 정원 ] : ");
				String capacity = inputCapacity();
				if (capacity == null) {
					System.out.println("▶ 입력이 취소되었습니다. ");
					DrawUtils.pause();
					break;
				}
				System.out.println("▶ 수강기간 입력, 취소: -1");
				System.out.print("[ 시작일자 ] : ");
				String startDate = inputDate(0);

				if (startDate == null) {
					System.out.println("▶ 입력이 취소되었습니다. ");
					DrawUtils.pause();
					break;
				}
				System.out.print("[ 종료일자 ] : ");
				String endDate = inputDate(1);

				if (endDate == null) {
					System.out.println("▶ 입력이 취소되었습니다. ");
					DrawUtils.pause();
					break;
				}

				System.out.println("▶ 강의 소개 및 설명(최소 15 글자이상), 취소: -1");
				System.out.print("[ 강의설명 ] : ");
				String desc = inputDesc();
				if (desc == null) {
					System.out.println("▶ 입력이 취소되었습니다. ");
					DrawUtils.pause();
					break;
				}
				Lecture lec = new Lecture(teacher, title, "1", startDate, endDate, capacity, desc);
				Data.lectureList.add(lec);
				Data.savelectureAppend(lec); // save lecture.txt 파일에 append한다.
			}
		}
	}


	/**
	 * 강의 상세정보 입력 및 유효성 검사
	 * @return
	 */
	private static String inputDesc() {
		Scanner scan = new Scanner(System.in);
		String desc = null;
		while (true) {
			desc = scan.nextLine().trim();
			// 1. 현재 수강선생에서 찾기 > 수강중 출력
			if (desc.equals("-1")) {
				return null;
			}
			if (desc.length() < 15) {
				System.out.println();
				System.out.println("▶ 최소 15글자 이상 입력하세요. ");
				System.out.print("[ 강의설명 ] : ");
				continue;
			} else if (desc.length() >= 15) {
				break;
			}
		}
		return desc;
	}
	
	/**
	 * 강의 수강인원 입력 및 유효성 검사
	 * @return
	 */
	private static String inputCapacity() {
		Scanner scan = new Scanner(System.in);
		String capacity = null;
		while (true) {
			capacity = scan.nextLine().trim();
			// 1. 현재 수강선생에서 찾기 > 수강중 출력
			if (capacity.equals("-1")) {
				return null;
			}
			if (Integer.parseInt(capacity) < 15 || Integer.parseInt(capacity) > 30) {
				System.out.println();
				System.out.println("▶ 최소 15명 ~ 최대 30명을 입력하세요. ");
				System.out.print("[ 정원 ] : ");
				continue;
			} else {
				break;
			}
		}
		return capacity;
	}

	/**
	 * 강의 날짜 입력 및 유효성 검사
	 * @param sel
	 * @return 시간 반환
	 */
	private static String inputDate(int sel) {
		Scanner scan = new Scanner(System.in);
		String date = null;
		while (true) {

			date = scan.nextLine().trim();
			if (date.equals("-1")) {
				return null;
			}
			if (!dateValidate(date)) {
				System.out.println();
				System.out.println("▶ 올바른 날짜를 입력해주세요.");
				if (sel == 0)
					System.out.print("[ 시작일자 ] : ");
				else
					System.out.print("[ 종료일자 ] : ");

				continue;
			} else
				break;
		}
		System.out.println();
		return date;
	}
	
	/**
	 * 
	 * @return 강의제목입력
	 */
	private static String inputCourseTitle() {
		Scanner scan = new Scanner(System.in);
		String title = null;
		while (true) {
			title = scan.nextLine().trim();
			// 1. 현재 수강선생에서 찾기 > 수강중 출력
			if (title.equals("-1")) {
				return null;
			}
			if (title.length() < 5) {
				System.out.println();
				System.out.println("▶ 최소 5글자 이상 입력하세요. ");
				System.out.print("[ 강의이름 ] : ");
				continue;
			} else if (title.length() >= 10) {
				break;
			}
		}
		return title;
	}
	/**
	 * 강의 담당 강사 입력
	 * @return
	 */
	private static String inputTeacher() {
		Scanner scan = new Scanner(System.in);
		String name = null;
		while (true) {
			name = scan.nextLine().trim();
			// 1. 현재 수강선생에서 찾기 > 수강중 출력
			if (name.equals("-1")) {
				return null;
			}
			int result = teacherValidate(name);
			if (result == 0) {
				System.out.println();
				System.out.println("▶ 학원에 존재하지 않는 강사입니다.");
				System.out.println("[ 강사명 ] : ");
				continue;
			} else if (result == 1) {
				System.out.println();
				System.out.println("▶ 이미 진행중인 수업이 있습니다.");
				System.out.println("[ 강사명 ] : ");

			} else if (result == 2) { // 성공
				break;
			}

		}
		System.out.println();
		return name;
	}

	// 이미 존재하면 가능함
	/**
	 * 선생이 이미 admin.txt에 있으면 강의 담당 강사로 등록가능
	 * @param name > 확인할 강사이름
	 * @return > 1. 이미 진행중인 수업 있음 > lecture에 이름있음 > 재입력
	 *         > 2. 이미 진행중인 수업이 없고 admin에 등록된 선생
	 */
	private static int teacherValidate(String name) {
		// 0 출력 > admin에 등록되지 않은 선생 > 재입력
		boolean exist = false;
		for (Admin adm : Data.adminList) {
			if (adm.getName().equals(name)) {
				exist = true;
				break;
			}
		}
		if (exist == false)
			return 0;
		boolean already = false;
		for (Lecture lec : Data.lectureList) {
			if (lec.getInstructor().equals(name)) {
				already = true;
				break;
			}
		}
		if (already == true)
			return 1;
		// 1 출력 > 이미 진행중인 수업 있음 > Lecture에 이름 있음 > 재입력
		// 2 출력 > 이미 진행중인 수업이 없고  admin에 등록된 선생
		return 2;

	}

	/**
	 * 날짜 유효성 검증
	 * @param date
	 * @return
	 */
	private static boolean dateValidate(String date) {

		String regex = "";
		Pattern pattern = null;
		Matcher matcher = null;

		regex = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(date);

		if (!matcher.find()) {
			return false;
		}

		return true;
	}
	
	/**
	 * 강사 계정 로그인 시 강사명이 같은 과거 강의 전체를 조회하는 메소드입니다.
	 * @param allLectures 전체 강의 리스트
	 */
	public static void previousLectures(ArrayList<Lecture> allLectures) {

		boolean b = true;

		while (b) {

			System.out.printf("\n▶ 총 %d개의 강의가 검색되었습니다.\n\n", allLectures.size());
			for (int i = 0; i < allLectures.size(); i++) {
				Lecture l = allLectures.get(i);
				String num = String.valueOf(i + 1);
				String title = l.getCourseTitle();
				String cap = l.getCapacity();
				System.out.println(" [  강사  ] : " + l.getInstructor());
				System.out.println(" [  번 호  ] : " + num);
				System.out.println(" [ 강의이름 ] : " + title);
				System.out.println(" [  정 원  ] : " + cap);
				System.out.println(" [ 수강기간 ] : [" + l.getStartDate() + "] ~ [" + l.getEndDate() + "]");
				System.out.println();
			}

			Scanner scan = new Scanner(System.in);
			DrawUtils.centerAlignText("[ 0. 메인으로 돌아가기 ]");
			Menu.printSelect();
			int sel = scan.nextInt();

			switch (sel) {
			case 0:
				b = false;
			}
		}
	}
	
	/**
	 * 강의가 현재 진행 중인 강의인지 식별하는 메소드입니다.
	 * @param i 메소드가 호출된 지점에서 검사 중인 강의 인덱스
	 * @return 진행 중 강의일 시 true 반환
	 */
	private static boolean isOnGoing(int i) {

		Calendar now = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();

		String[] temp = Data.lectureList.get(i).getEndDate().split("-");
		endDate.set(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));

		if (endDate.compareTo(now) == 1)
			return true;
		else
			return false;

	}
	
	/**
	 * 전체 강의 데이터를 새 리스트에 저장하는 메소드입니다.
	 * @return 전체 강의가 저장된 List
	 * @see Data#lectureList
	 */
	private static ArrayList<Lecture> getAllLectures() {

		ArrayList<Lecture> list = new ArrayList<Lecture>();
		List<Lecture> lecList = Data.lectureList;

		for (Lecture l : lecList) {
			list.add(l);
		}

		return list;
	}

}

