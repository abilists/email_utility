package io.utility.email;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmailerTest {

	@BeforeClass
	public static void beforeClass() {
		System.out.println("This is the first excuted");
	}

	@Before
	public void before() {
		System.out.println("Before");
	}

	@Test
	public void testOne() throws Exception {

		try {
			EmailBean email = new EmailBean();
			email.setTo("njoonk@daum.net");
			email.setSmtpSender("heyguybug@gmail.com");

			StringBuffer sb = new StringBuffer();
			sb.append("This is a test /n/r");
			sb.append("http://localhost:8080/employee/login/confirmSingup?token1=aa&token2=ccc");
			email.setMsg(sb.toString());
			email.setSubject("Please, complete your register");

			Emailer.sendEmail(email, "heyguybug@gmail.com", "asdfasdfTest12345");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@After
	public void after() {
		System.out.println("Before");
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("This is the end excuted");
	}
}
