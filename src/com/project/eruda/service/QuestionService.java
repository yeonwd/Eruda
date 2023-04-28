package com.project.eruda.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.project.eruda.Main;
import com.project.eruda.data.*;
import com.project.eruda.view.DrawUtils;
import com.project.eruda.view.Menu;

//게시글 목록 출력
/**
* 질문게시판 게시글 목록 화면
* @author chaerin
*
*/
public class QuestionService {
	
	static String qnum;	
	static String qdate;
	static String qid;
	static String qtitle;
	static String qmaintext;
	static String qteacher;
	
	static String rnum;
	static String rdate;
	static String rtext;
	
	/**
	 * 질문게시판 메인화면 상단을 출력하는 메소드
	 */
	public static void boardlist() {
	    DrawUtils.questionhead();
	    DrawUtils.printboardline();
	    
	    postlist();
	}
	
	/**
	 * 질문게시판 글 목록과 페이지를 출력하는 메소드
	 */
	public static void postlist() {
	    int pageNum = 1;
	    int postCount = Data.questionList.size();
	    int pageCount;
	    if (postCount % 10 == 0) {
	        pageCount = postCount / 10;
	    } else {
	        pageCount = (postCount / 10) + 1;
	    }

	    boolean loop = true;

	    while (loop) {
	        int start = (pageNum - 1) * 10;
	        int end = Math.min(start + 10, postCount);
	        List<Question> pageQuestions = Data.questionList.subList(start, end);
	        
	        // 여깄던거 DrawUtils.questionhead();  } else if (sel.equals("4")) { 로 아래쪽으로 이동
	        System.out.println(DrawUtils.DOTLINE);
	        for (Question q : pageQuestions) {
	            System.out.printf("%4s\t%7s\t%-18s\t%11s\t%s\n"
	                    , q.getQnum()
	                    , q.getTeacher()
	                    , q.getTitle()
	                    , q.getId()
	                    , q.getDate());
	            System.out.println(DrawUtils.DOTLINE);
	        }

	        DrawUtils.printMiddelLine();

	        DrawUtils.centerAlignText("현재 페이지: " + pageNum + "/" + pageCount);
	        System.out.println("  게시물 보기 : 1 | 게시글 작성 : 2 | 이전 페이지: 3 | 다음 페이지: 4 | 뒤로가기 : 5");
	        Menu.printSelect();

	        Scanner scan = new Scanner(System.in);
	        String sel = scan.nextLine();

	        if (sel.equals("1")) {
	            System.out.println("아래에 이동할 글 번호를 입력하세요.");
	            Menu.printSelect();

	            scan = new Scanner(System.in);
	            sel = scan.nextLine();
	            for (Question q : Data.questionList) {
	                if (q.getQnum().equals(sel)) {
	                    DrawUtils.printMiddelLine();
	                    qnum = q.getQnum();
	                    qdate = q.getDate();
	                    qid = q.getId();
	                    qtitle = q.getTitle();
	                    qmaintext = q.getMaintext();
	                    qteacher = q.getTeacher();	                  
	                    post();
	                }
	            }
	            
	            break;
	            
	        } else if (sel.equals("2")) {
	            QuestionService.writeq();
	            Data.qsave();
	            break;
	        } else if (sel.equals("3")) {
	            if (pageNum > 1) {
	                pageNum--;
	            } else {
	                System.out.println("이전 페이지가 없습니다.");
	                DrawUtils.pause();
	                boardlist();
	            }

	        } else if (sel.equals("4")) {
	            if (pageNum < pageCount) {
	            	DrawUtils.questionhead();
	                pageNum++;
	            } else {
	                System.out.println("다음 페이지가 없습니다.");
	                DrawUtils.pause();
	                boardlist();
	                break;
	            }
	        } else if (sel.equals("5")) {
	            QboardService.qboard();
	            break;
	        } else {
	            System.out.println("숫자를 다시 입력해주세요.");
	        }
	        
	    }
	}
	
	/**
	 * 질문 게시판의 글 본문을 출력하는 메소드
	 */
	public static void post(){

		System.out.println("\n\n\n\n");
		System.out.println(DrawUtils.LINE);
		System.out.println("\t\t\t      <<질문 게시판>>");
		System.out.println(DrawUtils.LINE);
		System.out.printf("작성자 : %-4s\t | 글제목 : %-20s\n", qid, qtitle);
		System.out.printf("글번호 : %-4s\t | 담당강사 : %-4s\t | 작성날짜 : %s\n", qnum, qteacher, qdate);
		System.out.println(DrawUtils.DOTLINE);
		System.out.printf("%s", qmaintext);
		System.out.println("\n\n\n\n");
		DrawUtils.printMiddelLine();
		
		reply();
	}
	
