package com.ktds.vo;

import java.util.Date;

public class NoticeVo {

	private Long no;
	private String title;
	private String author;
	private String content;
	private Date writeday;
	private Long readcnt;

	public NoticeVo() {

	}

	public NoticeVo(Long no, String title, String author, String content, Date writeday, Long readcnt) {
		this.no = no;
		this.title = title;
		this.author = author;
		this.content = content;
		this.writeday = writeday;
		this.readcnt = readcnt;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Long getReadcnt() {
		return readcnt;
	}

	public void setReadcnt(Long readcnt) {
		this.readcnt = readcnt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getWriteday() {
		return writeday;
	}

	public void setWriteday(Date writeday) {
		this.writeday = writeday;
	}

	@Override
	public String toString() {
		return "NoticeVo [no=" + no + ", title=" + title + ", author=" + author + ", content=" + content + ", writeday="
				+ writeday + ", readcnt=" + readcnt + "]";
	}

}