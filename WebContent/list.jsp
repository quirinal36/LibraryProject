<%@page import="www.lmj.com.vo.Book"%>
<%@page import="java.util.List"%>
<%@page import="www.lmj.com.controller.BookController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
BookController control = new BookController();

List<Book> list = control.selectList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 리스트</title>
</head>
<body>
	<%for(int i=0; i<list.size(); i++) {%>
	
	<%=list.get(i).toString() %><br>
	
	<%} %>
</body>
</html>