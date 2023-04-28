package com.project.eruda.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import java.util.regex.*;

import com.github.lalyos.jfiglet.FigletFont;
import com.project.eruda.data.*;
import com.project.eruda.view.*;
/**
 * 
 * @author 5조 
 * 회원가입을 하는 로직에 대한 클래스
 */
public class RegisterService {

	public static ArrayList<User> list;

	static {
		list = new ArrayList<User>();
	}

	static public void register() {

		Scanner scan = new Scanner(System.in);
		System.out.println("▶ 회원가입이 선택되었습니다.");
		DrawUtils.printEndLine();

		String asciiArt3;

		try {
			
			asciiArt3 = FigletFont.convertOneLine("Register");
			System.out.println(DrawUtils.alignCenter(asciiArt3));
			DrawUtils.printMiddelLine();
			System.out.println("\n\n");

			boolean loop = true;

			System.out.println("▶ 2~5자 한글 입력");
			System.out.print("[ 성명 ] : ");
			String name = inputName(); 
			
					
			System.out.println("▶ 7~13자 영문자 및 숫자 입력");
			System.out.print("[ 아이디 ] : ");
                                   // 아이디 같은 경우는 중복된 아이디를 입력하지 않도록 검증해야함
			String id = inputId(); // 아이디 입력받는 로직 > 검증받는 부분을 안으로 뺌

			System.out.println("▶ 7~13자 영문자 및 숫자 입력");
			System.out.print("[ 패스워드 ] : ");

			String pw = inputPw(); //

			System.out.println("▶ '-'를 포함하여 ex(1997-10-19)와 같이 입력");
			System.out.print("[ 생년월일 ]: ");

			String age = inputAge();

			// 엔터 입력시 다음 단계
			DrawUtils.pause();

			System.out.println("▶ '-' 를 포함하여 숫자 12~13자 입력");
			System.out.print("[ 전화번호 ] : ");
			String phoneNumber = inputPhoneNumber();
			
			System.out.println("▶ @ 를 포함하여 입력");
			System.out.print("[ 이메일 ] : ");
			String email = inputEmail();
					
			System.out.println("▶ 도로명 주소 및 지번 주소 입력");
			System.out.print("[ 주소 ]: ");
			String address = inputAddress(); 

			System.out.println("▶ 남 or 여");
			System.out.print("[ 성별 ] : ");
			String gender = inputGender(); 
		
			
			System.out.println("▶ 작성을 완료하시겠습니까?(yes or no)");
			String input = scan.nextLine();

			if (input.equals("yes")) {
				System.out.println();
				System.out.println("▶ 회원가입 완료하였습니다.");
				

				//(String id, String pw, String name, String gender, String age, String phoneNumber, String email,
				//String address, String univ, String major, String course, String start, String end, String company)
				User user = new User(id, pw, name, gender, age,  email, phoneNumber, address, "null", "null", "null", "null","null","null");
				saveUserInfo(user);
				Data.userList.add(user); // 이미 load된 userList에 추가
				// 엔터 입력시 다음 단계
				DrawUtils.pause();
				
			} else if (input.equals("no")) {
				System.out.println();
				System.out.println("▶ 회원가입을 취소하고 메인으로 이동합니다.");
				DrawUtils.pause();
			}
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 이름 입력 및 유효성 검사
	 * @return 입력된 이름을 출력
	 */
	public static String inputName() {
		Scanner scan = new Scanner(System.in);
		String name = null;
		while(true) {
			name = scan.nextLine().trim();
			if(name.equals("-1"))
				return null;
			if (!nameValidate(name)) {
				System.out.println();
				System.out.println("▶ 올바른 이름을 입력해주세요.");
				System.out.print("[ 성명 ] : ");
				continue;
			} else 
				break;
		}
		System.out.println();
		return name;

	}
	/**
	 *  성별입력 및 유효성 검사
	 * @return 입력된 성별 출력
	 */
	private static String inputGender() {
		Scanner scan = new Scanner(System.in);
		String gender = null;
		
		while(true) {
			gender = scan.nextLine().trim();
			if (!genderValidate(gender)) {
				System.out.println();
				System.out.println("▶ 올바른 성별을 입력해주세요.");
				System.out.print("[ 성별 ] : ");
				continue;
			}
			else 
				break;
		}
		
		System.out.println();
		return gender;
	}

	/**
	 * 주소 입력 및 유효성 검사
	 * @return 입력된 주소를 반환
	 */
	public static String inputAddress() {
		Scanner scan = new Scanner(System.in);
		String address = null;
		
		while(true) {
			address= scan.nextLine().trim();
			//if (!addressValidate(address)) {
			if(address.equals("-1"))
				return null;
			if (address.length() > 30) {
				System.out.println();
				System.out.println("▶ 올바른 주소를 입력해주세요.");
				System.out.print("[ 주소 ] : ");
				continue;
			}
			else 
				break;
		}
		
		System.out.println();
		return address;
	}
	/**
	 * 이메일 입력 및 유효성 검사
	 * @return 입력된 이메일 번환
	 */
	public static String inputEmail() {
		Scanner scan = new Scanner(System.in);
		String email=null;
		while(true) {
			
			email=scan.nextLine().trim();
			
			if(email.equals("-1"))
				return null;
			
			if (!emailValidate(email)) {
				System.out.println();
				System.out.println("▶ 올바른 이메일을 입력해주세요.");
				System.out.print("[ 이메일 ] : ");
				continue;
			}
			else 
				break;
		}
		
		System.out.println();
		return email;
	}
	/**
	 * 전화번호 입력 및 유효성 검사 
	 * @return 입력된 전화번호 반환
	 */
	public static String inputPhoneNumber() {
		
		Scanner scan = new Scanner(System.in);
		String phoneNumber = null;
		while ( true ) {
			phoneNumber = scan.nextLine().trim();
			if(phoneNumber.equals("-1"))
				return null;
			
			if (!phoneNumberValidate(phoneNumber)) {
				System.out.println();
				System.out.println("▶ 올바른 전화번호를 입력해주세요.");
				System.out.print("[ 전화번호 ] : ");
				continue;
			} else
				break;
		}
		
		System.out.println();
		return phoneNumber;
	}
	/**
	 * 생년월일 입력 및 유효성 검사
	 * @return 입력된 생년월일 반환
	 */
	public static String inputAge() {
		Scanner scan = new Scanner(System.in);
		String age = null;
		while (true) {
			age = scan.nextLine().trim();
			if(age.equals("-1"))
				return null;
			if (!ageValidate(age)) {
				System.out.println();
				System.out.println("▶ 올바른 생년월일을 입력해주세요.");
				System.out.print("[ 생년월일 ] : ");
				continue;
			} else
				break;
		}
		System.out.println();
		return age;
	}
	
	/**
	 * 패스워드 입력 및 유효성 검사
	 * @return 입력된 패스워드 반환
	 */
	private static String inputPw() {
		Scanner scan = new Scanner(System.in);
		String pw;

		while (true) {
			pw = scan.nextLine().trim();
			if (!pwValidate(pw)) {
				System.out.println();
				System.out.println("▶ 올바른 비밀번호를 입력해주세요.");
				System.out.print("[ 패스워드 ] : ");
				continue;
			} else
				break;
		}
		System.out.println();
		return pw;
	}

	/**
	 * 아이디를 입력 및 유효성 검사
	 * @return 입력된 아이디 반환
	 */
	private static String inputId() {

		Scanner scan = new Scanner(System.in);
		String id = null;
	
		while (true) {
			id = scan.nextLine().trim();
			if (!idValidate(id)) {
				System.out.println();
				System.out.println("▶ 올바른 아이디를 입력해주세요.");
				System.out.print("[ 아이디 ] : ");
				continue;
			} else {
				// 유효는 한데? id가 중복인 경우
				if(isDuplicate(id)) {
					System.out.println();
					System.out.println("▶ 중복되는 아이디 입니다. 다시 입력해주세요.");
					System.out.print("[ 아이디 ] : ");
					continue;
				}
				break;
			}
		}
		System.out.println();
		return id;
	}

	/**
	 * 아이디가 중복되는지 확인
	 * @param id
	 * @return
	 */
	private static boolean isDuplicate(String id) {
		
		for(User u : Data.userList) {
			if ( u.getId().equals(id) )
				return true;
		}
		return false;
	}

	/**
	 * @param gender 성별 유효성 검사
	 */
	private static boolean genderValidate(String gender) {

		if ((gender.equals("남") || gender.equals("여"))) {
			return true;
		} else
			return false;
	}

	/**
	 * @param address 주소 유효성 검사 : 한글, 영어대소문자, 숫자만 작성 가능하도록 설정
	 * @return 유효여부 반환
	 */
	private static boolean addressValidate(String address) {

		String regex = "";
		Pattern pattern = null;
		Matcher matcher = null;

		regex = "^[A-Za-z0-9가-힣]{1,}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(address);

		if (!matcher.find()) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param email email 유효성 검사
	 * @return 유효성 여부 반환
	 */
	public static boolean emailValidate(String email) {

		String regex = "";
		Pattern pattern = null;
		Matcher matcher = null;

		regex = "^[A-Za-z0-9]{1,}[@]{1}[A-Za-z]{1,}[.]{1}[A-Za-z]{2,4}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(email);

		if (!matcher.find()) {
			return false;
		}
		return true;
	}

	/**
	 * @param phoneNumber 번호 유효성 검사
	 */
	private static boolean phoneNumberValidate(String phoneNumber) {

		String regex = ""; // 정규식
		Pattern pattern = null; // 정규식 객체
		Matcher matcher = null; // 결과 객체

		regex = "^[0]{1}[1]{1}[0-9]{1}-[0-9]{3,4}-[0-9]{4}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(phoneNumber);

		if (!matcher.find()) {
			return false;
		}
		return true;
	}



	/**
	 * 
	 * @param age 생년월일 유효성 검사
	 */
	private static boolean ageValidate(String age) {

		String regex = "";
		Pattern pattern = null;
		Matcher matcher = null;

		regex = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(age);

		if (!matcher.find()) {
			return false;
		}

		return true;
	}

	/**
	 * @param pw pw유효성 검사
	 */
	private static boolean pwValidate(String pw) {

		String regex = "";
		Pattern pattern = null;
		Matcher matcher = null;

		regex = "^[A-Za-z0-9]{7,13}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(pw);

		if (!matcher.find()) {
			return false;
		}

		return true;

	}

	/**
	 * @param id id유효성 검사 중복된 ID 체크 메소드 작성해야됨
	 */
	private static boolean idValidate(String id) {

		String regex = "";
		Pattern pattern = null;
		Matcher matcher = null;

		if (true) {
			// id 유효성 검사
			regex = "^[A-Za-z0-9]{7,13}$";
			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(id);

			if (!matcher.find()) {
				return false;
			}

			// 중복된 id검사
			if (list.contains(id)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 회원가입한 정보를 user.txt에 추가
	 */
	private static void saveUserInfo(User user) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(Data.USER, true));
			//qwer2■1234■이길동■남■1997-10-05■010-134-578■aaa@gmail.com■서울시 강북구 어디 아파트■비트대학교■사회복지■자바풀스텍
			writer.write(String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s\n",
					user.getId(), user.getPw(),
					user.getName(), user.getGender(),
					user.getAge(), user.getEmail(), 
					user.getPhoneNumber(), user.getAddress(), 
					user.getUniv(), user.getMajor(),
					user.getCourse(),user.getStart(),user.getEnd(),user.getCompany()
					));
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param name 이름 유효성 검사
	 */
	private static boolean nameValidate(String name) {
		// ("2~5자 한글 입력");
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) < '가' || name.charAt(i) > '힣') {
				return false;
			}
		}
		if (name.length() < 2 || name.length() > 5)
			return false;

		return true;
	}
}
