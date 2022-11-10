<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
float negative =
	((float)request.getAttribute("message"));

float positive =
	(float)request.getAttribute("message2");

float neutral =
	(float)request.getAttribute("message3");
	

//Optional<String> string = 
	//Optional.ofNullable((String) request.getAttribute("string"));

	
%>
<a HREF="./SentimentRequest"> 検索ページ</a>
<body>
<H1>Sentiment</H1>
<H2>結果：Negative<%= negative  %></H2>
<H3>Positive:<%= positive %></H3>
<H4>Neutral:<%=neutral %></H4>
</body>
</html>