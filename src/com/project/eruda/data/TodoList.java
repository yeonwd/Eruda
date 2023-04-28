package com.project.eruda.data;
/**
 * todoList정보를 저장
 * @author 5조
 *
 */
public class TodoList {
	
	public String id;
	public String time;
	public String desc;
	
	@Override
	public String toString() {
		return "TodoList [id=" + id + ", time=" + time + ", desc=" + desc + "]";
	}
	public TodoList(String id, String time, String desc) {
		super();
		this.id = id;
		this.time = time;
		this.desc = desc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
//asdf■2023-03-11■자바복습