package com.project.eruda.view;

import java.io.BufferedReader;
import java.io.FileReader;
/**
 * 로고 파일을 불러오는 클래스
 * @author 5조
 *
 */
public class Logo {
	
	public final static String LOGOPATH = ".\\dat\\logo.txt"; 
	
	public static void printLogo() {
		DrawUtils.printStartLine();
		try {
			
			String line = null;
			BufferedReader reader = new BufferedReader(new FileReader(LOGOPATH));
			
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
