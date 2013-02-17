
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * Servlet implementation class ExamResults
 */
@WebServlet("/ExamResults")
public class ExamResults extends HttpServlet {
	private static final long serialVersionUID = 1L; 
    private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private ResultSet resultSet1 = null;
	
	private ArrayList<String> studentNames = null;
	private ArrayList<Float> grades = null;
	private ArrayList<String> names = null;
	private ArrayList<String> lastnames = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamResults() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String examtitle; 
		examtitle=request.getParameter("examtitle");
		
		studentNames = new ArrayList<String>();
		grades = new ArrayList<Float>();
		names = new ArrayList<String>();
		lastnames = new ArrayList<String>();
		
		try{
		// This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	     
	      connect = DriverManager.getConnection("jdbc:mysql://83.212.99.6/online_testdb", "sqluser","sqluser");
	      
	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // Result set get the result of the SQL query
	      resultSet = statement.executeQuery("select * from STUDENTS WHERE TEST_ID='"+examtitle+"'");
	      while(resultSet.next()){
	    	  
	    	  studentNames.add(resultSet.getString(1));
	    	  grades.add(resultSet.getFloat(3));
	    	  
	    	  
		      statement = connect.createStatement();
		      // Result set get the result of the SQL query
		      resultSet1 = statement.executeQuery("select * from USERS WHERE USERNAME='"+resultSet.getString("USERNAME")+"'");
		      
		      while(resultSet1.next()){
		    	  names.add(resultSet1.getString("FIRSTNAME"));
		    	  lastnames.add(resultSet1.getString("LASTNAME"));
		      }
	    	  
	      }
		//RequestDispatcher  dispatcher = request.getRequestDispatcher("examResults.jsp");
		//request.getSession().setAttribute("studentNames", studentNames);
		request.getSession().setAttribute("grades", grades);
		request.getSession().setAttribute("name",names);
		request.getSession().setAttribute("lastname", lastnames);
		
		//dispatcher.forward(request, response);
		
		response.sendRedirect("examResults.jsp");
		}catch(SQLException ex){
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
