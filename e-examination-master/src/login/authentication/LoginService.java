package login.authentication;

import users.Student;
import users.Teacher;
import users.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exam.Exam;


public class LoginService {

	  private Connection connect = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;

	/* ayth h methodos kaleitai gia na doume an o xrhsths
	 * einai sth bash dedomenwn.
	 * 
	 * Prepei me kapoio tropo na kseroume kai an einai teacher h student.Logika,
	 * analoga to username tha to kseroume auto.
	 */
	public User authenticate(String username,String password) throws Exception{
		
		
		try{
			
			
	    
			// This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	     
	      connect = DriverManager.getConnection("jdbc:mysql://83.212.99.6/online_testdb", "sqluser","sqluser");
	      
	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // Result set get the result of the SQL query
	      resultSet = statement
	          .executeQuery("select * from USERS");
	      int user_id;
	      
	      while(resultSet.next()){
	    	  
	    	  String usrn = resultSet.getString("USERNAME");
	    	  String pass = resultSet.getString("PASSWORD");
	    	  
	    	  
	    	  if( username.equals(usrn) && pass.equals(password)){
	    		  System.out.println("Username and pass are mathing");
	    		  /* this user is in the database
	    		   * 
	    		   * Create a new User object(student or teacher) and
	    		   * return it to the servlet.
	    		   */
	    		 user_id =  resultSet.getInt("id");
	    		 if(user_id == 1){
	    			 /*this is a student */
	    			 Student student = new Student();
	    			 
	    			 /*get the rest of the field from the database */
	    			 String firstname = resultSet.getString("FIRSTNAME");
	    			 String lastname  = resultSet.getString("LASTNAME");
	    			 
	    			 student.setUsername(username);
	    			 student.setPassword(password);
	    			 student.setFirstName(firstname);
	    			 student.setLastName(lastname);
	    			 

	    			 student.setID(1);
	    			 
	    			 /* diabazw plhrofories gia ton student*/
	    			 statement = connect.createStatement();
	    		     resultSet = statement.executeQuery("select TEST_ID from STUDENTS where USERNAME='"+student.getUsername()+"'");
	    		     
	    		     /* get the results of the query */
	    		     while(resultSet.next()){
	    		    	 String test_name = resultSet.getString("TEST_ID");
	    		    	 //float grade = resultSet.getFloat("GRADE");
	    		    	 
	    		    	 student.addExamTaken(test_name);
	    		     }
	    		     
	    		     statement = connect.createStatement();
	    		     resultSet = statement.executeQuery("select TEST_ID from EXAMS where EXAMS.TEST_ID not in ( select TEST_ID from STUDENTS where USERNAME='"+student.getUsername()+"')");
	    			 
	    		     while(resultSet.next()){
	    		    	 
	    		    	 /* bazw ta test pou den exei parei akoma o student */
	    		    	 String test_name = resultSet.getString("TEST_ID");
	    		    	 System.out.println("The student has taken the exam: "+test_name);
	    		    	 student.addExamToTake(test_name);
	    		    	 
	    		     }
	    			 
	    			 
	    			 close();
	    			 return student;
	    		 }
	    		 else if(user_id == 2 ){
	    			 /*this is a teacher */
	    			 Teacher teacher = new Teacher();
	    			 
	    			 /*get the rest of the field from the database */
	    			 String firstname = resultSet.getString("FIRSTNAME");
	    			 String lastname  = resultSet.getString("LASTNAME");
	    			 
	    			 teacher.setFirstName(firstname);
	    			 teacher.setLastName(lastname);
	    			 teacher.setUsername(username);
	    			 teacher.setPassword(password);
	    			 
	    			 teacher.setID(2);
	    			 statement = connect.createStatement();
	    		     resultSet = statement.executeQuery("select TEST_ID from EXAMS WHERE AUTHOR='"+teacher.getUsername()+"'");
	    			 
	    		     while(resultSet.next()){
	    		    	 String test_name = resultSet.getString("TEST_ID");
	    		    	 
	    		    	 teacher.addExam(test_name);
	    		    	 
	    		     }
	    			 System.out.println("Teacher is ready");
	    			 close();
	    			 return teacher;
	    			 
	    		 }
	    		 
	    	  }
	    	  
	    	  
	      }
	      
		}
		catch(SQLException ex){
			System.out.println("SQLException: "+ ex.getMessage());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		finally {
		      close();
		}
		return null;
	}
	 // You need to close the resultSet
	  private void close() {
	    try {
	      if (resultSet != null) {
	        resultSet.close();
	      }

	      if (statement != null) {
	        statement.close();
	      }

	      if (connect != null) {
	        connect.close();
	      }
	    } catch (Exception e) {

	    }
	  }
}
