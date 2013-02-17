

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import users.User;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class ExamTakenServlet
 */
@WebServlet("/Results")
public class ExamTakenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamTakenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/* get the parameters */
		String test_name = request.getParameter("examTitle");
		User user = (User)request.getSession().getAttribute("user");
		try{
			Class.forName("com.mysql.jdbc.Driver");
	     
			connect = DriverManager.getConnection("jdbc:mysql://83.212.99.6/online_testdb", "sqluser","sqluser");
			
			// Statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      // Result set get the result of the SQL query
		      resultSet = statement.executeQuery("select GRADE FROM STUDENTS WHERE TEST_ID='"+test_name+"' and USERNAME='"+user.getUsername()+"'");
		      
		      while(resultSet.next()){
		    	  request.setAttribute("grade", resultSet.getFloat("GRADE"));
		      }
		      
		      
		      statement = connect.createStatement();
		      // Result set get the result of the SQL query
		      resultSet = statement.executeQuery("select MO FROM EXAMS WHERE TEST_ID='"+test_name+"'");
		      
		      while(resultSet.next()){
		    	  request.setAttribute("average",resultSet.getFloat("MO"));
		      }
		      
		      RequestDispatcher  dispatcher = request.getRequestDispatcher("results.jsp");
		      dispatcher.forward(request,response);
		      
		      resultSet.close();
		      statement.close();
		      connect.close();
		}
		catch(SQLException ex){
			System.out.println("SQLEXception: " +ex.getMessage() );
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
