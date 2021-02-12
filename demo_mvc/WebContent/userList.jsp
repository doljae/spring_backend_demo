<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 목록</title>
<script type="text/javascript">
	function userDelete(id,userid){
		console.log(id);
		console.log(userid);
		var result=confirm(userid+" 사용자를 정말 삭제하시겠습니까?");
		if (result)
		{
			//querystring 으로 보내기
			//location.href="userDelete.do?id="+id;
			//PathVariable 으로 보내기
			location.href="userDelete.do/"+id;
		}
		
	}

</script>
<jsp:include page="common.jsp" />
</head>
<body>
	<h2>사용자 목록</h2>
	<table class="table">
		<tr>
			<th>순서</th>
			<th>사용자ID</th>
			<th>이름</th>
			<th>삭제</th>
		</tr>
		<%-- List<UserVO> users=(List)request.getAttribute("users");
		for(int i=0;i<user.size();i++){
			UserVO user=users.get(i);
		} --%>
		<c:forEach var="user" items="${users }" varStatus="status">
			<tr>
				<td>${status.count }</td>
				<td><a href="userDetail.do?userid=${user.userid }">
						${user.userid }</a></td>
				<td>${user.name  }</td>
				<td><a href="#"
					onclick="userDelete(${user.id}, '${user.userid}')">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
