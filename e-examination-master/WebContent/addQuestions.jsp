<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<html>
        <head><title>Add test questions</title>
        <link rel="stylesheet" title="main" media="screen" type="text/css" href="style_background.css" />
        </head>
        <body>
        	<font size="7"><%="Add questions and the answers here" %></font>
			
			<form action="AddExam" method="post">
        	Question <input type="text" name="question"/><br>        
        	Answer1  <input type="text" name="Answer1"/><br>
        	Answer2  <input type="text" name="Answer2"/><br>
        	Answer3  <input type="text" name="Answer3"/><br>
        	Answer4  <input type="text" name="Answer4"/><br>
        	
        	
        	Correct answer is answer no:
        	<select name=correct>
        		<option value=1>1</option>
        		<option value=2>2</option>
        		<option value=3>3</option>
        		<option value=4>4</option>
        	</select>            
   			<br>
   			<INPUT TYPE="SUBMIT" VALUE="Add question">
			<INPUT TYPE="RESET" VALUE="Reset">
   			</form>
   			
			<a href="Teacher.jsp">Finish Exam</a>
   			
   			
			
       
       
        </body>
</html>