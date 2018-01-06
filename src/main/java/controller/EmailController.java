package controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.StudentServiceInterface;
import util.Constants;

public class EmailController extends HttpServlet{
	private StudentServiceInterface studentService;
	private ClassPathXmlApplicationContext ctx;
	
	public EmailController(){
		super();
		ctx = new ClassPathXmlApplicationContext(Constants.SPRING_BEAN_CONTEXT);
		studentService = (StudentServiceInterface)ctx.getBean(Constants.SPRING_BEAN_STUDENTSERVICE);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter(Constants.REQUEST_PARAMETER_ACTION);
		String studentEmail = request.getParameter(Constants.REQUEST_PARAMETER_STUDENTEMAIL);
		
		if(action.equals(Constants.REQUEST_PARAMETER_EMAIL)) {
			sendEmail(studentEmail);
		}
		
		getServletContext().getRequestDispatcher("/login/student").forward(request, response);
		
	}
	
	private void sendEmail(String studentEmail) {
        final String username = "";
        final String password = "";
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
       
        
        Session session = Session.getInstance(props,
        		new javax.mail.Authenticator() {
        			protected PasswordAuthentication getPasswordAuthentication() {
        				return new PasswordAuthentication(username, password);
        			}
        		}
        );
        
        try {
        	Message message = new MimeMessage(session);
        	message.setFrom(new InternetAddress("dvdprr6@gamil.com"));
        	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(studentEmail));
        	message.setSubject("Testing Subject");
        	message.setText("Dear T, \n\n No spam to my email, please!");
        	Transport.send(message);
        	System.out.println("Done");
        }catch(MessagingException me) {
        	me.printStackTrace();
        }
	}
}
