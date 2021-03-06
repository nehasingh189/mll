package mll.utility;

import com.google.common.io.Files;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class SessionFactoryUtil 
{
	 private static final SessionFactory sessionFactory;

	//We need to initialize this object only one time so static block
	 static
	 {
		 try {

//		     String fileName = "C:\\Users\\nehas\\Documents\\SUMMER1_PROJECT\\Music-Licensing-Laboratory\\jasypt_pass";
			 // Read the key from file to decrypt password
//			 String key = Files.readFirstLine(new File(fileName), StandardCharsets.UTF_8);
//			 StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
//			 encryptor.setPassword(key);

			 String key = Files.readFirstLine(new File("/jasypt_pass"), StandardCharsets.UTF_8);
			 System.out.println("jasypt read");
			 StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			 encryptor.setPassword(key);
			 System.out.println("key");

//			 String encrypted_pass = encryptor.encrypt("NortheasternMLLdatabase_user123#");
//			 System.out.println(encrypted_pass);

			 // Create the SessionFactory from hibernate.cfg.xml
			 Configuration configuration = new Configuration().configure();
			 String hibernatePassword = configuration.getProperty("hibernate.connection.password");
			 configuration.setProperty("hibernate.connection.password", encryptor.decrypt(hibernatePassword));
			 sessionFactory = configuration.buildSessionFactory();
			 System.out.println("session created!!!!s");
		 }
		 catch (Throwable ex) 
		 {
			 System.err.println("Initial SessionFactory creation failed." + ex);
			 ex.printStackTrace();
			 throw new ExceptionInInitializerError(ex);
		 }
	 }
	
	 public static SessionFactory getSessionFactory() 
	 {	
		 return sessionFactory;
	 }
}
