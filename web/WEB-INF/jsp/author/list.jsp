<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.List"%>
<%@ page import="domain.Author"%>
<%
	@SuppressWarnings("unchecked")
	List<Author> authors = (List<Author>) request.getAttribute("authors");
	int amount = authors.size();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Authors</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/main.css">
</head>
<body>
	<h1 style="color: red;">Authors</h1>
	<table>
		<tr>
			<th>Firstname</th>
			<th>Lastname</th>
		</tr>
		<%
			for(Author author : authors) {
		%>
		<tr>
			<td><%= author.getFirstName() %></td>
			<td><%= author.getLastName() %></td>
		</tr>
		<%
			}
		%>
	</table>
	<p>Total <%= amount %> authors</p>
</body>
</html>
