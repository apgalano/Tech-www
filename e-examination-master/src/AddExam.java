
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import users.Teacher;
import users.User;

/**
 * Servlet implementation class AddExam
 */
@WebServlet("/AddExam")
public class AddExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;  
	private String testname;
	private String username;
    private Teacher teacher;
    
    /*variables for adding questions*/
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int correct;
    private String testid;
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public AddExam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		javax.servlet.http.HttpSession session = request.getSession();
		testname=(String)request.getParameter("testid");
		teacher=(Teacher)session.getAttribute("user");
		username=teacher.getUsername();
		try{
			// This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	     
	      connect = DriverManager.getConnection("jdbc:mysql://83.212.99.6/online_testdb", "sqluser","sqluser");
	      
	      System.out.println("username passed for exams:"+ username);
	      System.out.println("Test name passed:" + testname);
	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // Result set get the result of the SQL query
	      statement.executeUpdate("INSERT INTO EXAMS values ('"+username+"','"+testname+"',0)");
	      
	      teacher.addExam(testname);
	      session.removeAttribute("user");
	      session.setAttribute("user", teacher);
	      
	      //RequestDispatcher  dispatcher = request.getRequestDispatcher("addQuestions.jsp");
		  session.setAttribute("testid", testname);
		  session.setAttribute("username", username);
			//dispatcher.forward(request, response);
		  response.sendRedirect("addQuestions.jsp");
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		question=request.getParameter("question");
		answer1=request.getParameter("Answer1");
		answer2=request.getParameter("Answer2");
		answer3=request.getParameter("Answer3");
		answer4=request.getParameter("Answer4");
		correct=Integer.valueOf(request.getParameter("correct"));
		testid= (String) request.getSession().getAttribute("testid");
		
		
		try{
			// This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	     
	      connect = DriverManager.getConnection("jdbc:mysql://83.212.99.6/online_testdb", "sqluser","sqluser");
	      
	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // Result set get the result of the SQL query
	      statement.executeUpdate("INSERT INTO QUESTIONS values ('"+testid+"','"+question+"','"+answer1+"','"+answer2+"','"+answer3+"','"+answer4+"','"+correct+"')");
	      
	      RequestDispatcher  dispatcher = request.getRequestDispatcher("addQuestions.jsp");
			request.setAttribute("testid", testname);
			//request.setAttribute("username", username);
			dispatcher.forward(request, response);
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
		
		
		
		
		
	}
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
