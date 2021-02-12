<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 상세</title>
</head>
<body>
	<h2>사용자 상세정보</h2>
	<%-- List<UserVO> users=(List)request.getAttribute("users");
		for(int i=0;i<user.size();i++){
			UserVO user=users.get(i);
		} --%>
	<table>
		<tr>
			<td>id ${userOne.id }</td>
		</tr>
		<tr>
			<td>사용자ID ${userOne.userid }</td>
		</tr>
		<tr>
			<td>이름 ${userOne.name  }</td>
		</tr>
		<tr>
			<td>성별 ${userOne.gender  }</td>
		</tr>
		<tr>
			<td>도시 ${userOne.city  }</td>
		</tr>
		<tr>
			<td>등록일 <fmt:formatDate value="${userOne.regdate  }"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
	</table>
	<hr>
	<!-- el -->
	<!-- <a href="<%=request.getContextPath()%>/index.jsp">Home</a> -->
	<!-- jstl -->
	<a href="index.do">Home</a>
	<a href="userUpdate.do?userid=${userOne.userid }">수정</a>
</body>
</html>
