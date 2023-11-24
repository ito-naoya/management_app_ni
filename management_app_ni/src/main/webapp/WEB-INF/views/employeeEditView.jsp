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

	<form action="edit?accountId=<%=employee.getAccountId()%>" method="POST">
	
		<label for="employeeName">
			従業員名
		</label>
		
		<input type="text" name="employeeName" value="<%=employee.getEmployeeName()%>">
		
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

			<%
			if (department.equals(employee.getDepartment())) {
			%>

			<option selected>
				<%=department%>
			</option>

			<%
			} else {
			%>

			<option>
				<%=department%>
			</option>

			<%
			}
			%>

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


			<%
			if (position.equals(employee.getPosition())) {
			%>

			<option selected>
				<%=position%>
			</option>

			<%
			} else {
			%>

			<option>
				<%=position%>
			</option>

			<%
			}
			%>


			<%
			}
			%>

		</select>
		
		 <br>
		 
		  <input type="submit" value="更新する">
		  
	</form>

</body>

</html>