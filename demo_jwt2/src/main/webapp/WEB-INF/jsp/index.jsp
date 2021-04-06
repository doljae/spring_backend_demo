<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<%-- <%@ include file="common.jsp" %> --%>
<jsp:include page="common.jsp"/>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>Hello jsp</h1>
<%
    Date date = new Date();
    out.println("스트림으로 출력한 현재 시간은 " + date);
%>
<h3>
    현재 시간은 :
    <%=date%>
</h3>
<h1>사용자 관리</h1>
<ul>
    <!-- 		<li><a href="userList.do?cmd=userList">1. 사용자 리스트</a></li> -->
    <%-- 		<li><a href="${pageContext.request.contextPath }/userInsert.jsp">2. 회원가입</a></li> --%>
    <li><a href="userList.do">1. 사용자 리스트</a></li>
    <li><a href="userInsert.do">2. 회원가입</a></li>
</ul>
</body>
</html>