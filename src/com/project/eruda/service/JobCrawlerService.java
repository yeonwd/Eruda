package com.project.eruda.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.project.eruda.data.*;
import com.project.eruda.view.DrawUtils;


/**
 * 구인정보를 크롤링하는 클래스
 * @author 5조
 *
 */
public class JobCrawlerService {
	private final static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
	
	public static ArrayList<JobCrawlerDto> jobList ;
	static {
		jobList = new ArrayList<JobCrawlerDto>();
	}
	/**
	 * 해당 키워드를 입력 받으면 url에 keyword를 넣음, 그리고 saramin 사이트에서 구인정보 크롤링
	 * @param keyword 해당 키워드로 크롤링
	 */
	public static void crawlingByKeyword(String keyword){
		 JobCrawlerService.jobList.clear();
		 // 지원자수 많은거 10개만 출력
		 String URL = "https://www.saramin.co.kr/zf_user/search?searchword="+keyword+"&searchType=connect&search_done=y&recruitPage=1&recruitSort=apply_cnt&recruitPageCount=10&inner_com_type=scale003&company_cd=0%2C1%2C2%2C3%2C4%2C5%2C6%2C7%2C9%2C10&show_applied=&quick_apply=&except_read=&ai_head_hunting=&mainSearch=y";
		 try {
		 Connection conn = Jsoup.connect(URL)
					.header("Content-Type", "application/json;charset=UTF-8")
               .userAgent(USER_AGENT)
               .method(Connection.Method.GET)
               .ignoreContentType(true);
	      Document doc;
		  doc = conn.get();
	      String baseURL = "https://www.saramin.co.kr";
	      Elements e1 = doc.select(".content .item_recruit");
	      for(Element e : e1) {
	    	String company = e.select(".area_corp .corp_name" ).text().trim();
	    	String title = e.select(".job_tit span" ).text().trim();
	    	String link = baseURL+e.select(".job_tit a" ).attr("href");
	    	String date = e.select(".job_date .date" ).text().trim();
	    	String condition = e.select(".job_condition").text().trim();
	    	String sector = e.select(".job_sector a").text().trim();
	    	JobCrawlerDto jdto = new JobCrawlerDto(company, title, link, date, condition,sector );
	    	JobCrawlerService.jobList.add(jdto);
	      }
		 } catch (IOException e2) {
				e2.printStackTrace();
		}
	}
	
	/**
	 * 키워드로 검색 
	 */
	public static void crawlerService(){
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.println("[ 1. Java, 2.웹개발, 3. 자바스프링, 4. 프론트엔드, ( 뒤로가기 :-1 ) ]");
		System.out.print("▶ 구인검색 키워드 번호 입력 :");
		
		String keyword = scan.nextLine().trim();
		
		switch (keyword) {
		case "-1":
			return;
		case "1":
			// 출결관리
			crawlingByKeyword("Java");
			break;
		case "2":
			crawlingByKeyword("웹개발");
			break;
		case "3":
			crawlingByKeyword("자바스프링");
			break;
		case "4":
			crawlingByKeyword("프론트엔드");
			break;
		default:
			crawlingByKeyword("Java웹개발자");
			break;
		}
	    System.out.println("\n▶ 해당 키워드로 사람인에서 구인정보 10개를 크롤링 합니다.\n");
	    int cnt=1;
		for(JobCrawlerDto j : jobList) {
	    	System.out.printf("[ %2d. 제목 : %s, 회사 : %s]", cnt++, j.getTitle(), j.getCompany());
	    	System.out.println();
		}
		boolean loop = true;
		
		while( loop ) {
			System.out.print("\n▶ 자세한 정보를 보려면 해당 글의 번호를 누르시오.( 뒤로가기 : -1 ) : ");
			String sel = scan.nextLine();
			int selInt = Integer.parseInt(sel);
			if(   selInt > 0 && selInt <= jobList.size()) {
				JobCrawlerDto jdto =jobList.get(selInt-1);
				DrawUtils.printStartLine();
		    	System.out.printf("[제목 : %s]\n", jdto.getTitle());
		    	System.out.printf("[회사 : %s]\n", jdto.getCompany());
		    	System.out.printf("[날짜 : %s]\n", jdto.getDate());
		    	System.out.printf("[직업 조건 : %s]\n" ,jdto.getCondition() );
		    	System.out.printf("[직업 영역 : %s]\n", jdto.getJobSector());
		    	System.out.println("[링크 : "+jdto.getUrl()+" ]");
		    	crawlerService();
		    	break;
			} else if ( sel.equals("-1") ) {
				break;
			}
		}
		
		
	}
	
	
}
