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
			 // Read the key from file to decrypt password
			 String key = Files.readFirstLine(new File("/jasypt_pass"), StandardCharsets.UTF_8);
			 StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			 encryptor.setPassword(key);

			 // Create the SessionFactory from hibernate.cfg.xml
			 Configuration configuration = new Configuration().configure();
			 String hibernatePassword = configuration.getProperty("hibernate.connection.password");
			 configuration.setProperty("hibernate.connection.password", encryptor.decrypt(hibernatePassword));
			 sessionFactory = configuration.buildSessionFactory();
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