package io.utility.email;

import java.util.List;

public class EmailBean extends SmtpBean {

	private String subject; 
	private String msg;
	private String filePath;
	private String replyTo;
	private String cc;
	private List<String> toList;

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getReplyTo() {
		return replyTo;
	}
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public List<String> getToList() {
		return toList;
	}
	public void setToList(List<String> toList) {
		this.toList = toList;
	}

}
