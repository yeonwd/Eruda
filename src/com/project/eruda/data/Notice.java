
package com.project.eruda.data;


/**
 * 
 * <h1>학원 공지사항을 관리하는 Notice 클래스입니다.</h1>
 * @author hyerin
 *
 */
public class Notice {

	private String index;
	private String date;
	private String id;
	private String title;
	private String text;
	
	/**
	 * 
	 * <h1>공지사항 생성자</h1>
	 * @param index 공지사항 글 번호
	 * @param date	공지사항 작성 날짜
	 * @param id	공지사항 작성자 ID
	 * @param title	공지사항 제목
	 * @param text	공지사항 본문 내용
	 * 
	 */
	public Notice(String index, String date, String id, String title, String text) {
		this.index = index;
		this.date = date;
		this.id = id;
		this.title = title;
		this.text = text;
	}
	
	@Override
	public String toString() {
		return "Notice [index=" + index + ", date=" + date + ", id=" + id + ", title=" + title + ", text=" + text + "]";
	}

	public String getIndex() {
		return index;
	}


	public void setIndex(String index) {
		this.index = index;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * <h1>공지사항 본문(text)내용을 출력할 때 줄바꿈을 한 뒤 출력 하기 위한 메소드</h1>
	 * 50자 마다 '\n'을 추가한 뒤 리턴한다.
	 * 본문 글자열 중에 '\n'이 포함되어있지 않을때만 실행한다.
	 * 
	 * @param text 줄바꿈하여 출력할 본문
	 */
	public void TextSetting(String text) {
		
		StringBuffer str = new StringBuffer(text);
		
		if (text.contains("\n") == false) {
			
		
		int cnt = 0;
		for (int i=0; i<str.length(); i++) {
			cnt++;
			if (cnt % 50 == 0) {
				str.insert(cnt, "\n\t\t");
			}
		}
		}
		String res = str.toString();
		this.text = res;
	}

}
