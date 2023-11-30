<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.Employee"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/employeeList.css">
</head>
<body>

	<%
	Employee employee = (Employee) request.getAttribute("employee");
	%>

	<%
	if (employee != null) {
	%>

	<h1>社員情報</h1>

	<div class="employeeDiv">

		<div class="myEmployeeInfoDiv">

			<h2 class="myInfoHeader">マイ情報</h2>

			<p>
			<h3>社員名</h3>
			${employee.getEmployeeName()}
			</p>

			<p>
			<h3>役職</h3>
			${employee.getPosition()}
			</p>

			<p>
			<h3>所属部署</h3>
			${employee.getDepartment()}
			</p>


			<div class="employeeOptionBtn">

				<form action="edit?accountId=<%=employee.getAccountId()%>"
					method="GET">

					<input type="hidden" value="<%=employee.getAccountId()%>"
						name="accountId"> <input type="submit" value="編集"
						class="employeeEditBtn">

				</form>

				<form action="logout" method="POST">

					<input type="submit" value="ログアウト" class="logoutBtn">

				</form>

			</div>

		</div>

		<%
		ArrayList<Employee> employeeList = (ArrayList<Employee>) request.getAttribute("employeeList");
		%>

		<%
		if (employeeList != null) {
		%>

		<div class="employeeListDiv">

			<h2 class="employeeListHeader">部署員一覧</h2>

			<table class="employeeTable">

				<thead>

					<tr>

						<td>

							<h3>役職</h3>

						</td>

						<td>

							<h3>名前</h3>

						</td>

						<td>

							<h3>所属部署</h3>

						</td>

						<td></td>

						<td></td>

					</tr>

				</thead>

				<tbody>

					<%
					for (Employee emp : employeeList) {
					%>

					<tr>

						<td><%=emp.getPosition()%></td>

						<td><%=emp.getEmployeeName()%></td>

						<td><%=emp.getDepartment()%></td>

						<td></td>

						<td><a href="delete?accountId=<%=emp.getAccountId()%>">
								削除 </a></td>

					</tr>

					<%
					}
					%>

				</tbody>

			</table>

			<form action="create" method="get">

				<input type="submit" value="追加" class="newBtn">

			</form>

		</div>

		<%
		}
		%>

	</div>

	<%
	} else {
	%>

	<form action="login" method="POST">

		<input name="employeeName" type="text"> <input name="password"
			type="password"> <input type="submit" value="ログイン">

	</form>

	<%
	}
	%>

</body>

</html>