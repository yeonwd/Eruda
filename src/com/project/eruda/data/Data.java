package com.project.eruda.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/**
 * 프로젝트에 사용되는 데이터를 불러오거나 저장을 하는 메서드와 연관된 클래스
 * @author 5조
 *
 */
public class Data {
	
	public static final String LECTURE =  ".\\dat\\lecture.txt";
	
	public static final String USER    = ".\\dat\\user.txt";
	public static final String ADMIN   = ".\\dat\\admin.txt";
	public final static String SUGGEST = ".\\dat\\board_wish.txt"; // 질문게시판의 데이터가 있는 파일
	
	public final static String QUESTION = ".\\dat\\board_question.txt";
	public final static String REPLY    = ".\\dat\\reply.txt";
	public final static String TODOLIST = ".\\dat\\todoList.txt";
	public final static String QUIZ     = ".\\dat\\quiz.txt";
	public final static String NOTICE = ".\\dat\\board_notice.txt"; 
	
	public static ArrayList<User> userList ;
	public static ArrayList<Admin> adminList ;
	public static ArrayList<Suggest> suggestList ;
	public static ArrayList<TodoList> todoList ;
	public static ArrayList<Reply> replyList;       // 변수명 변경> rlist > replyList
	public static ArrayList<Question> questionList; // 변수명 변경 > question > questionList
	public static ArrayList<Quiz> quizList; 
	public static ArrayList<Lecture> lectureList;
	public static ArrayList<Notice> noticeList ;
	
	static {
		userList = new ArrayList<User>();
		adminList = new ArrayList<Admin>();
		suggestList = new ArrayList<Suggest>();
		replyList   =new ArrayList<Reply>();
		questionList = new ArrayList<Question>();
		todoList = new ArrayList<TodoList>();
		quizList = new ArrayList<Quiz>();
		lectureList = new ArrayList<Lecture>();
		noticeList = new ArrayList<Notice>();
	}
	
