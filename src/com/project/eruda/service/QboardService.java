package com.project.eruda.service;

import java.io.IOException;
import java.util.Scanner;

import com.github.lalyos.jfiglet.FigletFont;
import com.project.eruda.data.Data;
import com.project.eruda.view.DrawUtils;
import com.project.eruda.view.Menu;


/**
 * 질문게시판 기능을 호출하는 클래스
 * @author 5조
 *
 */
public class QboardService {
	
	/**
	 * 질문게시판 초기 화면을 출력하는 메소드
	 */
	public static void qboard() {
		Scanner scan = new Scanner(System.in);
		System.out.println("▶ 질문게시판 입니다.");
		DrawUtils.printEndLine();
		DrawUtils.printStartLine();
		
		boolean loop = true;
			
		while (loop) {
			
			Menu.printQboard();
			Menu.printSelect();
			
			int sel = scan.nextInt();
			
			//1. 게시글목록
			if (sel == 1) {
				QuestionService.boardlist();
				loop = false;
			//2. 글 작성
			} else if (sel ==2) {
				QuestionService.writeq();
				Data.qsave();
				qboard();
				break;
			} else if (sel ==3) {
				break;
			}
		}
		
		
	}

	
}