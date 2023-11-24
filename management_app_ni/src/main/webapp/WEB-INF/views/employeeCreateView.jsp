<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.*"%>
<%@ page import="beans.Employee"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	Employee employee = (Employee) request.getAttribute("employee");
	%>

	<h1>従業員情報編集</h1>

	<form action="create" method="POST">
	
		<label for="employeeName">
			従業員名
		</label>
		 
		<input type="text" name="employeeName">
		
		<br>
		
		<label for="password">
			パスワード
		</label>
		 
		<input type="password" name="password">
		
		<br> 
		
		<label for="department">
			所属部署
		</label>
		
		<select name="department">
		
			<%
			ArrayList<String> departmentList = (ArrayList<String>) request.getAttribute("departmentList");
			%>
			
			<%
			for (String department : departmentList) {
			%>

			<option>
				<%=department%>
			</option>

			<%
			}
			%>

		</select> 
		
		<br> 
		
		<label for="position">
			役職
		</label>
		
		<select name="position">

			<%
			ArrayList<String> positionList = (ArrayList<String>) request.getAttribute("positionList");
			%>

			<%
			for (String position : positionList) {
			%>

			<option>
				<%=position%>
			</option>

			<%
			}
			%>

		</select> 
		
		<br>
		
	<input type="submit" value="追加する">
	
	</form>

</body>

</html>