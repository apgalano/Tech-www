<% String admin_logged = (String) session.getAttribute("admin_logged");
   if(admin_logged == null || !(admin_logged.equals("YES")))
	   response.sendRedirect("Welcome.jsp");
%>

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<html>
        <head><title>Administrator Panel</title>
        <link rel="stylesheet" title="main" media="screen" type="text/css" href="style_background.css" />
        </head>
        <body>
        	<font size="6"><%="Welcome to the administrator panel" %></font><br>
			<font size="6"><%="You can add users by completing this form" %></font>
			<br><br>
			<br>
			<form action="AddUser" method="post">
        	First Name <input type="text" name="firstname"/><br><br>        
        	Last Name  <input type="text" name="lastname"/><br><br>
        	Username  <input type="text" name="username"/><br><br>
        	Password  <input type="text" name="password"/><br><br>
        	<select name=role>
        		<option value=teacher>Teacher</option>
        		<option value=student>Student</option>
        	</select>            
   			<br><br>
   			<INPUT TYPE="SUBMIT" VALUE="Submit">
			<INPUT TYPE="RESET" VALUE="Reset">
   			<br>
   			</form>
			<br>
			<form action="LogoutServlet" >
			<input type="submit" value="Log out" >
			</form>
       
       
        </body>
</html>