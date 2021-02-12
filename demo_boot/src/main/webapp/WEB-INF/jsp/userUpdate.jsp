<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 상세정보 수정</title>
</head>
<body>
	<h2>사용자 상세정보 수정</h2>
	<%-- List<UserVO> users=(List)request.getAttribute("users");
		for(int i=0;i<user.size();i++){
			UserVO user=users.get(i);
		} --%>
	<form method="post" action="userUpdate.do">
		<table>
			<tr>
				<td>id<input type="text" name="id" value="${map.user.id }"
					readonly>
				</td>
			</tr>
			<tr>
				<td>사용자<input type="text" name="userid"
					value="${map.user.userid }" readonly></td>
			</tr>
			<tr>
				<td>이름 <input type="text" name="name" value="${map.user.name}"
					readonly></td>
			</tr>
			<tr>
				<%-- 				<td>성별 <input type="text" value="${map.user.gender}"></td> --%>
				<td>성별 <c:choose>
						<c:when test="${map.user.gender  eq '남'}">
							<input type="radio" name="gender" value="남" checked>남
						<input type="radio" name="gender" value="여">여
					</c:when>
						<c:when test="${map.user.gender  eq '여'}">
							<input type="radio" name="gender" value="남">남
						<input type="radio" name="gender" value="여" checked>여
					</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>도시 <select name="city">
						<c:forEach var="city" items="${map.cities}">
							<c:choose>
								<c:when test="${city eq map.user.city }">
									<option value="${city }" selected>${city }</option>
								</c:when>
								<c:otherwise>
									<option value="${city }">${city }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>등록일 <input name="regdate" type="text"
					value=<fmt:formatDate value="${map.user.regdate  }"
					pattern="yyyy-MM-dd HH:mm:ss" />
					disabled="disabled" /></td>

			</tr>
			<tr>
				<td colspan="1"><input type="submit" value="수정"></td>
			</tr>
		</table>
	</form>
	<hr>
	<!-- el -->
	<!-- <a href="<%=request.getContextPath()%>/index.jsp">Home</a> -->
	<!-- jstl -->
	<a href="index.do">Home</a>
</body>
</html>
