

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.Exam;
import exam.Question;

import users.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class TakeExamServlet
 */
@WebServlet("/takeExam")
public class TakeExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeExamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String exam_name = request.getParameter("examTitle");
		User user =(User) request.getSession().getAttribute("user");
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
	     
			connect = DriverManager.getConnection("jdbc:mysql://83.212.99.6/online_testdb", "sqluser","sqluser");
			
			// Statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      // Result set get the result of the SQL query
		      resultSet = statement.executeQuery("select * from QUESTIONS where TEST_ID='"+exam_name+"'");
		      
		      Exam exam = new Exam();
		      exam.setTestID(exam_name);
		      while(resultSet.next()){
		    	  String str_question = resultSet.getString("QUESTION");
		    	  String str_answer_1 = resultSet.getString("ANSWER_1");
		    	  String str_answer_2 = resultSet.getString("ANSWER_2");
		    	  String str_answer_3 = resultSet.getString("ANSWER_3");
		    	  String str_answer_4 = resultSet.getString("ANSWER_4");
		    	  
		    	  int correct = resultSet.getInt("CORRECT");
		    	  
		    	  /* create a new question for the exam*/ 
		    	  Question question = new Question(exam_name,str_question,
		    			  str_answer_1,str_answer_2,str_answer_3,str_answer_4,correct);
		    	  
		    	  /* add the question to the exam */
		    	  exam.addQuestion(question);
		      }
		      resultSet.close();
		      statement.close();
		      connect.close();
		      
		      RequestDispatcher dis = request.getRequestDispatcher("StartExam.jsp");
		      request.setAttribute("exam", exam);
		      dis.forward(request, response);
		}
		catch (SQLException ex){
			System.out.println("A Sql exception occured:"+ ex.getMessage());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
