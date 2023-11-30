<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	String employeeCreateMsg = (String) request.getAttribute("employeeCreateMsg");
	%>

	<h1>追加完了</h1>

	<h2>
		<%=employeeCreateMsg%>
	</h2>

	<a href="top"> 戻る </a>

</body>
</html>