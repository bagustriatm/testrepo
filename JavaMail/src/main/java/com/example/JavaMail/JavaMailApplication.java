package com.example.JavaMail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JavaMailApplication {
	private final EmailService emailService;

	public JavaMailApplication(EmailService emailService){
		this.emailService = emailService;
	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(JavaMailApplication.class, args);
		JavaMailApplication app = context.getBean(JavaMailApplication.class);

		app.Run();
	}

	private void Run() {
		boolean status = emailService.sendEmail(
				"bagustri313@gmail.com",
				"test",
				"this is an email test",
				"src/main/resources/attachTest.txt");

		if (status) {
			System.out.println("\u001B[32mMail was sent successfully.\u001B[0m");

		} else {
			System.err.println("Failed to send mail.");
		}
	}
}