	/**
	 * 질문 게시판 글에 달린 댓글을 출력하는 메소드
	 */
	public static void reply() {
		
		for (Reply r : Data.replyList) {
			if (r.getQnum().equals(qnum)) {
				
				rnum = r.getRnum();
				rdate = r.getRdate();
				rtext = r.getRtext();
				
				DrawUtils.centerAlignText(String.format("[댓글 번호] : %s | [댓글 작성자] : %s | [작성 날짜] : %s\n", rnum, qteacher, rdate));
				System.out.printf("%s\n", rtext);
				System.out.println(DrawUtils.DOTLINE);
			}
		}
		
		System.out.println(DrawUtils.LINE);
		DrawUtils.centerAlignText("목록 : 1 | 댓글 작성 : 2 | 댓글 삭제 : 3 | 글 삭제 : 4");
		Menu.printSelect();
		
		Scanner scan = new Scanner(System.in);
		String sel = scan.nextLine();
		
		//1. 목록
		if (sel.equals("1")) {
			boardlist();
		//2. 댓글 작성
		} else if (sel.equals("2")) {
			writereply();
		//3. 댓글 삭제
		} else if (sel.equals("3")) {
			deletereply();
		//4. 글 삭제
		} else if (sel.equals("4")) {
			deleteq();
		}
		
	}
	/**
	 * 질문 게시판에 글을 작성하는 메소드
	 */
	public static void writeq() {
		System.out.println("▶ 질문 게시판에 글을 작성합니다.");
		
		Scanner scan = new Scanner(System.in);
		String qnum = "1";
		if(Data.questionList.size() == 0)
			qnum="1";
		else
			qnum = Data.questionList.stream()
				.mapToInt(q -> Integer.parseInt(q.getQnum()))
				.max().getAsInt() 
				+ 1 + "";
		
		//날짜
		Calendar c = Calendar.getInstance();
		String now = String.format("%tF", c);
		
		//이름 사용자 아이디
		if ( Main.currentUser==null) {
			System.out.println("▶ 학생만 작성 가능합니다.");
			DrawUtils.pause();
			//boardlist();
			return;
		}
		
		String id = Main.currentUser.getId();
		
		System.out.print("▶ 제목 : ");
		String title = scan.nextLine();
		
		System.out.print("▶ 본문 : ");
		String maintext = scan.nextLine();
		
		//강사 존재하는지 유효검사
		System.out.print("▶ 담당강사: ");
		String teacher = validTeacher();
		
		if(teacher==null) {
			boardlist();
			return;
		}
		
		System.out.println();
		//Question 인스턴스 만들기
		
		Question q = new Question(qnum, now, id, title, maintext, teacher);
		
		Data.questionList.add(q);
		Data.qsave();
		System.out.println("▶ 글을 작성했습니다.");
		boardlist();
		
	}
	
// 강사가 존재하는지 유효성 검사
	/**
	 * 강사가 존재하는지 유효성 검사하는 메소드. Scanner를 통하여 담당강사의 이름을 입력받습니다.
	 * @return 담당강사 이름
	 */
private static String validTeacher() {
	
		Scanner scan = new Scanner(System.in);
		String  teacher = scan.nextLine();
		boolean isTeacher = false;
		if( Main.currentUser!=null ) {
			if ( Main.currentUser.getStart()!="null") {  // 강의를 수강하는 학생만 글 입력 가능
				try {					
					for ( Lecture lec : Data.lectureList) {
						if ( lec.getInstructor().equals(teacher)){
							isTeacher = true;
							return teacher;
						}
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			} else {
				// 강의 수강 x
				System.out.println();
				System.out.print("▶ 당신은 강의를 수강하지 않았습니다. ");
				DrawUtils.pause();
				return null;
			}
		}
		// 현재 수강하는 강사와 일치하지 않습니다.
		System.out.println();
		System.out.print("▶ 현재 수강하는 강사와 일치하지 않습니다. ");
		DrawUtils.pause();
		
		return null; 
}


/**
 * 질문 게시판에 댓글을 작성하는 메소드.
 */
public static void writereply() {
		
		String name = "";
		if (Main.currentAdmin != null ) {
			name = Main.currentAdmin.getName();
			
			System.out.println("▶ 댓글을 작성합니다.\n");
			Scanner scan = new Scanner(System.in);
			
			//댓글번호
			//시나리오
			//reply.txt에서 endswith(글번호) 나올때마다 count,,> + 1
			String rnum;
			Path replyPath = Path.of(Data.REPLY);
			int count=0;
			try {
				List<String> replyContent = new ArrayList<>(Files.readAllLines(replyPath, StandardCharsets.UTF_8));
				for (Iterator<String> iterator = replyContent.iterator();iterator.hasNext();) {
					String line = iterator.next();
					if (line.endsWith(qnum)) {
						count++;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			rnum = String.valueOf(count + 1);
			
			//*수정* 담당강사 본인인지 검증
				
			System.out.print("▶ 댓글 입력: ");
			String rtext = scan.nextLine();
			
			//현재 날짜
			Calendar c = Calendar.getInstance();
			String now = String.format("%tF", c);
			
			Reply r = new Reply(rnum, name, rtext, now, qnum);
			
			Data.replyList.add(r);
			Data.rsave();
			System.out.println("▶ 댓글을 작성했습니다.");
			
			boardlist();
			
		} else {
			System.out.println("\n▶ 담당강사만 댓글을 작성할 수 있습니다.");
			DrawUtils.pause();
			boardlist();
		}
		
	}

	/**
	 * 질문게시판에 작성한 글을 삭제하는 메소드. 본인이 작성한 글만 삭제할 수 있음.
	 */
	public static void deleteq() {
		if (Main.currentUser != null) {
			if (qid.equals(Main.currentUser.getId())) { // 작성자는 학생 
				
				System.out.println("▶ 게시글을 삭제하시려면 글번호를 입력하세요.");
				
				Scanner scan = new Scanner(System.in);
				String qnumToDelete = scan.nextLine();
				
				boolean questionRemoved = false; 
				for (Question q : Data.questionList) {
					if (q.getQnum().equals(qnumToDelete)) {
						Data.questionList.remove(q); //방번호 or 요소로 삭제 가능
						questionRemoved = true;
						break;
					}
				}
				if (!questionRemoved) {
					System.out.println("▶ 게시글을 찾을 수 없습니다.");
					return;
				}
				
				//파일에서 삭제
				Path filePath = Path.of(Data.QUESTION);
				try {

					List<String> fileContent = new ArrayList<>(Files.readAllLines(filePath, StandardCharsets.UTF_8));
					boolean lineRemoved = false;
					for (Iterator<String> iterator = fileContent.iterator(); iterator.hasNext();) {
						String line = iterator.next();
						if (line.startsWith(qnumToDelete)) {
							iterator.remove();
							lineRemoved = true;
						}
					}
					if (lineRemoved) {
						Files.write(filePath, fileContent, StandardCharsets.UTF_8);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				//글에 달린 댓글삭제
				Path replyPath = Path.of(Data.REPLY);
				try {
					List<String> replyContent = new ArrayList<>(Files.readAllLines(replyPath, StandardCharsets.UTF_8));
					boolean lineRemoved = false;
					for (Iterator<String> iterator = replyContent.iterator(); iterator.hasNext();) {
						String line = iterator.next();
						if (line.endsWith(qnumToDelete)) {
							iterator.remove();
							lineRemoved = true;
						}
					}
					if (lineRemoved) {
						Files.write(replyPath, replyContent, StandardCharsets.UTF_8);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				System.out.println("게시글이 삭제되었습니다.");
				boardlist();
				
			} else {
				System.out.println();
				System.out.println("▶ 본인이 작성한 글만 삭제할 수 있습니다.");
				DrawUtils.pause();
			}	
		} else {
			System.out.println();
			System.out.println("본인이 작성한 글만 삭제할 수 있습니다.");
			DrawUtils.pause();
		}

		
		
	}
	
	/**
	 * 댓글 삭제 메소드. 관리자 모드일 때만 삭제 가능.
	 */
	public static void deletereply() {
		
		System.out.println("▶ 댓글을 삭제하시려면 댓글 번호를 입력하세요.");
		
		Scanner scan = new Scanner(System.in);
		String rnumToDelete = scan.nextLine();
		
		//T만 삭제가능(본인이 작성한 댓글만 삭제할 수 있습니다.)
		if (Main.currentAdmin != null) {
			
			boolean replyRemoved = false;
			for (Reply r : Data.replyList) {
				if(r.getRnum().equals(rnumToDelete)) {
					Data.replyList.remove(r);
					replyRemoved = true;
					break;
				}
			}
			if (!replyRemoved) {
				System.out.println("▶ 댓글을 찾을 수 없습니다.");
				return;
			}
			
			//reply.txt에서 삭제
			Path replyPath = Path.of(Data.REPLY);
			try {
				List<String> replyContent = new ArrayList<>(Files.readAllLines(replyPath, StandardCharsets.UTF_8));
				boolean lineRemoved = false;
				for (Iterator<String> iterator = replyContent.iterator(); iterator.hasNext();) {
					String line = iterator.next();
					if (line.startsWith(rnumToDelete)) {
						iterator.remove();
						lineRemoved = true;
					}
				}
				if (lineRemoved) {
					Files.write(replyPath, replyContent, StandardCharsets.UTF_8);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("▶ 댓글이 삭제되었습니다.");
			DrawUtils.pause();
			boardlist();
		} else {
			System.out.println("▶ 본인이 작성한 댓글만 삭제할 수 있습니다.");
			DrawUtils.pause();
			boardlist();
		}
		
	}
	
}
