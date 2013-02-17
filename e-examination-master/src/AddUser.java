

import java.io.IOException;
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

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private int role;
    private String rl;
    
    private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
    /**
     * 
     * 
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		firstname = request.getParameter("firstname");
		lastname = request.getParameter("lastname");
		username = request.getParameter("username");
		password = request.getParameter("password");
		rl = request.getParameter("role");
		
		if(rl.equals("teacher")){
			role=2;
		}
		else {
			role=1;
		}
		try{
		// This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      connect = DriverManager.getConnection("jdbc:mysql://83.212.99.6/online_testdb", "sqluser","sqluser");
	      
	      statement = connect.createStatement();
	     // resultSet = statement.executeQuery("INSERT INTO USERS values (1,'dimpapa','dimpapa','dimitris','Papadogiannhs'");
	      statement.executeUpdate("INSERT INTO USERS values ("+role+",'"+username+"','"+password+"','"+firstname+
	    		  "','"+lastname+"')");
	
	      statement.close();
	     
		  connect.close();
		}
		catch(SQLException ex){
			System.out.println("SQL exception: "+ ex.getMessage());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {

		}
		response.sendRedirect("Admin.jsp");

}
}