	public static void load() {		
		getUserList();
		getAdminList();
		getSuggestList();
		getQuestionList();
		getReplyList();
		getTodoList();
		getQuizList();
		getLectureList();
		getNoticeList();
	}
	
	
	/**
	 * 공지사항 정보를 변경한 후 텍스트 파일에 저장하는 메서드
	 */
	public static void saveNoticeList() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(Data.NOTICE));
			
			for(Notice no : noticeList) {
				writer.write(String.format("%s■%s■%s■%s■%s\r\n"
												, no.getIndex()
												, no.getDate()
												, no.getId()
												, no.getTitle()
												, no.getText()));
			}
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 건의 게시판 글을 편집 후 저장하는 메서드
	 */
	public static void saveSuggestList() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(Data.SUGGEST));
			
			for(Suggest su : suggestList) {
				writer.write(String.format("%s■%s■%s■%s■%s\r\n"
												, su.getIndex()
												, su.getDate()
												, su.getId()
												, su.getTitle()
												, su.getText()));
			}
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 강사 정보 추가 후 텍스트 파일에 추가하는 메서드
	 * @param adm 추가할 admin 정보
	 */
	public static void saveAdminAppend(Admin adm) {
		try {
			// 파일을 다시 열고
			BufferedWriter writer = new BufferedWriter(new FileWriter(Data.ADMIN, true));
			//admin_O3■0000■남림아■운영자■2003-02-09■011-974-9465■RxVy@hotmail.com■경기도 정대시 차주동■추계예술대학교■일어일문과
			writer.write(String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s■%s\r\n"
								,adm.getId()
								,adm.getPw()
								,adm.getName()
								,adm.getPosition()
								,adm.getBirth()
								,adm.getPhone()
								,adm.getEmail()
								,adm.getAddress()
								,adm.getUniv()
								,adm.getMajor()));
			
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 * 강의 개설후 텍스트 파일에다가 쓰는 메서드
	 * @param l 추가할 강의정보
	 */
	public static void savelectureAppend(Lecture l) {
		
		try {
			// 파일을 다시 열고
			BufferedWriter writer = new BufferedWriter(new FileWriter(Data.LECTURE, true));
			//담당강사■과정이름■회차■시작기간■종료기간■수강인원■설명
			//Lecture lect = new Lecture(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
			writer.write(String.format("%s■%s■%s■%s■%s■%s■%s\r\n"
										, l.getInstructor()
										, l.getCourseTitle()
										, l.getTimes()
										, l.getStartDate()
										, l.getEndDate()
										, l.getCapacity()
										, l.getLecDescription()
										));
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * todoList를 파일에다가 저장
	 */
	public static void saveTodoList() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(Data.TODOLIST));
			for (TodoList todo : todoList) {
				writer.write(String.format("%s■%s■%s\r\n"
											, todo.getId()
											, todo.getTime()
											, todo.getDesc()));
			}
			writer.close();
		} catch(Exception e) {
			
		}
	}
	
	/**
	 * 퀴즈정보를 파일에다가 저장
	 */
	public static void saveQuizList() {
		// 유저 정보를 변경하였을 경우에 > 데이터에 변경된 정보를 쓴다.
		try {
			// 파일을 다시 열고
			BufferedWriter writer = new BufferedWriter(new FileWriter(Data.QUIZ));
			for (Quiz q : quizList) {
				writer.write(String.format("%s■%s■%s■%s\r\n"
											, q.getTeacher()
											, q.getQuizFileName()
											, q.getAnswer()
											, q.getSolution()));
									
			}
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 유저정보를 파일에다가 쓰는 메서드
	 */
	public static void saveUserList() {
		// 유저 정보를 변경하였을 경우에 > 데이터에 변경된 정보를 쓴다.
		try {
			// 파일을 다시 열고
			BufferedWriter writer = new BufferedWriter(new FileWriter(Data.USER));
			
			for (User u : userList) {
				writer.write(String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s\n",
						u.getId(), u.getPw(),
						u.getName(), u.getGender(),
						u.getAge(), u.getEmail(),
						u.getPhoneNumber(), u.getAddress(), 
						u.getUniv(), u.getMajor(),
						u.getCourse(),u.getStart(),u.getEnd(),u.getCompany()
						));

			}
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 공지사항 정보를 파일에서 읽어옴
	 */
	public static void getNoticeList() {
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(Data.NOTICE));
			String line = null;
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split("■");
				//텍스트 1줄 > Member 객체 1개
				Notice n = new Notice(temp[0], temp[1], temp[2], temp[3], temp[4]);
				noticeList.add(n);
			}
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	//채린 > 함수명 loadqboard() > getQuestionList() : 변경 
	/**
	 * board_question.txt에 저장된 질문게시판 글 데이터를 불러오는 메소드.
	 */
	public static void getQuestionList() {
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(Data.QUESTION));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split("■");
				
				Question q = new Question(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
				
				questionList.add(q);
				
			}
		
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 퀴즈 정보를 파일에서 읽어옴
	 */
	public static void getQuizList() {
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(Data.QUIZ));
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				Quiz r = new Quiz(temp[0], temp[1], temp[2], temp[3]);
				quizList.add(r);
			}
			reader.close();
		} catch (Exception e) {
			
		}
		
	}
	
	/**
	 * reply.txt에 저장된 질문게시판 댓글 데이터를 불러오는 메소드.
	 */
	public static void getReplyList() {
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(Data.REPLY));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				
				//String rnum, String teacher, String rtext, String rdate, String qnum
				Reply r = new Reply(temp[0], temp[1], temp[2], temp[3], temp[4]);
				
				replyList.add(r);
			}
			
			reader.close();
		} catch (Exception e) {
			
		}
		
	}
	
	/**
	 * 질문게시판에 작성한 글을 board_question.txt에 저장하는 메소드.
	 */
	public static void qsave() {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(Data.QUESTION)); //true 쓰면 이어쓰기. 생략
			
			for (Question q : questionList) {
				writer.write(String.format("%s■%s■%s■%s■%s■%s\r\n"
											, q.getQnum()
											, q.getDate()
											, q.getId()
											, q.getTitle()
											, q.getMaintext()
											, q.getTeacher()));
			}
			
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 질문게시판에 작성한 댓글을 reply.txt에 저장하는 메소드.
	 */
	public static void rsave() {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(Data.REPLY));
			
			for (Reply r : replyList) {
				writer.write(String.format("%s■%s■%s■%s■%s\r\n"
											, r.getRnum()
											, r.getTeacher()
											, r.getRtext()
											, r.getRdate()
											, r.getQnum()));
			}
			
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 건의게시판 정보를 파일에서 읽어옴
	 */
	public static void getSuggestList() {
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(Data.SUGGEST));
			String line = null;
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split("■");
				//텍스트 1줄 > Member 객체 1개
				Suggest s = new Suggest(temp[0], temp[1], temp[2], temp[3], temp[4]);
				suggestList.add(s);
			}
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * user 정보를 파일에서 읽어옴
	 */
	private static void getUserList() {
		try {
			int cnt =0;
			BufferedReader reader = new BufferedReader(new FileReader(USER));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				//(String id, String pw, String name, String gender, String age, String phoneNumber, String email,
				//String address, String univ, String major, String course, String start, String end, String company)
				User user = new User(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8], temp[9],
						temp[10], temp[11], temp[12], temp[13]);
				userList.add(user);
			}
			reader.close();/////
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/** 
	 * todoList 정보를 파일에서 읽어옴 
	 */
	private static void getTodoList() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(TODOLIST));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				TodoList todo = new TodoList(temp[0], temp[1], temp[2]);
				todoList.add(todo);
			}
			reader.close();/////
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * admin정보를 파일에서 읽어오는 메서드
	 */
	private static void getAdminList() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(ADMIN));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				Admin admin = new Admin(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8], temp[9]);
				adminList.add(admin);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 강의정보를 파일에서 읽어오는 메서드
	 */
	private static void getLectureList() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Data.LECTURE));
			String line = null;

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				Lecture lect = new Lecture(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
				lectureList.add(lect);
			}

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
