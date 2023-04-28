package com.project.eruda.data;


/**
 * 크롤링하여 파싱한 정보를 담는 클래스
 * @author 5조
 *
 */
public class JobCrawlerDto {
	private String company;
	private String title;
	private String url;
	private String date;
	private String condition;
	private String jobSector;
	
	public JobCrawlerDto(String company, String title, String url, String date, String condition, String jobSector) {
		super();
		this.company = company;
		this.title = title;
		this.url = url;
		this.date = date;
		this.condition = condition;
		this.jobSector = jobSector;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getJobSector() {
		return jobSector;
	}
	public void setJobSector(String jobSector) {
		this.jobSector = jobSector;
	}

	@Override
	public String toString() {
		return "JobCrawlerDto [company=" + company + ", title=" + title + ", url=" + url + ", date=" + date
				+ ", condition=" + condition + ", jobSector=" + jobSector + "]";
	}

}
