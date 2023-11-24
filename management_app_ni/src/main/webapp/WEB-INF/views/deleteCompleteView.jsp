<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%String employeeDeleteMsg = (String)request.getAttribute("employeeDeleteMsg");%>

<h1>削除完了</h1>

<h2><%= employeeDeleteMsg %></h2>

<a href="top">戻る</a>

</body>
</html>