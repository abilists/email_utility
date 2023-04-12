package io.utility.email;

public class SmtpBean {

	private String smtpStarttlsEnable;
	private String smtpAuthEnable;
	private String smtpEnable;
	private String smtpHost;
	private String smtpPort;
	private String smtpSender;
	private String smtpPassword;
	private String timeSleep;

	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	public String getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}
	public String getSmtpAuthEnable() {
		return smtpAuthEnable;
	}
	public void setSmtpAuthEnable(String smtpAuthEnable) {
		this.smtpAuthEnable = smtpAuthEnable;
	}
	public String getSmtpStarttlsEnable() {
		return smtpStarttlsEnable;
	}
	public void setSmtpStarttlsEnable(String smtpStarttlsEnable) {
		this.smtpStarttlsEnable = smtpStarttlsEnable;
	}
	public String getSmtpSender() {
		return smtpSender;
	}
	public void setSmtpSender(String smtpSender) {
		this.smtpSender = smtpSender;
	}
	public String getSmtpEnable() {
		return smtpEnable;
	}
	public void setSmtpEnable(String smtpEnable) {
		this.smtpEnable = smtpEnable;
	}
	public String getSmtpPassword() {
		return smtpPassword;
	}
	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}
	public String getTimeSleep() {
		return timeSleep;
	}
	public void setTimeSleep(String timeSleep) {
		this.timeSleep = timeSleep;
	}

}
