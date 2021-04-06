<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사용자 상세</title>
    <script>
        $(function () {
            user_list();
            user_delete();
        });

        function user_delete() {
            // 동적으로 생성된 버튼이기 때문에 이벤트를 이렇게 걸어줘야 함
            $("body").on("click", "#btnDelete", function () {
                var id = $(this).closest("tr").find("#hidden_id").val();
                var name = $(this).closest("tr").find("#hidden_name").val();
                var result = confirm(name + " 님을 삭제하시겠습니까?")
                console.log(result);
                if (result) {
                    $.ajax({
                        url: 'users/' + id,
                        method: 'DELETE',
                        error: function (error, status, msg) {
                            alert("상태코드 " + status + "에러 메시지 " + msg);
                        },
                        success: user_delete_result
                    })
                }
            });
        }

        function user_list() {
            console.log(1);
            $.ajax({
                url: 'users',
                method: 'GET',
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                error: function (error, status, msg) {
                    alert("상태코드 " + status + "에러 메시지 " + msg);
                },
                success: user_list_result
            })
        }

        function user_delete_result(xhr) {
            user_list();
        }

        function user_list_result(xhr) {
            console.log(xhr);
            $("tbody").empty();
            $.each(xhr, function (idx, user) {
                $("<tr>")
                    .append($("<td>").html(idx + 1))
                    .append($("<td>").html(user.userid))
                    .append($("<td>").html(user.name))
                    .append($("<td>").html(user.gender))
                    .append($("<td>").html(user.city))
                    .append($("<td>").html("<button id='btnDelete' class='btn btn-danger'>삭제</button>"))
                    .append($("<input type='hidden' id='hidden_id'>").val(user.id))
                    .append($("<input type='hidden' id='hidden_name'>").val(user.name))
                    .appendTo('tbody');
            })
        }
    </script>
</head>
<body>
<h2>사용자 상세정보</h2>
<%-- List<UserVO> users=(List)request.getAttribute("users");
    for(int i=0;i<user.size();i++){
        UserVO user=users.get(i);
    } --%>
<!-- 	<form method="post" action="userInsert.do"> -->
<!-- 주소가 같으면 이렇게 action 생략해도 동작함 -->
<form method="post">
    <input type="hidden" name="cmd" value="userInsert">
    <table>
        <tr>
            <td>사용자ID</td>
            <td>
                <input type="text" name="userid">
            </td>
        </tr>
        <tr>
            <td>이름</td>
            <td>
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <td>성별</td>
            <td>
                <input type="radio" name="gender" value="남">
                남
                <input type="radio" name="gender" value="여">
                여
            </td>
        </tr>
        <tr>
            <td>도시</td>
            <td>
                <select name="city">
                    <c:forEach var="city" items="${cities}">
                        <option value="${city }">${city }</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="등록">
            </td>
        </tr>
    </table>
</form>
<hr>
<!-- el -->
<!-- <a href="<%=request.getContextPath()%>/index.jsp">Home</a> -->
<!-- jstl -->
<a href="${pageContext.request.contextPath }/index.jsp">Home</a>
</body>
</html>
