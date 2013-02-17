package login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import users.Student;
import users.Teacher;
import users.User;

import login.authentication.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String adminUsername = "admin";
	private String adminPassword = "admin12345";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		


		
		/* check if the admin logs in */
		if( username.equals(adminUsername) && password.equals(adminPassword)){
			
			/* redirect to a jsp (admin panel) */
			request.getSession().setAttribute("admin_logged","YES");
			response.sendRedirect("Admin.jsp");
			return;
		}
		HttpSession session = request.getSession();
		LoginService service = new LoginService();
		User user;
		try{
			user = service.authenticate(username, password);
		
			if(user == null){
				/* user is not in the database */
				session.setAttribute("LOGIN_FAILED", "FAILED");
				response.sendRedirect("Welcome.jsp");
				
			}
			else{
				/* this is a valid user.Attach information 
				 * to the session object(propably a type of User object)
				 * and redirect to an appropriate jsp
				 */
				if( user instanceof Student){
					
					
					
					session.setAttribute("user", user);
					response.sendRedirect("Student.jsp");
				}
				else if(user instanceof Teacher){
					session.setAttribute("user", user);
					response.sendRedirect("Teacher.jsp");
					
				}
				
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

	
	}

}
