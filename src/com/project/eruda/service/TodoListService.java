package com.project.eruda.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.project.eruda.Main;
import com.project.eruda.data.*;
import com.project.eruda.view.*;
/**
 * 
 * @author 5조
 * TodoList를 구현하는 클래스
 */
public class TodoListService {
		
	public final static String TODOLIST = ".\\dat\\todoList.txt";
	
	
	
	/**
	 * TodoList 구현부분 추가 삭제 구현
	 */
	public static void todoList() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("▶ 오늘의 할 일 ");
		DrawUtils.printEndLine();
		DrawUtils.printStartLine();
		
		boolean loop = true;
			
		while (loop) {
			
			Menu.printTodoListMenu();
			int i=1;
			for(TodoList D : Data.todoList) {
				if( D.getId().equals(Main.currentUser.getId()))
					DrawUtils.centerAlignText(" 📒 "+i++ +" : "+D.getDesc());
			}
			System.out.println();
			DrawUtils.centerAlignText("[ 1. TodoList 추가, 2. TodoList 삭제하기, 3. 뒤로가기 ] ");
			Menu.printSelect();
			String sel = scan.nextLine();
			
			
			if (sel.equals("1")) { // 할 일 추가
				System.out.print("▶ 내용을 입력하세요. :  ");
				String todo = scan.nextLine();
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String nowTime = sdf1.format(new Date());	
				Data.todoList.add(new TodoList(Main.currentUser.getId(), nowTime, todo));
				Data.saveTodoList();
				
			} else if (sel.equals("2")) { // 할 일 삭제
				System.out.print("▶ 삭제할 번호를 입력하세요. :  ");
				String delTodo = scan.nextLine();
				Data.todoList.remove( Integer.parseInt(delTodo)-1 );
				Data.saveTodoList();
				
			} else if (sel.equals("3")) { // 뒤로 가기
				break;
			}
		}
		
	}

}
