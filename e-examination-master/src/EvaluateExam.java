

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

import users.Student;
import users.User;

import exam.Exam;
import exam.Question;

/**
 * Servlet implementation class EvaluateExam
 */
@WebServlet("/evaluation")
public class EvaluateExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EvaluateExam() {
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
		// TODO Auto-generated method stub
		
		String exam_name = request.getParameter("examName");
		
		Exam exam = (Exam)request.getSession().getAttribute("examTaking");
		request.getSession().removeAttribute("examTaking");
		int numQuestions = exam.questionsSize();
		int correctAnswers = 0;
		
		float grade = 0;
		int j = 0;
		
		for( int i = 0 ; i< numQuestions; i++ ){
			
			Question que = exam.getQuestion(i);
			//String correctAnswer = que.getAnswer(que.getCorrectAnswer() - 1);
			//String answerParameter = String.valueOf((i+1)*numQuestions / 4 );
			//String answer_ticked = request.getParameter(answerParameter);
			for(int k = 0 ; k < 4 ; k++){
			
				String string_ticked = (String) request.getParameter( Integer.toString(j));
				if( string_ticked != null){
					int int_ticked = Integer.parseInt(string_ticked);
					if( (int_ticked % 4) == (que.getCorrectAnswer() - 1) )
						correctAnswers++;
				}
				j++;
			}
			//if(answer_ticked != null){
				/* this radio button was ticked.It is also
				 * the correct answer.Add one correctAnswer
				 */
				//correctAnswers++;
			
			
			}
		
		
		/* calculate grade */
		grade = (float) correctAnswers / (float)numQuestions ;
		grade = grade *100;
		Student student = (Student) request.getSession().getAttribute("user");
		student.addExamTaken(exam_name);
		student.removeExamToTake(exam_name);
		System.out.println("The user: "+ student.getUsername()+" at the test:"+exam_name+"scored:"+grade);
		
		try{
			/* update the database */
			Class.forName("com.mysql.jdbc.Driver");
	     
			connect = DriverManager.getConnection("jdbc:mysql://83.212.99.6/online_testdb", "sqluser","sqluser");
			
			statement = connect.createStatement();
		    statement.executeUpdate("INSERT INTO STUDENTS values ('"+student.getUsername()+"','"+exam_name+"',"+grade+")");
		
		    
		
		}
		catch(SQLException ex){
			System.out.println("SQLException:"+ex.getMessage());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		/* redirect to the students home page*/
		response.sendRedirect("Student.jsp");
	}

}
